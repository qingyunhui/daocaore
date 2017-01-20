package cn.com.yuzhushui.websocket.constant;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月20日上午10:42:04
 **/
public class WebsocketConstant {

	 public static final String JDBC_DRUID_USER = "jdbc_username";			//jdbc.user
	    
     public static final String JDBC_DRUID_PASSWORD = "jdbc_password";		//jdbc.password
    
    
     //Redis config ===>begin
    
     public static final String REDIS_IP="redis.ip";
    
     public static final String REDIS_PORT="redis.port";
    
     public static final String REDIS_PASSWORD="redis.password";
    
    
     //RSA==========>公钥======>加密
     public static final String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWbYw374WwDCe+RmMFrFXYmiWz9W0AVKvCaVANsEKxXWPUDIgMQPMKeQB/0Flryt0RFlFcfaVCljbVhgwRDEEAe8WYABxXHiEfhoRhWUoVSx5Vu50fOnk4mZUM2Wo2jnBDR/ZU/wZh9YpbTYImy2cNGh5kqNk/ZRm4EI0yq1lgUwIDAQAB";
    
     //RSA==========>私钥======>解密
     public static final String PRIVATE_KEY="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZtjDfvhbAMJ75GYwWsVdiaJbP1bQBUq8JpUA2wQrFdY9QMiAxA8wp5AH/QWWvK3REWUVx9pUKWNtWGDBEMQQB7xZgAHFceIR+GhGFZShVLHlW7nR86eTiZlQzZajaOcENH9lT/BmH1iltNgibLZw0aHmSo2T9lGbgQjTKrWWBTAgMBAAECgYBGfl/Pdre3oHqfeGy/I9u2/cXdZ44FMaisGp4hTBB0/cbigFzhIS7EIaBSRVkiYpbmgwbtyRPA+JQJzB+rfYqX7Qb42t4AaeKiZUMnqGh73gJ/746DqCl9iMg78Vt3fCVzRd41WJmb2nR+C/ftjZOIybL8TPzDh5BVgEZdYwH9AQJBAOcKJvyaQYQQWj8YmzwC4/xnZFg/yVZW5iwGe363Et3gp+gdMvH/OWDI2ekYclsSSSlnEE7MYcSBVXgeakWTdjECQQCmrewY20e/yIQhKi62QAWTR4fxjEEYxYRa+H1E5iVLF+n3m9LXFtvcThI54qgwknY1s+DsA0JIvXxlp2rDAKnDAkEAtC7nvuLyq3MxXrnq/v9mHIfp6yU7+TjmY3lewLE4Zo/5gv2aqL6aWg/qWm61aK83HbICNuIQcXdIV9iomsBpUQJAU095Q89OpIBf8oe4A2YqozvMqiIVY4FOL34mduql10vjqNyc3N9TE2F+YKp2sJh0N1Fqae0TE3KKm/C7Py60qwJABss3wYwjsMjWkl6CGP5h+Jm7pIjM0AhWu5Dm+779twjhGuxPmXqpkOmzagsAPqWShJAgINeuPDfNJgFfzsUZHg==";
    
     
     /**session标识*/
 	 public static final String SESSION_INFO="SessionInfo";

 	 /**消息*/
 	 public static final String MESSAGES_INFO="messages";
 	
 	 /**登陆次数标识*/
 	 public static final String  LOGON_COUNT_INFO="logonCount";
 	
 	 /**登陆次数**/
 	 public static final int LOGON_COUNTS=3;
 	
 	 /**锁定时间（单位:分钟）*/
 	 public static final int LOCK_TIME=30;
 	
 	 /**cookie有效时间（2小时）*/
 	 public static final int COOKIE_VALIDITY_TIME=2*60*60;
 	
 	 /**项目的顶级域名(子域名，二级域名都可以访问到，比如:abc.smiles8.com,top.smiles8.com)**/
 	 public static final String DOMAIN="smiles8.com";
 	
 	 /**项目名称*/
 	 public static final String PROJECT_NAME="MOVIE";
 	
 	 /**连接符*/
 	 public static final String JOINT="&=yzs=?";
 	
 	 /**根目录*/
 	 public static final String ROOT_PATH = "/";
     
}
