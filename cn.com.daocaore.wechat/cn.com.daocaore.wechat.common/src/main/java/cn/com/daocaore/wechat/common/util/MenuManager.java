package cn.com.daocaore.wechat.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.daocaore.wechat.common.menu.AccessToken;
import cn.com.daocaore.wechat.common.menu.Button;
import cn.com.daocaore.wechat.common.menu.CommonButton;
import cn.com.daocaore.wechat.common.menu.ComplexButton;
import cn.com.daocaore.wechat.common.menu.Menu;

/***
 ** @category 菜单管理器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月19日下午5:05:22
 **/
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = "wx38ed8fa87cb135db";
        // 第三方用户唯一凭证密钥
        String appSecret = "16a0f8d10fa886bd126a1ef07679eb5e";
        // 调用接口获取access_token
        AccessToken at = WechatUtil.getAccessToken(appId, appSecret);

        if (null != at) {
            // 调用接口创建菜单
            int result = WechatUtil.createMenu(getMenu(), at.getToken());
            // 判断菜单创建结果
            if (0 == result)
                System.out.println("菜单创建成功！");
            else
            	System.out.println("菜单创建失败，错误码：" + result);
        }
    }

    /**
     * 组装菜单数据
     * 
     * @return
     */
    private static Menu getMenu() {
        CommonButton btn11 = new CommonButton();
        btn11.setName("今日歌曲");
        btn11.setType("click");
        btn11.setKey("V1001_TODAY_MUSIC");

        CommonButton btn12 = new CommonButton();
        btn12.setName("搜索");
        btn12.setType("click");
        btn12.setKey("V1001_GOOD");

        /**
         * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
         */
        
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("生活助手");
        //一级下有4个子菜单
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12 });

        
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1 });

        return menu;
    }
}
