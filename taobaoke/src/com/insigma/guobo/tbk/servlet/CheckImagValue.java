package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckImagValue extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//��ȡ�û��������֤��
		String value1 = request.getParameter("value");
		value1 = URLDecoder.decode(value1,"utf-8");
		//��ȡ���ɵ���֤��
		String value2 = (String) request.getSession().getAttribute("validataImag");
		//�ж��Ƿ����
		if(value2.equals(value1)){
			//���
			response.getWriter().write("1");
		}else{
			//bu���
			response.getWriter().write("0");
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
