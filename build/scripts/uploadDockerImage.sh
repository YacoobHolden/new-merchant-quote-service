#!/bin/bash

# Set variables
aws_account_id=$(aws sts get-caller-identity | jq -r '.Account')

# Tag image and upload
docker tag new-merchant-quote-service-api ${aws_account_id}.dkr.ecr.ap-southeast-2.amazonaws.com/new-merchant-quote-service-repository
$(aws ecr get-login --no-include-email)
docker push ${aws_account_id}.dkr.ecr.ap-southeast-2.amazonaws.com/new-merchant-quote-service-repository:latest