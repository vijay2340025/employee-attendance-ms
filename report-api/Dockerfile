FROM gcr.io/distroless/java17-debian12
EXPOSE 8802
WORKDIR /report-service
COPY ./target/report-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "report-api-0.0.1-SNAPSHOT.jar"]