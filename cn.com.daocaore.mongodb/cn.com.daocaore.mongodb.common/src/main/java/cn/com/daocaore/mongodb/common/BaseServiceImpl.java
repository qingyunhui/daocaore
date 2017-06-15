package cn.com.daocaore.mongodb.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午2:52:44
 **/
@Service
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
	public int queryCount(Map<String, Object> map) {
	
		return 0;
	}

	@Override
	public List<MODEL> query(Map<String, Object> map,Class<?> clz) {
		return baseDao.query(map, clz);
	}

	@Override
	public List<MODEL> queryPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
