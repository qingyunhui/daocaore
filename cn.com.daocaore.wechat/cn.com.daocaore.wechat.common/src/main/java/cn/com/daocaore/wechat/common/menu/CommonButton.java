package cn.com.daocaore.wechat.common.menu;
/***
 ** @category 子菜单项 :没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月19日下午4:58:00
 **/
public class CommonButton extends Button {
    
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
