# syntax=docker/dockerfile:1
# Simple Dockerfile for datekeeper-backend
# Relies on building on local machine using gradle build
FROM eclipse-temurin:17-jdk-jammy
LABEL version="1.0"
LABEL description="Simple Dockerfile for datekeeper-backend. Relies on building datekeeper-backend on a machine using grade build."

VOLUME /tmp
COPY /build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]