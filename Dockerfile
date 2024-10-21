# Sử dụng JDK 17 làm base image
FROM openjdk:17-jdk-alpine
# Đặt thư mục làm việc
WORKDIR /app
# Sao chép file JAR vào container
COPY target/*.jar app.jar
# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
