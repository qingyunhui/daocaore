package cn.com.daocaore.bms.sys.biz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import cn.com.daocaore.bms.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
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
	 * @Fields attachmentType:附件类型
	 */
	private String attachmentType;
	
	/**
	 * @Fileds handleType 处理类型(缩略处理/截取处理/无处理)
	 * */
	private Integer handleType;
	
	/**
	 * @Fields classify:分类(相册、音乐、头像、视频)
	 */
	private Integer classify;
	
	/**
	 * @Fields isSystem 是否系统内置(0:系统,1:用户)
	 * */
	private Integer isSystem; 
	
	/**
	 * @Fields url:访问的相对或绝对路径url
	 */
	private String url;
	
	/**
	 * @Fields 源文件所在磁盘上绝对路径(处理前文件的所在位置)
	 * */
	private String sourceFilePath;
	
	/**
	 * @Fields comments:描述
	 */
	private String comments;
	
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
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields editorId:修改人ID
	 */
	private Integer editorId;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

}