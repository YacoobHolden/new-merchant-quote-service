#!/bin/bash

# Login to ECR and create repo
$(aws ecr get-login)
aws ecr create-repository --repository-name new-merchant-quote-service-repository --region ap-southeast-2