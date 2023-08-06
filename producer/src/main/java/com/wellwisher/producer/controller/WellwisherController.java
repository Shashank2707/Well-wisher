package com.wellwisher.producer.controller;

import static com.wellwisher.producer.constants.Constants.PRODUCER_STATUS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return ResponseEntity.ok(PRODUCER_STATUS);
	}

	@CrossOrigin
	@PostMapping(value = "/subscribe")
	public ResponseEntity<String> subscribe(@RequestBody PeopleRequest people)
	{
		 return wellwisherService.subscribe(people);
	}

	@CrossOrigin
	@GetMapping(value = "/unsubscribe")
	public ResponseEntity<String> unsubscribe(@RequestParam(name="email") String email)
	{
		return wellwisherService.unsubscribe(email);
	}
	
	@GetMapping(value = "/getAllUser")
	public ResponseEntity<List<PeopleRequest>> getAllUser(@RequestParam(name = "otp", defaultValue = "000000") String otp)
	{
		return wellwisherService.getAllUser(otp);
	}	
}
