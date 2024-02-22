package com.beep.beep.domain.email.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class EmailCertificationDao {

    @Qualifier("mailRedisTemplate")
    private final StringRedisTemplate redisTemplate;

    public void saveCode(String email, String code) {
        redisTemplate.opsForValue()
                .set(email, code,
                        Duration.ofMinutes(10));
    }

    public String getCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public void removeCode(String email) {
        redisTemplate.delete(email);
    }

    public boolean hasKey(String email) {
        Boolean keyExists = redisTemplate.hasKey(email);
        return keyExists != null && keyExists;
    }

}
