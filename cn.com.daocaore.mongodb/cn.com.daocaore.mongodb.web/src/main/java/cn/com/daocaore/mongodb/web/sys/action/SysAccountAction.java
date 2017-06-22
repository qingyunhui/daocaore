package cn.com.daocaore.mongodb.web.sys.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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
	public List<SysAccount> queryByAccount(SysAccount sysAccount) {
		List<SysAccount> sysAccountList= sysAccountService.query(sysAccount);
		return sysAccountList;
	}
	
	@RequestMapping("/queryByAccountWithSort")
	@ResponseBody
	public List<SysAccount> queryByAccountWithSort(SysAccount sysAccount) {
		List<Order> list=Arrays.asList(new Order(Direction.ASC,"account"),new Order(Direction.DESC,"comments"));
		List<SysAccount> sysAccountList= sysAccountService.query(sysAccount, list.toArray(new Order[list.size()]));
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
	public int deleteById(String id) {
		return sysAccountService.deleteById(id, SysAccount.class);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save() {
		SysAccount account=new SysAccount();
		Random rd=new Random();
		int rdnum=rd.nextInt(9999999);
		account.setAccount(rdnum+"");
		account.setComments("fuck you"+rdnum);
		account.setEmail(rdnum+"@qq.com");
		return sysAccountService.insert(account);
	}
	
	@RequestMapping("/saveBatch")
	@ResponseBody
	public List<SysAccount> saveBatch() {
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
		return sysAccountService.insertBatch(accountList);
	}
	
	@RequestMapping("/deleteByIdWithAccount")
	@ResponseBody
	public int deleteByIdWithAccount(SysAccount sysAccount){
		return sysAccountService.delete(sysAccount);
	}
	
	@RequestMapping("/queryByCount")
	@ResponseBody
	public long queryByCount(SysAccount sysAccount) {
		return sysAccountService.queryCount(sysAccount);
	}
	
	@RequestMapping("/queryPage")
	@ResponseBody
	public DataTableInfo<SysAccount> queryPage(HttpServletRequest request,SysAccount account) {
		DataTableInfo<SysAccount> dataTableInfo=sysAccountService.queryPage(request, account);
		return dataTableInfo;
	}
}
