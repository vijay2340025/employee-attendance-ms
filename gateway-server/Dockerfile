FROM gcr.io/distroless/java17-debian12
EXPOSE 8080
WORKDIR /apigateway
COPY ./target/gateway-server-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]