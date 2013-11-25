package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insigma.guobo.tbk.services.LoginService;
import com.insigma.guobo.tbk.services.LoginServiceImpl;

@Entity
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//��ȡ��Ϣ
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String keepLogin = request.getParameter("terms");//�����ѡ�� ��Ϊ 1 
		
		//������˳�����  
		String logout = request.getParameter("logout");
		if("1".equals(logout)){
			//�޸ı���Զ��ύ��cookie
			Cookie cookie = new Cookie("keepLogin","");
			cookie.setMaxAge(0);
			cookie.setPath("/taobaoke");
			response.addCookie(cookie);
			
			//�Ƴ�session
			request.getSession(false).removeAttribute("userName");
			request.getRequestDispatcher("/jsp/user/indexp.jsp").forward(request, response);
			return;
		}
		
		//��֤
		LoginService loginService = new LoginServiceImpl();
		boolean isAccept = loginService.isValidUser(userName, password);
		
		if(isAccept){
			
			//�ж��Ƿ񱣳ֵ�¼
			if("1".equals(keepLogin)){
				//��¼��cookie
				Cookie userNameCookie = new Cookie("userName", URLEncoder.encode(userName, "utf-8"));//��������Ҫurl����
				userNameCookie.setMaxAge(7*24*3600);//����һ��
				userNameCookie.setPath("/taobaoke");
				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(7*24*3600);//����һ��
				passwordCookie.setPath("/taobaoke");
				Cookie keepLoginCookie = new Cookie("keepLogin","1");
				keepLoginCookie.setMaxAge(7*24*3600);//����һ��
				keepLoginCookie.setPath("/taobaoke");				
				response.addCookie(passwordCookie);
				response.addCookie(userNameCookie);
				response.addCookie(keepLoginCookie);
			}
		

			
			//����ɹ�  ��ת����ҳ  ����Ự
			request.getSession().setAttribute("userName", userName);
			request.setAttribute("userName", userName);
			if("0".equals(role)){
				//����Ա
				request.getRequestDispatcher("/jsp/admin/index.jsp").forward(request, response);
				return;
			}else{
				request.getRequestDispatcher("/jsp/user/index.jsp").forward(request, response);
				return;
			}
		}
		
		String message = "�û�������������󣡣���";
		request.setAttribute("msg", message);
		request.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
