package com.wellwisher.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.service.WellwisherService;

@RestController
@RequestMapping(value = "api/v1")
public class WellwisherController{
	
	@Autowired
	WellwisherService wellwisherService;
	
	@PostMapping(value = "/subscribe")
	public People subscribe(@RequestBody People people)
	{
		return wellwisherService.subscribe(people);
	}
}
