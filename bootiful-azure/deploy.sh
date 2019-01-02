#!/usr/bin/env bash

NAME=bootiful
./destroy-all.sh $NAME
./create-resource-group.sh $NAME
./create-sql-server.sh $NAME
./create-cosmosdb.sh $NAME
./create-servicebus.sh $NAME
./create-oss.sh $NAME
./create-app-insights.sh $NAME