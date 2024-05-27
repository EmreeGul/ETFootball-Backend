FROM gradle:8-jdk21 as builder
WORKDIR /app
COPY . ./
RUN gradle build

FROM openjdk:21-slim
LABEL authors="emre.tekguel"
WORKDIR /app
COPY --from=builder /app/build/libs/backend-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","backend-0.0.1-SNAPSHOT.jar"]
