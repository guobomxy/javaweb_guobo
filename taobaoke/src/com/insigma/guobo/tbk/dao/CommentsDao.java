package com.insigma.guobo.tbk.dao;

import java.util.List;

import com.insigma.guobo.tbk.vo.CommentsVo;

public interface CommentsDao {
	/**
	 * �������� ��ѯ���
	 * @param userName  �û���
	 * @param goodName  ��Ʒ��
	 * @param time      ����ʱ��
	 * @return       ���ز�ѯ���
	 */
	public List<CommentsVo> getList(String userName,String goodName,String time1,String time2);

	/**
	 * ��������idɾ������
	 * @param cId   Ҫɾ��������id 
	 * @return  �������   ��  �ɹ�   ��  ʧ��
	 */
	public boolean delCommentById(int cId);

}
