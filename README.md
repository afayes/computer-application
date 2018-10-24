# Computer Application

## Overview
This Computer application is built using Java 10, Spring Boot, Angular 6, Spring Data JPA, Docker, SWAGGER, NGINX and Bootstrap.

## Quick start
To run the project execute
```
docker-compose up
```

This will pull the pre-built images from Docker Hub for the computer-service and computer-ui and run it in docker. The UI
runs on the NGINX web server in Docker.

Navigate to: http://localhost to access the Angular UI application
Navigate to: http://localhost:8080/swagger-ui.html to access the backend API through SWAGGER

## Tests
You can run all unit and integration tests by executing
```
mvn clean verify
```

## Some Implementation Details
I implemented edit and delete for computer resources in the backend API as well as in the UI for completeness although they were not mentioned in the test spec.

## Useful Commands
A [run.sh](run.sh) Bash script has been written to provide useful commands for the project. It is executed in the following way:

``` ./run.sh {build|start|stop|run|push|logs}```

- build: build the backend service, UI and build the docker images

- start: start the docker containers

- stop: stop and remove the docker containers

- run: build and start

- push: push the images to Docker Hub

- logs: shows log output from docker containers

## TODO
- For the backend, implement pure unit tests mocking out dependencies. The controller tests in this project were integration tests
- Implement unit tests for the UI
- Use HATEOAS for REST
- Implement validation for the IP and MAC address format in the API and UI
- UX improvements - get confirmation before deleting computer
- Add logging
