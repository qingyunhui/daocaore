package cn.com.daocaore.bms.apply;

import java.util.Properties;

import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

import qing.yun.hui.common.utils.RSAUtil;
import cn.com.daocaore.bms.common.constant.PropertyConstant;

import com.xxl.conf.core.XxlConfClient;
import com.xxl.conf.core.spring.XxlConfPropertyPlaceholderConfigurer;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月27日上午10:40:11
 **/
public class XxlconfPropertyConfigurer extends XxlConfPropertyPlaceholderConfigurer{
	
	private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Setter
	private String beanName;

	@Setter
	private BeanFactory beanFactory;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		// init value resolver
		StringValueResolver valueResolver = new StringValueResolver() {
			String placeholderPrefix = "${";
			String placeholderSuffix = "}";
			@Override
			public String resolveStringValue(String strVal) {
				StringBuffer buf = new StringBuffer(strVal);
				// loop replace by xxl-conf, if the value match '${***}'
				boolean start = strVal.startsWith(placeholderPrefix);
				boolean end = strVal.endsWith(placeholderSuffix);
				while (start && end) {
					// replace by xxl-conf
					String key = buf.substring(placeholderPrefix.length(), buf.length() - placeholderSuffix.length());
					String zkValue = XxlConfClient.get(key, "");
					zkValue=decode(zkValue, PropertyConstant.PRIVATE_KEY);
					buf = new StringBuffer(zkValue);
					logger.info(">>>>>>>>>>> xxl-conf resolved placeholder '" + key + "' to value [" + zkValue + "]");
					start = buf.toString().startsWith(placeholderPrefix);
					end = buf.toString().endsWith(placeholderSuffix);
				}
				return buf.toString();
			}
		};
		// init bean define visitor
		BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
		// visit bean definition
		String[] beanNames = beanFactoryToProcess.getBeanDefinitionNames();
		if (beanNames != null && beanNames.length > 0) {
			for (String beanName : beanNames) {
				if (!(beanName.equals(this.beanName) && beanFactoryToProcess.equals(this.beanFactory))) {
					BeanDefinition bd = beanFactoryToProcess.getBeanDefinition(beanName);
					visitor.visitBeanDefinition(bd);
				}
			}
		}
	}
	protected String decode(String value,String privateKey){
		try {
			return RSAUtil.decryptionByPrivateKey(privateKey, value);
		} catch (Exception e) {}
		return value;
	}
}
