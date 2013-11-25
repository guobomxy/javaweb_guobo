package com.insigma.guobo.tbk.services;

import com.insigma.guobo.tbk.dao.UserDao;
import com.insigma.guobo.tbk.dao.UserDaoImpl;
import com.insigma.guobo.tbk.log.LogStyle;
import com.insigma.guobo.tbk.pojo.Users;
import com.insigma.guobo.tbk.utils.Constants;

public class ActiveUserServiceImpl implements ActiveUserService {
	UserDao userDAO = new UserDaoImpl();
	public Users activeUser(String activeCode) {
		//��ȡuser
		Users user = userDAO.findUserByCode(activeCode);
		//�ж�user�Ƿ����
		if(null != user){
			//����  ����м������
			//���ü���״̬
			user.setStatus(Constants.USER_ACTIVE_YES);
			//���漤����û�״̬  ����boolean����
			boolean isSuccess = userDAO.updateUser(user);
			//�������ʧ��  ����userΪ��
			if(!isSuccess){
				return null;
			}
			LogStyle.serviceLog(ActiveUserServiceImpl.class, "�û�"+user.getNick() + "������", "info");
		}
		return user;
	}

}
