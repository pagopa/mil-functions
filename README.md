# Multi-channel Integration Layer Functions
Handling of end-user services for Multi-channel Integration Layer of SW Client Project.

## Dependencies
This project has as parent mil-bom and depends on mil-common.

## How to build native image on a Mac M1
```shell script
docker build -f src/main/docker/Dockerfile.graalvm -t graalvm-ce:latest .
./mvnw package -Pnative  -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=graalvm-ce:latest
```


## How to run native imaged on a Mac M1
```shell script
docker build -f src/main/docker/Dockerfile.native-micro-2 -t pagopa/mil-functions .
docker run --rm -p 8080:8080 pagopa/mil-functions
```