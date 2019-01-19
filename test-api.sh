#!/bin/sh

# Ensure a re-usable Docker volume for the Maven repository exists for caching purposes
docker volume create --name mondial-api-builder-repository

# Ensure a network exists to connect the API server container with the testing container
docker network create mondial-server-test-network

# Connect the running API server container to the testing network
docker network connect --alias api-server mondial-server-test-network mondial-server_api-server_1

# Run the tests in a disposable Docker container
docker run --rm --name mondial-api-tester --network=mondial-server-test-network -v mondial-api-builder-repository:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-11 mvn test -Djersey.test.host=api-server

# Cleanup
docker network disconnect mondial-server-test-network mondial-server_api-server_1
docker network rm mondial-server-test-network