package com.beep.beep.domain.user.domain.repository;

import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findByIdx(Long idx);

    boolean existsByEmail(String email);
    boolean existsById(String id);

    @Query("SELECT s FROM UserEntity s WHERE s.id = :id AND s.email = :email")
    Optional<UserEntity> findByIdEmail(@Param("id") String id, @Param("email") String email);

    @Query("SELECT s FROM UserEntity s WHERE (s.authority = :authority) AND (s.name LIKE %:name%)")
    List<UserEntity> findByName(@Param("name") String name, @Param("authority") UserType authority);

    @Query("SELECT s FROM UserEntity s WHERE s.authority = :authority")
    List<UserEntity> findAllByAuthority(@Param("authority") UserType authority);

    Optional<UserEntity> findById(String id);

    Optional<User> findByEmail(String email);


}
