package com.insigma.guobo.tbk.dao;

public interface GoodsDao {

	/**
	 * ���ݹؼ��� ģ����ѯ ��ȡ��Ʒ��
	 * @param name  �ؼ��� ��Ʒ��
	 * @return   ����
	 */
	public abstract String[] getGoodsNames(String name);

}