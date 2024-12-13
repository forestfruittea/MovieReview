# Use the TomEE base image with JRE 17 and Ubuntu
FROM tomee:jre17-Semeru-ubuntu-webprofile

WORKDIR /usr/local/tomee/webapps

COPY target/MovieRev-1.0-SNAPSHOT.war /usr/local/tomee/webapps/MovieRev-1.0-SNAPSHOT.war

RUN curl -L https://jdbc.postgresql.org/download/postgresql-42.5.0.jar -o /usr/local/tomee/lib/postgresql-42.5.0.jar


EXPOSE 8080

CMD ["catalina.sh", "run"]