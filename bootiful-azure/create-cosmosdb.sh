#!/bin/bash

# the name of the resource group
export rg=$1
export adminlogin=${rg}-cosmosdb

location='southcentralus'
accountname=${adminlogin}
databasename=bootiful
containername=reservations

# Create a SQL API Cosmos DB account with session consistency and multi-master enabled
az cosmosdb create \
    --resource-group $rg \
    --name $adminlogin \
    --kind GlobalDocumentDB \
    --default-consistency-level "Session" 

# Create a database
az cosmosdb database create \
    --resource-group $rg \
    --name $adminlogin \
    --db-name $databasename

# Create a SQL API container with a partition key and 1000 RU/s
az cosmosdb collection create \
    --resource-group $rg \
    --collection-name $containername \
    --name $adminlogin \
    --db-name $databasename \
    --partition-key-path /id \
    --throughput 1000

