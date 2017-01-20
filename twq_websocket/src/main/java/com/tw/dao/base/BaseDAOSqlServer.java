package com.tw.dao.base;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tw.util.DataGrid;
import com.tw.util.GenericsUtils;

//@Repository
//@Transactional(value="sqlserverEM")
public class BaseDAOSqlServer<T> implements BaseDAO<T> {
	@SuppressWarnings("unchecked")
	private Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
//	@PersistenceContext(unitName="sqlserverdb")
	protected EntityManager em;
	
	public void clear(){
		em.clear();
	}
	
	public void batchUpdate(List<T> list) {
		for (int i = 0,h=list.size(); i < h; i++) {
			em.merge(list.get(i));
			if (i%30==0) {
				em.flush();
				em.clear();
			}
		}
	}

	public void delete(Serializable ... entityids) {
		for(Object id : entityids){
			em.remove(em.getReference(this.entityClass, id));
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> getListEntitys(String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		Query query = em.createQuery("select o from "+getEntityName(this.entityClass)+" o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
		setQueryParams(query, queryParams);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findByProperty(String property,Object value) {
		String sql = "select o from "+getEntityName(this.entityClass)+" o where o."+property+"=?1";
		return em.createQuery(sql).setParameter(1, value).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findByProperty(String property,Object value,String property2,Object value2) {
		String sql = "select o from "+getEntityName(this.entityClass)+" o where o."+property+"=?1 and o."+property2+"=?2";
		return em.createQuery(sql).setParameter(1, value).setParameter(2, value2).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T findEntity(String property,Object value) {
		String sql = "select o from "+getEntityName(this.entityClass)+" o where o."+property+"=?1";
		return (T) em.createQuery(sql).setParameter(1, value).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T findEntity(String property,Object value,String property2,Object value2) {
		String sql = "select o from "+getEntityName(this.entityClass)+" o where o."+property+"=?1 and o."+property2+"=?2";
		return (T) em.createQuery(sql).setParameter(1, value).setParameter(2, value2).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<T> findByPropertyauto(String property,Object value) {
		String sql = "select o from "+getEntityName(this.entityClass)+" o where o."+property+" like ?1";
		return em.createQuery(sql).setParameter(1, "%"+value+"%").getResultList();
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Long countByLike(String property,Object value) {
		String sql = "select count(o) from "+getEntityName(this.entityClass)+" o where o."+property+" like ?1";
		return (Long)em.createQuery(sql).setParameter(1, "%"+value+"%").getSingleResult();
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getsysId(String entityid){
		String sql = "select max("+entityid+") from "+getEntityName(this.entityClass)+"";
		Integer id = 0;
		if (em.createQuery(sql).getSingleResult()!=null) {
			id=(Integer) em.createQuery(sql).getSingleResult();
		}
		return id;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public String getsysIdk(String sysId) {
		Query query = em.createNativeQuery("select (nextval for "+sysId+") from SYSIBM.SYSDUMMY1");
		return query.getResultList().get(0).toString();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit(String field,Object value){
		String sql = "select count(o) from "+getEntityName(this.entityClass)+" o where o."+field+"=?1";
		long count = (Long)em.createQuery(sql).setParameter(1, value).getSingleResult();
		return count>0;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		return em.find(this.entityClass, entityId);
	}

	public void save(Object entity) {
		em.persist(entity);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long)em.createQuery("select count("+ getCountField(this.entityClass) +") from "+ getEntityName(this.entityClass)+ " o").getSingleResult();
	}
	
	public void update(Object entity) {
		em.merge(entity);
		em.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public DataGrid<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		DataGrid<T> dataGrid = new DataGrid<T>();
		String entityname = getEntityName(this.entityClass);
		Query query = em.createQuery("select o from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql)+ buildOrderby(orderby));
		setQueryParams(query, queryParams);
		firstindex = firstindex-1;
		if(firstindex!=-1 && maxresult!=-1) query.setFirstResult(firstindex*maxresult).setMaxResults(maxresult);
		dataGrid.setRows(query.getResultList());
		query = em.createQuery("select count("+ getCountField(this.entityClass)+ ") from "+ entityname+ " o "+(wherejpql==null || "".equals(wherejpql.trim())? "": "where "+ wherejpql));
		setQueryParams(query, queryParams);
		dataGrid.setTotal((Long)query.getSingleResult());
		return dataGrid;
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public DataGrid<T> getScrollData(int page, int rows) {
		return getScrollData(page, rows, null, null, null);
	}
	
	@Override
	public DataGrid<T> getScrollData(int page, int rows,LinkedHashMap<String, String> orderby) {
		return getScrollData(page, rows,null,null,orderby);
	}
	
	protected static void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	/**
	 * 组装order by语句
	 * @param orderby
	 * @return
	 */
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取实体的名称
	 * @param <E>
	 * @param clazz 实体类
	 * @return
	 */
	protected static <E> String getEntityName(Class<E> clazz){
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
	
	protected static <E> String getCountField(Class<E> clazz){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){					
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getSqlQuery(String sql){
		return em.createNativeQuery(sql).getResultList();
	}

	@Override
	public List<T> getListEntitys() {
		return null;
	}

	@Override
	public List<T> getListIsNull(String propertyName) {
		return null;
	}

	@Override
	public boolean exsit(String field, Object value, String field1,
			Object value1) {
		return false;
	}

	@Override
	public List<Object> getObjects(String propertyNames, String wherejpql,
			Object[] queryParams, String propertyNames2,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getObjects(String propertyNames, String wherejpql,
			Object[] queryParams, String propertyNames2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getObjects(String propertyNames, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getObjects(String propertyNames, String wherejpql,
			Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getObjects(String propertyNames, String propertyNames2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListEntitys(String wherejpql, Object[] queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
