#!/bin/bash

# Build docker image
mvn clean package -f pom.xml
docker build -t new-merchant-quote-service-api -f localdev/Dockerfile .

# Create repo & upload image
./build/scripts/createDockerRepo.sh
./build/scripts/uploadDockerImage.sh

# Deploy stack
./build/scripts/cloudformationDeploy.sh