#!/usr/bin/env bash

destination=messages
topic=${destination}-topic
subscription=${destination}-subscription
namespace=bootiful
rg=$1

az servicebus namespace create --resource-group $rg \
    --name ${namespace}

az servicebus topic create --resource-group $rg \
    --namespace-name ${namespace} \
    --name ${topic}

az servicebus topic subscription create --resource-group $rg  \
    --namespace-name ${namespace} --topic-name ${topic} \
    --name ${subscription}

