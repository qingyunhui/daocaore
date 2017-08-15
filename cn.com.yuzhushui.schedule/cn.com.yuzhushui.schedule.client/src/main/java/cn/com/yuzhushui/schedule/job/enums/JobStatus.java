package cn.com.yuzhushui.schedule.job.enums;

import lombok.Getter;

@Getter
public enum JobStatus {
	EXECUTING(0, "正在执行"), FINISHED(1, "已完成"), UNKNOW(2, "未知状态");

	private int code;
	private String desc;

	private JobStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
