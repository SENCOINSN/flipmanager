# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY ../pom.xml .
RUN mvn dependency:go-offline
COPY ../src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/flipmanager-*.jar /app/

EXPOSE 9095

ENV DB_URL=jdbc:postgresql://host.docker.internal:5432/bd_console_flip

# for mac user or linux
#ENV DB_URL=jdbc:postgresql://postgres:5432/bd_console_flip
ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource=${DB_URL} flipmanager-0.0.1-SNAPSHOT.jar