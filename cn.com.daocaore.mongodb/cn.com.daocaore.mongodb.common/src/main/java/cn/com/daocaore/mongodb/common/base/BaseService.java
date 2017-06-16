package cn.com.daocaore.mongodb.common.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午2:51:11
 **/
public interface BaseService<MODEL,KEY_TYPE> {
	
	/**
	 * <p>新增</p>
	 * @param model待操作的对象
	 * @return int 受影响的行数
	 * **/
	int insert(MODEL model);
	
	/**
	 * <p>批量新增</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int insertBatch(List<MODEL> models);
	
	/**
	 * <p>更新</p>
	 * @param model 待操作的对象
	 * @return int 受影响的行数
	 * */
	int update(MODEL model);
	
	/**
	 * <p>批量更新</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int updateBatch(List<MODEL> models);
	
	/**
	 * <p>根据给定id删除</p>
	 * @param id 
	 * @return int 受影响的行数
	 * */
	int deleteById(KEY_TYPE id,Class<?> clz);
	
	/**
	 * <p>根据给定ids列表删除</p>
	 * @param ids 
	 * @return int 受影响的行数
	 * */
	int delete(MODEL model);
	
	/**
	 * <p>根据给定id查询对应model</p>
	 * @param id
	 * @return Model 
	 * */
	MODEL getById(KEY_TYPE id,Class<?> clz);
	
	long queryCount(MODEL model);
	
	/**
	 * <p>根据给定参数查询</p>
	 * @param map
	 * @return List<Model> 
	 * */
	List<MODEL> query(MODEL model);
	
	DataTableInfo<MODEL> queryPage(HttpServletRequest request,MODEL model);

}
