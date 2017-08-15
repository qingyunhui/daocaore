package cn.com.yuzhushui.schedule.job.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装了job执行结果等相关信息，通过Response返回给server端
 */
@Getter
@Setter
public class JobResult implements Serializable {

	private static final long serialVersionUID = 1530198121024793393L;

	/**
	 * 对应job_detail表中的id
	 */
	private long jobDetailId;

	/**
	 * job执行成功还是失败，成功返回true，失败返回false
	 */
	private boolean isSuccess;

	/**
	 * job执行花费的时长，单位：秒
	 */
	private long timeConsume;

	/**
	 * 成功、失败，返回的结果
	 * <p>
	 * 注：这个结果由业务方自定义，会返回到控制台界面，方便查看
	 * </p>
	 */
	private String result;

}
