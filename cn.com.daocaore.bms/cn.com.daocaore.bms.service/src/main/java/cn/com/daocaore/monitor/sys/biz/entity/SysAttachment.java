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
public class SysAttachment extends BaseModel<String>{
	
	//alias
	public static final String TABLE_ALIAS = "SysAttachment";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private String id;
	
	/**
	 * @Fields targetTable:目标表
	 */
	private String targetTable;
	
	/**
	 * @Fields targetField:目标字段
	 */
	private String targetField;
	
	/**
	 * @Fields targetId:目标记录ID
	 */
	private String targetId;
	
	/**
	 * @Fields attachmentType:附件类型(附件类型:(text/html,image/jpeg等)
	 */
	private String attachmentType;
	
	/**
	 * @Fields handleType:处理类型(缩略处理/截取处理/无处理)
	 */
	private Integer handleType;
	
	/**
	 * @Fields classify:分类(相册、音乐、头像、视频)
	 */
	private Integer classify;
	
	/**
	 * @Fields url:访问的相对或绝对路径url
	 */
	private String url;
	
	/**
	 * @Fields physicalPath:文件在磁盘上所在的绝对路径
	 */
	private String physicalPath;
	
	/**
	 * @Fields originName:文件名称
	 */
	private String originName;
	
	/**
	 * @Fields suffix:后缀
	 */
	private String suffix;
	
	/**
	 * @Fields size:文件大小
	 */
	private Long size;
	
	/**
	 * @Fields data:数据(转换二进制文件)
	 */
	private byte[] data;
	
	/**
	 * @Fields comments:描述
	 */
	private String comments;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}