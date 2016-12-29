package cn.com.yuzhushui.schedule.sys.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.yuzhushui.schedule.cache.ShardedJedisCached;
import cn.com.yuzhushui.schedule.sys.biz.service.SysAccountService;
import cn.com.yuzhushui.schedule.sys.biz.service.SysUserService;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月15日下午10:43:34
 **/

@Controller
@RequestMapping(SysMainAction.ACTION_PATH)
public class SysMainAction {
	
	protected Logger logger=LoggerFactory.getLogger(SysMainAction.class);
	
	protected static final String ACTION_PATH = "/sys/sysMain";
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysAccountService sysAccountService;
	
	/**进入登陆页面**/
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/index");
		return modelView;
	}
}
