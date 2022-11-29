# Multi-channel Integration Layer Functions
Handling of end-user services for Multi-channel Integration Layer of SW Client Project.

## Dependencies
This project has as parent mil-bom and depends on mil-common.

## How to run dev mode
Before running the Maven goal quarkus:dev, run Mongo on Docker:
```shell script
docker run -d -p 27017:27017 --name test-mongo mongo:latest
```

## How to play locally with native image
### Build native image
```shell script
mvn package -Pnative -Dquarkus.native.container-build=true -Dquarkus-profile=playground
docker build -f src/main/docker/Dockerfile.native-micro -t pagopa/mil-functions .
```

### Create a network
```shell script
docker network create --driver bridge functions-net
```

### Run Mongo attached to network created before
```shell script
docker run -d -p 27017:27017 --name test-mongo --network functions-net mongo:latest
```

### Run mil-functions attached to network created before
```shell script
docker run -i --rm -p 8080:8080 --name mil-functions --network functions-net pagopa/mil-functions
```
