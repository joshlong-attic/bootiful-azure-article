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


# Create a logical server in the resource group
az sql server create \
	--name $servername \
	--resource-group $rg \
	--admin-user $adminlogin \
	--admin-password $password

# Configure a firewall rule for the server
az sql server firewall-rule create \
	--resource-group $rg \
	--server $servername \
	-n AllowYourIp \
	--start-ip-address $startip \
	--end-ip-address $endip

# Create a database in the server with zone redundancy as true
az sql db create \
	--resource-group $rg \
	--server $servername \
	--name ${1}-sample-db \
	--sample-name AdventureWorksLT \
	--service-objective Basic \
