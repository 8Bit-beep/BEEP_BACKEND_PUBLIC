FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar","-Duser.timezone=Asia/Seoul"]

# Redis의 공식 Docker 이미지를 기반으로 합니다.
FROM redis:7.2.4-alpine3.19

# Redis의 기본 포트인 6379를 열어줍니다.
EXPOSE 6379

# Redis 서버를 실행합니다.
CMD ["redis-server"]
