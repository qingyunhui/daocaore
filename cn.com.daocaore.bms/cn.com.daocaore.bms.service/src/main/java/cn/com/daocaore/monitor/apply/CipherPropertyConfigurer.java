package cn.com.daocaore.monitor.apply;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import cn.com.daocaore.monitor.common.constant.PropertyConstant;
import qing.yun.hui.common.enums.ICommonEnum;
import qing.yun.hui.common.enums.PropertiesEnum;
import qing.yun.hui.common.utils.EnumUtil;
import qing.yun.hui.common.utils.RSAUtil;
import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年10月26日上午9:52:56
 **/
public class CipherPropertyConfigurer extends PropertyPlaceholderConfigurer{
	
	//继承PropertyPlaceholderConfigurer类重写processProperties方法>>>>处理逻辑.....
	
	//继承PropertyPlaceholderConfigurer类重写convertProperty方法..>>>>
	
	Logger logger=LoggerFactory.getLogger(CipherPropertyConfigurer.class);
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)throws BeansException {
		 try {
			resetData(props, PropertyConstant.PRIVATE_KEY);
		} catch (Exception e) {
			logger.error(""+e);
		}
         super.processProperties(beanFactoryToProcess, props);
	}
	
	/**
	 * 对指定数据使用私钥进行解密
	 * @param props 加密的后Properties(源)
	 * @param privateKey 待解密的私钥
	 * */
	protected void resetData(Properties props,String privateKey) throws Exception{
		Object[] objs=EnumUtil.getEnumValues(PropertiesEnum.class.getName());
		if(null!=objs&& objs.length>0){
			for(Object obj:objs){
				ICommonEnum ico=(ICommonEnum) obj;
				String key=ico.getName();
				String encryptionData= props.getProperty(key);
				if(!StringUtil.isEmpty(encryptionData)){
					try {
						logger.info("==================>berfor-key:{}-value:{}==================>",new Object[]{key,encryptionData});
						props.setProperty(key, RSAUtil.decryptionByPrivateKey(privateKey, encryptionData.trim()));
						logger.info("==================>end-key:{}==================>",new Object[]{props.get(key)});
					} catch (Exception e) {
						logger.error("=========>key={},value={},异常信息:{}<==========",new Object[]{key,encryptionData.trim(),e});
						continue;
					}
				}
			}
		}
	}
}
