FROM openjdk:17-alpine

ARG JAR_FILE=target/rinhabackend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java",  "-XX:+UseG1GC", "-Xmx64m", "-Xms64m", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]

EXPOSE ${PORT}