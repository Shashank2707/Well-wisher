package com.wellwisher.producer.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.service.WellwisherService;

@Component
public class ReadJob implements Job {
	
	@Autowired 	
	WellwisherService wellwisherService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<People> people = wellwisherService.get();
	}
}
