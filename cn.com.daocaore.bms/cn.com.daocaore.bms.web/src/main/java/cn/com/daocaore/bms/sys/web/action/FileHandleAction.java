package cn.com.daocaore.bms.sys.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daocaore.bms.common.base.ResponseData;

/***
 ** @category 文件处理控制器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月18日上午9:46:22
 **/
@Controller
@RequestMapping(FileHandleAction.ACTION_PATH)
public class FileHandleAction {
	
	protected Logger logger=LoggerFactory.getLogger(FileHandleAction.class);
	protected static final String ACTION_PATH = "/sys/fileHandle";
	
	@RequestMapping(value = "/fileUpload")
	public ModelAndView fileUpload() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/fileUpload");
		return modelView;
	}
	
	@RequestMapping(value = "/doFileUpload")
	@ResponseBody
	public ResponseData doFileUpload(MultipartFile file) {
		ResponseData rd=new ResponseData();
		try {
			//@1.非空判断
			if(null==file || file.isEmpty() || file.getSize()==0){
				throw new Exception("上传的文件为空，或者大小等于0.");
			}
			//@2.大小判断
			if(file.getSize()>10000){
				throw new Exception("上传的文件大小超过指定大小.");
			}
			
			//@3.落地（0.判断文件是否存在，1.获取落地服务器地址，2.落地文件夹（命名），3.落地成功后同时 insert 一条记录到数据库 ）
			
			
			rd.setMsg("落地成功.");
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//============================> demo 仅测试============================>
	
	@RequestMapping(value = "/demo")
	public ModelAndView demo() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/demo");
		return modelView;
	}

	@RequestMapping(value = "/doDemo")
	@ResponseBody
	public String doDemo() {
		return "success";
	}
}
