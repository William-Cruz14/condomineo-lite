FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/condomineo-lite-0.0.1-SNAPSHOT.jar /app/condomineo-lite-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/condomineo-lite-0.0.1-SNAPSHOT.jar"]