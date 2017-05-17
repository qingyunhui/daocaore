package cn.com.daocaore.bms.common.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.daocaore.bms.common.bean.DataTableInfo;

import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午1:27:34
 **/
public interface BaseService<MODEL, KEY_TYPE>{

	/***
	 * <p>新增</p>
	 * @param model 待新增的对象
	 * @return int 受影响的行数
	 * */
	public int add(MODEL model);

	/***
	 * <p>批量新增</p>
	 * @param models 待新增的对象集合
	 * @return int 受影响的行数
	 * */
	public int add(List<MODEL> models);

	/***
	 * <p>更新</p>
	 * @param model 待更新的对象
	 * @return int 受影响的行数
	 * */
	public int update(MODEL model);

	/***
	 * <p>批量更新</p>
	 * @param models 待更新的对象集合
	 * @return int 受影响的行数
	 * */
	public int update(List<MODEL> models);

	/***
	 * <p>删除</p>
	 * @param id 待删除的id
	 * @return int 受影响的行数
	 * */
	public int delete(KEY_TYPE id);

	/***
	 * <p>批量删除</p>
	 * @param ids 待删除的ids
	 * @return int 受影响的行数
	 * */
	public int delete(List<KEY_TYPE> ids);

	/***
	 * <p>根据id查询对象</p>
	 * @param id 待查询的id
	 * @return MODEL 
	 * */
	public MODEL query(KEY_TYPE id);

	/***
	 * <p>根据map查询对象</p>
	 * @param map 待查询的map
	 * @return List<MODEL> 
	 * */
	public List<MODEL> query(Map<String, Object> map);

	/***
	 * <p>根据给定条件查询且分页</p>
	 * @param query 
	 * @return PageInfo<MODEL> 
	 * */
	public PageInfo<MODEL> queryPage(BaseQuery query);
	
	/***
	 * <p>根据给定条件查询且分页</p>
	 * @param request 
	 * @param model 
	 * @return DataTableInfo<MODEL> 
	 * */
	public DataTableInfo<MODEL> queryPages(HttpServletRequest request,MODEL model);
	
}
