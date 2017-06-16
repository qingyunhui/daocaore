package cn.com.daocaore.mongodb.web.sys.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.daocaore.mongodb.common.beans.DataTableInfo;
import cn.com.daocaore.mongodb.sys.entity.SysAccount;
import cn.com.daocaore.mongodb.sys.service.SysAccountService;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日下午10:43:44
 **/
@Controller
@RequestMapping(SysAccountAction.ACTION_PATH)
public class SysAccountAction {
	
	protected static final String ACTION_PATH="/sys/sysAccount";
	
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping("/queryByAccount")
	@ResponseBody
	public List<SysAccount> queryByAccount(String account) {
		SysAccount sysAccount=new SysAccount();
		sysAccount.setAccount(account);
		List<SysAccount> sysAccountList= sysAccountService.query(sysAccount);
		return sysAccountList;
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public SysAccount getById(String id) {
		SysAccount account= sysAccountService.getById(id, SysAccount.class);
		return account;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public void deleteById(String id) {
		sysAccountService.deleteById(id, SysAccount.class);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save() {
		SysAccount account=new SysAccount();
		Random rd=new Random();
		int rdnum=rd.nextInt(9999999);
		account.setAccount(rdnum+"");
		account.setComments("fuck you"+rdnum);
		account.setEmail(rdnum+"@qq.com");
		sysAccountService.insert(account);
	}
	
	@RequestMapping("/saveBatch")
	@ResponseBody
	public void saveBatch() {
		List<SysAccount> accountList=new ArrayList<SysAccount>();
		for(int i=0;i<5;i++){
			SysAccount account=new SysAccount();
			Random rd=new Random();
			int rdnum=rd.nextInt(9999999);
			account.setAccount(rdnum+"");
			account.setComments("hello word ! "+rdnum);
			account.setEmail(rdnum+"@163.com");
			accountList.add(account);
		}
		sysAccountService.insertBatch(accountList);
	}
	
	@RequestMapping("/deleteByIdWithAccount")
	@ResponseBody
	public void deleteByIdWithAccount(String account,String id){
		SysAccount sysAccount=new SysAccount();
		sysAccount.setAccount(account);
		sysAccount.setAccountId(id);
		sysAccountService.delete(sysAccount);
	}
	
	@RequestMapping("/queryByCount")
	@ResponseBody
	public long queryByCount(String account) {
		SysAccount sysAccount=new SysAccount();
		sysAccount.setAccount(account);
		return sysAccountService.queryCount(sysAccount);
	}
	
	@RequestMapping("/queryPage")
	@ResponseBody
	public DataTableInfo<SysAccount> queryPage(HttpServletRequest request,SysAccount account) {
		DataTableInfo<SysAccount> dataTableInfo=sysAccountService.queryPage(request, account);
		return dataTableInfo;
	}
}
