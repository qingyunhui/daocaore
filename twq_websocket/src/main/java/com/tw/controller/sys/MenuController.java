package com.tw.controller.sys;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tw.entity.sys.Tmenu;
import com.tw.interceptor.Perm;
import com.tw.service.sys.MenuService;
import com.tw.util.Json;
import com.tw.vo.sys.MenuVo;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/init")
	public void init() {
		/*Tmenu tm = new Tmenu();
		tm.setText("系统管理");
		tm.setType("0");
		menuService.save(tm);
		Tmenu tm01 = new Tmenu();
		tm01.setText("菜单管理");
		tm01.setTmenu(tm);
		tm01.setUrl("/admin/menu.jsp");
		tm01.setType("1");
		menuService.save(tm01);*/
		
		/*List<Tmenu> tmenus = menuService.getListIsNull("tmenu");
		for (int i = 0,h=tmenus.size(); i < h; i++) {
			Tmenu tmenu = tmenus.get(i);
			tmenu.setSeq(i);
			menuService.update(tmenu);
			
			List<Tmenu> tmenus2 = menuService.findByProperty("tmenu.id", tmenu.getId());
			for (int j = 0,h1=tmenus2.size(); j < h1; j++) {
				Tmenu tmenu2 = tmenus2.get(j);
				tmenu2.setSeq(j);
				menuService.update(tmenu2);
			}
		}*/
		System.out.println(11);
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<MenuVo> tree(MenuVo menu) {
		return menuService.gettrees(menu.getId());
	}
	
	@RequestMapping("/allTree")
	@ResponseBody
	public List<MenuVo> allTree() {
		return menuService.allTree();
	}
	
	@Perm(privilegeValue="menuAdd")
	@RequestMapping("/add")
	@ResponseBody
	public Json add(MenuVo menu) {
		Tmenu tmenu = null;
		if (menu.getId()==null) {
			tmenu = new Tmenu();
		}else {
			tmenu = menuService.find(menu.getId());
			tmenu.setUpdateDate(new Date());
		}
		tmenu.setText(menu.getName());
		tmenu.setUrl(menu.getUrl());
		tmenu.setType(menu.getType());
		if (menu.getPid()!=null) {
			tmenu.setTmenu(new Tmenu(menu.getPid()));
		}
		Json j = new Json();
		try {
			menuService.update(tmenu);
			j.setMsg("添加或修改成功");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	@Perm(privilegeValue="menuEdit")
	@RequestMapping("/editUI")
	public String editUI(Integer id,Model model) {
		model.addAttribute("menu", menuService.find(id));
		return "/admin/menu_add";
	}
	@Perm(privilegeValue="menuDelete")
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(MenuVo menu) {
		Json j = new Json();
		try {
			menuService.delete(menu.getId());
			j.setMsg("删除成功");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
}
