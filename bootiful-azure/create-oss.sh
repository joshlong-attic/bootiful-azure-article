#!/usr/bin/env bash

#
rg=$1
accountname=bootiful

#
az storage account create --name ${accountname} --resource-group ${rg}
az storage container create -n files --account-name ${accountname}

#
az storage account show-connection-string --resource-group $rg --name $accountname