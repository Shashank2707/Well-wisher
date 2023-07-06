package com.wellwisher.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Value("${spring.rabbitmq.queue}")
	private String queueName;
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	 @Bean
	 public Queue birthdayQueue() 
	 {
	    return new Queue(queueName);
	 }

	 @Bean
	 public TopicExchange birthdayExchange() 
	 {
	        return new TopicExchange(exchange);
	 }

	 @Bean
	 public Binding binding(Queue queue, TopicExchange exchange) 
	 {
	    return BindingBuilder.bind(queue).to(exchange).with(queueName);
	 }
	 
	 @Bean
	 public Jackson2JsonMessageConverter messageConverter() 
	 {
	    return new Jackson2JsonMessageConverter();
	 }
}
