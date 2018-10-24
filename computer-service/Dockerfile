FROM openjdk:10-jre

ADD ./target/computer-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/computer-service.jar"]

EXPOSE 8080