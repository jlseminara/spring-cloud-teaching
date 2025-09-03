
cd producer
./gradlew clean build

cd ../consumer
./gradlew clean build

cd ..
docker-compose -f .\services-compose.yml up


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

