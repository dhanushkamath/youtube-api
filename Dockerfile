FROM openjdk:8-jdk-alpine
MAINTAINER dhanushkamath
COPY target/youtube-api-1.0.0.jar youtube-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/youtube-api-1.0.0.jar"]