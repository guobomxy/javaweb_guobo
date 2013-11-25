package com.insigma.guobo.tbk.dao;

import java.util.List;

import com.insigma.guobo.tbk.pojo.Users;

public interface UserDao {
	
	public Users findUserByNickOrEmail(String nickOrEmail);
	public Users findUserByEmail(String email);
	
	//ע��
	public boolean isRisterSuccess(Users user);
	//����activeCode��user
	public Users findUserByCode(String activeCode);
	//�����û��ļ���״̬
	
	/**
	 * 
	 * @param user ���ݴ�����û�����ƥ�伤��
	 * @return
	 */
	public boolean updateUser(Users user);
	//��ȡȫ���û�
	
	/**
	 * ��ȡȫ���û���Ϣ
	 * @return  ����list<Users>����
	 */
	public List<Users> getAllUsers();
	//����ɾ���û�
	/**
	 *  ����idֵ ����ɾ���û�
	 * @param ids ����id������
	 * @return  ���ɾ���ɹ�Ϊ true ���� false
	 */
	public boolean delUsersByids(int[] idInts);
	/**
	 * dao��ģ����ѯ�û�������
	 * @param name  �ؼ���
	 * @return    ������������
	 */
	public String[] findUserNames(String name);
}
