package com.tw.service.sys;

import java.util.List;

import com.tw.dao.base.BaseDAO;
import com.tw.entity.sys.Tmenu;
import com.tw.vo.sys.MenuVo;

public interface MenuService extends BaseDAO<Tmenu>{

	public List<MenuVo> allTree();
	
	public List<MenuVo> gettrees(Integer id);
}
