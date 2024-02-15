#!/bin/bash

echo Clean Environment
containers=$(docker ps -a -q)

if [ -n "$containers" ]; then
    docker rm -f $containers
fi

docker volume prune --all --force

echo Build Application
cd app
./mvnw -Pnative native:compile
cd ..

echo Start Containers
docker-compose -f docker-compose-graalvm.yml up -d --build
