package cn.com.daocaore.monitor.sys.biz.entity;

import java.util.Date;

import cn.com.daocaore.monitor.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-11-25 23:03:18
 * @history
 */
@Getter
@Setter
public class SysDict extends BaseModel<Long>{
	
	//alias
	public static final String TABLE_ALIAS = "SysDict";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Long id;
	
	/**
	 * @Fields pid:pid
	 */
	private Integer pid;
	
	/**
	 * @Fields itemKey:键
	 */
	private String itemKey;
	
	/**
	 * @Fields itemValue:值
	 */
	private String itemValue;
	
	/**
	 * @Fields name:名称
	 */
	private String name;
	
	/**
	 * @Fields status:状态
	 */
	private Integer status;
	
	/**
	 * @Fields orderValue:排序
	 */
	private Integer orderValue;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}