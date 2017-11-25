package cn.com.daocaore.monitor.merchandise.biz.entity;

import java.util.Date;

import cn.com.daocaore.monitor.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-11-25 23:03:17
 * @history
 */
@Getter
@Setter
public class MerchandiseDetail extends BaseModel<Long>{
	
	//alias
	public static final String TABLE_ALIAS = "MerchandiseDetail";
	
	//columns START
	/**
	 * @Fields id:主键id
	 */
	private Long id;
	
	/**
	 * @Fields merchandiseId:商品id
	 */
	private Long merchandiseId;
	
	/**
	 * @Fields color:商品颜色
	 */
	private String color;
	
	/**
	 * @Fields imgPath:商品图片
	 */
	private String imgPath;
	
	/**
	 * @Fields unitPrice:单价
	 */
	private Long unitPrice;
	
	/**
	 * @Fields unit:单位
	 */
	private Integer unit;
	
	/**
	 * @Fields status:状态
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
	 * @Fields remarks:备注
	 */
	private String remarks;
	
	/**
	 * @Fields deleted:是否删除
	 */
	private Integer deleted;
	
	//columns END

}