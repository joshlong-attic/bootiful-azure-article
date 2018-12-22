package com.example.bootifulazure;
/*
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Log4j2
//@Component
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
*/