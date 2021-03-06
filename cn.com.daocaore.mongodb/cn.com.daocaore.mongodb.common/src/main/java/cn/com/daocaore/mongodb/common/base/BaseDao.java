package cn.com.daocaore.mongodb.common.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日上午11:57:47
 **/
@Repository
public interface BaseDao<MODEL extends BaseModel<KEY_TYPE>,KEY_TYPE> {

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
	List<MODEL> insertBatch(List<MODEL> models);
	
	/**
	 * <p>更新</p>
	 * @param model 待操作的对象
	 * @return int 受影响的行数
	 * */
	int update(MODEL model);
	
	/**
	 * <p>根据给定model删除，model中必须指定id</p>
	 * @param id 
	 * @return int 受影响的行数
	 * */
	int delete(MODEL model);
	
	int deleteById(KEY_TYPE id,Class<?> clz);
	
	/**
	 * <p>根据给定id查询对应model</p>
	 * @param id
	 * @param clz
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
	 * @param model
	 * @return List<Model> 
	 * */
	List<MODEL> query(MODEL model);
	
	/**
	 * <p>根据给定参数查询</p>
	 * @param model
	 * @param order
	 * @return List<Model> 
	 * */
	List<MODEL> query(MODEL model,Order ...orders);
	
	/**
	 * <p>根据给定条件，分页</p>
	 * @param request
	 * @param model
	 * @return DataTableInfo
	 * */
	DataTableInfo<MODEL> queryPage(HttpServletRequest request,MODEL model);
	
}
