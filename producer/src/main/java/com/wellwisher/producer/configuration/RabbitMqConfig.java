package com.wellwisher.producer.configuration;

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
public class RabbitMqConfig {
	
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
	public Binding broadcastBinding(Queue broadcastQueue, TopicExchange boradcastExchange)
	{
		return BindingBuilder.bind(broadcastQueue).to(boradcastExchange).with(broadcastQueueName);
	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() 
	{
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

