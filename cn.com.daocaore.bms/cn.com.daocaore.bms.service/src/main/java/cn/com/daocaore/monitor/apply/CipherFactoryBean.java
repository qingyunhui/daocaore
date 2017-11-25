package cn.com.daocaore.monitor.apply;

import org.springframework.beans.factory.FactoryBean;

/***
 ** @category 加密解密(用于对jdbc配置文件properties的解密或加密)...PropertyPlaceholderConfigurer  
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年10月26日上午9:31:53
 **/
public class CipherFactoryBean implements FactoryBean<String>{

	private String password;
	
	private String jdbcName;
	
	@Override
	public String getObject() throws Exception {
		// 返回代理对象 >>>>>处理逻辑........
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		// 返回代理对象的类型
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		// true: 返回单列,false 返回的原型
		return true;
	}
}
