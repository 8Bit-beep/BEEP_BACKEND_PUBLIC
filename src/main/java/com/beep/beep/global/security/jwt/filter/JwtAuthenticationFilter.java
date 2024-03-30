package com.beep.beep.global.security.jwt.filter;


import com.beep.beep.global.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtProvider.extractTokenFromRequest(request);

        if (token != null) { // 토큰이 존재하면
            // 사용자 인증정보를 SecurityContext에 설정하는 과정
            // SecurityContext : 인증된 사용자의 세부정보 저장소 -> 구성요소 : Authentication 객체
            // SecurityContextHolder : SecurityContext에 접근하기 위한 메커니즘 제공
            SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));
            // setAuthentication  : 인증 정보를 인자로 받는 setter , 이거 return 값 : Authentication 객체
        }

        filterChain.doFilter(request, response); // doFilter : 현재 필터의 처리를 마치고 다음 필터로 response와 request를 보냄.
    }
}
