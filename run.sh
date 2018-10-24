#!/usr/bin/env bash

#!/bin/sh

build() {
    build_service
    build_ui
}

build_service() {
    mvn clean install -Dmaven.test.skip=true -pl computer-service || exit 1
    docker build -t afayes/computer-service computer-service || exit 1
}

build_ui() {
    cd computer-ui
    npm install
    ng build --prod || exit 1
    docker build -t afayes/computer-ui .
}

start() {
    docker-compose up
}
stop() {
    docker-compose down
}

run() {
    build
    start
}

push() {
    docker push afayes/computer-service
    docker push afayes/computer-ui
}

logs() {
    docker-compose logs -f
}

case "$1" in
    build)
        build
        ;;
    start)
        start
        ;;
    stop)
        stop
        ;;
    run)
        run
        ;;

    push)
        push
        ;;

    logs)
        logs
        ;;
    *)
        echo "USAGE: $0 {build|start|stop|run}"
        echo "build: build the maven project and build the docker image"
        echo "start: start the docker containers"
        echo "stop: stop the docker containers"
        echo "run: build and start"
        echo "push: push images to docker hub"
        echo "logs: shows output from docker containers"
esac
