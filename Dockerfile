FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomee:jre17-Semeru-ubuntu-webprofile
WORKDIR /usr/local/tomee
COPY --from=build /app/target/MovieRev-1.0-SNAPSHOT.war ./webapps/MovieRev-1.0-SNAPSHOT.war

RUN apt-get update && apt-get install -y curl && \
    curl -o lib/postgresql-42.6.0.jar https://jdbc.postgresql.org/download/postgresql-42.6.0.jar


COPY src/main/webapp/WEB-INF/resources.xml /usr/local/tomee/conf/resources.xml
EXPOSE 8080
CMD ["catalina.sh", "run"]