FROM openjdk:17-slim
LABEL authors="TRUNG KIEN"

# Set the working directory
WORKDIR /app

COPY target/DatingApp-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]