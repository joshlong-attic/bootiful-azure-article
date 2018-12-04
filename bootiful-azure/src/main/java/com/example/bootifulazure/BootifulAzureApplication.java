package com.example.bootifulazure;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@SpringBootApplication
public class BootifulAzureApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootifulAzureApplication.class, args);
	}
}

@Component
@Log4j2
class ServiceBusDemo {

	private final ITopicClient iTopicClient;
	private final ISubscriptionClient iSubscriptionClient;

	ServiceBusDemo(ITopicClient iTopicClient, ISubscriptionClient iSubscriptionClient) {
		this.iTopicClient = iTopicClient;
		this.iSubscriptionClient = iSubscriptionClient;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void demo() throws Exception {

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

		Thread.sleep(1000);

		this.iTopicClient.send(new Message("Hello @ " + Instant.now().toString()));

	}
}

@Component
@Log4j2
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


@RestController
class GreetingsRestController {

	@GetMapping("/hi")
	String hello() {
		return "Hello " + Instant.now().toString();
	}
}

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

interface ReservationRepository extends DocumentDbRepository<Reservation, String> {
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservations")
class Reservation {
	@Id
	private String id;
	private String name;
}

@Component
@Log4j2
class SqlServerDemo {

	private final JdbcTemplate jdbcTemplate;

	SqlServerDemo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void demo() throws Exception {
		String query = "select TOP 5  * from SalesLT.Customer ";
		RowMapper<Customer> rowMapper =
			(rs, rowNum) -> new Customer(rs.getLong("customerid"), rs.getString("firstname"), rs.getString("lastname"));
		List<Customer> customerList = this.jdbcTemplate.query(query, rowMapper);
		customerList.forEach(log::info);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Customer {
		private Long id;
		private String firstName, lastName;
	}
}

