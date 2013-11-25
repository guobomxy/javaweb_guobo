package com.insigma.guobo.tbk.servlet;

import java.io.IOException;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insigma.guobo.tbk.services.LoginService;
import com.insigma.guobo.tbk.services.LoginServiceImpl;

@Entity
public class CheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//���÷��ص�����
		String message = "0";
		//��ȡ���� ����ajaxУ��
		String email = request.getParameter("email");
		System.out.println(email);
		//��ȡ���ݿ��жϸ������Ƿ�ע���
		boolean isValidEmail = false;
		LoginService service = new LoginServiceImpl();
		isValidEmail = service.isValidEmail(email);
		if(isValidEmail){
			message = "1";
		}
		//������Ϣ
		response.getWriter().write(message);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
