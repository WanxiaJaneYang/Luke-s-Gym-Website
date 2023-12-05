# Use a base image with Java 20 runtime
FROM openjdk:20-slim as build

# Add Maintainer Info
LABEL maintainer="your_email@example.com"

# Set the working directory in the Docker image
WORKDIR /app

# Copy Maven's pom.xml and wrapper files
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Grant execution permissions to the Maven wrapper
RUN chmod +x mvnw

# Copy your application's source code into the Docker image
COPY src src

# Build the application using Maven
RUN ./mvnw package -DskipTests

# Use a smaller base image for the final image
FROM openjdk:20-slim

# Copy the built application JAR from the build stage
COPY --from=build /app/target/lukegymbackend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
