package com.insigma.guobo.tbk.dao;

import java.util.List;

import com.insigma.guobo.tbk.pojo.TaskConf;

public interface TaskConfDao {

	/**
	 * �����ݿ��л�ȡ�����б�
	 * @return  �����б�  ���� Ϊnull
	 */
	public abstract List<TaskConf> getAllTaskConf();

	/**
	 * dao�� �����ݿ���� һ������
	 * @param tc ��Ҫ��ӵ��������
	 */
	public abstract void addTaskConf(TaskConf tc);

}