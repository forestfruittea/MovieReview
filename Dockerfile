FROM tomee:10-jdk11

# Set the working directory inside the container
WORKDIR /usr/local/tomee/webapps

# Copy the WAR file into the TomEE webapps directory
COPY target/MovieRev-1.0-SNAPSHOT.war /usr/local/tomee/webapps/MovieRev-1.0-SNAPSHOT.war

# Expose TomEE HTTP port
EXPOSE 8080

# Start TomEE server
CMD ["catalina.sh", "run"]
