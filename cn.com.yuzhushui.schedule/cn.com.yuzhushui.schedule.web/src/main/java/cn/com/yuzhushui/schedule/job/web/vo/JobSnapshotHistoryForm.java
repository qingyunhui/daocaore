package cn.com.yuzhushui.schedule.job.web.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.yuzhushui.schedule.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-12-27 14:53:21
 * @history
 */
@Getter
@Setter
public class JobSnapshotHistoryForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields job_snapshot_id:jobSnapshotId
	 */
	private Long jobSnapshotId;
	
	/**
	 * @Fields job_info_id:jobInfoId
	 */
	private Integer jobInfoId;
	
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
	 * @Fields time_consume:任务耗时
	 */
	private Long timeConsume;
	
	/**
	 * @Fields detail:执行信息
	 */
	private String detail;
	
	/**
	 * @Fields create_time:createTime
	 */
	private Date createTime;
	
	/**
	 * @Fields modify_time:modifyTime
	 */
	private Date modifyTime;
	//columns END
}