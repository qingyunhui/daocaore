package cn.com.daocaore.bms.sys.biz.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.daocaore.bms.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-23 13:55:30
 * @history
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class SysWarning extends BaseModel<Integer> implements Serializable{
	
	/**
	 * @Fields id:主键id
	 */
	private Integer id;
	
	/**
	 * @Fields warningDate:预警日期
	 */
	private Date warningDate;
	
	/**
	 * @Fields action:动作(比如:标的还款..)
	 */
	private String action;
	
	/**
	 * @Fields methodName:方法名称
	 */
	private String methodName;
	
	/**
	 * @Fields returnType:返回类型
	 */
	private String returnType;
	
	/**
	 * @Fields returnValue:返回值
	 */
	private String returnValue;
	
	/**
	 * @Fields args:参数
	 */
	private String args;
	
	/**
	 * @Fields annotations:注解
	 */
	private String annotations;
	
	/**
	 * @Fields advice:通知对象(比如:张三,李四,王五..)
	 */
	private String advice;
	
	/**
	 * @Fields contactWay:联系方式(电话,邮箱都可以)
	 */
	private String contactWay;
	
	/**
	 * @Fields status:状态(0:未通知,1.通知中,2.通知失败,3.已通知)
	 */
	private Integer status;
	
	/**ip地址*/
	private String ip;
	
	/**访问设备*/
	private String devices;
	
	/**
	 * @Fields operator:操作人
	 */
	private String operator;
	
	/**
	 * @Fields gmtCreate:创建时间
	 */
	private Date gmtCreate;
	
	/**
	 * @Fields gmtModify:修改时间
	 */
	private Date gmtModify;
	
	private int deleted;
	
	//columns END

}