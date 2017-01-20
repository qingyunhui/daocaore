package com.tw.interceptor;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tw.entity.sys.Permission;
import com.tw.entity.sys.RolesPermissionRel;
import com.tw.entity.sys.User;
import com.tw.entity.sys.UserRoleRel;
import com.tw.service.sys.PermissionService;
import com.tw.service.sys.RolesPermissionRelService;
import com.tw.service.sys.UserRoleRelService;
import com.tw.service.sys.UserService;
import com.tw.util.BaseUtil;

public class PermissionInterceptor implements HandlerInterceptor {

	@Autowired
	private UserRoleRelService userRoleRelService;
	@Autowired
	private RolesPermissionRelService rolesPermissionRelService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	private List<String> excludeUrls;
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (excludeUrls.contains(url)) {
			return true;
		}
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("U");
		if (null==u) {
			Cookie[] cookies = request.getCookies();
			if (cookies!=null && cookies.length>0) {
				for (Cookie cookie : cookies) {
					if ("userCookie".equals(cookie.getName())) {
						String name = URLDecoder.decode(cookie.getValue(),"UTF-8");;
						if (BaseUtil.isEmpty(name)) {
							String[] ss = name.split(",");
							if (userService.exsit("name", ss[0].trim(), "pwd", ss[1].trim())) {
								u = userService.findEntity("name", ss[0].trim(), "pwd", ss[1].trim());
								session.setAttribute("U", u);
								break;
							}
						}
					}
				}
			}
		}
		if (null==u) {
			response.sendRedirect("login.jsp");
			return false;
		}
		
		HandlerMethod method = (HandlerMethod)handler;
        Perm perm = method.getMethodAnnotation(Perm.class);
		if (perm==null) {
			return true;
		}
		List<UserRoleRel> ur = userRoleRelService.findByProperty("id.userId", u.getId());
		for (UserRoleRel userRoleRel : ur) {
			List<RolesPermissionRel> rp = rolesPermissionRelService.findByProperty("id.roleId", userRoleRel.getId().getRoleId());
			for (RolesPermissionRel rolesPermissionRel : rp) {
				Permission permission = permissionService.find(rolesPermissionRel.getId().getPermissionId());
				if (perm.privilegeValue().equals(permission.getPermissionCode())) {
					return true;
				}
			}
		}
		request.getRequestDispatcher("/error/noSecurity.jsp").forward(request, response);
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
