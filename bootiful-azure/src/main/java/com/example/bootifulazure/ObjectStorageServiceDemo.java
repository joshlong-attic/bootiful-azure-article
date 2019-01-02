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

