package com.wellwisher.producer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${spring.rabbitmq.queue}")
	private String queueName;
	@Value("${spring.rabbitmq.exchange}")
	private String exchangeName;
	
	@Value("${spring.rabbitmq.broadcastQueue}")
	private String broadcastQueueName;
	@Value("${spring.rabbitmq.broadcastExchange}")
	private String broadcastExchangeName;
	
	@Bean
	public Queue queue()
	{
		return new Queue(queueName);
	}
	
	@Bean
	public TopicExchange exchange()
	{
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
	
	@Bean
	public Queue broadcastQueue()
	{
		return new Queue(broadcastQueueName);
	}
	
	@Bean
	public TopicExchange boradcastExchange()
	{
		return new TopicExchange(broadcastExchangeName);
	}
	
	@Bean
	public Binding broadcastBinding(Queue queue, TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with(broadcastQueueName);
	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() 
	{
	   return new Jackson2JsonMessageConverter();
	}
}

