#!/bin/bash

mvn clean install

IMAGE_NAME=$1
echo  "image being build is "$IMAGE_NAME
docker build -t $IMAGE_NAME:snapshot .
docker-compose up -d 
