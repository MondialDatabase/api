#!/bin/sh

# Ensure a re-usable Docker volume for the Maven repository exists for caching purposes
docker volume create --name mondial-api-builder-repository

# Run the tests in a disposable Docker container
docker run --rm --name mondial-api-tester --network=mondial-server_default -v mondial-api-builder-repository:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn test -Djersey.test.host=api-server
