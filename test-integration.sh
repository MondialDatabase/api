#!/bin/sh

# Ensure a re-usable Docker volume for the Maven repository exists for caching purposes
docker volume create --name mondial-api-maven-repository

# Run the tests in a disposable Docker container
docker run --rm --name mondial-api-tester --network=mondial_default -v mondial-api-maven-repository:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn test -Djersey.test.host=api-server -Dgroups="edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.integration.IntegrationTest"
