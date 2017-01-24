package cn.com.yuzhushui.websocket.common.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qing.yun.hui.common.utils.Validate.ValidataUtil;

import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午1:22:36
 **/
public abstract class BaseAction<MODEL extends BaseModel<KEY_TYPE>, FORM extends BaseForm<KEY_TYPE>, KEY_TYPE>{

	@Autowired
	private BaseService<MODEL,KEY_TYPE>  baseService;

	public abstract String getActionPath();// 获取action路径
	
	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/list");
		return modelAndView;
	}
	
	@RequestMapping(value="doList.json", method={RequestMethod.POST})
	@ResponseBody
	public PageInfo<MODEL> doList(HttpServletRequest request) {
		BaseQuery query = EncapsulateQueryCondition(request);
		PageInfo<MODEL> queryPage = baseService.queryPage(query);
		return queryPage;
	}

	@RequestMapping(value = "add")
	public ModelAndView add(FORM form) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		FORM f = null;
		try {
			Class<FORM> genericType2 = getGenericType(1);
			f = genericType2.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject(getLowerClassName(f.getClass()), form);
		modelAndView.addObject("validate", ValidataUtil.getValidate(f.getClass()));
		return modelAndView;
	}

	/**
	 * 
	 * @Description: 将类名首字母小写
	 * @Title: getLowerClassName 
	 * @param c
	 * @return String
	 * @throws
	 */
	private String getLowerClassName(Class<?> c) {
		return c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
	}

	/**
	 * 
	 * @Description: 提交新增 
	 * @Title: doAdd 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	public ModelAndView doAdd(@Valid FORM form, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
			modelAndView.addObject(getLowerClassName(form.getClass()), form);
			modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = null;
		try {
			model = modelClass.newInstance();
			BeanUtils.copyProperties(form, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		baseService.add(model);
		ModelAndView modelAndView = new ModelAndView("redirect:" + getActionPath() + "/list.htm");
		redirectAttributes.addFlashAttribute("msg", "新增成功！");
		return modelAndView;
	}

	@RequestMapping(value="update")
	public ModelAndView update(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
		modelAndView.addObject(getLowerClassName(form.getClass()), form);
		modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
		return modelAndView;
	}
	
	@RequestMapping(value = "doUpdate", method = { RequestMethod.POST })
	public ModelAndView doUpdate(@Valid FORM form, BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
			modelAndView.addObject(getLowerClassName(form.getClass()), form);
			modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = modelClass.newInstance();
		BeanUtils.copyProperties(form, model);
		baseService.update(model);
		ModelAndView modelAndView = new ModelAndView("redirect:" + getActionPath() + "/list.htm");
		redirectAttributes.addFlashAttribute("msg", "更新成功！");
		return modelAndView;
	}
	
	@RequestMapping(value="detail")
	public ModelAndView detail(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		modelAndView.addObject(getLowerClassName(form.getClass()), form);
		return modelAndView;
	}

	@RequestMapping(value = "doDelete.json")
	@ResponseBody
	public ResponseData doDelete(KEY_TYPE[] ids) {
		int deleteNum = baseService.delete(Arrays.asList(ids));
		ResponseData rd = new ResponseData();
		rd.setMsg("成功删除【" + deleteNum + "】条数据！");
		rd.addData("ids", ids);
		return rd;
	}
	
	protected BaseQuery EncapsulateQueryCondition(HttpServletRequest request) {
		BaseQuery query = new BaseQuery();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			if(key.startsWith("arr_")) {
				String[] values = request.getParameterValues(key);
				key = key.split("_|\\[")[1];
				query.getQueryData().put(key, values);
				continue;
			}
			if("pageNum".equals(key)){
				query.setPageNum(Integer.parseInt(value));
			}else if("pageSize".equals(key)){
				query.setPageSize(Integer.parseInt(value));
			}else if("orderColumns".equals(key)){
				query.setOrderColumns(value);
			}else if("orderDirection".equals(key)){
				query.setOrderDirection(value);
			}else{
				query.getQueryData().put(key, value);
			}
		}
		query.setOrderBy();// 将排序字段和排序方向拼接
		return query;
	}

	@SuppressWarnings({"unchecked" })
	public <T> Class<T> getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType)
					.getActualTypeArguments();
			return (Class<T>) params[index];
		}
		return null;
	}
}
