package com.beep.beep.domain.auth.facade;


import com.beep.beep.domain.auth.domain.Token;
import com.beep.beep.domain.auth.domain.repository.TokenRepository;
import com.beep.beep.global.security.jwt.exception.TokenNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenFacade {
    private final TokenRepository tokenRepository;

    @Transactional
    public void saveTokenInfo(String userId, String refreshToken, String accessToken) {
        tokenRepository.save(Token.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .accessToken(accessToken).build());
    }

    public Token findByAccessToken(String token) {
        return tokenRepository.findByAccessToken(token)
                .orElseThrow(() -> TokenNotFoundException.EXCEPTION);
    }

    public void delete(Token token){
        tokenRepository.delete(token);
    }



}
