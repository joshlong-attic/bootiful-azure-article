#!/bin/bash

# Set an admin login and password for your database
export adminlogin=bootiful
export password=B00t1ful

# The logical server name has to be unique in the system
export servername=${1}-server

# The ip address range that you want to allow to access your DB
export startip=0.0.0.0
export endip=223.255.255.255

# the name of the resource group
export rg=$1

#!/bin/bash


location='southcentralus'
accountname=$adminlogin
databasename=$rg 
containername=${rg}-reservations


# Create a SQL API Cosmos DB account with session consistency and multi-master enabled
az cosmosdb create \
    --resource-group $rg \
    --name $adminlogin \
    --kind GlobalDocumentDB \
    --locations "South Central US"=0 "North Central US"=1 \
    --default-consistency-level "Session" \
    --enable-multiple-write-locations true


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
    --partition-key-path /mypartitionkey \
    --throughput 1000