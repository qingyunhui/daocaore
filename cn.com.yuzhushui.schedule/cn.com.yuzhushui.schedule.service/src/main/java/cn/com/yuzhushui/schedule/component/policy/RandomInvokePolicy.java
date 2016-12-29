package cn.com.yuzhushui.schedule.component.policy;

import java.util.List;
import java.util.Random;

public class RandomInvokePolicy implements InvokePolicy {

	private final List<String> urlList;
	private final int retryMaxTimes;

	private int retryTimes = 0;

	public RandomInvokePolicy(List<String> urlList) {
		this(urlList, 3);
	}

	public RandomInvokePolicy(List<String> urlList, int retryMaxTimes) {
		this.urlList = urlList;
		this.retryMaxTimes = retryMaxTimes;
	}

	public String getNextUrl() {
		if (retryTimes > retryMaxTimes) {
			// 超过重试次数.
			return null;
		}
		retryTimes++;
		int size = urlList.size();
		int i = new Random().nextInt(size);
		return urlList.get(i);
	}
}