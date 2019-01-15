# Mondial Server

An Web API for the [Mondial Database](https://www.dbis.informatik.uni-goettingen.de/Mondial/).

It is implemented in Java (11.x), using the
[jersey-quickstart-webapp](https://jersey.github.io/documentation/latest/getting-started.html)
archetype.

Build artifacts are produced using Maven (3.x).

The Web API runs on a Tomcat (9.x) server.

## Getting Started

1. Install Docker CE.
1. Install IntelliJ IDEA Ultimate with all plugins enabled.
2. Install Oracle JDK (11.x).
3. Import the project into IntelliJ IDEA.
4. You may be asked that "Maven Projects need to be imported"; click Import Changes if so.
5. Configure the build configuration:
    1. Set the Server to Docker
    2. Set the compose file to the *docker-compose.yml* in this directory.
    3. Check the option to force rebuilds.
6. Configure other quality of life configuration:
    1. Set the Database Explorer to PostgresSQL using the following settings:
        1. Host: `localhost`
        2. Username: `postgres`
        3. Database: `postgres`
        4. Port: `5432`
    2. Configure the debugger configuration:
        1. Create a new Remote debugger.
        2. Set the port number to `8000`.