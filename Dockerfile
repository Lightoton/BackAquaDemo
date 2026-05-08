# Этап 1: Сборка проекта (используем образ с Maven)
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
# Копируем pom.xml и скачиваем зависимости (кэшируем их)
COPY pom.xml .
RUN mvn dependency:go-offline
# Копируем исходный код и собираем .jar файл
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск (используем легкий образ только с JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Забираем собранный .jar из первого этапа
COPY --from=builder /app/target/*.jar app.jar
# Открываем порт
EXPOSE 8080
# Запускаем Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]