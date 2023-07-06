package com.wellwisher.consumer.service;

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
import static com.wellwisher.consumer.config.Constants.*;
import com.rabbitmq.client.Channel;
import com.wellwisher.consumer.exception.InternalServerErrorException;
import com.wellwisher.consumer.pojo.People;

@Service
public class BirthdayEmailConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(BirthdayEmailConsumerService.class);
	@Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consumeBirthdayEmail(People people,Channel channel) {
      
    	logger.info("Inside BirthdayEmailConsumerService consumeBirthdayEmail");
        String emailContent = prepareEmailContent(people);

        sendEmail(people, emailContent);
        logger.info("Send email successfully");
    }

	private void sendEmail(People people, String emailContent) {
		try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(people.getEmail());
            helper.setSubject("Happy " + people.getOccasion() + "!!");
            helper.setText(emailContent);
            mailSender.send(message);
            
        } catch (MessagingException e) {
            logger.error("Inside BirthdayEmailConsumerService sendEmail, Exception occured | Reason : {}",e.getMessage());
            throw new InternalServerErrorException(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}

	private String prepareEmailContent(People people) {
		String name = people.getName();
	    
	    StringBuilder sb  = new StringBuilder();
	    sb.append("Dear " + name + ",\n\n");
	    sb.append("Wishing you a very happy "+ people.getOccasion() + "! May this day bring joy, happiness, and success in your life.\n\n" );
	    sb.append("Warm Regards,\n");
	    sb.append("Well Wisher Team");

	    return sb.toString();
	}
}
