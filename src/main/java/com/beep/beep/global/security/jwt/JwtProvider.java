package com.beep.beep.global.security.jwt;



import com.beep.beep.domain.auth.domain.Token;
import com.beep.beep.domain.auth.domain.repository.TokenRepository;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.global.security.jwt.exception.ExpiredRefreshTokenException;
import com.beep.beep.global.security.jwt.exception.ExpiredTokenException;
import com.beep.beep.global.security.jwt.exception.InvalidTokenException;
import com.beep.beep.global.security.jwt.exception.TokenNotFoundException;
import com.beep.beep.global.security.jwt.exception.UnExpiredTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);



    public String generateAccessToken(String id){
        return generateToken(id,jwtProperties.getAccessExp());
    }



//    public void existsRefreshToken(String refreshToken){
//        if (!refreshTokenRepository.existsById(parseToken(refreshToken)))
//            throw RefreshTokenNotFoundException.EXCEPTION;
//    }




    @Transactional
    public String generateRefreshToken(String userId){
        return generateToken(userId, jwtProperties.getRefreshExp());
    }

    public String resolveToken(HttpServletRequest request){
        return parseToken(request.getHeader(jwtProperties.getHeader()));
    }

    public User userValidateToken(String token){
        return userRepository.findById(getTokenSubject(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            System.out.println(refreshToken);
            Jwts.parserBuilder().setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build().parseClaimsJws(refreshToken);
        } catch (ExpiredJwtException e) {
            throw ExpiredRefreshTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    @Transactional
    public String validateAccessToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build().parseClaimsJws(parseToken(accessToken));
        } catch (ExpiredJwtException e) {
            Token tokenInfo = tokenRepository.findByAccessToken(parseToken(accessToken))
                    .orElseThrow(() -> TokenNotFoundException.EXCEPTION );

            validateRefreshToken(tokenInfo.getRefreshToken());

            String userId = tokenInfo.getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> UserNotFoundException.EXCEPTION );

            accessToken = generateAccessToken(userId);

            tokenInfo.updateTokenInfo(accessToken);

            return accessToken;

        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }

        throw UnExpiredTokenException.EXCEPTION;
    }

    private String generateToken(String userId,Long exp){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getSigningKey(jwtProperties.getSecretKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())){
            return bearerToken.replace(jwtProperties.getPrefix(),"").replaceAll("\\s", "");
        }
        return null;
    }


    public String getTokenSubject(String token){
        return getTokenBody(token).getSubject();
    }


    private Claims getTokenBody(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build().parseClaimsJws(token).getBody();
        } catch (ExpiredTokenException e) {
            throw ExpiredTokenException.EXCEPTION;
       } catch (Exception e){
            System.out.println("??");
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private Key getSigningKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
