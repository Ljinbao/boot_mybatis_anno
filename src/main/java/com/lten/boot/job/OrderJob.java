package com.lten.boot.job;

import java.util.Date;

import com.lten.boot.mapper.OrderMapper;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 订单任务
 * @author lijinbao
 * @date   2019/03/07
 */
public class OrderJob extends QuartzJobBean {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//在传统配置quartz下，必须指定是Spring容器。不然获取不到报异常，在SpringBoot2.0之后其集成了Quartz之后，默认的容器就是Spring容器，
		// 这样的话就可以进行对象的注入了。不用单独去获取Spring容器
	  /* ApplicationContext applicationContex = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
	   OrderMapper orderMapper = applicationContex.getBean(OrderMapper.class);*/

	   Date twoDay  = new DateTime().toDate();
	   
	   orderMapper.updateStatus(twoDay);
	   
	   System.out.println("定时任务执行成功");
		
	}

}
