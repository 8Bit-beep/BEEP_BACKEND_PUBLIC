package com.beep.beep;

import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeepApplicationTests {

	private final UserRepository userRepository;

    BeepApplicationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
	void contextLoads() {
		User user = userRepository.findByEmail("teacher@email.com")
				.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}
}
