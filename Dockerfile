FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} apigateway.jar

# start the jar file
ENTRYPOINT ["java", "-jar", "/apigateway.jar"]

# expose port
EXPOSE 9090
