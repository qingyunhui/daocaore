package cn.com.daocaore.mongodb.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.daocaore.mongodb.sys.dao.SysAccountDao;
import cn.com.daocaore.mongodb.sys.entity.SysAccount;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:38:30
 **/
@Service
public class SysAccountService {

	@Autowired
	private SysAccountDao sysAccountDao;
	
	public SysAccount getById(Integer id){
		return sysAccountDao.findById(id);
	}
	
	public void saveOrUpdate(SysAccount sysAccount){
		sysAccountDao.saveOrUpdate(sysAccount);
	}
	
	public List<SysAccount> queryByAccount(String account){
		return sysAccountDao.list(account);
	}
	
}
