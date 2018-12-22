#!/usr/bin/env bash

RG_NAME=$1
az group create --name $RG_NAME --location "West US 2"
