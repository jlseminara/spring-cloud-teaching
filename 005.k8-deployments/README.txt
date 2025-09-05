
<start podman>

minikube start --driver=podman --memory=8192
minikube start --driver=podman --memory=7000

kubectl config use-context minikube


cd producer
./gradlew clean build

cd ../consumer
./gradlew clean build

cd ../configuration-server
./gradlew clean build


podman pull redis:alpine
podman pull consul:1.15
podman pull tykio/tyk-gateway:v5.8.5


minikube image load redis:alpine
minikube image load consul:1.15
minikube image load tykio/tyk-gateway:v5.8.5
minikube image load configserver:latest
minikube image load producer:latest
minikube image load consumer:latest


cd ../deployment

minikube ssh -- mkdir -p /home/docker/configurations
minikube ssh -- mkdir -p /home/docker/spring-config-files

minikube cp ./configurations/consumer-api.json /home/docker/configurations/consumer-api.json
minikube cp ./configurations/producer-api.json /home/docker/configurations/producer-api.json
minikube cp ./configurations/consul.json /home/docker/configurations/consul.json
minikube cp ./configurations/tyk.conf /home/docker/configurations/tyk.conf
minikube cp ./spring-config-files/producer-prod.yml /home/docker/spring-config-files/producer-prod.yml
minikube cp ./spring-config-files/consumer-prod.yml /home/docker/spring-config-files/consumer-prod.yml

kubectl apply -f ./

minikube service consumer --url




curl --location 'http://localhost:8080/consumer/consume' \
--header 'x-tyk-authorization: SOME_SECRET' \
--header 'x-api-version: Default'


Test Consul:
	curl --location 'http://localhost:8500/v1/catalog/service/consumer'
	
Test Tyk:
	curl --location "http://localhost:8080/hello"
	curl --location "http://localhost:8080/tyk/apis" --header "x-tyk-authorization: SOME_SECRET"
	
Get data:
	curl --location "http://localhost:8080/consumer/consume" --header "x-tyk-authorization: SOME_SECRET" --header "x-api-version: Default"

