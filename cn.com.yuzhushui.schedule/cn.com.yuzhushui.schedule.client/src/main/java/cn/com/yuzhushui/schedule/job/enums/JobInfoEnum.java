package cn.com.yuzhushui.schedule.job.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月29日上午11:36:22
 **/
public class JobInfoEnum {
	
	@Getter
	public enum IS_ACTIVITY implements ICommonEnum{
		//'状态(0.禁用、1.启用)',
		DISABLE(0,"禁用"),
		ENABLED(1,"启用");
		
		private  String name;
		private int value;
		
		private IS_ACTIVITY(int value,String name){
			this.value=value;
			this.name=name;
		}
		
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	public enum TYPE{
		ONCE,// 一次性job
		REPEAT;//重复执行
	}
	
	public enum INVOKE_POLICY{
		/**
		 * 优先
		 */
		PRIORITY,
		/**
		 * 随机
		 */
		RANDOM
	}
}
