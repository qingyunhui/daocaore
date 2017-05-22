package cn.com.daocaore.bms.sys.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.utils.BeanUtil;
import cn.com.daocaore.bms.common.base.ResponseData;
import cn.com.daocaore.bms.common.bean.ExtrasStruct;
import cn.com.daocaore.bms.common.bean.FileStruct;
import cn.com.daocaore.bms.sys.biz.entity.SysAttachment;
import cn.com.daocaore.bms.sys.biz.service.SysAttachmentService;

import com.alibaba.fastjson.JSONObject;

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
	
	@Autowired
	private SysAttachmentService sysAttachmentService;
	
	@RequestMapping(value = "/fileUpload")
	public ModelAndView fileUpload() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/fileUpload");
		//回显 begin
		List<FileStruct> fileList=new ArrayList<FileStruct>();
		String path="F:/test/test2";
		File file=new File(path);
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for(File tmp:files){
				FileStruct tf=new FileStruct();
				tf.setFileName(tmp.getName());
				tf.setFileSize(tmp.length());
				tf.setUrl(tmp.getPath());
				fileList.add(tf);
			}
		}
		modelView.addObject("fileList", fileList);
		modelView.addObject("dataMap", JSONObject.toJSONString(fileList));
		//回显 end
		return modelView;
	}
	
	@RequestMapping(value = "/doFileUpload")
	@ResponseBody
	public ResponseData doFileUpload(HttpServletRequest request) {
		ResponseData rd=new ResponseData();
		try {
			
			String contentType=request.getContentType();
			
			logger.info("contentType",new Object[]{contentType});
			
 			MultipartHttpServletRequest mulitRequest= (MultipartHttpServletRequest) request;
 			
// 			List<MultipartFile> multipartFileList= mulitRequest.getFiles("file");
// 			logger.info(multipartFileList.size()+"条.");
 			
			Map<String, MultipartFile> multipartMap= mulitRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> iterator= multipartMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, MultipartFile> entry= iterator.next();
				String key=entry.getKey();
				MultipartFile multipartFile= entry.getValue();
				String fileName=multipartFile.getOriginalFilename();
				logger.info("{key},{fileName}",new Object[]{key,fileName});
				File source = new File("F:/test/test2/"+fileName);   
			    multipartFile.transferTo(source);   
			}
			//@1.非空判断
			/*if(null==file || file.length==0){
				throw new Exception("上传的文件为空，或者大小等于0.");
			}*/
			//@2.大小判断
			/*if(file.getSize()>10000){
				throw new Exception("上传的文件大小超过指定大小.");
			}
			*/
			//@3.落地（0.判断文件是否存在，1.获取落地服务器地址，2.落地文件夹（命名），3.落地成功后同时 insert 一条记录到数据库 ）
			rd.setMsg("落地成功.");
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@RequestMapping(value = "/demo2")
	public ModelAndView demo2() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/demo2");
		//回显 begin
		List<FileStruct> fileList=new ArrayList<FileStruct>();
		String path="F:/test/test2";
		File file=new File(path);
		if(file.isDirectory()){
			File[] files=file.listFiles();
			for(File tmp:files){
				FileStruct tf=new FileStruct();
				tf.setFileName(tmp.getName());
				tf.setFileSize(tmp.length());
				tf.setUrl(tmp.getPath());
				fileList.add(tf);
			}
		}
		modelView.addObject("fileList", fileList);
		modelView.addObject("dataMap", JSONObject.toJSONString(fileList));
		return modelView;
	}

	@RequestMapping(value = "/doDemo")
	@ResponseBody
	public String doDemo() {
		return "success";
	}
	
	@RequestMapping(value = "/demo3")
	public ModelAndView demo3() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/demo3");
		return modelView;
	}
	
	@RequestMapping(value = "/demo4")
	public ModelAndView demo4(ExtrasStruct extras) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/demo4");
		//回显 begin
		extras.setDeleted(0);
		List<SysAttachment> attachments= sysAttachmentService.query(BeanUtil.pojoToMap(extras));
		List<FileStruct> urlPaths=new ArrayList<FileStruct>();
		for(SysAttachment att:attachments){
			FileStruct ext=new FileStruct();
			ext.setFileName(att.getOriginName());
			ext.setFileSize(att.getSize());
			ext.setUrl(att.getUrl());
			ext.setId(att.getId());
			urlPaths.add(ext);
		}
		modelView.addObject("dataMap", JSONObject.toJSONString(urlPaths));
		return modelView;
	}
	
	@RequestMapping(value = "/doDemo4")
	@ResponseBody
	public ResponseData doDemo4(HttpServletRequest request) {
		ResponseData rd=new ResponseData();
		try {
			String contentType=request.getContentType();
			logger.info("contentType",new Object[]{contentType});
 			MultipartHttpServletRequest mulitRequest= (MultipartHttpServletRequest) request;
// 			List<MultipartFile> multipartFileList= mulitRequest.getFiles("file");
// 			logger.info(multipartFileList.size()+"条.");
			Map<String, MultipartFile> multipartMap= mulitRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> iterator= multipartMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, MultipartFile> entry= iterator.next();
				String key=entry.getKey();
				MultipartFile multipartFile= entry.getValue();
				String fileName=multipartFile.getOriginalFilename();
				logger.info("{key},{fileName}",new Object[]{key,fileName});
				File source = new File("F:/test/test2/"+fileName);   
			    multipartFile.transferTo(source);   
			}
			//@1.非空判断
			/*if(null==file || file.length==0){
				throw new Exception("上传的文件为空，或者大小等于0.");
			}*/
			//@2.大小判断
			/*if(file.getSize()>10000){
				throw new Exception("上传的文件大小超过指定大小.");
			}
			*/
			//@3.落地（0.判断文件是否存在，1.获取落地服务器地址，2.落地文件夹（命名），3.落地成功后同时 insert 一条记录到数据库 ）
			rd.setMsg("落地成功.");
		} catch (Exception e) {
			e.printStackTrace();
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
}
