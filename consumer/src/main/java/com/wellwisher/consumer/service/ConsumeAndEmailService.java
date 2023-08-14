package com.wellwisher.consumer.service;

import static com.wellwisher.consumer.constant.Constants.SOMETHING_WENT_WRONG;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.wellwisher.consumer.exception.InternalServerErrorException;
import com.wellwisher.consumer.pojo.BroadcastDTO;
import com.wellwisher.consumer.pojo.People;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service
public class ConsumeAndEmailService {

	private static final Logger logger = LoggerFactory.getLogger(ConsumeAndEmailService.class);
	@Autowired
    JavaMailSender mailSender;
	@Autowired
	Configuration freemarkerConfig; 

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consumeAndSendEmail(People people, Channel channel) throws IOException, TemplateException {
    	logger.info("Inside consumeAndSendEmail()");
        sendEmail(people);
        logger.info("Inside consumeAndSendEmail, Email sent successfully!!");
    }
    
    @RabbitListener(queues = "${spring.rabbitmq.broadcastQueue}")
    public void broadcastConsumeAndSendEmail(BroadcastDTO broadcast, Channel channel) {
    	logger.info("Inside broadcastCconsumeAndSendEmail()");
        sendBroadcastEmail(broadcast);
        logger.info("Broadcast emailed sent successfully!!");
    }
     
	private void sendEmail(People people) throws IOException, TemplateException {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        helper.setTo(people.getEmail());
	        helper.setSubject("Happy " + people.getOccasion() + " !!");
	        Map<String, Object> model = new HashMap<>();
	        model.put("name", people.getName());
	        model.put("occasion", people.getOccasion());
	        helper.setText(geContentFromTemplate(model),true);
	        mailSender.send(message);
	        logger.info("Email sent successfully");

        } catch (MessagingException e) {
            logger.error("Inside sendEmail, Exception occured | Reason : {}", e.getMessage());
            throw new InternalServerErrorException(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	private void sendBroadcastEmail(BroadcastDTO broadcast) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        logger.info("Inside ConsumeAndEmailService, sendBroadcastEmail -> Sending Email !!");
	        helper.setTo(broadcast.getEmail());
	        helper.setSubject("Happy " + broadcast.getOccasion() + " !!");
	        Map<String, Object> model = new HashMap<>();
	        model.put("name", broadcast.getName());
	        model.put("occasion", broadcast.getOccasion());
	        helper.setText(getContentFromBroadcastTemplate(model,broadcast.getTemplate()),true);
	        mailSender.send(message);
	        logger.info("BroadCast Email sent successfully");
            
	        
        } catch (MessagingException e) {
            logger.error("Inside sendEmail, Exception occured | Reason : {}", e.getMessage());
            throw new InternalServerErrorException(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	  public String geContentFromTemplate(Map < String, Object >model)     { 
		  StringWriter stringWriter = new StringWriter();
	        try {
				freemarkerConfig.getTemplate("occasionTemplateForPerson.ftlh").process(model, stringWriter);
			} catch (TemplateException | IOException e) {
				e.printStackTrace();
			}
	        return stringWriter.getBuffer().toString();
	  }
	  private String getContentFromBroadcastTemplate(Map<String, Object>model, String templateName)
	  {
		  logger.info("Inside ConsumeAndEmailService, getContentFromBroadcastTemplate");
		  StringWriter stringWriter = new StringWriter();
		  try {
			  freemarkerConfig.getTemplate(templateName +".ftlh").process(model, stringWriter);
		  }catch(TemplateException | IOException e)
		  {
			  e.printStackTrace();
		  }
		  logger.info("Inside ConsumeAndEmailService, getContentFromBroadcastTemplate -> Successfully reterive the content of template");
		  return stringWriter.getBuffer().toString();
	  }
}
