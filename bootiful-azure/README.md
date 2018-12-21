# Bootiful Azure 

* Bootiful Azure: Taking Your First Steps with Microsoft Azure 
* Bootiful Azure: SQL-based data access with Microsoft SQL Server (bonus! it supports reactive programming)
* Bootiful Azure: Global Data Access with CosmosDB 
* Bootiful Azure: Azure Service Bus 
* Bootiful Azure: Object Storage Service 
* Bootiful Azure: To Production
** Azure Application Insights
** Using the Azure Services in a Cloud Foundry Environment 

:sma: Spring for Microsoft Azure 
:msa: Microsoft Azure 
:gcp: Google Cloud Platform 

# Bootiful Azure: Taking Your First Steps with Microsoft Azure 

In this installment we're going to introduce the Spring for Microsoft Azure support, focusing on the interesting technologies that are enabled by the platform for consumption from Spring applications. As with all such discussions, I feel it worth raising a few key points. 

## Don't Run What You Can't Charge For 

I think we can all agree with the new but old axim, you shouldn't run software that you can't charge for. That is, the average organization has enough problems. Running commodity software like MySQL or Kafka shouldn't be among them. It'll cost infinitely less to pay someone to run that for you. Ideally, whoever ends up running that software should have a vested interest in running those things well. Most organizations have a mission that's far away from configuring SSL, or debugging MySQL replication issues. It is virtually always cheaper to let someone else do that for you.  

If you are trying to do something standard, then you should absolutely rely on standardized tools. Why use a cloud vendor's lockin-ware to run a Java or Node-based application when you could use something like Cloud Foundry or Kubernetes, for which the hiring pool is larger, the cost can be virtually nill, and the ease of use is on-par or better than whatever the cloud vendor is offering? 

This is a big part of the reason that Microsoft and Pivotal have such a great relationship.  Enterprise customers understand that for some workloads the public cloud is a foregone conclusion, but they don't want to be locked in. Optionality is valuable. We see this all the time; GCP's prices were for some workload-types cheaper than AWS's. Some public cloud vendors have availability zones in regions you might want to be in that others dont. Increasingly, organizations embark upon hybrid cloud or mutlicloud strategies, knowing that their workloads will vary, and their availability demands will vary. As much as possible, these organizations want to reduce the cost of operationalizing, securing, training-up and deploying to the varities of cloud infrastructure. 

Pivotal Cloud Foundry is a natural choice here; it allows organizations to deploy reliably to any of a number of cloud platforms and, as easily, and when necessary, drop down to platform-specific services. This fact is one of the reasons that Microsoft named Pivotal partner of the year in 2017 and 2018. We help drive resource usage on Microsoft Azure and we do so in as portable a way possible while still surfacing  the valuable and unique. 

## Data Gravity

That's not to say that some platforms don't offer compelling reasons to choose them over the others. All platforms have things that set them ahead of the herd in one way or another. These things necessarily have gravity; where anybody could run MySQL, very few can run a Google Cloud Spanner or a Microsoft CosmosDB. If you have a usecase for which different sorts of tools are a good solution then you should absolutely use them and Spring wants to make the work as easy as possible! Use CosmosDB if it is the solution you need. 

But, if the work you're trying to do is generic enough that it is untethered to a particular platform then you should absolutely work to consume it in as untethered a way as possible. Stay generic, whenever possible. Want to run a Java or Node.js process? Use a container orchestrator like Kubernetes. Or, if you like yourself, and you like production, use a platform-as-a-service like Heroku or Cloud Foundry whose unit of work are applications, not containers. These technologies let you think in terms of the application binary and deliver to production quickly. They're also both open-source and  have huge use bases and ecosystems, so they are the easiest solution _and_ the most powerful one. Using the generic tool is also the best choice, in this case, technically. It's dead simple to get started with tools like Cloud Foundry (just install and deploy the Pivotal Cloud Foundry service on Microsoft Azure) or Kubernetes on Microsoft Azure.

