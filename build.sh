#!/bin/bash

gradle wrapper && ./gradlew build
mkdir -p build/dependency && (cd build/dependency && jar -xf ../libs/*.jar)
docker build --build-arg DEPENDENCY=build/dependency -t gs-springboot-sql-proxy .
