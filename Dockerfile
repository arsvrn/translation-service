# Этап сборки
FROM openjdk:17-jdk-slim AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test --no-daemon

# Этап выполнения
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/translation-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]