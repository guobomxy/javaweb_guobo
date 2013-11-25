package com.insigma.guobo.tbk.services;

import com.insigma.guobo.tbk.exception.SendEmailException;
import com.insigma.guobo.tbk.exception.UserExistException;
import com.insigma.guobo.tbk.pojo.Users;

public interface RegisterService {
	//�ж��Ƿ�ע��ɹ�
	public boolean isRegister(Users user) throws UserExistException, SendEmailException ;
	
	/**
	 * ���ݴ�������user����������ݿ�ĸ���
	 * @param user  Ҫ�޸ĵĶ���  ���Ѿ��޸Ĺ��ģ�
	 * @return  ���� true �ɹ�  false ʧ��
	 */
	public boolean updateUser(Users user);
}