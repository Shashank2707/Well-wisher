package com.wellwisher.producer.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wellwisher.producer.entity.BroadcastEntity;
import com.wellwisher.producer.entity.PeopleEntity;
import com.wellwisher.producer.pojo.BroadcastDTO;
import com.wellwisher.producer.pojo.PeopleDTO;
import com.wellwisher.producer.service.WellwisherService;

@Component
public class ReadJob implements Job {
	
	@Autowired 	
	WellwisherService wellwisherService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.exchange}")
	String exchange;
	
	@Value("${spring.rabbitmq.queue}")
	String queueName;
	
	@Value("${spring.rabbitmq.broadcastExchange}")
	String broadcastExchange;
	
	@Value("${spring.rabbitmq.broadcastQueue}")
	String broadcastQueueName;
	
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<PeopleEntity> peoples = wellwisherService.getByDate();
		for(var people : peoples) {
			if (people.isSubscription())
				rabbitTemplate.convertAndSend(exchange, queueName, new PeopleDTO(people));
		}
		
		List<BroadcastEntity> occasions = wellwisherService.getBroadcastOccasion();
		Set<String> emails = new HashSet<>();
		peoples = wellwisherService.getAllPeoples();
		List<PeopleEntity> uniquePeoples = new ArrayList<>();
		for (var people: peoples) {
			if(!emails.contains(people.getEmail())) {
				emails.add(people.getEmail());
				uniquePeoples.add(people);
			}
		}
		for (var occasion: occasions) {
			for(var people : uniquePeoples) {
				if (people.isSubscription())
					rabbitTemplate.convertAndSend(broadcastExchange, broadcastQueueName, new BroadcastDTO(occasion, people));
			}
		}
	}
}
