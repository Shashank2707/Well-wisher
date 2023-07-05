package com.wellwisher.producer.Scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.wellwisher.producer.Job.ReadJob;
import com.wellwisher.producer.Util.DateUtil;

public class EachDay {
	
	public static void initSchedule() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			  // define the job and tie it to our HelloJob class
			  JobDetail job = newJob(ReadJob.class)
			      .withIdentity("job1", "group1")
			      .build();

			  // Trigger the job to run now, and then repeat every 40 seconds
			  SimpleTrigger trigger = 
					  TriggerBuilder.newTrigger()
					  .withIdentity("trigger1", "group1")
					  .startAt(DateUtil.getStartTime())
					  .withSchedule(simpleSchedule().
							  withIntervalInMinutes(1).repeatForever())
					  .build();

			  // Tell quartz to schedule the job using our trigger
			  scheduler.scheduleJob(job, trigger);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
