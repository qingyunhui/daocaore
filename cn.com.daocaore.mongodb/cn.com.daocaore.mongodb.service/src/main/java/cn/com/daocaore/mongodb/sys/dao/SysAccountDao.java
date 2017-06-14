package cn.com.daocaore.mongodb.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.com.daocaore.mongodb.sys.entity.SysAccount;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:37:06
 **/
@Repository
public class SysAccountDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public SysAccount findById(Integer id){
		return mongoTemplate.findById(id, SysAccount.class);
	}
	
	public void saveOrUpdate(SysAccount sysAccount){
		mongoTemplate.save(sysAccount);
	}
	
	public List<SysAccount> list(String account){
		Query query = new Query();
		Criteria criteria = new Criteria("account").is(account);
		query.addCriteria(criteria);
		List<SysAccount> sysAccounts = mongoTemplate.find(query, SysAccount.class);
		return sysAccounts;
	}
	
}
