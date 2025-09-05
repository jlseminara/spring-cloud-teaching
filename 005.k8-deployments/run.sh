#!/bin/bash

minikube delete
minikube start --driver=docker --memory=10000

kubectl config use-context minikube


#cd producer
#./gradlew clean build
#cd ..
minikube image load producer:latest

#cd consumer
#./gradlew clean build
#cd ..
minikube image load consumer:latest

#cd configuration-server
#./gradlew clean build
#cd ..
minikube image load configserver:latest

docker pull redis:alpine
docker pull consul:1.15
docker pull tykio/tyk-gateway:v5.8.5
minikube image load redis:alpine
minikube image load consul:1.15
minikube image load tykio/tyk-gateway:v5.8.5

cd deployment

minikube ssh -- mkdir -p /home/docker/configurations
minikube ssh -- mkdir -p /home/docker/spring-config-files

minikube cp ./configurations/consumer-api.json /home/docker/configurations/consumer-api.json
minikube cp ./configurations/producer-api.json /home/docker/configurations/producer-api.json
minikube cp ./configurations/consul.json /home/docker/configurations/consul.json
minikube cp ./configurations/tyk.conf /home/docker/configurations/tyk.conf
minikube cp ./spring-config-files/producer-prod.yml /home/docker/spring-config-files/producer-prod.yml
minikube cp ./spring-config-files/consumer-prod.yml /home/docker/spring-config-files/consumer-prod.yml

kubectl apply -f ./


