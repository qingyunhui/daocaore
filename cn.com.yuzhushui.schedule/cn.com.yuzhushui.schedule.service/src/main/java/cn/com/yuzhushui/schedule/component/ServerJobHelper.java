package cn.com.yuzhushui.schedule.component;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.yuzhushui.schedule.component.policy.InvokePolicyFactory;
import cn.com.yuzhushui.schedule.job.biz.service.JobInfoService;
import cn.com.yuzhushui.schedule.job.biz.service.JobSnapshotService;

@Component
public class ServerJobHelper {

	@Autowired
	private JobSnapshotService jobSnapshotService;

	@Autowired
	private JobInfoService jobInfoService;

	@Autowired
	private InvokePolicyFactory invokePolicyFactory;

	private static Map<Class<?>, Object> compomentCache = new HashMap<Class<?>, Object>();

	@PostConstruct
	public void init() {
		compomentCache.put(JobSnapshotService.class, jobSnapshotService);
		compomentCache.put(InvokePolicyFactory.class, invokePolicyFactory);
		compomentCache.put(JobInfoService.class, jobInfoService);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getComponent(Class<? extends T> clazz) {
		return (T) compomentCache.get(clazz);
	}
}
