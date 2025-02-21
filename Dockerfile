FROM amazoncorretto:17-alpine3.18 AS extractor
WORKDIR /app
COPY target/ComfortSoftTestTask-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]