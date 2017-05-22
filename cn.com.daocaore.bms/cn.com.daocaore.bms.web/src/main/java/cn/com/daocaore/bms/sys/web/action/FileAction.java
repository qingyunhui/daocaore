package cn.com.daocaore.bms.sys.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.daocaore.bms.common.base.ResponseData;
import cn.com.daocaore.bms.common.bean.ExtrasStruct;
import cn.com.daocaore.bms.sys.biz.entity.SysAttachment;
import cn.com.daocaore.bms.sys.biz.service.SysAttachmentService;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.FileType;

/***
 ** @category 文件上传下传处理器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月19日下午2:37:28
 **/
@Controller
@RequestMapping("/")
public class FileAction {
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	SysAttachmentService sysAttachmentService;
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public ResponseData doFileUpload(HttpServletRequest request) {
		ResponseData rd=new ResponseData();
		try {
 			MultipartHttpServletRequest mulitRequest= (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> multipartMap= mulitRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> iterator= multipartMap.entrySet().iterator();
			String physicalPath="F:/test/test2/";
			while(iterator.hasNext()){
				Entry<String, MultipartFile> entry= iterator.next();
				String key=entry.getKey();
				logger.info("key",key);
				MultipartFile multipartFile= entry.getValue();
				String fileName=multipartFile.getOriginalFilename();
				if(multipartFile.isEmpty()){
					 logger.error("上传的文件不能为空!");
					 return rd;
				}
				String originFileName = multipartFile.getOriginalFilename();
				String suffix = FileType.getSuffixByFilename(originFileName);
				//@1.判断单个文件上传大小是否大于系统设定的大小 TODO 
				long maxSize=1024*1024;
				if (multipartFile.getSize() > maxSize) {
					logger.error("单个文件上传大小不能大于.{}.",new Object[]{maxSize});
					 return rd;
				}
				String id = UUID.randomUUID().toString();
				SysAttachment sysAttachment = new SysAttachment();
            	sysAttachment.setId(id);
				sysAttachment.setOriginName(originFileName);
				physicalPath=physicalPath+fileName;
				sysAttachment.setPhysicalPath(physicalPath);//文件落地所在的绝对路径
				sysAttachment.setSourceFilePath(physicalPath);//源文件所在路径 ，暂时与physicalPath 保持一致.
				sysAttachment.setSize(multipartFile.getSize());
				sysAttachment.setSuffix(suffix);
				sysAttachment.setAttachmentType(multipartFile.getContentType());
				sysAttachment.setUrl("download.json?id="+id);
				sysAttachmentService.add(sysAttachment);
				File source = new File(physicalPath);   
			    multipartFile.transferTo(source);   
			}
			//@3.落地（0.判断文件是否存在，1.获取落地服务器地址，2.落地文件夹（命名），3.落地成功后同时 insert 一条记录到数据库 ）
			rd.setMsg("落地成功.");
		} catch (Exception e) {
			logger.error("upload file is error. {}.",new Object[]{JSONObject.toJSONString(e)});
			rd.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return rd;
	}
	
	/**
	 * 文件下载
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="download")
	public void download(ExtrasStruct extras,HttpServletRequest request, HttpServletResponse response){
		if(null==extras) {
			logger.info("----啥都没有..");
			try {
				response.sendRedirect("images/notexist.png");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			extras.setTargetTable(StringUtil.getTableName(extras.getTargetTable()));
			extras.setTargetField(StringUtil.getTableName(extras.getTargetField()));
			Map<String,Object> map=BeanUtil.pojoToMap(extras);
			List<SysAttachment> sysAttachments= sysAttachmentService.query(map);
			if(null==sysAttachments || sysAttachments.size()==0) {
				try {
					response.sendRedirect("images/notexist.png");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			FileInputStream fis = null;
			try {
				OutputStream out = response.getOutputStream();
				for(SysAttachment sysAttachment:sysAttachments){
					File file = new File(sysAttachment.getPhysicalPath());
					if(file.exists()){
						response.setContentType(sysAttachment.getAttachmentType());  
						response.setContentLength(sysAttachment.getSize().intValue());  
						String originName = sysAttachment.getOriginName();
						String agent  = request.getHeader("user-agent");
						//处理浏览器
						if(agent.contains("Firefox")){
							originName = "=?UTF-8?B?"+ new String(new Base64().encode(originName.getBytes("UTF-8"))) + "?=";
						} else {
							// 其它浏览器 IE 、google 采用URL编码
							originName = URLEncoder.encode(originName, "utf-8");
							originName = originName.replace("+", " ");
						}
						response.setHeader("Content-Disposition", "inline; filename=" + originName);
						fis = new FileInputStream(file);
						byte[] b = new byte[fis.available()];
						fis.read(b);
						out.write(b);
						out.flush();
					} else {
						response.sendRedirect("images/notexist.png");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@RequestMapping(value = "/doDelete")
	@ResponseBody
	public ResponseData doDelete(String id) {
		SysAttachment attachment= sysAttachmentService.query(id);
		if(null==attachment){
			logger.error("未查询到id={}的附件.",new Object[]{id});
			return new ResponseData("未查询到附件!");
		}
		int count=sysAttachmentService.delete(id);
		File file=new File(attachment.getPhysicalPath());
		if(!file.exists()){
			logger.error("附件{}在磁盘上不存在.",new Object[]{attachment.getPhysicalPath()});
			return new ResponseData("附件在磁盘上不存在!");
		}
		file.delete();
		ResponseData rd=new ResponseData(count>0?"处理成功!":"处理失败!");
		rd.put("code", 200);
		return rd;
	}
	
}
