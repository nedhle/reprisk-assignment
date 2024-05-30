# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/task-0.0.1-SNAPSHOT.jar /app/task-0.0.1-SNAPSHOT.jar

# Copy the resources directory into the container at /app/resources
COPY src/main/resources /app/resources

# Specify the command to run the jar file
CMD ["java", "-jar", "task-0.0.1-SNAPSHOT.jar"]