Databases are a particularly sticky proposition. If you invest in a proprietary database like Google's Spanner, you should expect that it'll be _very_ hard to move the data off of that platform if you should ever want to do that one day. Dave McCrory, former Riak CTO, talks about the idea of _data gravity_ - this notion that data pools inspire ecosystems of applications that feed into, and draw from, that pool. The more usefult hose applications the more people use them, and the more likely people are to the build on those applications. Salesforce is a great example of this. It's data is _sticky_. Salesforce, the CRM, is rich and extensive, but it's by no means the only full-featured CRM. What it does have, more than any competitive offering, is an ecosystem of partner integrations on which people have since become dependent. Thsoe partner integrations make Salesforce a Hotel California-style proposition: you can checkin but you can never leave. 


## Setup 

Let's take a look at Microsoft Azure itself. You'll need to login to the portal to obtain the relevant configuration keys for the various services we'll introduce in the course of this series. The portal is [something you should bookmark](http://portal.azure.com). Usually, when you look at  the relevant section of the portal for the services you're using, you'll see a section called "Keys" or "Configuration Keys," or something like that. It is unfortunately not 100% consistent across the services. 

In this series we'll focus on leveraging the platforms strengths - the things where Microsoft  can help us excel in ways only Microsoft can. You can achieve most of what we're going to introduce in this article using the `az` command-line tool - it's easy enough to install on multiple operating systems since it's written in Python. Consult [this link for more information](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli?view=azure-cli-latest) on how to install the Microsoft Azure CLI, `az`.
That's about it. As we've already established, there's no platform-wide notion of authentication. You authenticate per-service. This makes working with eaxch individual service straightforward, but it means that - holistically - you worry about authentication more often. 

Using the Spring support in your application is straightforward. You can, as you'd expect, start with [the Spring Initializr](http://start.Spring.io) and there just select `Azure Support`. Alternatively, you can add a Maven bill-of-materials (BOM) aritfact to your Spring Boot project's Maven or Gradle build manually. Here's how you'd do it for Maven.


```xml 

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.microsoft.azure</groupId>
                <artifactId>azure-spring-boot-bom</artifactId>
                <version>${azure.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

```

In my case, the Maven property `azure.version`, was set to `2.0.5`. 

## The Cloud Foundry Service Broker

There's [a Cloud Foundry service broker](https://pivotal.io/platform/services-marketplace/data-management/microsoft-azure) that you can use to quickly spin up various Microsoft Azure services and then bind them to your application. This service broker makes running applications on Cloud Foundry, especially on top of Microsoft Azure, the easiest path to production for applications targeting Microsoft Azure services.

There ya have it! You know everything you need to know to setup a Spring application to leverage services on Microsoft Azure.

# Bootiful Azure: SQL-based data access with Microsoft SQL Server  

Let's start with something short and sweet, the legendary Microsoft SQL Server. Microsoft SQL Server is an interesting beast. Sure, you can run it yourself, but wouldn't you rather Microsoft, who build the product itself, do that for you? Now _that_ is a full-service solution! Imagine how nice that is? You don't often see that sort of solution in other contexts. Imagine buying a car that you can drive as fast as you'd like, and on which the the manufacturer will guarantee any and all maintenance and repairs? Forever? Even if the car is hit with an asteroid? This is why running Microsoft SQL Server on Microsoft Azure is so appealing to me.

Now, don't get me wrong. There's plenty to recommend SQL Server in its own right. SQL Server has been around since 1989! It's routinely ranked up there with the likes of Oracle DB and PostgreSQL as being among the most feature-filled database options out there. It's been built over decades to serve enterprise use cases. It's even got things that other databases, including the venerable  PostgreSQL, sometimes lack, like Transparent Data Encryption wherein data is transparenly encrypted at rest. 

SQL Server has its origins as an enterprise-grade database that ran on one operating system.... OS/2! Wait, OS/2? Surely you mean Microsoft Windows? Not so much, as it happens. Yep, Microsoft joined Ashton-Tate and Sybase in the late 1980s to create a variant of Sybase SQL Server for IBM OS/2 (developed jointly with Microsoft at the time), which was released the following year. 

This was the first version of Microsoft SQL Server, and served as Microsoft's entry to the enterprise-level database market, competing against Oracle, IBM, and later, Sybase. SQL Server 6.0 was the first version designed for NT without direction from Sybase. 

