FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory
WORKDIR /app

# Copy the JAR file
COPY target/spring-boot-docker.jar spring-boot-docker.jar

# Expose the application port
EXPOSE 1999

# Run the application
CMD ["java", "-jar", "spring-boot-docker.jar"]
