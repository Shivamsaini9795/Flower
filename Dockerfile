# Use official OpenJDK 17 base image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Download dependencies (better caching)
RUN ./mvnw dependency:go-offline

# Copy all source code
COPY src ./src

# Package the application (skip tests)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot app
CMD ["java", "-jar", "target/flower-0.0.1-SNAPSHOT.jar"]
