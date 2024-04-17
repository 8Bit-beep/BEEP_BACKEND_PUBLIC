package com.beep.beep.domain.user.domain.repository;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByIdx(Long idx);

    boolean existsByEmail(String email);
    boolean existsById(String id);

    @Query("SELECT s FROM User s WHERE s.id = :id AND s.email = :email")
    Optional<User> findByIdEmail(@Param("id") String id,@Param("email") String email);

    @Query("SELECT s FROM User s WHERE (s.authority = :authority) AND (s.name LIKE %:name%)")
    List<User> findByName(@Param("name") String name,@Param("authority") UserType authority);

    @Query("SELECT s FROM User s WHERE s.authority = :authority")
    List<User> findAllByAuthority(@Param("authority") UserType authority);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);


}
