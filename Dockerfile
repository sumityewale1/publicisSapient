# Use OpenJDK 17 base image
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/sumitTest.jar sumitTest.jar
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "sumitTest.jar"]
