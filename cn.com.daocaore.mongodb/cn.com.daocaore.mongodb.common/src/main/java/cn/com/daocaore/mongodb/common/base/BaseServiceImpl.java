package cn.com.daocaore.mongodb.common.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午2:52:44
 **/
public class BaseServiceImpl<MODEL extends BaseModel<KEY_TYPE>,KEY_TYPE> implements BaseService<MODEL, KEY_TYPE>{

	@Autowired
	private BaseDao<MODEL, KEY_TYPE> baseDao;
	
	@Override
	public int insert(MODEL model) {
		baseDao.insert(model);	//TODO
		return 0;
	}

	@Override
	public int insertBatch(List<MODEL> models) {
		baseDao.insertBatch(models);
		return 0;
	}

	@Override
	public int update(MODEL model) {
		baseDao.update(model);
		return 0;
	}

	@Override
	public int updateBatch(List<MODEL> models) {
		baseDao.updateBatch(models);
		return 0;
	}

	@Override
	public int deleteById(KEY_TYPE id,Class<?> clz) {
		baseDao.deleteById(id, clz);
		return 0;
	}

	@Override
	public int delete(MODEL model) {
		baseDao.delete(model);
		return 0;
	}

	@Override
	public MODEL getById(KEY_TYPE id,Class<?> clz) {
		return baseDao.getById(id, clz);
	}

	@Override
	public long queryCount(MODEL model) {
		return baseDao.queryCount(model);
	}

	@Override
	public List<MODEL> query(MODEL model) {
		return baseDao.query(model);
	}

	@Override
	public DataTableInfo<MODEL> queryPage(HttpServletRequest request,MODEL model) {
		DataTableInfo<MODEL> dataTableInfo= baseDao.queryPage(request, model);
		return dataTableInfo;
	}

}
