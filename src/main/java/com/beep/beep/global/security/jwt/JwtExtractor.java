package com.beep.beep.global.security.jwt;

import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.mapper.UserMapper;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.security.auth.AuthDetails;
import com.beep.beep.global.security.jwt.config.JwtProperties;
import com.beep.beep.global.security.jwt.enums.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class JwtExtractor {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        if (isWrongType(claims, JwtType.ACCESS)) {
            throw new IllegalArgumentException("토큰 타입이 옳지 않습니다");
        }

        System.out.println("what's the matter");
        UserVO userVO = userRepository.findByEmail(claims
                        .getBody()
                        .getSubject())
                .map(userMapper::toUserDto)
                .orElseThrow(()-> new IllegalArgumentException("유저가 존재하지 않습니다") );
        System.out.println("here.");

        final AuthDetails details = new AuthDetails(userVO);
        System.out.println(userVO.getAuthority());

        System.out.println("네?");
        // 사용자 인증 정보를 생성 후 관리 (객체 생성을 통해)
        // UsernamePasswordAuthenticationToken(Principal, Credentials, Authorities) // 사용자의 주요정보, 인증과정의 비밀번호 저장 안하려고 null , 사용자의 권한 목록
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    // token 유효성 검증 및 클레임 정보 반환
    public Jws<Claims> getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }

    // 토큰 타입 검증 로직
    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }


    public String extractTokenFromRequest(HttpServletRequest request) {
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) { // 토큰이 존재하고 "Bearer "로 시작할때
            return token.substring(7); // "Bearer " 제거
        }
        return token; // 토큰 이상하면 그냥 token return
    }

}
