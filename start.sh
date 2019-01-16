#!/bin/sh

# Build the WAR file in a disposable Docker container
docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn clean package 

# Start Docker services
docker-compose up
