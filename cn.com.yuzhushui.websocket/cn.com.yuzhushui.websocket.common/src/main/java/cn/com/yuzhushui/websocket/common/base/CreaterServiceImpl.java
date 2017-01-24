package cn.com.yuzhushui.websocket.common.base;

import org.springframework.stereotype.Service;

import cn.com.yuzhushui.websocket.common.util.SessionUtil;
import cn.com.yuzhushui.websocket.sys.biz.entity.SysUser;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月20日下午9:09:43
 **/
@Service
public class CreaterServiceImpl implements PluginBeforeService{

	@SuppressWarnings("rawtypes")
	public void process(BaseModel baseModel) {
		if(baseModel instanceof CreaterInfo) {
			CreaterInfo creater = (CreaterInfo) baseModel;
			SysUser sysUser = SessionUtil.getSysUser();
			creater.setCreater(sysUser.getName());
			creater.setCreaterId(sysUser.getId());
		}
	}
}
