package cn.com.yuzhushui.schedule.job.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.schedule.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:20
 * @history
 */
@Getter
@Setter
public class JobInfoCopyForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields name:名称
	 */
	private String name;
	
	/**
	 * @Fields group:分组,一般是模块名
	 */
	private String group;
	
	/**
	 * @Fields type:一次性任务或者cron任务
	 */
	private String type;
	
	/**
	 * @Fields time:一次性任务执行时间
	 */
	private String time;
	
	/**
	 * @Fields cron:cron 任务的 cron 表达式
	 */
	private String cron;
	
	/**
	 * @Fields urls:目标服务器URL列表,如果多条使用逗号分割.
	 */
	private String urls;
	
	/**
	 * @Fields class_path:任务类的 ClassPath
	 */
	private String classPath;
	
	/**
	 * @Fields invoke_policy:调用策略, 优先,随机 等.
	 */
	private String invokePolicy;
	
	/**
	 * @Fields is_activity:是否启用
	 */
	private Integer isActivity;
	
	/**
	 * @Fields desc:描述信息
	 */
	private String desc;
	
	/**
	 * @Fields create_time:createTime
	 */
	private Date createTime;
	
	/**
	 * @Fields modify_time:modifyTime
	 */
	private Date modifyTime;
	
	/**
	 * @Fields param:param
	 */
	private String param;
	//columns END
}