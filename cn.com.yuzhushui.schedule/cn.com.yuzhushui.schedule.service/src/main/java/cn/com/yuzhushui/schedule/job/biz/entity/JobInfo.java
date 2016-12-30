package cn.com.yuzhushui.schedule.job.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.schedule.common.base.BaseModel;
import cn.com.yuzhushui.schedule.job.enums.JobInfoEnum;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:19
 * @history
 */
@Getter
@Setter
public class JobInfo extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "JobInfo";
	
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
	private String groups;
	
	/**
	 * @Fields type:一次性任务或者cron任务
	 */
	private JobInfoEnum.TYPE type;
	
	/**
	 * @Fields time:一次性任务执行时间
	 */
	private Date time;
	
	/**
	 * @Fields cron:cron 任务的 cron 表达式
	 */
	private String cron;
	
	/**
	 * @Fields urls:目标服务器URL列表,如果多条使用逗号分割.
	 */
	private String urls;
	
	/**
	 * @Fields classPath:任务类的 ClassPath
	 */
	private String classPath;
	
	/**
	 * @Fields invokePolicy:调用策略, 优先,随机 等.
	 */
	private JobInfoEnum.INVOKE_POLICY invokePolicy;
	
	/**
	 * @Fields isActivity:是否启用
	 */
	private Integer isActivity;
	
	/**
	 * @Fields desc:描述信息
	 */
	private String desc;
	
	/**
	 * @Fields createTime:createTime
	 */
	private Date createTime;
	
	/**
	 * @Fields modifyTime:modifyTime
	 */
	private Date modifyTime;
	
	/**
	 * @Fields param:param
	 */
	private String param;
	
	//columns END

}