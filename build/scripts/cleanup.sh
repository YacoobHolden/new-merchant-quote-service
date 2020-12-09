#!/bin/bash

# Delete ECR
aws ecr delete-repository --repository-name new-merchant-quote-service-repository --region ap-southeast-2 --force

# TBD - Delete CFN