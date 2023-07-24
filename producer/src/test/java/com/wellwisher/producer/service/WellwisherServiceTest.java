package com.wellwisher.producer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;

import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.PeopleRequest;
import com.wellwisher.producer.repository.WellwisherDAO;

@SpringRabbitTest
@ExtendWith(MockitoExtension.class)
class WellwisherServiceTest {

	@Mock
	WellwisherDAO wellwisherDAO;
	
	@InjectMocks
	WellwisherService wellwisherService;
	
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
	
	@DisplayName("return people successfully")
	@Test
	void subscribeSuccessfully()
	{	
		PeopleEntity peopleEntity = new PeopleEntity(people);
	    when(wellwisherDAO.save(peopleEntity)).thenReturn(peopleEntity);
	    String obj = wellwisherService.subscribe(people).getBody();
	    assertTrue(true);
	}
}
