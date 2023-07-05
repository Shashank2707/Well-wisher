package com.wellwisher.producer.Job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.wellwisher.producer.pojo.People;
import com.wellwisher.producer.service.WellwisherService;

public class ReadJob extends QuartzJobBean {
	
	@Autowired
	WellwisherService wellwisherService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Fetching from database");
		List<People> people = wellwisherService.get();
		System.out.println("Fetched Successfully size: "+ people.size());
	}
}
