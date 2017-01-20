package com.tw.dao.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.tw.util.DataGrid;

public interface BaseDAO<T> {
	/**
	 * 批量更新
	 * @param list
	 */
	public void batchUpdate(List<T> list);
	/**
	 * 原生sql查询
	 * @param sql
	 * @return
	 */
	public List<Object> getSqlQuery(String sql);
	/**
	 * 一个属性的实体集查询
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(String property,Object value);
	/**
	 * 两个属性的实体集查询
	 * @param property
	 * @param value
	 * @param property2
	 * @param value2
	 * @return
	 */
	public List<T> findByProperty(String property,Object value,String property2,Object value2);
	/**
	 * 一个属性的模糊实体集查询
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByPropertyauto(String property,Object value);
	/**
	 * 带有条件和排序的实体集查询
	 * @param wherejpql 属性
	 * @param queryParams 属性值
	 * @param orderby 排序
	 * @return
	 */
	public List<T> getListEntitys(String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 无任何条件的实体查询等同于全表查询
	 * @return
	 */
	public List<T> getListEntitys();
	/**
	 * 带有条件的实体集查询
	 * @param wherejpql
	 * @param queryParams
	 * @return
	 */
	public List<T> getListEntitys(String wherejpql, Object[] queryParams);
	/**
	 * 一个属性的单实体查询,没有返回null
	 * @param property
	 * @param value
	 * @return
	 */
	public T findEntity(String property,Object value);
	/**
	 * 两个属性的单实体查询,没有返回null
	 * @param property
	 * @param value
	 * @return
	 */
	public T findEntity(String property,Object value,String property2,Object value2);
	/**
	 * 查询某个属性值为null的实体集
	 * @param propertyName
	 * @return
	 */
	public List<T> getListIsNull(String propertyName);
	/**
	 * 判断唯一性
	 * @param entity
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean exsit(String field,Object value);
	public boolean exsit(String field,Object value,String field1,Object value1);

	/**
	 * 获取记录总数
	 * @param entityClass 实体类
	 * @return
	 */
	public long getCount();
	
	public Long countByLike(String property,Object value);
	/**
	 * 清除一级缓存的数据
	 */
	public void clear();
	/**
	 * 保存实体
	 * @param entity 实体id
	 */
	public void save(Object entity);
	/**
	 * 更新实体
	 * @param entity 实体id
	 */
	public void update(Object entity);
	/**
	 * 删除实体
	 * @param entityClass 实体类
	 * @param entityids 实体id数组
	 */
	public void delete(Serializable ... entityids);
	/**
	 * 获取实体
	 * @param <T>
	 * @param entityClass 实体类
	 * @param entityId 实体id
	 * @return
	 */
	public T find(Serializable entityId);
	/**
	 * 获取最大值
	 * @param entityid
	 * @return
	 */
	public int getsysId(String entityid);
	
	public DataGrid<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	public DataGrid<T> getScrollData(int page, int rows);
	public DataGrid<T> getScrollData(int page, int rows,LinkedHashMap<String, String> orderby);
	
	public List<Object> getObjects(String propertyNames,String wherejpql, Object[] queryParams,String propertyNames2,LinkedHashMap<String, String> orderby);
	public List<Object> getObjects(String propertyNames,String wherejpql, Object[] queryParams,String propertyNames2);
	public List<Object> getObjects(String propertyNames,String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	public List<Object> getObjects(String propertyNames,String wherejpql, Object[] queryParams);
	public List<Object> getObjects(String propertyNames,String propertyNames2);
}
