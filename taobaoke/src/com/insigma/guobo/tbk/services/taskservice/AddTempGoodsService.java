package com.insigma.guobo.tbk.services.taskservice;

import java.util.List;

import com.insigma.guobo.tbk.pojo.TempGoods;

public interface AddTempGoodsService {

	/**
	 * ��ͨ�������ȡ����ʱ��Ʒ�ŵ����ݿ�
	 * @param list   ��ʱ��Ʒ�б�
	 */
	public void addTempGood(List<TempGoods> list); 
	/**
	 * ��ȡ���ݿ���ָ������Ʒ��Ϣ
	 * @param pageNo   ҳ��
	 * @param pageNum  ÿҳ������
	 * @return  ������б���ʽ����
	 */
	public List<TempGoods> getTempGoods(int pageNo,int pageNum);
}
