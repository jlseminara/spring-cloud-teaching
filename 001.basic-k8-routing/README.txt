
<start podman>

minikube start --driver=podman

kubectl config use-context minikube

<build producer: ./gradlew clean build>

<build consumer: ./gradlew clean build>

minikube image load producer:latest

minikube image load consumer:latest

cd deployment

kubectl aplly ./

minikube service consumer --url

