package cn.com.yuzhushui.schedule.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.utils.StringUtil;
import cn.com.yuzhushui.schedule.job.core.Job;

/***
 ** @category 邮件通知
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月29日上午10:34:40
 **/
@Component
public class EmailNotificationJob implements Job{

	Logger logger=LoggerFactory.getLogger(EmailNotificationJob.class);
	
	public String execute(String parameters) {
		if(StringUtil.isEmpty(parameters)) {
			logger.error("===========>参数不能为null。");
			return "fail";
		}
		logger.info("=================>success.........");
		return "success。";
	}
}
