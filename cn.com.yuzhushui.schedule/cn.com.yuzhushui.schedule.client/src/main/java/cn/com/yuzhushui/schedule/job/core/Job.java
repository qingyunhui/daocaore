package cn.com.yuzhushui.schedule.job.core;
/***
 ** @category Job接口，所有job都必须实现该接口...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月28日上午11:24:53
 **/
public interface Job {
	
	/***
	 * <p>根据指定参数执行job</p>
	 * @param parameters job执行的参数
	 * @return T 返回类型
	 * */
	public String execute(String parameters);

}
