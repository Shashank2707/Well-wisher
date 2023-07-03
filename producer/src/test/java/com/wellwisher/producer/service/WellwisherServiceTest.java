package com.wellwisher.producer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.repository.WellwisherDAO;

@SpringRabbitTest
@ExtendWith(MockitoExtension.class)
public class WellwisherServiceTest {

	@Mock
	WellwisherDAO wellwisherDAO;
	
	@InjectMocks
	WellwisherService wellwisherService;
	
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
	
	@DisplayName("retur people successfully")
	@Test
	void subscribeSuccessfully()
	{
		when(wellwisherDAO.save(people)).thenReturn(people);
	    People obj = wellwisherService.subscribe(people);
	    verify(wellwisherDAO).save(people);
	    assertEquals(obj,people);
	}
}
