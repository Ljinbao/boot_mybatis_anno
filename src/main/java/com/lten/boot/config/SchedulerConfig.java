package com.lten.boot.config;

import com.lten.boot.job.OrderJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe  该配置是SpringBoot2.0集成Quartz
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 14:33
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail createJobDetail() {
        return JobBuilder.newJob(OrderJob.class).withIdentity("orderJob","Order").storeDurably().build();
    }

    @Bean
    public Trigger createTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger().forJob(createJobDetail()).withIdentity("orderTrigger").withSchedule(cronScheduleBuilder).build();
    }
}
