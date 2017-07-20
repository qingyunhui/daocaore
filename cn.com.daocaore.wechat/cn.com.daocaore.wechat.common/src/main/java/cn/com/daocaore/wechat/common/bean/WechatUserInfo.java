package cn.com.daocaore.wechat.common.bean;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 用户基本信息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月20日上午11:56:37
 **/
@Getter
@Setter
public class WechatUserInfo {
	
	/*我们先来看看获取用户信息的接口：
	https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	根据分析，获取用户的基本信息需要一个token。*/
	
	// 用户的标识
    private String openId;
    // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
    private int subscribe;
    // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private String subscribeTime;
    // 昵称
    private String nickname;
    // 用户的性别（1是男性，2是女性，0是未知）
    private int sex;
    // 用户所在国家
    private String country;
    // 用户所在省份
    private String province;
    // 用户所在城市
    private String city;
    // 用户的语言，简体中文为zh_CN
    private String language;
    // 用户头像
    private String headImgUrl;

}
