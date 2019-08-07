# BASE to deploy application
FROM openjdk:8-jdk-alpine AS base

RUN apk update --no-cache && \
    apk add maven --no-cache && \
    rm -rf /var/cache/apk/*

WORKDIR /app
COPY pom.xml /app/pom.xml

RUN mvn package spring-boot:repackage

COPY src /app/src
RUN mvn package spring-boot:repackage

#------------#
# Main image #
#------------#
FROM openjdk:8-jdk-alpine
LABEL Author="Bruno Melo <bruno.raphael@gmail.com>"

COPY --from=base /app/target /app/target

WORKDIR /app
#ENTRYPOINT [ "mvn", "spring-boot:run" ]
ENTRYPOINT [ "java", "-jar", "target/funcionarios-service-0.0.1-SNAPSHOT.jar" ]


