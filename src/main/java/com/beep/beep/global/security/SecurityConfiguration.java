package com.beep.beep.global.security;


import com.beep.beep.global.security.jwt.filter.JwtAuthenticationFilter;
import com.beep.beep.global.security.jwt.filter.JwtExceptionFilter;
import com.beep.beep.global.security.jwt.handler.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handlingConfigures -> handlingConfigures.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                        .requestMatchers("/auth/**","/email/**").permitAll()

                        .requestMatchers("/beep/enter").hasAuthority(STUDENT.getAuthority())
                        .requestMatchers("/beep/exit").hasAuthority(STUDENT.getAuthority())
                        .requestMatchers("/beep/rooms").hasAuthority(TEACHER.getAuthority())
                        .requestMatchers("/beep/attendances").hasAuthority(TEACHER.getAuthority())

                        .requestMatchers("/teachers").hasAuthority(ADMIN.getAuthority())
                        .requestMatchers("/teachers/info").hasAuthority(TEACHER.getAuthority())

                        .requestMatchers("/students").hasAuthority(ADMIN.getAuthority())
                        .requestMatchers("/students/info").hasAuthority(STUDENT.getAuthority())
                        .requestMatchers("/students/cls").hasAuthority(TEACHER.getAuthority())
                        .requestMatchers("/students/cls-member").hasAuthority(TEACHER.getAuthority())
                        .requestMatchers("/students/name").hasAuthority(TEACHER.getAuthority())

                        .requestMatchers("/users/find-user","/users/change-pw","/users/find-id","/id-check").permitAll()
                        .requestMatchers("/users").authenticated()

                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

}
