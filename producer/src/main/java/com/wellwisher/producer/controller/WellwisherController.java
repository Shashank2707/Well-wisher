package com.wellwisher.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.service.WellwisherService;

@RestController
@RequestMapping(value = "producer/api/v1")
public class WellwisherController{
	
	@Autowired
	WellwisherService wellwisherService;
	
	@GetMapping(value = "/status")
	public ResponseEntity<String> getStatus() {
		return ResponseEntity.ok("Producer Operational");
	}
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody PeopleRequest people)
	{
		return wellwisherService.subscribe(people);
	}
}
