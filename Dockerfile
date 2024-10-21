FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Chuyển đổi định dạng dòng và cấp quyền thực thi
RUN apk add --no-cache dos2unix && dos2unix mvnw && chmod +x mvnw

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
