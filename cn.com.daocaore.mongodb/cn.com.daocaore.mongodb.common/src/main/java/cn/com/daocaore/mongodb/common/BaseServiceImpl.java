package cn.com.daocaore.mongodb.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

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
		baseDao.insert(model);
		return 0;
	}

	@Override
	public int insertBatch(List<MODEL> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MODEL model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBatch(List<MODEL> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(KEY_TYPE id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBatch(List<KEY_TYPE> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MODEL getById(KEY_TYPE id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MODEL> query(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MODEL> queryPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
