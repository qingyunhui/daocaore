package cn.com.daocaore.monitor.sys.biz.service;

import org.springframework.web.multipart.MultipartFile;

import cn.com.daocaore.monitor.common.base.BaseService;
import cn.com.daocaore.monitor.common.bean.ExtrasStruct;
import cn.com.daocaore.monitor.sys.biz.entity.SysAttachment;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
public interface SysAttachmentService extends BaseService<SysAttachment,String>{
	
	/**
	 * <p>保存附件到数据库，且文件落地磁盘上</p>
	 * @param file
	 * @param extras
	 * @return boolean
	 * */
	public boolean saveAttachment(MultipartFile file,ExtrasStruct extras) throws Exception;
    
}