Windows NT was released in July 1993 and Sybase and Microsoft took differing directions. Each pursued its own design and marketing schemes. Microsoft negotiated exclusive rights to all versions of SQL Server written for Microsoft operating systems. Sybase and Microsoft SQL Server are two very different things today, with radically different codebases. Now, Microsoft maintain several editions of SQL Server for different usecases and workloads. It's hard to qualify why Microsoft SQL Server is so amazing, so I'll instead refer you to this [pretty exhaustive Wikipedia page demonstrating](https://en.wikipedia.org/wiki/Comparison_of_relational_database_management_systems) different features acrosss various SQL database engines. Microsoft SQL Server fares pretty well! 

## Configuring SQL Server on Microsoft Azure 

* source the configuration values 
* install the sample database 

## Introducing SQL Server into your Spring Application 

Now that we've got a freshly confiugured SQL Server instrance up and running we need only use it like we would any other JDBC dependency in our codde. If you're using the Spring Initialzir you can select `SQL Server` and the appropriate dependency will be added to your Maven or Gradle build. Or, you can add it manually to your build using the following coordinates: `com.microsoft.sqlserver` : `mssql-jdbc`. You don't need to specify the version; that's done for you by Spring Boot itself. This particular dependency and itnegration with Microosft technologies doesn't even require  a particular Maven bill-of-materials dependency - it just works. 

Then, you'll need to specify the usual confiuguration properites so that Spring can instnantatie a connection tot he `DataSource` for you. 

```properties
#sql-server-pw=SOURCE THIS FROM AN ENVIRONMENT VARIABLE
spring.datasource.password=${sql-server-pw}
spring.datasource.url=jdbc:sqlserver://host:1433;database=bootiful-adventures;user=bootiful-adventures-admin@bootiful-adventures;password=${sql-server-pw};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=bootiful-adventures-admin
```

Now it's trivial to use the resulting connection using any technology that supports both JDBC, gneerally, and Microsoft SQL Server, specifically. 

```java
package com.example.bootifulazure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
class SqlServerDemo {

    private final JdbcTemplate jdbcTemplate;

    SqlServerDemo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void demo() {
        String query = "select TOP 5  * from SalesLT.Customer ";
        RowMapper<Customer> rowMapper =
            (rs, rowNum) -> new Customer(rs.getLong("customerid"), rs.getString("firstname"), rs.getString("lastname"));
        List<Customer> customerList = this.jdbcTemplate.query(query, rowMapper);
        customerList.forEach(log::info);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Customer {
        private Long id;
        private String firstName, lastName;
    }
}

```


# Bootiful Azure: Global Scale Data Access with CosmosDB 

I can hear you thinking - yes, not even your feintest thoughts escape me! - that you like Microsoft SQL Server, but it's definitely something you could've run yourself, on any platform. To which I say, "yep!" It sure is nice that Microosft runs it for us, though, isn't it?"  

But I concede the point. What can Azure do for you? You don't need to look much further than  Microsoft Azure CosmosDB. CosmosDB is actually an umbrella name - it describes a single product that can be used in mutliple ways. It's a single, multi-model, multi-modal database that supports  document data, SQL queries, graph data access, and more. 

Per [the product web page](https://azure.microsoft.com/en-us/services/cosmos-db/): CosmosDB was bult from the ground up iwth global didstibution and h9orizontal scale at its core. guarantees single-digit-millisecond read and write latencies at the 99th percentile, and guarantees 99.999 high availability with multi-homing anywhere in the world—all backed by industry-leading, comprehensive service level agreements (SLAs).

## Items and Containers 

Internally, CosmosDB stores "items" in "containers." But you don't necessarily deal with items or containers as the concepts will surfaced in the language oft he data model you're using to consume the data. If you're using it as a document store, like MongoDB, then items would be mapped to documents in collections, for example.

Containers are grouped into databases, which are a sort of namespace above containers. Containers enforce unique key constraints to ensure integrity of the data. But containers do so much more. You can ask each container for a feed of what's changed; you could power Change Data Capture (CDC) schemes using this feed.  You could use the feed for eventsourcing. The feed is itself persisted so you can _replay_ changes, if you like. 

You can specify time-to-live (TTL) values for the containers, as well, letting CosmosDB automatically expunge existing records after a certain period. You could also override the TTL for speciifc items, too.  

## A Mutli-Model, Multi-Paradigm Datastore 

First of all, CosmosDB is schemaless. Keep that mind when using it - it can have some important ramifications if you're not prepared.  

CosmosDB supports a multi-model, multi-paradigm approach to building applications. Clients can talk to the HTTP REST API and drive it using a SQL-like language for queries. You can create, update and delete containers  using the SQL API, too. 

You can talk to CosmosDB using the MongDB API, supporting collections as xontrainer and documents as items. 

You can talk to it using the Gremlin API supporting graphis ad container sand nodes and edfges as items. According to the Gremlin website, "Gremlin is the graph traversal language of Apache TinkerPop. Gremlin is a functional, data-flow language that enables users to succinctly express complex traversals on (or queries of) their application's property graph." So,  basically a way to traverse data in a graph. 

You can talk to CosmosDB using the Cassandra API supporting tables as containers and rows as items. The Cassandra API even supports the Cassandra query language (CQL). 

You can _also_ talk to it using the AZure Table Storage API supporting tables as containers and items as... well.. _items_.

CosmosDB also embeds a JavaScript engine so you can use JavaScript to define triggers, user-defined functions that can be called from, and augment, the SQL query language, and stored procedures. Stored procedures can manage a number of  actions in a single ACID-compliant transaction.  



## Configuring CosmosDB  on Microsoft Azure 

* source the configuration values 
* install the sample database 

## Introducing CosmosDB into your Spring Application 

Alrighty - with all that established let's look at its use in a Spring application. You _could_, in theory, talk to it through the appropriate abstractions for the aforementioned technologies like MongoDB and Cassandra, but I prefer to use the Spring Data CosmosDB abstraction. CosmosDB was historically called Microosft DocumentDB. So, for historical reasons, we need to use the Maven dependency that references that old project name. Add the relevant starter dependency, `com.microsoft.azure`:`azure-documentdb-spring-boot-starter`, to your build file. 


Then we'll have to configure the relevant connection in our properties files.


```java
azure.documentdb.database=DB_NAME
azure.documentdb.uri=https://URI.documents.azure.com:443/
azure.documentdb.key=KEY
```

Replace the property values with the relevant and appropriate string values.

Now we must define an object class to map it to our record. This will be an entity. We'll use the Spring Data DocumentDB mapping annotations.


```java
package com.example.bootifulazure;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservations")
class Reservation {
    @Id
    private String id;
    private String name;
}
```

Mostly, this looks like any other Lombok-annotated POJO you've ever seen. Of particular note, of course, is that the entity uses `@Document` from the Spring Data CosmosDB namespace. In it, we specify the `reservations` collection. 

Thd `Id` annotation is standard Spring Data. Note, however, that it's good practice to use a `String` - monotonically incrementing primary keys aren't a great idea in planet-scale distributed systems. 

Now, we need to flesh out the Spring Data repository to support our entity.

```java  
package com.example.bootifulazure;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;

interface ReservationRepository extends DocumentDbRepository<Reservation, String> {
}
```

Simple enough! We're using a `DocumentDbRepository`, but otherwise everything is as you'd expect it if you've ever used Spring Data. Using it is straightforward from this point. We'll use exercise some of the usual behavior in the repository definition in an event listener.  

```java
package com.example.bootifulazure;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Log4j2
@Component
class CosmosDbDemo {

    private final ReservationRepository rr;

    CosmosDbDemo(ReservationRepository rr) {
        this.rr = rr;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void demo() throws Exception {

        this.rr.deleteAll();

        Stream.of("A", "B", "C")
            .map(name -> new Reservation(null, name))
            .map(this.rr::save)
            .forEach(log::info);

    }
}

```

# Bootiful Azure: Integration with Azure Service Bus

Azure Service Bus is a cloud messaging as a service and integration technology. It is, just like CosmosDB, as flexible as possible. It [supports the AMQP 1.0 protocol](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-jms-api-amqp), like RabbitMQ. AMQP is a flexible wire protocol. The protocol itself includes instructions for administering the broker, beyond just interacting with it. AMQP brokers are ideal for integration because they are language- and platform-agnostic.  In an AMQP broker producers send messages to _exchanges_ which then route the messages to _queues_, from which consumers then read the messages. The exchange is responsible for deciding to which queue the message should be sent. It does this in any of a number of ways but it usually involves looking at a key in the message headers called the _routing key_. 

This indirection between the exhcange and the queues makes AMQP a bit more flexible than JMS-based brokers where producers  send messages  directly to `Destination` objects  that consumers then read from. This means that producers and consumers are coupled by their  choice of `Destination`. Additionally,  JMS is an API for the JVM, it is  not a wire protocol. As  such, producers and consumers are dependent on the version  of  the library they're using being correct. That said, [you can also use Azure Service Bus through the JMS API](https://docs.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-jms-api-amqp), if you want, as well. 

Like I said, Azure Service Bus is nothing if not flexible! 

The AMQP model is illustrative because, basically, the native model for Azure Service Bus looks like AMQP. In Azure Service Bus you have topics or queues to which you send messages. Messages are then connected to subscriptions, from which consumers read. Let's build a simple example that sends and then consumes messages. We won't use AMQP or JMS, just the regular Microsoft Azure ServiceBus API. 


## Configuring Azure Service Bus on Microsoft Azure 

... 

## Introducing Azure Service Bus into your Spring Application 

Add the following dependency to your build: `com.microsoft.azure` : `azure-servicebus-spring-boot-starter`.

We'll write two components: one a producer and the other a consumer. Naturally, in  a real application these things would naturally live in separate applications and separate processes since messaging serves to support the integration of applications. We'll look at the consumer first.  The consumer needs to register a subscriber _before_ something else has produced the message, so we'll make these beans _ordered_ - the Spring container will order their initialization one before the other based on the `Ordered` value we give it. 

```java
package com.example.bootifulazure;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.ISubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Log4j2
@Component
class ServiceBusConsumer implements Ordered {

    private final ISubscriptionClient iSubscriptionClient;

    ServiceBusConsumer(ISubscriptionClient isc) {
        this.iSubscriptionClient = isc;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consume() throws Exception {

        this.iSubscriptionClient.registerMessageHandler(new IMessageHandler() {

            @Override
            public CompletableFuture<Void> onMessageAsync(IMessage message) {
                log.info("received message " + new String(message.getBody()) + " with body ID " + message.getMessageId());
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void notifyException(Throwable exception, ExceptionPhase phase) {
                log.error("eeks!", exception);
            }
        });

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
```

When a message arrives, we log its `body` and `messageId` . 

Now, let's look at  the producer. 

```java
package com.example.bootifulazure;

import com.microsoft.azure.servicebus.ITopicClient;
import com.microsoft.azure.servicebus.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Log4j2
@Component
class ServiceBusProducer implements Ordered {

    private final ITopicClient iTopicClient;

    ServiceBusProducer(ITopicClient iTopicClient) {
        this.iTopicClient = iTopicClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() throws Exception {
        this.iTopicClient.send(new Message("Hello @ " + Instant.now().toString()));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
```

Pretty straightforward right? We send a messasge, and we get a message. If you've ever done any work with messaging technoogies you might find the lack of mention of any sort of _destination_ - the topic or queue name - a bit puzzling. Remember that this all lives in the properties (such as those in your `application.properties` file) and is used when auto-configuring the `ITopicClient` and `ISubscriptionClient`. If you want to send messages or consume messages from multiple destinations, simply define the relevant beans yourself and make sure to _not_ specify `azure.service-bus.connection-string` in your application's properties, otherwise the default Spring Boot autoconfiguration will kick in and try to create these beans for you. 

# Bootiful Azure: Object Storage Service 

Now let's turn to something a bit more... mundane. Something that you, ideally, won't even think about all that often. Applications often have storage requirements: they may need to store uploaded user content (binary data like pictures or documents), generated artifacts like PDF fils, videos, music, etc. They might want to store logs. It's not hard to think of things an application might want to durably store.  

These applications could use a filesystem, such as that on the local machine or a network attached filesystem like [NFS (network file system)](https://en.wikipedia.org/wiki/Network_File_System). I'll use NFS to generically refer to any network attached file system like Samba, NFS itself, or legacy options like DAC, FAL, etc. NFS options provide a filesystem-like interface to files, often in a hierarchical format. I say "like" because not all filesystems are the same. Some support more nuanced permissions models than others. Some support encheoding and replication of metatadata attached to files and directories in the tree. Support support different speed guarantees for different operations; some filesystems might optimize for reads versus writes. Some might optimize for directory traversals. The client's perspective of a file read or write is different depending on the client used. Is a write consistent on all replication nodes immediately? Finally, what if the client doesn't speak POSIX? What if it can only speak HTTP? Or if it wants to speak Bittorrent for more efficient consolidation of downloads through peer-to-peer networks? 

For these reasons, and more, Amazon Web Services introduced S3, the Simple Cloud Storage Service (get it? "S" times 3? "S3"?) which has since been something of a prevailing standard that all other cloud vendors need to support. For Microsoft Azure, the Object Storage Service (OSS) is the thing that provides an S3-like experience. You can use its API directly, as we will here, but it's [also possible to use the S3Proxy to proxy writes to OSS](https://www.microsoft.com/developerblog/2016/05/22/access-azure-blob-storage-from-your-apps-using-s3-api/) using an AWS S3 client, of which there are countless! Microsoft Azure isn't playing catchup though. Furthest thing from it! They even offer a standalone browser called the Microsoft Azure Storage Explorer which runs, yep, on Linux, Macintosh and Windows. That standalone browser lets you interrogate OSS stores _as well as_ CosmosDB data. How's that for convenient? You can of course using the `az` CLI or the API itself. We're going to use the Java API and abstraction in terms of Spring. 


## Configuring Azure Object Storage Service

* you need to create a bucket and upload a file called `cat.jpg` 
* you need to get the configuration values for `application.properties` 

## Introducing Azure Object Storage Service into your Spring Application  

Add `com.microsoft.azure`: `azure-storage-spring-boot-starter` to your application's build file. Make sure you've specified the OSS connection string for the `azure.storage.connection-string` property. 

We're going to read the bytes for an image of a cat in our application's `src/main/resources` directory and then write those bytes to the Object Storage Service as a "block blog". There are other interfaces through which you can talk to OSS, but for our purposes it's very natural to think about it as an ensemble of "containers" (logical groupings of things, almost like a directory)  and "blobs." A blob is a file, basically, with a name and metadata associated with it. So, all that the following example does is store the bytes for a cat into a container in Microsoft Azure called `files` under a random name prefixed with `cat-` and suffixed with `.jpg`. That's it! 

```java
package com.example.bootifulazure;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.UUID;

@Log4j2
@Component
class ObjectStorageServiceDemo {

    private final CloudStorageAccount cloudStorageAccount;
    private final Resource resource;
    private final CloudBlobContainer files;

    ObjectStorageServiceDemo(
        CloudStorageAccount csa,
        @Value("classpath:/cat.jpg") Resource cat) throws URISyntaxException, StorageException {
        this.resource = cat;
        this.cloudStorageAccount = csa;
        this.files = this.cloudStorageAccount
            .createCloudBlobClient()
            .getContainerReference("files");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void demo() throws Exception {
        CloudBlockBlob blockBlobReference = this.files.getBlockBlobReference("cat-" + UUID.randomUUID().toString() + ".jpg");
        try (InputStream in = this.resource.getInputStream()) {
            blockBlobReference.upload(in, this.resource.contentLength());
            log.info("uploaded blockblob to " + blockBlobReference.getStorageUri());
        }

    }
}
```

The Microsoft Azure-specific bits are less than trivial. We obtain a reference to a container and then write to it and then log out the addressable URI of the resource. It is, if nothing else, _mundane_! And that's exactly what you want in a computing system primitive like a filesystem. It should be _mundane_. To be very honest, I was more pleased with getting to use Java 7's try-with-resources syntax for the `Autocloseable` `InputStream` reference!  


# To Production! 

This last post is really a quick wrapup post that looks at things to keep in mind when deploying an application built with Spring Boot and Micosoft Azure to production. 

## Secure Configuration

We've developed the application with ease and aplomb from the comfort of our local machines, plugging in the relevant confuguration values as we need them. Trouble is, these are often very sensitie values tht shouldn't be left laying around on the filesystem at rest, unencrypted. There are a _number_ of good solutions for this. You could of course deploy the Spring Cloud Config Service itself. If you're running [Pivotal Cloud Foundry on Microsoft Azure (or otherwise)](https://pivotal.io/partners/microsoft), this is the recommended way because it's a one-liner to get it deployed and working. You could of course deploy [Hashicorp Vault and use Spring Cloud Vault](https://www.hashicorp.com/resources/introduction-to-using-hashicorp-vault-with-azure). Or, you could store the keys and values in Microsoft's [own Key Vault service](https://azure.microsoft.com/en-us/services/key-vault/). In order to effectively use Key Vault you'll need to get into configuring Microsoft Active Directory which, while not something I'd wish on you or your loved ones, dear reader, is something in which I'm quite interested. 

## Microsoft Active Directory 

In my 20+ years of helping organizations build software I've seen nary a handful that _weren't_ using Microsoft Active Directory_ even if only from the LDAP interface. I hope Google gains more traction but let's be very clear: Active Directory is _the_ prevailing standard in enterprise IT. It is a way of life, even if you and I probably don't have to worry about it all that often in our idealized world of application development. And for good reason! It integrates the entire Windows desktop experience for the business professional. It's the beating heart of the Office365 story and it's the way organization organizes and self structures itself. Want to know if you got that promotion? Check Active Directory! Want to know where someone is seated? Check Active Directory! Want to force password resets? Maintain enterprise-wide audit logs? Fire someone? Check Active Directory! You may think your organizations runs Active Directory but lets be clear: it runs your organization. 

Active Directory is a directoy server. It provides a tree of users, organizations and more. It acts as  identity manager for technologies like Microsoft CRM, Microsoft SQL Server, Microsoft Office and even Microsoft Windows itself. You can describe users, their rights and roles, and so much more in Active Directory. Which brings us back around. Microsoft Azure runs Active Directory for you! You can import and configure all the relevant information for your Microsoft Active Directory install right from the platform. [There's even a Spring Boot starter to connect Microsoft Azure to your OAuth-delegating Spring Boot- and Spring Security-powered applications](https://azure.microsoft.com/en-us/blog/spring-security-azure-ad/). Could you deploy and manage something like Microsoft SQL Server or Microsoft Active Directory yourself? Sure. But, _should you_?

## Application Insights 

As you scale out and spin up more microservices you'll introduce more and more moving parts and it becomes all the more critical to be able to observe the movement of data from one node to another in the system.  Here, the Microsoft Application Insights integration for Spring applications - which is for the moment at least delivered separate from the main Spring integration for Microsoft Azure - makes using it an cinch! Add `com.microsoft.azure`: `applicationinsights-spring-boot-starter` : `1.1.0-BETA`  to your build file. 

You'll then need to specify an `azure.application-insights.instrumentation-key` and to give your application a  `spring.application.name` name. Which, to be fair, you should do anyway. That's it! Restart your application, drive some traffic through an HTTP endpoint and then login to the Microsoft Application Insights dashboard and watch the instrumentation in action! 

// TODO obtaining the key 
// TODO screenshot of APp Insights

## Cloud Foundry 

I'm all for using something like Microsoft Azure to simplify the work of standing up infrastructure where Microsoft can provided a differentiated experience. But running a Java process or a Node.js process? Stick to de-facto standard infrastructure like    Cloud Foundry or Kubernetes. It's fairly trivial to get Cloud Foundry deployed on top of Microsoft Azure and, once deployed, it's trivial to deploy Spring Boot applications there. It's often as simple as `cf push -p my.jar`. In the world of Cloud Foundry a _service broker_  is an HTTP API with which the platform interacts to manage the provisoning and lifecycle of... _something_. It could be a service  like MySQL or Apache Kafka. It could be an Active Directory  installation. It could be any of a number of things. The Microsoft and Pivotal teams have worked hand-in-hand to ensure that the service broker options for users running Pivotal Cloud Foundry on Microsoft Azure support [the tentpole services in a convenient way](https://docs.pivotal.io/partners/azure-sb/index.html). This list includes Microsoft Azure services like Azure Storage, Azure Redis Cache, Azure Service Bus, Azure Event Hubs, Azure SQL Databases (SQL Server, PostgreSQL and MySQL) and failover groups, and Azure CosmosDB.  

You can inspect the Cloud Foundry service catalog by issuing a `cf marketplace` command on the CLI. It'll show you all the relevant services  and you can then choose to provision an instance of the service and its associated plan.  A plan describes the particular levels of service you can expect from a service broker  resource and is naturally specific to each service broker on offer. 

Let's say you have a Spring Boot application deployed to Cloud Foundry, given the logical name `myapp`:

```shell
> cf push -p my.jar --no-start myapp 
```


Suppose you wanted to provision an instance of Microsoft SQL Server for your application, and you wanted to be able to reference that storage, logically, as `mydb`. Your session might look like this:

```shell
> cf create-service azure-sqldb basic mydb -c '{"sqlServerName": "Bootiful"}'
```

The service would soon be provisioned and usable from within an application. You'd just need to bind the service to your application, so that the relevant connectivity information could be injected into the application's environment as an environment variable.

```shell
cf bind-service myapp mydb
cf restart myapp
```

Now your running Spring Boot application would have an environment variable, `VCAP_SERVICES`, whose contents would include the connection information for the just-provisioned service. It'd look something like this:


```json
"VCAP_SERVICES": {
  "azure-sqldb": [
    {
      "credentials": {
        "sqldbName": "fake-database",
        "sqlServerName": "fake-server",
        "sqlServerFullyQualifiedDomainName": "fake-server.database.windows.net",
        "databaseLogin": "ulrich",
        "databaseLoginPassword": "u1r8chP@ss",
        "jdbcUrl": "jdbc:sqlserver://fake-server.database.windows.net:1433;database=fake-database;user=fake-admin;password=fake-password;Encrypt=true;TrustServerCertificate=false;HostNameInCertificate=*.database.windows.net;loginTimeout=30",
        "jdbcUrlForAuditingEnabled": "jdbc:sqlserver://fake-server.database.secure.windows.net:1433;database=fake-database;user=fake-admin;password=fake-password;Encrypt=true;TrustServerCertificate=false;HostNameInCertificate=*.database.secure.windows.net;loginTimeout=30",
        "hostname": "fake-server.database.windows.net",
        "port": 1433,
        "name": "fake-database",
        "username": "ulrich",
        "password": "u1r8chP@ss",
        "uri": "mssql://ulrich:u1r8chP@ss@fake-server.database.windows.net:1433/fake-database?encrypt=true&TrustServerCertificate=false&HostNameInCertificate=*.database.windows.net"
      }
    }
  ]
}
```

In Spring Boot, you could reference these properties using a flattened  property access  syntax, e.g.: `vcap.services.mydb.credentials.jdbcUrl`. A common pattern here is to run applications  in the cloud with a Spring profile active. Say, `cloud`? That way you could put a config file  in your code under `application-cloud.properties` and that property file would be loaded when the application starts up in Cloud Foundry. You could put default, local configuration in `application-default.properties`. So, when Spring Boot starts with no profile specified it'll load the configuration in `application-default.properties`. When running in Cloud Foundry on Azure  it'd  load the configuration in `application-cloud.properties`. You could thus add the following to your `application-default.properties` file.

```properties
spring.datasource.url=${vcap.services.mydb.credentials.jdbcUrl}
```

Bootiful! 

## The Next Steps 

We've only just begun to scratch the surface of what's possible with  Spring, Microsoft Azure and Cloud Foundry in this and the posts before it. What should be clear is that there's a  nice symbiosis here, each technology making the layer below it even more powerful. It's no wonder that a ton of Azure's  workloads are Linux,  Spring Boot and Cloud Foundry-based: these things work well together.  

 


