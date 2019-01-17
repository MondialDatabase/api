#!/bin/sh

# Build the Database in a disposable Docker container
docker run --rm --name mondial-database-builder -v "$(pwd)/fixtures":/docker-entrypoint-initdb.d -v "$(pwd)/db-target":/var/lib/postgresql/data postgres:11 --version