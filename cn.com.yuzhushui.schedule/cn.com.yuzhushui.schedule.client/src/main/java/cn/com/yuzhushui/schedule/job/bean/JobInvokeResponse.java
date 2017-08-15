package cn.com.yuzhushui.schedule.job.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装了请求执行结果等相关参数的PO
 * <p>
 * 注：这个response是在server端调用invoke()方法的时候返回的结果
 * </p>
 */
@Getter
@Setter
public class JobInvokeResponse implements Serializable {

	private static final long serialVersionUID = -5873493554044978371L;

	/**
	 * 是否invoke成功，成功返回true，失败返回false
	 */
	private boolean isInvokedSucc;

	/**
	 * invoke失败返回的错误信息
	 */
	private String errorMsg;

}
