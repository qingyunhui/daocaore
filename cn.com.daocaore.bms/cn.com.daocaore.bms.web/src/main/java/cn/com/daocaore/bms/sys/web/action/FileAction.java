package cn.com.daocaore.bms.sys.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import qing.yun.hui.common.utils.StringUtil;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;

import cn.com.daocaore.bms.common.base.ResponseData;
import cn.com.yuzhushui.movie.sys.web.action.SysAttachment;

/***
 ** @category 文件上传下传处理器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年5月19日下午2:37:28
 **/
@Controller
@RequestMapping("/")
public class FileAction {
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public ResponseData doFileUpload(HttpServletRequest request) {
		ResponseData rd=new ResponseData();
		try {
			String contentType=request.getContentType();
 			MultipartHttpServletRequest mulitRequest= (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> multipartMap= mulitRequest.getFileMap();
			/*mulitRequest.getFiles("file");*/
			Iterator<Entry<String, MultipartFile>> iterator= multipartMap.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, MultipartFile> entry= iterator.next();
				String key=entry.getKey();
				MultipartFile multipartFile= entry.getValue();
				String fileName=multipartFile.getOriginalFilename();
//				File source = new File("F:/test/test2/"+fileName);   
//			    multipartFile.transferTo(source);   
			    
			    
	            if(multipartFile.isEmpty()){
	            	return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
	            }else{
	                String originFileName = multipartFile.getOriginalFilename();
					String suffix = FileType.getSuffixByFilename(originFileName);
					
					if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
						return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE).toJSONString();
					}
					long maxSize = ((Long) conf.get("maxSize")).longValue();
					if (multipartFile.getSize() > maxSize) {
						return new BaseState(false, AppInfo.MAX_SIZE).toJSONString();
					}
					String id = UUID.randomUUID().toString();
					savePath = savePath + id + suffix;
					savePath = PathFormat.parse(savePath, originFileName);
					String physicalPath = (String) conf.get("rootPath") + savePath;
					SysAttachment sysAttachment = new SysAttachment();
					if (null != obj) {
						sysAttachment.setCreater(StringUtil.isEmpty(info.getName())?info.getNickName():info.getName());
						sysAttachment.setCreaterId(info.getAppUserId());
					} else {
						sysAttachment.setCreater("admin");
						sysAttachment.setCreaterId(1);
					}
                	sysAttachment.setId(id);
					sysAttachment.setOriginName(originFileName);
					sysAttachment.setPhysicalPath(physicalPath);
					sysAttachment.setSize(file.getSize());
					sysAttachment.setSuffix(suffix);
					sysAttachment.setAttachmentType(file.getContentType());
					sysAttachment.setUrl("download.json?id="+id);
	                try {
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(physicalPath));
						sysAttachmentService.addSysAttachment(sysAttachment);
						state = new BaseState(true);
						state.putInfo("attachmentId", id);
						state.putInfo("url", request.getScheme()+"://"+request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/" + sysAttachment.getUrl());
						state.putInfo("type", suffix);
						state.putInfo("original", originFileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
			    
			    
			    
			    
			    
			    
			    
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
