package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IsSuccessServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		//��ȡ��־message
		String message = (String)request.getAttribute("message");
		if("1".equals(message)){
			System.out.println("----------------�ɹ�-------------------");
			//����ɹ�
			response.setHeader("refresh", "3;url='/taobaoke/jsp/user/login.jsp'");
			response.getWriter().write("����ɹ�������");
			response.getWriter().write("��3��� ������¼ҳ��<a href='/taobaoke/jsp/user/login.jsp'>   �Ȳ����� </a>");
		}else{
			System.out.println("----------------ʧ��-------------------");
			//����ʧ��
			response.setHeader("refresh", "3;url='/taobaoke/jsp/user/index.jsp'");
			response.getWriter().write("����ʧ�ܣ�����");
			response.getWriter().write("��3��� ������ҳ    ��<a href='/taobaoke/jsp/user/index.jsp'>   �Ȳ�����   </a>");
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
