FROM openjdk:17
EXPOSE 8801
WORKDIR /access-control-service
COPY ./target/access-control-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "access-control-api-0.0.1-SNAPSHOT.jar"]