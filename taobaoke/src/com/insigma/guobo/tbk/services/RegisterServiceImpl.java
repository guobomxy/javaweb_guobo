package com.insigma.guobo.tbk.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.insigma.guobo.tbk.dao.UserDao;
import com.insigma.guobo.tbk.dao.UserDaoImpl;
import com.insigma.guobo.tbk.exception.SendEmailException;
import com.insigma.guobo.tbk.exception.UserExistException;
import com.insigma.guobo.tbk.pojo.Users;
import com.insigma.guobo.tbk.services.mail.MailSenderInfo;
import com.insigma.guobo.tbk.services.mail.SimpleMailSender;
import com.insigma.guobo.tbk.utils.Configuration;
import javax.persistence.Entity;



@Entity
public class RegisterServiceImpl implements RegisterService {
	
	
	private UserDao userDAO = new UserDaoImpl();
	
	public boolean isRegister(Users user) throws UserExistException, SendEmailException {
		
		boolean isSuccess = false;
		//�ж��û��Ƿ����
		Users userByEmail = userDAO.findUserByEmail(user.getEmail());
		
		
		
		if(null == userByEmail){
			//˵���û�������  ���б���
			isSuccess = userDAO.isRisterSuccess(user);
		}
		else{//�� �û�Ҳ�Ѿ������쳣
			isSuccess = false;
			throw new UserExistException("�û��Ѿ����ڣ�����");
		}
		//�����ʼ�  ����
		isSuccess = sendActiveMail(user);
		if(!isSuccess){
			throw new SendEmailException("�ʼ�����ʧ�ܣ�����");
		}
		
		return isSuccess;
	}
	
	private boolean sendActiveMail(Users user){
		boolean isSendSuccess = false;
		 //�������Ҫ�������ʼ�  
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost(Configuration.getMailServer());
//        System.out.println("����SMTP!");
        mailInfo.setMailServerPort(Configuration.getMailPort());
//        System.out.println("���ö˿�!");
        mailInfo.setValidate(true); 
//        System.out.println("������У����ȷ!");
        mailInfo.setUserName(Configuration.getMailUser()); 
//        System.out.println("����û���!");
        mailInfo.setPassword(Configuration.getMailPassword());//������������   
//        System.out.println("��������������!");
        mailInfo.setFromAddress(Configuration.getMailFrom()); 
//        System.out.println("��ȡ�����������ַ!");
        mailInfo.setToAddress(user.getEmail());
//        System.out.println("��ȡ�ռ��˵�ַ!");
        mailInfo.setSubject("��л��ע���Ա�����վ���뼤�������û�!");
//        System.out.println("�����ʼ�����!");
           //�������Ҫ�������ʼ�  
        String url = "";
		try {
			url = "http://" + Configuration.getTaobaokeServerName() + ":" + Configuration.getTaobaokeServerPort() + "/taobaoke/servlet/ActiveUserServlet?activeCode=" + URLEncoder.encode(user.getActiveCode(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mailInfo.setContent("��л��ע���Ա�����վ�������������������������û�:\n<br/><a href='" + url + "'>��������û�</a>");
        isSendSuccess = SimpleMailSender.sendHtmlMail(mailInfo);//����html��ʽ    
        
        return isSendSuccess;
	}

	public boolean updateUser(Users user) {
		boolean isSuccess = false;
		
		isSuccess = userDAO.updateUser(user);
		
		return isSuccess;
	}
	

}
