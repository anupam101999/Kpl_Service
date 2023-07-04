FROM maven:3.6.3-jdk-11 AS build
# RUN mvn clean package -DskipTests

FROM openjdk:11.0.11-jre-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]




# FROM openjdk:11
# # EXPOSE 1999
# LABEL maintainer=com.kpl.registration
# ADD target/spring-boot-docker.jar spring-boot-docker.jar
# CMD ["java", "-jar", "spring-boot-docker.jar"]
# # FROM openjdk:11

# # # Set the working directory
# # WORKDIR /app

# # # Copy the JAR file
# # COPY . /app

# # RUN RegistrationApplication.java
# # # Expose the application port
# # # EXPOSE 8080

# # # Run the application
# # CMD ["java", "RegistrationApplication"]
