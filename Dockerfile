FROM maven:3.8.6 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine3.18 AS runner
WORKDIR /app
COPY --from=builder /app/target/ComfortSoftTestTask-0.0.1-SNAPSHOT.jar ./app.jar
COPY Test.xlsx ./Test.xlsx
ENTRYPOINT ["java", "-jar", "app.jar"]
