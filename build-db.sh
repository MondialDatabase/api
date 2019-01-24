#!/bin/sh

# Ensure a re-usable Docker volume for the Database files exists for caching purposes
docker volume create --name mondial-database-data

# Build the Database in a disposable Docker container
docker run --rm --name mondial-database-builder -v "$(pwd)/fixtures":/docker-entrypoint-initdb.d -v mondial-database-data:/var/lib/postgresql/data postgres:11 --version