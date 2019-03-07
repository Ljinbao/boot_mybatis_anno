package com.lten.boot.config;

import com.lten.boot.job.OrderJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @describe 该配置是模仿订单过时失效而配置的，使用这种配置是在SpringBoot2.0之前没有集成Quartz时所用。
 *              所用的依赖有quartz、spring-context-support，没有第二个依赖不能定义Job继承QuartzJobBean这个类。
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/7 11:36
 */
//@Configuration
public class QuartzConfig {

    /**
     *  定义任务bean
     */
    @Bean
    public JobDetailFactoryBean createJobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //指定具体的job类
        jobDetailFactoryBean.setJobClass(OrderJob.class);
        //设置job名称
        jobDetailFactoryBean.setName("orderJob");
        //设置job的组
        jobDetailFactoryBean.setGroup("Order");
        //设置触发器不会删除调度器中的任务
        jobDetailFactoryBean.setDurability(true);
        //指定spring容器的key
        jobDetailFactoryBean.setApplicationContextJobDataKey("applicationContext");
        return jobDetailFactoryBean;
    }

    /**
     * 定义触发器
     */
    @Bean
    public CronTriggerFactoryBean createTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(createJobDetail().getObject());
        cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
        return cronTriggerFactoryBean;
    }

    /**
     * 定义调度器
     */
    @Bean
    public SchedulerFactoryBean createScheduler() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(createTrigger().getObject());
        return  schedulerFactoryBean;
    }
}
