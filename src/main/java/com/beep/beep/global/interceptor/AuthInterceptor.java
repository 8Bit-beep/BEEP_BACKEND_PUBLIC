package com.beep.beep.global.interceptor;


import com.beep.beep.domain.auth.facade.TokenFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.global.annotation.AuthCheck;

import com.beep.beep.global.security.jwt.JwtProvider;
import com.beep.beep.global.security.jwt.exception.TokenNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final TokenFacade tokenFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler){

        System.out.println(1);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        System.out.println(2);

        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        System.out.println(3);

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        System.out.println(4);
        AuthCheck authCheckAnnotation = handlerMethod.getMethodAnnotation(AuthCheck.class);

        if (authCheckAnnotation == null) {
            System.out.println("개빡치네");
            return true;
        }

        System.out.println(5);
        String token = jwtProvider.resolveToken(request);

        if (token == null || token.isEmpty())
            throw TokenNotFoundException.EXCEPTION;

        tokenFacade.findByAccessToken(token);

        if (authCheckAnnotation.role().equals(UserType.ROLE_STUDENT)) {
            request.setAttribute("student",findValidateToken(token).getId());
        } else if (authCheckAnnotation.role().equals(UserType.ROLE_TEACHER)) {
            request.setAttribute("teacher",findValidateToken(token).getId());
        } else if (authCheckAnnotation.role().equals(UserType.ROLE_ADMIN)){
            request.setAttribute("admin",findValidateToken(token).getId());
        }

        return true;

    }

    private User findValidateToken(String token){
        return jwtProvider.userValidateToken(token);
    }

}
