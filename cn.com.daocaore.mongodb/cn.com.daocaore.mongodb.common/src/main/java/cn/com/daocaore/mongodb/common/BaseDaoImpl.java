package cn.com.daocaore.mongodb.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import qing.yun.hui.common.utils.BeanUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午2:57:19
 **/
public class BaseDaoImpl<MODEL extends BaseModel<KEY_TYPE>, KEY_TYPE> implements BaseDao<MODEL, KEY_TYPE>{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public void insert(MODEL model) {
		mongoTemplate.insert(model);
	}

	@Override
	public void insertBatch(List<MODEL> models) {
		mongoTemplate.insertAll(models);
	}

	@Override
	public void update(MODEL model) {
		/*
		 * Query query = new Query(Criteria.where("logUuid").is(ObjectUtils.toString(args[0], "")));  
           Update update = new Update().set("exitTime", exitTime);  
		*/
		Map<String,Object>map=BeanUtil.pojoToMap(model);
		if(!map.isEmpty()){
			Query  query=new Query (Criteria.where("_id").is(model.getId()));
			Update update=new Update ();
			Iterator<Entry<String, Object>> iterator= map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> entry= iterator.next();
				String key=entry.getKey();
				Object value=entry.getValue();
				update.set(key, value);
			}
			mongoTemplate.upsert(query, update, model.getClass());
		}
	}

	@Override
	public void updateBatch(List<MODEL> models) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(KEY_TYPE id) {
		
		mongoTemplate.remove(id);

	}

	@Override
	public void deleteBatch(List<KEY_TYPE> ids) {
		// TODO Auto-generated method stub
	}

	@Override
	public MODEL getById(KEY_TYPE id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MODEL> query(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MODEL> queryPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
