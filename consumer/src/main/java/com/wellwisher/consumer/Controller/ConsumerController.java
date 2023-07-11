package com.wellwisher.consumer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer/api/v1")
public class ConsumerController {

	@GetMapping(value = "/status")
	public ResponseEntity<String> getStatus() {
		return ResponseEntity.ok("Consumer Operational");
	}
}
