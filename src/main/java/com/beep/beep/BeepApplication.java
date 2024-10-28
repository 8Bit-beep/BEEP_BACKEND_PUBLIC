package com.beep.beep;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
@OpenAPIDefinition(
		servers = {
				@Server(url = "https://beep.pe.kr", description = "beep https 서버입니다."),
				@Server(url = "http://beep.pe.kr", description = "beep http 서버입니다."),
				@Server(url = "http://43.201.252.15", description = "beep local 서버입니다.")
		}
)
public class BeepApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeepApplication.class, args);
	}
}
