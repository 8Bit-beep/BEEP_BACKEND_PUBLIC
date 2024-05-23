package com.beep.beep.global.security.jwt.filter;


import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.global.exception.error.ErrorCode;
import com.beep.beep.global.exception.error.ErrorProperty;
import com.beep.beep.global.security.jwt.exception.TokenTypeException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        } catch (ServletException e) {
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
        }
    }

    // 에러 response 설정
    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status.value());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("status", String.valueOf(status.value()));
        map.put("message", ex.getMessage());
        response.getWriter().write(mapper.writeValueAsString(map));
    }

}
