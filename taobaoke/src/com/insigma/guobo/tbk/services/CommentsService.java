package com.insigma.guobo.tbk.services;

import java.util.List;

import com.insigma.guobo.tbk.vo.CommentsVo;

public interface CommentsService {

	/**
	 *�������� ��ѯ����������۽��
	 * @param userName  �û�����
	 * @param goodName   ��Ʒ����
	 * @param time    ����ʱ��
	 * @return    ���ؽ���б�
	 */
	public abstract List<CommentsVo> listCommennt(String userName,
			String goodName, String time,String time2);

	/**
	 * ��������idɾ������
	 * @param cId  ���۵�id
	 * @return  ��  �ɹ�   ��  ʧ��
	 */
	public boolean delCommentById(String cId);
}