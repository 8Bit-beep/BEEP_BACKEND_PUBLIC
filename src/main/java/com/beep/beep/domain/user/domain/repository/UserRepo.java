package com.beep.beep.domain.user.domain.repository;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repository.querydsl.UserRepoCustom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> , UserRepoCustom {

    boolean existsByEmail(String email);

    boolean existsById(String id);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);


}
