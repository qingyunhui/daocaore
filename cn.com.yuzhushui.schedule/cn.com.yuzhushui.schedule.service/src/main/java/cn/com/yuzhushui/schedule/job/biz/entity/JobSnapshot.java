package cn.com.yuzhushui.schedule.job.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.schedule.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:21
 * @history
 */
@Getter
@Setter
public class JobSnapshot extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "JobSnapshot";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields jobInfoId:jobInfoId
	 */
	private Long jobInfoId;
	
	/**
	 * @Fields name:名称
	 */
	private String name;
	
	/**
	 * @Fields group:分组,一般是模块名
	 */
	private String group;
	
	/**
	 * @Fields status:init, invoking, completed , error 等
	 */
	private String status;
	
	/**
	 * @Fields ip:目标服务器 IP 地址
	 */
	private String ip;
	
	/**
	 * @Fields url:目标服务器 URL
	 */
	private String url;
	
	/**
	 * @Fields result:任务结果
	 */
	private String result;
	
	/**
	 * @Fields timeConsume:任务耗时
	 */
	private Long timeConsume;
	
	/**
	 * @Fields detail:执行信息
	 */
	private String detail;
	
	/**
	 * @Fields createTime:createTime
	 */
	private Date createTime;
	
	/**
	 * @Fields modifyTime:modifyTime
	 */
	private Date modifyTime;
	
	//columns END

}