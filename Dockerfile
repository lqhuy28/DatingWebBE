# Stage 1: Build the application
FROM maven:3-openjdk-17 AS build
WORKDIR /app

# Copy the entire project to the container
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the generated WAR file from the build stage
COPY --from=build /app/target/*.war drcomputer.war

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "drcomputer.war"]
