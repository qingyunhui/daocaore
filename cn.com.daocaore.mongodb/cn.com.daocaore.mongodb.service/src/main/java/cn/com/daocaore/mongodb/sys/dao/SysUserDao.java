package cn.com.daocaore.mongodb.sys.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import qing.yun.hui.common.utils.BeanUtil;

import com.mongodb.WriteResult;

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
	
	public void deleteByAccountId(Integer accountId){
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("account_id").is(accountId));
		query.addCriteria(criteria);
		WriteResult result=mongoTemplate.remove(query, SysUser.class);
		int count=result.getN();
		System.out.println("getN:"+count);
		Object upsertedId=result.getUpsertedId();
		System.out.println("upsertedId:"+upsertedId);
	}
	
	public void saveOrUpdate(SysUser sysUser){
		mongoTemplate.save(sysUser);
	}
	
	public void updateById(SysUser sysUser){
		/*
		 * Query query = new Query(Criteria.where("logUuid").is(ObjectUtils.toString(args[0], "")));  
           Update update = new Update().set("exitTime", exitTime);  
		*/
		Map<String,Object>map=BeanUtil.pojoToMap(sysUser);
		if(!map.isEmpty()){
			Query  query=new Query (Criteria.where("_id").is(sysUser.getId()));
			Update update=new Update ();
			Iterator<Entry<String, Object>> iterator= map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> entry= iterator.next();
				String key=entry.getKey();
				Object value=entry.getValue();
				update.set(key, value);
			}
			mongoTemplate.upsert(query, update, sysUser.getClass());
		}
	}
	
	public void saveBatch(List<SysUser> sysUserList){
		mongoTemplate.insertAll(sysUserList);
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
