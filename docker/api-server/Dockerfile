# Official image for Tomcat 9.x with JRE 11
FROM tomcat:9-jre11

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file into Tomcat webapps directory
# Renaming to ROOT.war to mount the application at the server root
COPY ./target/mondial-server.war /usr/local/tomcat/webapps/ROOT.war

# Add environment variables for Tomcat JPDA support
ENV JPDA_ADDRESS="*:8000"
ENV JPDA_TRANSPORT="dt_socket"

# Start the Tomcat server
CMD ["catalina.sh", "jpda", "run"]