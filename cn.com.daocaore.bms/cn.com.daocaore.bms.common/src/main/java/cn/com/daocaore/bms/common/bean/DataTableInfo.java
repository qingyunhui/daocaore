package cn.com.daocaore.bms.common.bean;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 该对象中的属性必须与前端dataTable保持一致...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月17日上午10:28:18
 **/
@Getter
@Setter
public class DataTableInfo <T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<T> data; //查询分页的数据集
	
	private int recordsTotal;// 总记录数
	
	private int recordsFiltered;// 过滤后的总记录数
	
	private int startRow;//开始行
	
	private int pageSize;////每页大小
	
	private Boolean isPaging; //分页标识(true标识分页，false标识不分页)
	
	private int draw;//请求次数
	
	private int pageCount;//总页数
	
	public DataTableInfo(){}
	
	public DataTableInfo(HttpServletRequest request){
        String startRow = request.getParameter("startRow");//开始的数据行数
        String pageSize = request.getParameter("pageSize");//每页的数据数
//        String draw = request.getParameter("draw");//DT传递的draw:
        this.setStartRow(Integer.parseInt(startRow));
        this.setPageSize(Integer.parseInt(pageSize));
//        this.setDraw(Integer.parseInt(draw));
        this.pageCount = (Integer.parseInt(startRow) / Integer.parseInt(pageSize)) + 1; //计算页码
	}
}
