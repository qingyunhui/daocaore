package cn.com.daocaore.monitor.merchandise.biz.entity;

import java.util.Date;

import cn.com.daocaore.monitor.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;
/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-11-25 23:03:16
 * @history
 */
@Getter
@Setter
public class Merchandise extends BaseModel<Long>{
	
	//alias
	public static final String TABLE_ALIAS = "Merchandise";
	
	//columns START
	/**
	 * @Fields id:主键id
	 */
	private Long id;
	
	/**
	 * @Fields name:商品名称
	 */
	private String name;
	
	/**
	 * @Fields type:商品类型
	 */
	private Integer type;
	
	/**
	 * @Fields status:商品状态
	 */
	private Integer status;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields describes:describes
	 */
	private String describes;
	
	/**
	 * @Fields remarks:备注
	 */
	private String remarks;
	
	/**
	 * @Fields deleted:是否删除
	 */
	private Integer deleted;
	
	//columns END

}