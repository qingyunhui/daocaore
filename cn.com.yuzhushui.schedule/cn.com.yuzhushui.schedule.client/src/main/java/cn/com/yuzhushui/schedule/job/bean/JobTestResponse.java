package cn.com.yuzhushui.schedule.job.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于测试结果返回
 * 
 */
@Getter
@Setter
public class JobTestResponse implements Serializable {

	private static final long serialVersionUID = -1846413433523614639L;

	/**
	 * 成功true，失败false
	 */
	private boolean isSuccess;

	/**
	 * 成功、失败，返回的结果
	 */
	private String result;

}
