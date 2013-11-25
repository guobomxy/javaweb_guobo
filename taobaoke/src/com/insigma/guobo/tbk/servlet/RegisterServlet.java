package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.insigma.guobo.tbk.exception.SendEmailException;
import com.insigma.guobo.tbk.exception.UserExistException;
import com.insigma.guobo.tbk.pojo.Users;
import com.insigma.guobo.tbk.services.RegisterService;
import com.insigma.guobo.tbk.services.RegisterServiceImpl;
import com.insigma.guobo.tbk.utils.PwdMd5Util;

@Entity
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ʵ��ע��ҵ��
		//����������Ϣ
		String message = "ע��ɹ�������";
		//��ȡҳ���ϵ�ֵ  ��װ����   ʹ��beanutils��װ����
		Map<String, String[]> map = request.getParameterMap();
		Users user = new Users();
		try {
			//����ȡ�������ݷ�װ��user��ȥ
			BeanUtils.populate(user, map);//map�����е��������bean����
			
			//�ٷ�װһЩ������Ϣ   
			user.setActiveCode(PwdMd5Util.md52Code(user.getEmail() + user.getPassword()));
			user.setRole(1);
			user.setStatus("0");
					
		} catch (Exception e) {
			e.printStackTrace();
			message = "ע��ʧ�ܣ�����";
			request.setAttribute("registerMessage", message);
			request.getRequestDispatcher("/jsp/user/register.jsp").forward(request, response);
			return;
		}
		//����ע��ҵ��
		boolean isSuccess = false;
		RegisterService service = new RegisterServiceImpl();
		try {
			isSuccess = service.isRegister(user);
			if(!isSuccess){
				message = "�û�ע��ʧ��";
			}
		} catch (UserExistException e) {
			e.printStackTrace(); //�û��Ѿ�����
			isSuccess = false;
			message = e.getMessage();
		} catch (SendEmailException e) {
			e.printStackTrace();
			isSuccess = false;
			message = e.getMessage();//�ʼ�����ʧ��
		}
		
		//��ͻ��˷�����Ϣ  
		request.setAttribute("registerMessage", message);
		request.getRequestDispatcher("/jsp/user/register.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
