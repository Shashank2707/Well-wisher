package com.wellwisher.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.virtualhost}")
    private String virtualHost;

	@Value("${spring.rabbitmq.queue}")
	private String queueName;
	@Value("${spring.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${spring.rabbitmq.broadcastQueue}")
	private String broadcastQueueName;
	@Value("${spring.rabbitmq.broadcastExchange}")
	private String broadcastExchangeName;
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);


	@Bean
	public Queue getQueue() {
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ username);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ password);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ host);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ virtualHost);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ queueName);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ exchange);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ broadcastQueueName);
		logger.info("~~~~~~~~~~~~~~~~~~~~~~>>>>>>>"+ broadcastExchangeName);
		return new Queue(queueName);
	}

	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Binding getBinding(Queue getQueue, TopicExchange getExchange) {
		return BindingBuilder.bind(getQueue).to(getExchange).with(queueName);
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
	public Binding broadcastBinding(Queue broadcastQueue, TopicExchange boradcastExchange)
	{
		return BindingBuilder.bind(broadcastQueue).to(boradcastExchange).with(broadcastQueueName);
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
