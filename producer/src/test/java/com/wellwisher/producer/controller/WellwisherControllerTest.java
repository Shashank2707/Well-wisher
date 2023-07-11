package com.wellwisher.producer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.service.WellwisherService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WellwisherControllerTest {

	@Mock
	WellwisherService wellwisherService;
	
	@InjectMocks
	WellwisherController wellwisherController;
	
	PeopleRequest people = new PeopleRequest();
	
	@BeforeEach()
	void setUp()
	{
		people.setName("shashank");
		LocalDate date = LocalDate.of(1999, 07, 27);
		people.setOccasionDate(date);
		people.setEmail("kingbreath2707@gmail.com");
		people.setOccasion("birthday");
		
	}
	
	@DisplayName("return people detail successfully")
	@Test
	void subscribeSuccessfully()
	{
	    Mockito.when(wellwisherService.subscribe(people)).thenReturn(ResponseEntity.ok("Saved"));
	    ResponseEntity<String> obj = wellwisherController.subscribe(people);
	    assertEquals("Saved", obj.getBody());
	}
	
}
