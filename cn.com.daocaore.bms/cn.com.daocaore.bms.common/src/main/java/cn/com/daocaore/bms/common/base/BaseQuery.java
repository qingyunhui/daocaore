package cn.com.daocaore.bms.common.base;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午9:18:19
 **/
@Data
public class BaseQuery {
	
	private Map<String, Object> queryData = new HashMap<String, Object>();// 一些查询条件
	private int pageNum = 1;// 当前页
	private int pageSize = 5;// 每页的数量
	private String orderDirection;// 排序方向
	private String orderColumns;// 排序字段（多个以半角逗号隔开）

	private String orderBy;// 排序
	
	public void addParam(String key, Object value){
		this.queryData.put(key, value);
	}

	public void setOrderBy() {
		if(!StringUtil.isEmpty(getOrderColumns())) {
			this.orderBy = getOrderColumns() + " " + (getOrderDirection() == null ? "" : getOrderDirection());
		}
	}
}