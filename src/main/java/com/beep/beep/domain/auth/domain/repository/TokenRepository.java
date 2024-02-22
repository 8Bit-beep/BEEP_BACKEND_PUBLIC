package com.beep.beep.domain.auth.domain.repository;


import com.beep.beep.domain.auth.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TokenRepository extends CrudRepository<Token,String> {
    Optional<Token> findByAccessToken(String accessToken);

    Optional<Token> findByUserId(String userId);

}
