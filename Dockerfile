FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN ./mvnw clean install

CMD ["java", "-jar", "target/app.jar"]