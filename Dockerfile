# Build Stage: Use Maven's official image with Corretto (OpenJDK 17)
FROM maven:3.9.11-amazoncorretto-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src/ ./src/

# Build the application using Maven (skip tests during the build)
RUN mvn clean package -DskipTests

# Final Stage: Use Amazon Corretto to run the application
FROM amazoncorretto:17

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
