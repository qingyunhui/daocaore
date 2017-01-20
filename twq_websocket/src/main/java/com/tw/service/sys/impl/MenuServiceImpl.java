package com.tw.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dao.base.BaseDAOSupport;
import com.tw.entity.sys.Tmenu;
import com.tw.service.sys.MenuService;
import com.tw.vo.sys.MenuVo;

@Service("menuService")
public class MenuServiceImpl extends BaseDAOSupport<Tmenu> implements MenuService{

	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<MenuVo> allTree() {
		List<Tmenu> tmenus = (List<Tmenu>) getListEntitys();
		List<MenuVo> menus = new ArrayList<MenuVo>();
		MenuVo m = null;
		for (Tmenu tmenu : tmenus) {
			m = new MenuVo();
			m.setId(tmenu.getId());
			m.setIconcls(tmenu.getIconcls());
			m.setName(tmenu.getText());
			m.setUrl(tmenu.getUrl());
			m.setCreateDate(tmenu.getCreateDate());
			m.setUpdateDate(tmenu.getUpdateDate());
			m.setType(tmenu.getType());
			Tmenu t = tmenu.getTmenu();
			if (t!=null) {
				m.setPid(t.getId());
				m.setPname(t.getText());
			}
			menus.add(m);
		}
		return menus;
	}
	
	public List<MenuVo> gettrees(Integer id) {
		List<Tmenu> tmenus = null;
		if (id==null) {
			tmenus = getListIsNull("tmenu");
		}else {
			tmenus = findByProperty("tmenu.id", id);
		}
		List<MenuVo> menus = new ArrayList<MenuVo>();
		MenuVo m = null;
		for (Tmenu tmenu : tmenus) {
			m = new MenuVo();
			BeanUtils.copyProperties(tmenu, m);
			Set<Tmenu> tmenus2 = tmenu.getTmenus();
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", tmenu.getUrl());
			m.setAttributes(attributes);
			if (tmenus2!=null && !tmenus2.isEmpty()) {
				m.setState("closed");
			}else {
				m.setState("open");
			}
			menus.add(m);
		}
		return menus;
	}
}
