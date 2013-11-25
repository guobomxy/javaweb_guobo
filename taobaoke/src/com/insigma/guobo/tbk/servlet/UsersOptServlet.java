package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insigma.guobo.tbk.services.GetUsersServiceImpl;

public class UsersOptServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//��ȡ����
		String type = request.getParameter("type");
		String ids = request.getParameter("ids");
		if("user".equals(type)){  //�û�����
			//���巵�ص���Ϣ
			String message = "0";
			boolean isSuccess = false;
			
			//У��ids�Ƿ�Ϊ�յ�
			if(null == ids || "".equals(ids)){
				response.getWriter().write(message);
				return;
			}
			//��ȡ�û���id
			String[] id = ids.split(";");
			//���ַ����͵�id  תΪ  int���͵�
			int[] idInts = new int[id.length];
			for (int i = 0;i < id.length ;i++) {
				idInts[i] = Integer.parseInt(id[i]);
			}
			//ɾ�����ݿ��е�����
			isSuccess = new GetUsersServiceImpl().delUsers(idInts);
			if(isSuccess){
				message = "1";
			}
			
			//������Ϣ
			response.getWriter().write(message);
			
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
