#!/usr/bin/env bash

rg=$1
appname=${rg}-appinsights

# create the App Insights subscription (it's not available in all regions yet so I specified South Central US)
az resource create \
    --resource-group $rg \
    --resource-type "Microsoft.Insights/components" \
    --name $appname \
    --location "South Central US" \
    --properties '{"ApplicationId":"bootiful","Application_Type":"web"}'

# use the following command to get the key
az resource show -g $rg -n $appname --resource-type "Microsoft.Insights/components" --query properties.InstrumentationKey
