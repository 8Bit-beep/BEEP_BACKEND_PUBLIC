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
import java.util.NoSuchElementException;

import static com.beep.beep.domain.user.exception.error.UserErrorProperty.USER_NOT_FOUND;
import static com.beep.beep.global.exception.error.ErrorCode.BAD_REQUEST;
import static com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty.EXPIRED_TOKEN;
import static com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty.ILLEGAL_TOKEN;
import static com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty.TOKEN_TYPE_ERROR;
import static com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty.UNSUPPORTED_TOKEN;


@Component
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response); // 에러 없으면 다음 필터로
        } catch (ExpiredJwtException e) {
            //토큰의 유효기간 만료
            log.error("만료된 토큰입니다");
            setErrorResponse(EXPIRED_TOKEN,response);
        } catch(TokenTypeException e){
            log.error("옳지 않은 타입입니다.");
            setErrorResponse(TOKEN_TYPE_ERROR,response);
        }
//        catch (JwtException | IllegalArgumentException e) {
//            //유효하지 않은 토큰
//            log.error("유효하지 않은 토큰이 입력되었습니다.");
//            setErrorResponse(ILLEGAL_TOKEN,response);
//        }
        catch (NoSuchElementException | UserNotFoundException e) {
            //사용자 찾을 수 없음
            log.error("사용자를 찾을 수 없습니다.");
            setErrorResponse(USER_NOT_FOUND,response);

        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("토큰을 추출할 수 없습니다.");
            setErrorResponse(UNSUPPORTED_TOKEN,response);
        } catch (ServletException e) {
            setErrorResponse(BAD_REQUEST, response);
        }catch (NullPointerException e) {
            filterChain.doFilter(request, response);
        }
    }

    // 에러 response 설정
    public void setErrorResponse(ErrorProperty errorCode, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("status", String.valueOf(errorCode.getStatus().value()));
        map.put("message", errorCode.getMessage());
        response.getWriter().write(mapper.writeValueAsString(map));
    }


}
