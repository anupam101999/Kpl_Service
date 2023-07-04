FROM maven:3.8.5-openjdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11.0.12-jdk-slim
COPY --from=build /target/kpl-service-0.0.1-SNAPSHOT.jar kpl-service.jar
EXPOSE 1999
ENTRYPOINT ["java","-jar","kpl-service.jar"]
