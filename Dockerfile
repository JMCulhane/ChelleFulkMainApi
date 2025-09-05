# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file (update the name if needed)
COPY chelle-fulk-main-api/target/chelle-fulk-main-api-0.0.1-SNAPSHOT.jar app.jar

# Optionally copy your google-service-account.json if needed
# COPY chelle-fulk-main-api/src/main/resources/google-service-account.json /app/google-service-account.json

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
