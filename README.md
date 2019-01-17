# Mondial Server

An Web API for the [Mondial Database](https://www.dbis.informatik.uni-goettingen.de/Mondial/).

It is implemented in Java (11.x), using the
[jersey-quickstart-webapp](https://jersey.github.io/documentation/latest/getting-started.html)
archetype.

Build artifacts are produced using Maven (3.x).

The Web API runs on a Tomcat (9.x) server.

## Running the Web API

NOTE: For API development, see the Development Environment Setup section instead.
For serving the Web API for use with web clients:

1. Install Docker CE.
2. Change into the project directory.
3. Run `sh build-api.sh` to build the project source code.
4. Run `sh build-db.sh` to build the project Mondial database.
   NOTE: This will not do a clean rebuild of the database. If this is desired (e.g. due to a schema change), execute
   an `rm -r db-target` command first.
3. Run `docker-compose up` to start the web services. To shut down the web services, run `docker-compose down`.

## Development Environment Setup 

1. Install Docker CE.
1. Install IntelliJ IDEA Ultimate with all plugins enabled.
2. Install Oracle JDK (11.x).
3. Import the project into IntelliJ IDEA.
4. You may be asked that "Maven Projects need to be imported"; click Import Changes if so.
5. Configure the build configuration:
    1. Add a Bash configuration:
        1. Set the script to the *build-db.sh* in this directory.
        2. Set the working directory to the project directory.
        3. Set the interpreter to a shell executable e.g. `/bin/sh`.
    2. Add a Docker Compose configuration:
        1. Set the Server to Docker
        2. Set the compose file to the *docker-compose.yml* in this directory.
        3. Check the option to force rebuilds.
        4. Set the bash configuration defined in the previous step to run before launch.
        5. Set Build Artifact: WAR to run before launch.
6. Configure other quality of life configuration:
    1. Set the Database Explorer to PostgresSQL using the following settings:
        1. Host: `localhost`
        2. Username: `postgres`
        3. Database: `postgres`
        4. Port: `5432`
    2. Configure the debugger configuration:
        1. Add a Remote configuration.
            1. Set the port number to `8000`.


The Web API will be served at `localhost:8080/api/webapi`.
