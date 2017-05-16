package cn.com.daocaore.bms.sys.biz.entity;

import cn.com.daocaore.bms.common.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月11日上午11:12:34
 **/

@Getter
@Setter
public class Alarm extends BaseModel<Integer>{
	
	private Integer level;
    private Long total;
    
	@Override
	public Integer getId() {
		return level;
	}
}
