package cn.com.daocaore.wechat.common.menu;
/***
 ** @category 父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年7月19日下午4:58:37
 **/
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
