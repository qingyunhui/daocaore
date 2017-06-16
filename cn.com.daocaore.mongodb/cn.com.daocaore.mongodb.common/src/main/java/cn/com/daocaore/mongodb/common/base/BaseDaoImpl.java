package cn.com.daocaore.mongodb.common.base;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.daocaore.mongodb.common.beans.DataTableInfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午2:57:19
 **/
@Repository
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
		if(StringUtil.isEmpty(model,model.getId())) return;
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
		for(MODEL model:models){
			update(model);
		}
	}

	@Override
	public void delete(MODEL model) {
		mongoTemplate.remove(model);
	}

	@Override
	public void deleteById(KEY_TYPE id,Class<?> clz){
		mongoTemplate.remove(new Query (Criteria.where("_id").is(id)), clz);  
	}

	@SuppressWarnings("unchecked")
	@Override
	public MODEL getById(KEY_TYPE id,Class<?> clz) {
	     return (MODEL)mongoTemplate.findById(id, clz);
	}

	@Override
	public long queryCount(MODEL model) {
		if(null==model) return 0;
		Query query=buildQuery(model);
		return mongoTemplate.count(query,model.getClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MODEL> query(MODEL model) {
		if(null==model) return null;
		Query  query=buildQuery(model);
		return (List<MODEL>)mongoTemplate.find(query, model.getClass());
	}

	/**
	 * <p>根据给定model构造Query查询条件</p>
	 * @param model
	 * @return Query
	 * */
	protected Query buildQuery(MODEL model){
		Query  query=new Query();
		if(null==model) return query;
		Map<String,Object> map=BeanUtil.pojoToMap(model);
		Iterator<Entry<String, Object>> iterator= map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry= iterator.next();
			String key=entry.getKey();
			Object value=entry.getValue();
			Criteria criteria= Criteria.where(key).is(value);
			query.addCriteria(criteria);
		}
		return query;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataTableInfo<MODEL> queryPage(HttpServletRequest request,MODEL model) {
		DataTableInfo<MODEL> dataTableInfo=new DataTableInfo<MODEL>(request);
		PageHelper.startPage(dataTableInfo.getStartRow(), dataTableInfo.getPageSize());
		long count=queryCount(model);
		dataTableInfo.setRecordsTotal(Integer.parseInt(count+""));
		dataTableInfo.setRecordsFiltered(Integer.parseInt(count+""));
		Query query=buildQuery(model);
		query.skip((dataTableInfo.getStartRow()) * dataTableInfo.getPageSize()).limit(dataTableInfo.getPageSize());  
		List<MODEL> list=(List<MODEL>) mongoTemplate.find(query, model.getClass());
		PageInfo<MODEL> pageInfo=new PageInfo<MODEL>(list);
		dataTableInfo.setData(pageInfo.getList());
		return dataTableInfo;
	}
}
