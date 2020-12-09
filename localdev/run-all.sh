#!/bin/bash
# Must run from parent dir for docker build
cd ..
set -e

mvn clean package -f pom.xml
docker build -t new-merchant-quote-service-api -f localdev/Dockerfile .

# Move back to localdev dir and start docker
cd localdev
docker-compose rm -f
docker-compose up --build