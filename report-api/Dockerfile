FROM openjdk:17
EXPOSE 8802
WORKDIR /report-service
COPY ./target/report-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "report-api-0.0.1-SNAPSHOT.jar"]