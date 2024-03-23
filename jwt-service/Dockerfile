FROM gcr.io/distroless/java17-debian12
EXPOSE 9898
WORKDIR /jwt-service
COPY ./target/jwt-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "jwt-service-0.0.1-SNAPSHOT.jar"]