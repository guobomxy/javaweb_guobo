package com.insigma.guobo.tbk.vo;

import java.util.Date;

/**
 * ��TaskServlet �и���taskConf��ʱ�����÷�װ������������ʱ������ӳٶ೤ʱ������
 * @author gb
 *
 */
public class TaskInfo {

	private Date runTime;
	private boolean flag;
	private long yanChiTime;

	public long getYanChiTime() {
		return yanChiTime;
	}
	public void setYanChiTime(long yanChiTime) {
		this.yanChiTime = yanChiTime;
	}
	public Date getRunTime() {
		return runTime;
	}
	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
