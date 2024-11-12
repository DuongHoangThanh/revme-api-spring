# Bước 1: Sử dụng image Java chính thức làm base image
FROM openjdk:17-jdk-slim

# Bước 2: Thiết lập thư mục làm việc trong container
WORKDIR /app

# Bước 3: Sao chép file .jar từ thư mục target vào container
COPY target/*.jar app.jar

# Bước 4: Mở cổng 8080 (port mà Spring Boot sẽ chạy)
EXPOSE 8080

# Bước 5: Chạy ứng dụng Spring Boot khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]