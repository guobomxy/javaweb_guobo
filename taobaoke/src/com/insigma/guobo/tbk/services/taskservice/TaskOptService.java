package com.insigma.guobo.tbk.services.taskservice;

import java.util.List;

import com.insigma.guobo.tbk.pojo.TaskConf;

public interface TaskOptService {

	/**
	 * ��ȡ���ݿ��е� �����б�
	 * @return  ���������б�  û���򷵻�null
	 */
	public List<TaskConf> getTaskConf();
	
	/**
	 * �����ݿ������һ�������¼
	 * @param tc  Ҫ��ӵ����� ����
	 */
	public void addTaskConf(TaskConf tc);
}
