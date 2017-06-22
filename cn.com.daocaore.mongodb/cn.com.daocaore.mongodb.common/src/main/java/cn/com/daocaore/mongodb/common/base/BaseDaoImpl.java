package cn.com.daocaore.mongodb.common.base;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
public class BaseDaoImpl<MODEL extends BaseModel<KEY_TYPE>, KEY_TYPE> implements BaseDao<MODEL, KEY_TYPE>{

	/*	   
	 *                            排序
	 * Sort ：sort () 已过时，现在是用query.with(sort)，with参数是sort类
	   Sort提供了几种构造函数
	   (1)一个字段的排序
        	例如onumber字段升序
        	query.with(new Sort(Direction.ASC,"onumber"));
       (2)如果是多个字段时同时升序或者降序时
        query.with(new Sort(Direction.ASC,"a","b","c"))
	   (3)不同的字段按照不同的排序  
	    List<Sort.Order>orders=new ArrayList<Sort.Order>();  
		orders.add(newSort.Order(Direction.ASC, "a"));  
		orders.add(newSort.Order(Direction.DESC, "b"));  
		query.with(newSort(orders ));  
	   *
	   */
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public int insert(MODEL model) {
		mongoTemplate.insert(model);
		return StringUtil.isEmpty(model.getId())?0:1;
	}

	@Override
	public List<MODEL> insertBatch(List<MODEL> models) {
		mongoTemplate.insertAll(models);
		return models;
	}

	@Override
	public int update(MODEL model) {
		int count=0;
		if(StringUtil.isEmpty(model,model.getId())) return count;
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
			return mongoTemplate.upsert(query, update, model.getClass()).getN();
		}
		return count;
	}

	@Override
	public int delete(MODEL model) {
		return mongoTemplate.remove(model).getN();
	}

	@Override
	public int deleteById(KEY_TYPE id,Class<?> clz){
		return mongoTemplate.remove(new Query (Criteria.where("_id").is(id)), clz).getN();  
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MODEL> query(MODEL model, Order ...orders) {
		if(null==model) return null;
		Query  query=buildQuery(model);
		if(null!=orders && orders.length>0) query.with(new Sort(orders));
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
