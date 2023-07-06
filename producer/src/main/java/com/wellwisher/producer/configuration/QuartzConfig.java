package com.wellwisher.producer.configuration;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.wellwisher.producer.job.ReadJob;
import com.wellwisher.producer.util.DateUtil;

@Configuration
public class QuartzConfig {
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory(applicationContext));
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }
	
	@Bean
	public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
	    AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
	    jobFactory.setApplicationContext(applicationContext);
	    return jobFactory;
	}
	
	@Bean
    public JobDetail jobDetail() {
        return newJob().ofType(ReadJob.class)
        		.storeDurably()
        		.withIdentity(JobKey.jobKey("Qrtz_Job_Detail"))
        		.withDescription("Invoke Read Job service...")
        		.build();
    }
	
	@Bean
    public Trigger trigger(JobDetail job) {
        return newTrigger().forJob(job)
        		.withIdentity(TriggerKey.triggerKey("Qrtz_Trigger"))
        		.startAt(DateUtil.getStartTime())
        		.withDescription("Read trigger")
        		.withSchedule(simpleSchedule().withIntervalInHours(24).repeatForever()).build();
    }
	
	@Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }
	
	public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
