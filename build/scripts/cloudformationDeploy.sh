#!/bin/bash

# Get acccount id for docker image
aws_account_id=$(aws sts get-caller-identity | jq -r '.Account')

# Network stack
aws cloudformation deploy \
--template-file build/cloudformation/cloudformation-network.yml \
--stack-name new-merchant-quote-service-network \
--capabilities CAPABILITY_NAMED_IAM

# RDS stack
aws cloudformation deploy \
--template-file build/cloudformation/cloudformation-rds.yml \
--stack-name new-merchant-quote-service-rds \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides NetworkStackName=new-merchant-quote-service-network

# App stack
aws cloudformation deploy \
--template-file build/cloudformation/cloudformation-app.yml \
--stack-name new-merchant-quote-service-app \
--capabilities CAPABILITY_NAMED_IAM \
--parameter-overrides NetworkStackName=new-merchant-quote-service-network \
DatabaseStackName=new-merchant-quote-service-network \
ImageUrl=${aws_account_id}.dkr.ecr.ap-southeast-2.amazonaws.com/new-merchant-quote-service-repository:latest