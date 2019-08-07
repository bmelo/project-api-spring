FROM openjdk:8-jdk-alpine

RUN apk update --no-cache && \
    apk add maven --no-cache && \
    rm -rf /var/cache/apk/*

WORKDIR /app
COPY pom.xml /app/pom.xml

#RUN mvn install compile package spring-boot:repackage
RUN mvn package spring-boot:repackage

COPY src /app/src
RUN mvn spring-boot:repackage
#ENTRYPOINT [ "mvn", "spring-boot:run" ]
ENTRYPOINT [ "java", "-jar", "target/funcionarios-service-0.0.1-SNAPSHOT.jar" ]