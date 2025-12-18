FROM maven:4.0.0-rc-5-eclipse-temurin-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17

COPY --from=build /app/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]