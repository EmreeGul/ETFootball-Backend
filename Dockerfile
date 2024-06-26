FROM gradle:8-jdk21 as builder
WORKDIR /
COPY . ./
RUN gradle build -x test

FROM openjdk:21-slim
LABEL authors="emre tekguel"
COPY --from=builder build/libs .
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/ETFootball-0.0.1-SNAPSHOT.jar"]