package cn.com.yuzhushui.schedule.component.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.utils.StringUtil;
import cn.com.yuzhushui.schedule.enums.JobInfoEnum;

@Component
public class InvokePolicyFactory {
	
	@Value("${retry.max.times}")
	private int retryMaxTimes;

	public InvokePolicy getJobExecutePolicy(JobInfoEnum.INVOKE_POLICY policy, List<String> urlList) {
		if (policy == null) {
			throw new IllegalArgumentException("The policy must not be null!");
		}
		if (StringUtil.isEmpty(urlList)) {
			throw new IllegalArgumentException("The urlList must not be empty!");
		}
		switch (policy) {
		case PRIORITY:
			return new PriorityInvokePolicy(urlList,retryMaxTimes);
		case RANDOM:
			return new RandomInvokePolicy(urlList,retryMaxTimes);
		}
		throw new RuntimeException("Unexpected invoke policy!");
	}
}
