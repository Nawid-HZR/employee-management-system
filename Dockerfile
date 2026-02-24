# ========= STAGE 1: BUILD =========

FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml first (better caching)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy source
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# ========= STAGE 2: RUN =========

FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]