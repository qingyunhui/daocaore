package cn.com.daocaore.mongodb.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.daocaore.mongodb.sys.dao.SysUserDao;
import cn.com.daocaore.mongodb.sys.entity.SysUser;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:41:24
 **/
@Service
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	public SysUser getById(Integer id){
		return sysUserDao.findById(id);
	}
	
	public void saveOrUpdate(SysUser sysUser){
		sysUserDao.saveOrUpdate(sysUser);
	}
	
	public List<SysUser> queryByName(String name){
		return sysUserDao.list(name);
	}
	
}
