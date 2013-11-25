package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.insigma.guobo.tbk.pojo.Users;
import com.insigma.guobo.tbk.services.ActiveUserService;
import com.insigma.guobo.tbk.services.ActiveUserServiceImpl;

public class ActiveUserServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ñ�־λ
		String message = "0";
		//����������ʵ��
		ActiveUserService aus = new ActiveUserServiceImpl();
		//��ȡ���Ӵ�������code
		String activeCode = request.getParameter("activeCode");
		activeCode = URLDecoder.decode(activeCode, "utf-8");
		System.out.println(activeCode + "-------------------------");
		//����code��Ψһ��  ���Ҹ�user  ������user���� 
		Users user = aus.activeUser(activeCode);
		System.out.println(user.getActiveCode() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		//�ж��Ƿ񼤻�ɹ�
		if(null != user){
			message = "1";
			request.setAttribute("message", message);
			//�û�����ɹ� ��ת������ɹ�ҳ��
			request.getRequestDispatcher("/servlet/IsSuccessServlet").forward(request, response);
			return;
		}else{
			request.setAttribute("message", message);
			//����ʧ��  ����ת��ʧ��ҳ��
			request.getRequestDispatcher("/servlet/IsSuccessServlet").forward(request, response);
		}

		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
