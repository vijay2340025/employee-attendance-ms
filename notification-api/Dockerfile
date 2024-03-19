FROM openjdk:17
EXPOSE 8803
WORKDIR /notification-service
COPY ./target/notification-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "notification-api-0.0.1-SNAPSHOT.jar"]