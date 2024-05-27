FROM gradle:8-jdk21 as builder
WORKDIR /
COPY . ./
RUN gradle build

FROM openjdk:21-slim
LABEL authors="emre.tekguel"
COPY --from=builder /home/gradle/src/build/libs/ETFootball /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]