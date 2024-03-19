FROM openjdk:17
EXPOSE 8080
WORKDIR /apigateway
COPY ./target/gateway-server-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]