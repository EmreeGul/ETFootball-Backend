FROM gradle:8-jdk21 as builder
WORKDIR /
COPY . ./
RUN gradle build

FROM openjdk:21-slim
LABEL authors="emre.tekguel"
COPY --from=builder build/libs/EtFootball-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/EtFootball-0.0.1-SNAPSHOT.jar"]