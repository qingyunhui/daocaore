package cn.com.daocaore.bms.sys.biz.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.daocaore.bms.common.base.BaseServiceImpl;
import cn.com.daocaore.bms.common.bean.ExtrasStruct;
import cn.com.daocaore.bms.sys.biz.dao.SysAttachmentDao;
import cn.com.daocaore.bms.sys.biz.entity.SysAttachment;
import cn.com.daocaore.bms.sys.biz.service.SysAttachmentService;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.FileType;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
@Service("sysAttachmentService")
public class SysAttachmentServiceImpl extends BaseServiceImpl<SysAttachment,String> implements SysAttachmentService{

	@Autowired
	private SysAttachmentDao sysAttachmentDao ;
	
	private Logger logger=LoggerFactory.getLogger(SysAttachmentServiceImpl.class);
	
	@Override
	public boolean saveAttachment(MultipartFile multipartFile,ExtrasStruct extras) throws Exception{
		boolean flag=false;
		if(null==multipartFile) return flag;
		String originFileName = multipartFile.getOriginalFilename();
		String suffix = FileType.getSuffixByFilename(originFileName);
		
		String id = UUID.randomUUID().toString();
		SysAttachment sysAttachment = new SysAttachment();
		if(null!=extras) BeanUtils.copyProperties(extras, sysAttachment);
		sysAttachment.setId(id);
		sysAttachment.setOriginName(originFileName);
		sysAttachment.setSuffix(suffix);
		sysAttachment.setAttachmentType(multipartFile.getContentType());
		sysAttachment.setComments(multipartFile.getContentType());
		sysAttachment.setUrl("download.json?id=" + id);
		String separator=System.getProperty("file.separator");
		String saveFilePath="F:/dropzone/image/";	//文件落地的文件夹比如:(mnt目录或者data目录...)  TODO 
		saveFilePath=saveFilePath.endsWith(separator)?saveFilePath:saveFilePath+separator;	//文件以等级目录出现比如:(mnt/或者data/)
		StringBuffer sourceSb=new StringBuffer();
		sourceSb.append(saveFilePath);//目录
		sourceSb.append(extras.getClassify()).append(separator);//分类
		long size=multipartFile.getSize();
		byte[] bytes=multipartFile.getBytes();
		
		try {
			sysAttachment.setSourceFilePath(saveFilePath);
			sysAttachment.setPhysicalPath(saveFilePath);
			sysAttachment.setHandleType(extras.getHandleType());
//			byte[] data=inputStreamByte(multipartFile.getInputStream());
			sysAttachment.setData(bytes);
			sysAttachment.setSize(size);
			sysAttachmentDao.insert(sysAttachment);
			multipartFile.transferTo(new File(saveFilePath+originFileName));	//TODO
			flag=true;
		} catch (Exception e) {
			logger.error("上传出错，出错原因：{}.",new Object[]{JSONObject.toJSONString(e)});
			flag=false;
		}
		return flag;
	}
	
	/**  
     * InputStream 转为 byte  
     * @param InputStream  
     * @return 字节数组  
     * @throws Exception  
     */    
    public static byte[] inputStreamByte(InputStream inStream)throws Exception {    
        int count = 0;    
        while (count == 0) {    
            count = inStream.available();    
        }    
        byte[] b = new byte[count];    
        inStream.read(b);    
        return b;    
    }    
}