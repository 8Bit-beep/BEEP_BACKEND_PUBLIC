package com.beep.beep.domain.auth.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "jwtToken", timeToLive = 2592000L)
public class Token {
    @Id
    @Indexed
    private String userId;

    private String refreshToken;

    @Indexed
    private String accessToken;

    @Builder
    public Token(String userId,String refreshToken,String accessToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public void updateTokenInfo(String accessToken){
        this.accessToken = accessToken.isBlank() ? this.accessToken : accessToken;
    }
}
