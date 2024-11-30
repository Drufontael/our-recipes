FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn dependency:resolve
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17.0.13_11-jre-ubi9-minimal
WORKDIR /app
COPY --from=builder ./app/target/*.jar ./application.jar
EXPOSE 8080

ENV POSTGRES_HOST=localhost
ENV SECURITY_PREFIX=Bearer
ENV SECURITY_KEY=54cc26e364ac66062a7cd7f1efc176eba645de1f3a43666d09aac5bc667c3886
ENV SECURITY_EXPIRATION=360000
ENV SECURITY_ROOT=admin
ENV SECURITY_ROOT_PASSWORD=admin

ENV DB_NAME=our_recipes
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres


RUN echo "the env var POSTGRES_HOST value is $POSTGRES_HOST"

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "application.jar"]