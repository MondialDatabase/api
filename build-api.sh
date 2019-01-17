#!/bin/sh

# Build the WAR file in a disposable Docker container
docker run --rm --name mondial-api-builder -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn clean package