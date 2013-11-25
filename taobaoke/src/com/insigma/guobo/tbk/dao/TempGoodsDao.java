package com.insigma.guobo.tbk.dao;

import java.util.List;

import com.insigma.guobo.tbk.pojo.TempGoods;

public interface TempGoodsDao {
	
	/**
	 * �ж�tempgoods�����Ƿ��� �����id ������
	 * @param id  Ҫ��֤��id
	 * @return  true ��  false û��
	 */
	public boolean isExitTg(String id);
	/**
	 * �����ݿ��в���TempGoods
	 * @param tg  Ҫ����Ķ���
	 */
	public void addTg(TempGoods tg);
	
	/**
	 * ��ȡ���ݿ���ָ��ҳ������Ʒ��Ϣ
	 * @param pageNo   ҳ��
	 * @param pageNum  ÿҳ������
	 * @return   ������б����ʽ����
	 */
	public List<TempGoods> getTempGoods(int pageNo,int pageNum);
}
