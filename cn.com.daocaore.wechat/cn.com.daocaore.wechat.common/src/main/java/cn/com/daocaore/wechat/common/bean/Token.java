package cn.com.daocaore.wechat.common.bean;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category TOken 凭证...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月20日上午11:58:05
 **/
@Getter
@Setter
public class Token {

	// 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;

	
}
