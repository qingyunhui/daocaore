package cn.com.yuzhushui.schedule.job.bean;

import cn.com.yuzhushui.schedule.job.enums.MethodFlag;
import lombok.Getter;
import lombok.Setter;

/**
 * 封装了http请求中的参数信息
 * 
 */
@Getter
@Setter
public class JobRequest implements java.io.Serializable {

	private static final long serialVersionUID = -4154061846821583761L;

	/**
	 * 对应job_snapshot表中的id
	 */
	private long jobDetailId;

	/**
	 * 需要执行的job的类的全路径名
	 */
	private String classFullPath;

	/**
	 * server端执行方法标记，值为INVOKE、EXECUTING
	 */
	private MethodFlag methodFlag;

	/**
	 * 参数
	 */
	private String param;

}
