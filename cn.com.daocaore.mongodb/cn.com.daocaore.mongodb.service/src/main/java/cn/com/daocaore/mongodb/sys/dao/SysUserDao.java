package cn.com.daocaore.mongodb.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.com.daocaore.mongodb.sys.entity.SysUser;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月14日下午5:32:36
 **/
@Repository
public class SysUserDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public SysUser findById(Integer id){
		return mongoTemplate.findById(id, SysUser.class);
	}
	
	public void saveOrUpdate(SysUser sysUser){
		mongoTemplate.save(sysUser);
	}
	
	public List<SysUser> list(String name){
		Query query = new Query();
		Criteria criteria = new Criteria();
		query.addCriteria(criteria);
		List<SysUser> sysUsers=null;
		try {
			sysUsers = mongoTemplate.find(query, SysUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysUsers;
	}

}
