# Use official OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY . .

# Build application (skip tests)
RUN ./mvnw clean package -DskipTests

# Run the jar file
CMD ["java", "-jar", "target/*.jar"]
