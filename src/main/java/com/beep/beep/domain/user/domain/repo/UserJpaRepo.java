package com.beep.beep.domain.user.domain.repo;

import com.beep.beep.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User,String> {
}
