# Official image for Tomcat 9.x with JRE 11
FROM tomcat:9-jre11

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file into Tomcat webapps directory
COPY ./target/mondial-server.war /usr/local/tomcat/webapps/api.war

# Start the Tomcat server
CMD ["catalina.sh", "run"]