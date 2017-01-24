package cn.com.yuzhushui.websocket.common.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.websocket.common.util.SessionUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午2:25:54
 **/
@Service
public class BaseServiceImpl<MODEL extends BaseModel<KEY_TYPE>, KEY_TYPE> implements BaseService<MODEL , KEY_TYPE>{

	@Autowired
	private BaseDao<MODEL, KEY_TYPE> baseDao;
	
	private final BaseDao<MODEL, KEY_TYPE> getBaseDao() {
		return baseDao;
	}
	
	public int add(MODEL model) {
		beforeHand(model);
		int count = getBaseDao().insert(model);
		return count;
	}
	public int add(List<MODEL> models) {
		for(MODEL model:models){
			beforeHand(model);
		}
		int count = getBaseDao().insertBatch(models);
		return count;
	}
	public int update(MODEL model) {
		afterHand(model);
		int count = getBaseDao().update(model);
		return count;
	}
	public int update(List<MODEL> models) {
		for(MODEL model:models){
			afterHand(model);
		}
		int count = getBaseDao().updateBatch(models);
		return count;
	}
	
	public int delete(KEY_TYPE id) {
		return getBaseDao().delete(id);
	}
	
	public int delete(List<KEY_TYPE> ids) {
		return getBaseDao().deleteBatch(ids);
	}
	
	public MODEL query(KEY_TYPE id) {
		return getBaseDao().queryById(id);
	}
	
	public List<MODEL> query(Map<String, Object> map) {
		return getBaseDao().query(map);
	}
	
	public PageInfo<MODEL> queryPage(BaseQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		return new PageInfo<MODEL>(query(query.getQueryData()));
	}
	
	/**
	 * <p>调用接口处理model</p>
	 * @param model 待处理的model
	 * @return void
	 * */
	protected void beforeHand(MODEL model){
		List<PluginBeforeService> pluginBeforeService = SessionUtil.getBeansOfType(PluginBeforeService.class);
		for (PluginBeforeService plugin : pluginBeforeService) {
			plugin.process(model);
		}
	}
	
	protected void afterHand(MODEL model){
		List<PluginAfterService> pluginAfterService = SessionUtil.getBeansOfType(PluginAfterService.class);
		for (PluginAfterService plugin : pluginAfterService) {
			plugin.process(model);
		}
	}
}
