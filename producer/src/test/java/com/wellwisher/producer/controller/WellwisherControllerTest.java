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

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.service.WellwisherService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WellwisherControllerTest {

	@Mock
	WellwisherService wellwisherService;
	
	@InjectMocks
	WellwisherController wellwisherController;
	
	People people = new People();
	
	@BeforeEach()
	void setUp()
	{
		people.setName("shashank");
		people.setNickName("shan");
		LocalDate date = LocalDate.of(1999, 07, 27);
		people.setDate(date);
		people.setEmail("kingbreath2707@gmail.com");
		people.setOccasion("birthday");
		people.setSubscription(true);
		
	}
	
	@DisplayName("return people detail successfully")
	@Test
	void subscribeSuccessfully()
	{
	    Mockito.when(wellwisherService.subscribe(people)).thenReturn(people);
	    People obj = wellwisherController.subscribe(people);
	    assertEquals(obj,people);
	}
	
}
