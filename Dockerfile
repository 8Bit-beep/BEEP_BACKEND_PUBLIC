FROM openjdk:17-jdk
WORKDIR /app
ARG JAR_FILE=build/libs/beep-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /beep.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/beep.jar"]