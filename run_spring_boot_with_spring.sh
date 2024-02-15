#!/bin/bash

echo Clean Environment
containers=$(docker ps -a -q)

if [ -n "$containers" ]; then
    docker rm -f $containers
fi

docker volume prune --all --force

echo Build Application
mvn clean install -f ./app/pom.xml

echo Start Containers
docker-compose -f docker-compose-spring.yml up -d --build
