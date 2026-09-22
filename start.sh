#!/bin/bash

mkdir -p /data/db
mkdir -p /code/log

mongod --fork --logpath /code/log/mongodb.log --bind_ip 127.0.0.1

cd /code/sample-data
chmod +x load-sample-data.sh
./load-sample-data.sh
cd /code

java -jar /code/build/libs/spring-starter-0.0.1-SNAPSHOT.jar --server.port=${PORT:-8080}