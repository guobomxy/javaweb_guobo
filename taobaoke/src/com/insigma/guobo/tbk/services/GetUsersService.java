package com.insigma.guobo.tbk.services;

import java.util.List;

import com.insigma.guobo.tbk.pojo.Users;

public interface GetUsersService {
	
	//��ȡȫ���û�
	public List<Users> getAllUsers();
	//ɾ���û�

	public boolean delUsers(int[] ids);
	
	/**
	 * ���ݹؼ���ģ����ѯ  �����û������� ����
	 * @param name  �ؼ���
	 * @return    �û�������
	 */
	public String[] getLikeNames(String name);
}
