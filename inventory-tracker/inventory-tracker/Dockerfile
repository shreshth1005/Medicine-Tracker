# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml to leverage caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# --- FIX: Make the Maven wrapper executable ---
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of your application's source code
COPY src ./src

# Package the application into a .jar file
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run your app
CMD ["java", "-jar", "target/inventory-tracker-0.0.1-SNAPSHOT.jar"]

