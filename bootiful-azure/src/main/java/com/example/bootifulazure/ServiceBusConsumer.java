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
//@Component
class ServiceBusConsumer implements Ordered {

	private final ISubscriptionClient iSubscriptionClient;

	ServiceBusConsumer(ISubscriptionClient iSubscriptionClient) {
		this.iSubscriptionClient = iSubscriptionClient;
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

	// we want this to start up before the Producer
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
