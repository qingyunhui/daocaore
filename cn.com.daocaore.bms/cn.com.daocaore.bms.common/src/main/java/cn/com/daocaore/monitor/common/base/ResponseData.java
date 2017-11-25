package cn.com.daocaore.monitor.common.base;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:38:45
 **/
@Data
public class ResponseData {
	
	private int code;
	private String msg ;
	
	private static final int DEFAULT_CODE=200;
	
	private Map<String,Object> datas;
	
	public ResponseData(){}
	
	public ResponseData(String msg){
		this(DEFAULT_CODE,msg);
	}
	
	public ResponseData(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public void put(String key,Object value){
		if(null==datas) datas=new HashMap<String, Object>();
		datas.put(key, value);
	}
	

}
