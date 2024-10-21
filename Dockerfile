# Sử dụng JDK 17 làm base image
FROM openjdk:17-jdk-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file cấu hình Maven vào container
COPY pom.xml .

# Sao chép mã nguồn vào container
COPY src ./src

# Build ứng dụng Spring Boot
RUN ./mvnw clean package -DskipTests

# Sao chép file JAR đã build vào container
COPY target/*.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
