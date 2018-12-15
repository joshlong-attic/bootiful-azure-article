package com.example.bootifulazure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
class GreetingsRestController {

	@GetMapping("/hi")
	String hello() {
		return "Hello " + Instant.now().toString();
	}
}
