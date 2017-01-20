package com.tw.controller.sys;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.tw.entity.sys.Permission;
import com.tw.entity.sys.Roles;
import com.tw.entity.sys.RolesPermissionRel;
import com.tw.entity.sys.RolesPermissionRelId;
import com.tw.entity.sys.Tmenu;
import com.tw.entity.sys.User;
import com.tw.entity.sys.UserMenuRel;
import com.tw.entity.sys.UserMenuRelId;
import com.tw.entity.sys.UserRoleRel;
import com.tw.entity.sys.UserRoleRelId;
import com.tw.interceptor.Perm;
import com.tw.service.sys.MenuService;
import com.tw.service.sys.PermissionService;
import com.tw.service.sys.RolesPermissionRelService;
import com.tw.service.sys.RolesService;
import com.tw.service.sys.UserMenuRelService;
import com.tw.service.sys.UserRoleRelService;
import com.tw.service.sys.UserService;
import com.tw.util.BaseUtil;
import com.tw.util.DataGrid;
import com.tw.util.EmailSender;
import com.tw.util.Json;
import com.tw.util.MD5;
import com.tw.vo.sys.UserVo;
import com.tw.webSocket.SystemWebSocketHandler;
/**
 * 用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleRelService userRoleRelService;
	@Autowired
	private UserMenuRelService userMenuRelService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolesPermissionRelService rolesPermissionRelService;
	@Autowired
	private MenuService menuService;
	
	@Bean
	public SystemWebSocketHandler systemWebSocketHandler(){
		return new SystemWebSocketHandler();
	}
	
	@RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request,String msg){
        //无关代码都省略了
        systemWebSocketHandler().sendMessageToUsers(new TextMessage(msg));
//        systemWebSocketHandler().sendMessageToUser("admin", new TextMessage("你好"));
        return "";
    }
	
	/**
	 * 用户列表查看UI
	 * @return
	 */
	@Perm(privilegeValue="userList")
	@RequestMapping("/listUI")
	public String listUI() {
		return "admin/user";
	}
	/**
	 * 内部员工注册
	 * @param uVo
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/reg")
	public Json reg(UserVo uVo,HttpSession session) {
		Json json = new Json();
		User u  = new User();
		u.setCancel("1");
		if (!BaseUtil.checkEmail(uVo.getEmail())) {
			json.setMsg("邮箱格式不正确");
			return json;
		}
		u.setEmail(uVo.getEmail().trim());
		if (!BaseUtil.isEmpty(uVo.getName())) {
			json.setMsg("用户名不能为空");
			return json;
		}
		if (userService.exsit("name", uVo.getName())) {
			json.setMsg("该用户用已经存在,请换一个哦！");
			return json;
		}
		u.setName(uVo.getName().trim());
		if (!BaseUtil.isEmpty(uVo.getPwd())) {
			json.setMsg("密码不能为空");
			return json;
		}
		u.setPwd(MD5.MD5Encode(uVo.getPwd()));
		
		try {
			userService.save(u);
			session.setAttribute("U", u);
			json.setMsg("注册用户成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("注册用户失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 外网用户注册
	 * @param uVo
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/regu")
	public Json regu(UserVo uVo,HttpSession session) {
		Json json = new Json();
		if (!BaseUtil.isEmpty(uVo.getName())) {
			json.setMsg("用户名不能为空");
			return json;
		}
		if (userService.exsit("name", uVo.getName())) {
			json.setMsg("该用户用已经存在,请换一个哦！");
			return json;
		}
		if (!BaseUtil.isEmpty(uVo.getPwd())) {
			json.setMsg("密码不能为空");
			return json;
		}
		/*String safeCode = (String) session.getAttribute("captchaToken");
		if (!BaseUtil.convertString(safeCode).equals(uVo.getSafecode().trim())) {
			json.setMsg("验证码不正确");
			return json;
		}*/
		if (!BaseUtil.checkEmail(uVo.getEmail())) {
			json.setMsg("邮箱格式不正确");
			return json;
		}else{
			//判断是否是公司员工
			if (!uVo.getEmail().contains("unionman.com.cn") && !uVo.getEmail().contains("company.um")) {
				if (!BaseUtil.isEmpty(uVo.getInvite())) {
					json.setMsg("邀请码不能为空");
					return json;
				}
				if (!userService.exsit("name", uVo.getInvite())) {
					json.setMsg("该邀请码不存在");
					return json;
				}
			}
		}
		if (userService.exsit("email", uVo.getEmail().trim())) {
			json.setMsg("该邮箱已经存在，如果你以前注册过，可以由此找回用户名和密码。");
			return json;
		}
		
		User u  = new User();
		u.setCancel("0");
		u.setPwd(MD5.MD5Encode(uVo.getPwd()));
		u.setEmail(uVo.getEmail().trim());
		u.setName(uVo.getName().trim());
		try {
			userService.save(u);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("注册用户失败");
			return json;
		}
		try {
			VelocityContext context = new VelocityContext();
			Template template = Velocity.getTemplate("yanzheng.vm");
			context.put("username", u.getName());
			context.put("validateCode", u.getName()+u.getPwd());
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			sw.flush();
			String mailContent = sw.toString();
			EmailSender.send(u.getEmail(), "验证邮箱", mailContent);
			
			json.setMsg("注册用户成功，请尽快去邮箱验证,如有延迟请耐心等待，谢谢！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("注册用户成功，但邮件发送失败，请您联系相关人员解决，谢谢！");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 管理员添加用户
	 * @param uVo
	 * @return
	 */
	@Perm(privilegeValue="userAdd")
	@ResponseBody
	@RequestMapping("/add")
	public Json add(UserVo uVo) {
		Json json = new Json();
		User u  = null;
		if (BaseUtil.isEmpty(uVo.getId())) {
			u = userService.find(uVo.getId());
		}else {
			u  = new User();
		}
		u.setEmail(uVo.getEmail().trim());
		if (userService.exsit("name", uVo.getName())) {
			json.setMsg("该用户用已经存在,请换一个哦！");
			return json;
		}
		u.setName(uVo.getName().trim());
		u.setPwd(MD5.MD5Encode(uVo.getPwd().trim()));
		
		try {
			userService.update(u);
			json.setMsg("用户添加/修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("用户添加/修改失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 管理员修改用户UI
	 * @param id
	 * @param model
	 * @return
	 */
	@Perm(privilegeValue="userEdit")
	@RequestMapping("/editUI")
	public String editUI(Integer id,Model model) {
		User u = userService.find(id);
		model.addAttribute("u", u);
		return "admin/user_add";
	}
	/**
	 * 内部员工登陆
	 * @param name
	 * @param pwd
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Json login(String name,String pwd,String remember,Model model,HttpSession session,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		Json json = new Json();
		if (userService.exsit("name", name.trim(), "pwd", MD5.MD5Encode(pwd.trim()))) {
			User u = userService.findEntity("name", name.trim(), "pwd", MD5.MD5Encode(pwd.trim()));
			if (u.getCancel().equals("1")) {
				if (BaseUtil.isEmpty(remember) && "yes".equals(remember.trim())) {
					Cookie cookie = new Cookie("userCookie", URLEncoder.encode(u.getName(), "UTF-8") + ","  + u.getPwd());
					cookie.setMaxAge(60 * 60 * 24 * 14);//保存两周
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				session.setAttribute("U", u);
				json.setMsg("登陆成功");
				json.setSuccess(true);
			}else {
				json.setMsg("对不起你的账号还没有通过邮箱验证");
			}
		}else {
			json.setMsg("用户名或密码错误");
		}
		return json;
	}
	/**
	 * 内部员工登出
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public Json logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		Json j = new Json();
		if (session != null) {
//			session.invalidate();
			session.removeAttribute("U");
		}
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userCookie".equals(cookie.getName())) {
                	Cookie cookie2 = new Cookie("userCookie", null);
                    cookie2.setMaxAge(0);
                    cookie2.setPath("/");
                    response.addCookie(cookie2);
                    break;
                }
            }
        }
		j.setSuccess(true);
		j.setMsg("注销成功！");
		return j;
	}
	/**
	 * 用户列表展示
	 * @param uVo
	 * @return
	 */
	@Perm(privilegeValue="userList")
	@ResponseBody
	@RequestMapping("/list")
	public DataGrid<User> list(UserVo uVo) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		return userService.getScrollData(uVo.getPage(), uVo.getRows(),orderby);
	}
	/**
	 * 管理员删除用户
	 * @param id
	 * @return
	 */
	@Perm(privilegeValue="userDelete")
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json json = new Json();
		try {
			userRoleRelService.deleteByUserId(id);
			userService.delete(id);
			json.setMsg("用户删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("用户删除失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 管理员添加角色UI
	 * @param id
	 * @param model
	 * @return
	 */
	@Perm(privilegeValue="userRole")
	@RequestMapping("/roleUI")
	public String roleUI(Integer id,Model model) {
		List<UserRoleRel> ur = userRoleRelService.findByProperty("id.userId", id);
		model.addAttribute("userId", id);
		model.addAttribute("ur", ur);
		return "admin/user_role";
	}
	/**
	 * 管理员添加角色
	 * @param id
	 * @param ids
	 * @return
	 */
	@Perm(privilegeValue="userRole")
	@RequestMapping("/role")
	@ResponseBody
	public Json role(Integer id,String ids) {
		Json json = new Json();
		try {
			userRoleRelService.deleteByUserId(id);
			if (BaseUtil.isEmpty(ids)) {
				String[] rids = ids.split(",");
				for (String s : rids) {
					userRoleRelService.save(new UserRoleRel(new UserRoleRelId(id, Integer.valueOf(s))));
				}
			}
			json.setMsg("授权成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("授权失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 管理员添加菜单UI
	 * @param id
	 * @param model
	 * @return
	 */
	@Perm(privilegeValue="userMenu")
	@RequestMapping("/menuUI")
	public String menuUI(Integer id,Model model) {
		List<UserMenuRel> userMenuRels = userMenuRelService.findByProperty("id.userId", id);
		model.addAttribute("userId", id);
		model.addAttribute("um", userMenuRels);
		return "admin/user_menu";
	}
	/**
	 * 管理员添加菜单
	 * @param id
	 * @param ids
	 * @return
	 */
	@Perm(privilegeValue="userMenu")
	@RequestMapping("/menu")
	@ResponseBody
	public Json menu(Integer id,String ids) {
		Json json = new Json();
		try {
			userMenuRelService.deleteByUserId(id);
			if (BaseUtil.isEmpty(ids)) {
				String[] rids = ids.split(",");
				for (String s : rids) {
					userMenuRelService.save(new UserMenuRel(new UserMenuRelId(id, Integer.valueOf(s))));
				}
			}
			json.setMsg("菜单授权成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("菜单授权失败");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 内部员工修改密码
	 * @param pwd
	 * @param session
	 * @return
	 */
	@RequestMapping("/editPwd")
	@ResponseBody
	public Json editPwd(String pwd,HttpSession session) {
		Json json = new Json();
		if (BaseUtil.isEmpty(pwd.trim())) {
			User u = (User) session.getAttribute("U");
			u.setPwd(MD5.MD5Encode(pwd.trim()));
			try {
				userService.update(u);
				json.setMsg("修改成功了哈哈");
				json.setSuccess(true);
			} catch (Exception e) {
				json.setMsg("修改失败了");
				e.printStackTrace();
			}
			return json;
		}else {
			json.setMsg("密码是空的呀");
			return json;
		}
	}
	/**
	 * 内部员工修改个人资料
	 * @param uVo
	 * @param session
	 * @return
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public Json editUser(UserVo uVo,HttpSession session) {
		Json json = new Json();
		User u = (User) session.getAttribute("U");
		u.setEmail(uVo.getEmail());
		u.setEmailw(uVo.getEmailw());
		u.setPhone(uVo.getPhone());
		u.setMphone(uVo.getMphone());
		u.setRealName(uVo.getRealName());
		try {
			userService.update(u);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("资料修改失败了");
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 内部员工发送邀请函
	 * @param emails
	 * @param session
	 * @return
	 */
	@RequestMapping("/invite")
	@ResponseBody
	public Json invite(String emails,HttpSession session) {
		Json json = new Json();
		if (BaseUtil.isEmpty(emails.trim())) {
			User u = (User) session.getAttribute("U");
			StringTokenizer st = new StringTokenizer(emails.trim(), ";\r\n");
			List<String> ms = new ArrayList<String>();
			while (st.hasMoreElements()) {
				String code = st.nextToken();
				ms.add(code);
			}
			VelocityContext context = new VelocityContext();
			Template template = Velocity.getTemplate("notice.vm");
			context.put("username", u.getName());
			context.put("realname", u.getRealName());
			context.put("phone", u.getPhone());
			context.put("mphone", u.getMphone());
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			sw.flush();
			String mailContent = sw.toString();
			String[] addr = (String[]) ms.toArray();
			EmailSender.send(addr, "邀请函", mailContent);
//			for (String s : ms) {
//				EmailSender.send(s, "邀请函", mailContent);
//			}
			json.setSuccess(true);
		}else {
			json.setMsg("邮箱不能为空");
		}
		
		return json;
	}
	/**
	 * 用户验证
	 * @param username
	 * @param validateCode
	 * @return
	 */
	@RequestMapping("/verify")
	public String verify(String username,String validateCode) {
		if (BaseUtil.isEmpty(username.trim()) && BaseUtil.isEmpty(validateCode.trim())) {
			if (userService.exsit("name", username.trim())) {
				User u = userService.findEntity("name", username.trim());
				if ((username.trim()+u.getPwd()).equals(validateCode.trim())) {
					return "";
				}
			}
		}
		return "";
	}
	/**
	 * 找回密码第一步：发送验证邮箱
	 * @param safecode
	 * @param email
	 */
	@RequestMapping("/checkEmailForPwd")
	@ResponseBody
	public Json checkEmailForPwd(String safecode,String email,HttpSession session) {
		Json j = new Json();
		/*String safeCode = (String) session.getAttribute("captchaToken");
		if (!BaseUtil.convertString(safeCode).equals(safecode.trim())) {
			j.setMsg("验证码不正确");
			return j;
		}*/
		
		if (userService.exsit("email", email.trim())) {
			List<User> users = userService.findByProperty("email", email.trim());
			User u = users.get(0);
			
			VelocityContext context = new VelocityContext();
			Template template = Velocity.getTemplate("repwd.vm");
			context.put("userName", u.getName());
			context.put("email", u.getEmail());
			context.put("vcode", MD5.MD5Encode(u.getName()+u.getPwd()));
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			sw.flush();
			EmailSender.send(u.getEmail(), "找回密码", sw.toString());
			j.setMsg("邮箱已发送成功");
			j.setSuccess(true);
		}else {
			j.setMsg("该邮箱不存在！");
		}
		return j;
	}
	
	/**
	 * 找回密码第二步：验证邮箱
	 * @param email
	 * @param vcode
	 * @param model
	 * @return
	 */
	@RequestMapping("/findPwdUI")
	public String findPwdUI(String email,String vcode,Model model) {
		List<User> users = userService.findByProperty("email", email.trim());
		if (users.size()==1) {
			User u = users.get(0);
			if (MD5.MD5Encode(u.getName()+u.getPwd()).equals(vcode.trim())) {
				model.addAttribute("userId", u.getId());
				model.addAttribute("email", email.trim());
				model.addAttribute("vcode", vcode.trim());
				return "findPwd";
			}
		}
		return null;
	}
	@RequestMapping("/findPwd")
	@ResponseBody
	public Json findPwd(Integer userId,String pwd,String email,String vcode) {
		Json json = new Json();
		if (!BaseUtil.isEmpty(pwd.trim())) {
			json.setMsg("密码不能为空");
		}
		if (!BaseUtil.isEmpty(userId)) {
			json.setMsg("你不要捣乱");
		}
		List<User> users = userService.findByProperty("email", email.trim());
		if (users.size()==1) {
			User u = users.get(0);
			if (MD5.MD5Encode(u.getName()+u.getPwd()).equals(vcode.trim())) {
				User user = userService.find(userId);
				user.setPwd(MD5.MD5Encode(pwd.trim()));
				try {
					userService.update(user);
					json.setSuccess(true);
				} catch (Exception e) {
					json.setMsg("密码重置失败");
					e.printStackTrace();
				}
			}else {
				json.setMsg("你不要捣乱");
			}
		}else {
			json.setMsg("你不要捣乱");
		}
		return json;
	}
	
	/**
	 * 初始化系统数据
	 */
	@RequestMapping("/init")
	public void init() {
		User user = new User();
		user.setEmail("hong.li1@qq.com");
		user.setName("lihong");
		user.setPwd(MD5.MD5Encode("123456"));
		userService.save(user);
		User user1 = new User();
		user1.setEmail("yufan.liu@qq.com");
		user1.setName("liuyufan");
		user1.setPwd(MD5.MD5Encode("827212"));
		userService.save(user1);
		User user2 = new User();
		user2.setEmail("kehui.zhang@qq.com");
		user2.setName("zhangkehui");
		user2.setPwd(MD5.MD5Encode("91229"));
		userService.save(user2);
		User user3 = new User();
		user3.setEmail("yuxiu.chen@qq.com");
		user3.setName("chenyuxiu");
		user3.setPwd(MD5.MD5Encode("2014622"));
		userService.save(user3);
		User user4 = new User();
		user4.setEmail("jiahong.liu@qq.com");
		user4.setName("liujiahong");
		user4.setPwd(MD5.MD5Encode("123456"));
		userService.save(user4);
		User user5 = new User();
		user5.setEmail("11111@qq.com");
		user5.setName("cuiyanye");
		user5.setPwd(MD5.MD5Encode("123456ycy"));
		userService.save(user5);
		System.out.println(11111);
		
		if (userService.exsit("name", "admin")) {
			User u = userService.findEntity("name", "admin");
			userService.delete(u.getId());
		}
		User u = new User();
		u.setEmail("22222@qq.com");
		u.setName("admin");
		u.setPwd(MD5.MD5Encode("123456"));
		userService.save(u);
		
		User u1 = userService.findEntity("name", "admin");
		
		Roles r = new Roles();
		r.setRoleName("系统管理员");
		int rid = rolesService.getsysId("roleId")+1;
		r.setRoleId(rid);
		rolesService.save(r);
		
		List<Permission> ps = permissionService.getListEntitys();
		for (Permission p : ps) {
			rolesPermissionRelService.save(new RolesPermissionRel(new RolesPermissionRelId(rid, p.getPermissionId())));
		}
		
		userRoleRelService.save(new UserRoleRel(new UserRoleRelId(u1.getId(), rid)));
		
		List<Tmenu> ms = menuService.getListEntitys();
		for (Tmenu tmenu : ms) {
			userMenuRelService.save(new UserMenuRel(new UserMenuRelId(u1.getId(), tmenu.getId())));
		}
		
	}
}
