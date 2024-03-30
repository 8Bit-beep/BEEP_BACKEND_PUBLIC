package com.beep.beep.global.security.jwt.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private String header;
    private String prefix;
    private Long accessExp;
    private Long refreshExp;

}
