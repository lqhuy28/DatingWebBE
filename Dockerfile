FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw

# Chạy lệnh build và hiển thị log
RUN ./mvnw clean package -DskipTests -e -X

COPY src ./src

RUN ./mvnw clean package -DskipTests

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
