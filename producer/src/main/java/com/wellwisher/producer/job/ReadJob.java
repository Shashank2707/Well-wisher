package com.wellwisher.producer.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.pojo.PeopleDTO;
import com.wellwisher.producer.service.WellwisherService;

@Component
public class ReadJob implements Job {
	
	@Autowired 	
	WellwisherService wellwisherService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<People> birthdayPeople = wellwisherService.get();
		for(var people : birthdayPeople)
		{
			PeopleDTO peopleDto = new PeopleDTO(people);
			rabbitTemplate.convertAndSend("birthday-exchange","birthday-queue",peopleDto);
		}
	}
}
