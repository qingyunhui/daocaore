package cn.com.daocaore.bms.common.bean;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 附件的一个扩展...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月25日下午10:43:51
 **/
@Getter
@Setter
public class ExtrasStruct {
	
	//分类
	private String classify;
	
	//文件处理类型
	private int handleType;
	
	private Integer isSystem; 
	
	//目标表
	private String targetTable;
	
	//目标字段
	private String targetField;
	
	//目标id
	private String targetId;
	
	/**
	 * 初始化Extras
	 * @param clz 目标表
	 * @param targetField 目标字段
	 * @param targetId 目标id
	 * @return Extras 
	 * **/
	public static ExtrasStruct getExtrasStruct(Class<?> clz,String targetField,String targetId){
		ExtrasStruct extras =new ExtrasStruct();
		extras.setTargetTable(StringUtil.getTableName(clz));
		extras.setTargetField(targetField);
		extras.setTargetId(targetId);
		return extras;
	}

}
