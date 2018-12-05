package br.com.company.scheduling.cron;

import br.com.company.scheduling.job.*;
import br.com.company.spark.SparkContextSingleton;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTrigger{
	private static JobDetail jobApp;
	private static Trigger triggerApp;
	
	public static void main( String[] args ) throws Exception{
		jobDefinition();
		triggerDefinition();	
		run();
    }
	
	protected static void jobDefinition(){
		
		JobKey jobKey2 = new JobKey("jobApp", "group1");
		jobApp = JobBuilder.newJob(JobApp.class).withIdentity(jobKey2).build();

	}
	
	protected static void triggerDefinition(){


		triggerApp = TriggerBuilder.newTrigger()
				.withIdentity("triggerApp", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
	}
	
	protected static void run() throws SchedulerException{
		SparkContextSingleton.get();
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();    	
		scheduler.start();
		scheduler.scheduleJob(jobApp, triggerApp);
	}
}
