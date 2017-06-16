package cn.com.daocaore.mongodb.common.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日上午11:57:47
 **/
public interface BaseDao<MODEL extends BaseModel<KEY_TYPE>,KEY_TYPE> {

	/**
	 * <p>新增</p>
	 * @param model待操作的对象
	 * @return int 受影响的行数
	 * **/
	void insert(MODEL model);
	
	/**
	 * <p>批量新增</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	void insertBatch(List<MODEL> models);
	
	/**
	 * <p>更新</p>
	 * @param model 待操作的对象
	 * @return int 受影响的行数
	 * */
	void update(MODEL model);
	
	/**
	 * <p>批量更新</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	void updateBatch(List<MODEL> models);
	
	/**
	 * <p>根据给定model删除，model中必须指定id</p>
	 * @param id 
	 * @return int 受影响的行数
	 * */
	void delete(MODEL model);
	
	void deleteById(KEY_TYPE id,Class<?> clz);
	
	/**
	 * <p>根据给定id查询对应model</p>
	 * @param id
	 * @return Model 
	 * */
	MODEL getById(KEY_TYPE id,Class<?> clz);
	
	/**
	 * @param model
	 * @param
	 * */
	long queryCount(MODEL model);
	
	/**
	 * <p>根据给定参数查询</p>
	 * @param map
	 * @return List<Model> 
	 * */
	List<MODEL> query(MODEL model);
	
	/**
	 * <p>根据给定条件，分页</p>
	 * @param request
	 * @param model
	 * @return DataTableInfo
	 * */
	DataTableInfo<MODEL> queryPage(HttpServletRequest request,MODEL model);
	
}
