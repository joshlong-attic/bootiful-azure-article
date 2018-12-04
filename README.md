# Bootiful Azure 

* Getting Started with Spring for Microsoft Azure 
* SQL-based data access with Microsoft SQL Server (bonus! it supports reactive programming)
* CosmosDB 
* Azure Service Bus 
* Object Storage Service 
* Azure Application Insights
* Using the Azure Services in a Cloud Foundry Environment 


:sma: Spring for Microsoft Azure 
:msa: Microsoft Azure 
:gcp: Google Cloud Platform 

# Bootiful Azure: Getting Started with :sma:

In this installment we're going to introduce the Spring for Microsoft Azure support, focusing on the interesting technologies that are enabled by the platform for consumption from Spring applications. As with all such discussions, I feel it worth raising a few key points. 

## Don't Run What You Can't Charge For 

I think we can all agree with the new but old axim, you shouldn't run software that you can't charge for. That is, the average organization has enough problems. Running commodity software like MySQL or Kafka shouldn't be among them. It'll cost infinitely less to pay someone to run that for you. Ideally, whoever ends up running that software should have a vested interest in running those things well. Most organizations have a mission that's far away from configuring SSL, or debugging MySQL replication issues. It is virtually always cheaper to let someone else do that for you.  

If you are trying to do something standard, then you should absolutely rely on standardized tools. Why use a cloud vendor's lockin-ware to run a Java or Node-based application when you could use something like Cloud Foundry or Kubernetes, for which the hiring pool is larger, the cost can be virtually nill, and the ease of use is on-par or better than whatever the cloud vendor is offering? 

This is a big part of the reason that Microsoft and Pivotal have such a great relationship.  Enterprise customers understand that for some workloads the public cloud is a foregone conclusion, but they don't want to be locked in. Optionality is valuable. We see this all the time; GCP's prices were for some workload-types cheaper than AWS's. Some public cloud vendors have availability zones in regions you might want to be in that others dont. Increasingly, organizations embark upon hybrid cloud or mutlicloud strategies, knowing that their workloads will vary, and their availability demands will vary. As much as possible, these organizations want to reduce the cost of operationalizing, securing, training-up and deploying to the varities of cloud infrastructure. 

Pivotal Cloud Foundry is a natural choice here; it allows organizations to deploy reliably to any of a number of cloud platforms and, as easily, and when necessary, drop down to platform-specific services. This fact is one of the reasons that Microsoft named Pivotal partner of the year in 2017 and 2018. We help drive resource usage on Microsoft Azure and we do so in as portable a way possible while still surfacing  the valuable and unique. 

## Data Gravity

That's not to say that some platforms don't offer compelling reasons to choose them over the others. All platforms have things that set them ahead of the herd in one way or another. These things necessarily have gravity; where anybody could run MySQL, very few can run a Google Cloud Spanner or a Microsoft CosmosDB. If you have a usecase for which sorts of tools are a good solution then you should absolutely use them and Spring wants to make the work as easy as possible!


## Setup 


