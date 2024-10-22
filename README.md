국밥 블로그

레디스 배포

https://adjh54.tistory.com/449#google_vignette

네트워크 구축

https://velog.io/@eenaa/Docker-container%EC%8B%A4%ED%96%89%ED%96%88%EC%9D%84-%EB%95%8C-Redis%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EC%97%B0%EA%B2%B0

QueryDSL

https://m.blog.naver.com/anstnsp/222109155983

  - group by  
  https://gent.tistory.com/505

vi 쓰는법

https://velog.io/@zeesoo/Linux-vi-%ED%8E%B8%EC%A7%91%EA%B8%B0-%EC%82%AC%EC%9A%A9%EB%B2%95-%EB%B0%8F-%EB%AA%85%EB%A0%B9%EC%96%B4

스왑 메모리 생성

https://hagenti0612.github.io/aws/aws-memory/

이메일 인증 만들기

https://green-bin.tistory.com/83

spring boot 3 batch
https://4h-developer.tistory.com/23

batch 하면서 발견한 문제 
1. 그냥 실행 자체가 안됨.. 설정한 시간이 되었으나 query를 안날림
```
@SpringBootApplication
@EnableScheduling // <- 이거를 추가해야됨.
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
```

2. 나쁜 sql 이라구요
```
org.springframework.jdbc.BadSqlGrammarException: PreparedStatementCallback; bad SQL grammar [SELECT JOB_INSTANCE_ID, JOB_NAME
FROM BATCH_JOB_INSTANCE
WHERE JOB_NAME = ?
 and JOB_KEY = ?]
```
테이블 직접 쑤셔넣어줘야 함.

SSL 인증

https://www.youtube.com/watch?v=AmREvaxgsQI -> Route53, aws certificate manager 이용 (실패)

https://usage.tistory.com/199 -> cloudflare에서 무료로 (성공)

웹 도메인 연결

https://velog.io/@soluinoon/%EA%B0%80%EB%B9%84%EC%95%84%EC%97%90%EC%84%9C-%EB%8F%84%EB%A9%94%EC%9D%B8-%EA%B5%AC%EB%A7%A4%ED%95%B4-%EC%A0%81%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0 

JPA 메소드 명명규칙 (querydsl 삭제)

https://zara49.tistory.com/130

LocalDateTime 쓰는법 (지역시간 설정하기)

https://covenant.tistory.com/255

iTerm2 커스텀
https://ooeunz.tistory.com/21

검색기능 개선
https://velog.io/@joonghyun/Springboot-%EA%B2%80%EC%83%89-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EC%84%A0%ED%95%98%EA%B8%B01-%EB%9D%84%EC%96%B4%EC%93%B0%EA%B8%B0-%EC%97%86%EB%8A%94-%EB%AC%B8%EC%9E%90%EC%97%B4%EB%A1%9C-%EA%B2%80%EC%83%89%ED%96%88%EC%9D%84-%EB%95%8C-%EA%B2%B0%EA%B3%BC-%EB%B0%98%ED%99%98%ED%95%98%EA%B8%B0

JMETER로 tps 체크
설치 - https://junuuu.tistory.com/901

header사용법 - https://m.blog.naver.com/pcmola/221980258911

json - https://velog.io/@rkfksh/JMeter-%EB%AC%B4%EC%9E%91%EC%A0%95-%EB%94%B0%EB%9D%BC%ED%95%B4%EB%B3%B4%EA%B8%B0Windows

(나중에 할것)

비동기처리

jpa - https://freedeveloper.tistory.com/139

api - https://lovethefeel.tistory.com/126

무중단 배포

https://velog.io/@habins226/Gitlab-Jenkins-Docker-Docker-Hub-Nginx-Blue-Green-%EB%AC%B4%EC%A4%91%EB%8B%A8%EB%B0%B0%ED%8F%AC-CICD-%EA%B5%AC%EC%B6%95-1

TDD

https://mangkyu.tistory.com/182


백엔드 개발자 유튜브

https://www.youtube.com/watch?v=L3y3xk56SI8&list=PL8RgHPKtjlBjssE4aKLmdpup6AyrW9DE-


