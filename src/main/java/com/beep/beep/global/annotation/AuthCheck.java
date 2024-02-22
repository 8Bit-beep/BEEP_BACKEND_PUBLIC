package com.beep.beep.global.annotation;



import com.beep.beep.domain.user.domain.enums.UserType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented // javaDos가 문서화하도록 지정
// 해당 어노테이션을 사용한 곳에서 자동으로 문서화함.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지 정책, 어플리케이션 실행동안 어노테이션 정보가 유지돼야함.(runtime시점에도 사용가능)
@Target({ElementType.TYPE, ElementType.METHOD}) // 이 어노테이션을 적용가능한 대상, 클래스와 메소드에 적용 가능
public @interface AuthCheck { // @interface : 새로운 커스텀 어노테이션 적용 시 사용가능
    // 속성들
    boolean require() default true; // require() : boolean 반홥하며, 특별한 설정이 없다면 기본 반환값은 true
    UserType role() default UserType.ROLE_STUDENT; // role() : UserType 반환, 기본 반환값은 ROLE_USER

}
