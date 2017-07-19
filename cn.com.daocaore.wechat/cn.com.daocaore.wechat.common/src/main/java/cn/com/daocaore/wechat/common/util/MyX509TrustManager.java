package cn.com.daocaore.wechat.common.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/***
 ** @category 证书信任管理（用户http请求）...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月19日下午4:59:58
 **/
public class MyX509TrustManager implements X509TrustManager {
	
	
	/**
	 * 这个证书管理器的作用就是让它信任我们指定的证书，上面的代码意味着信任所有证书，不管是否权威机构颁发。
		证书有了，通用的https请求方法就不难实现了，实现代码如下：
	 * **/
	

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}