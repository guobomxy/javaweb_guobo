package com.insigma.guobo.tbk.services;

import com.insigma.guobo.tbk.dao.UserDao;
import com.insigma.guobo.tbk.dao.UserDaoImpl;
import com.insigma.guobo.tbk.log.LogStyle;
import com.insigma.guobo.tbk.pojo.Users;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class LoginServiceImpl implements LoginService {
	
	@ManyToOne
	private UserDao userDAO = new UserDaoImpl();

	public boolean isValidUser(String userName, String password) {
		//�����ݿ��ȡ���˻���
		Users user = userDAO.findUserByNickOrEmail(userName);
		//���ﻹҪ�жϼ��ܵ����� TODO
		if(null != user && user.getPassword().equals(password)){
			String message = "�û��� " + user.getNick() + " ��¼�ˣ�";
			String level = "info";
			LogStyle.serviceLog(LoginServiceImpl.class, message, level);
			return true;
		}
		return false;
	}

	public boolean isValidEmail(String email) {
		
		boolean isValidEmail = false;
		//�����ݿ���У��
		Users user = userDAO.findUserByEmail(email);
		if(user == null){
			isValidEmail = true;
		}
		return isValidEmail;
	}

}
