package com.insigma.guobo.tbk.services;

public interface GoodsService {

	/**
	 * ��ȡ��Ʒ�� ����  ͨ��ģ����ѯ
	 * @param name  �ؼ���
	 * @return   ��������
	 */
	public abstract String[] getGoodsByLikeName(String name);

}