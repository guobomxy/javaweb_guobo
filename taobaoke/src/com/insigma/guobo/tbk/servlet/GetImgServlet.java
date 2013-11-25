package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.insigma.guobo.tbk.pojo.TempGoods;
import com.insigma.guobo.tbk.services.taskservice.AddTempGoodsServiceImpl;
import com.insigma.guobo.tbk.utils.Constants;

public class GetImgServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//��ȡ���� Ҫ��ȡ���ǵڼ�ҳ������   ��ȡ������ �Լ�����
		String page = request.getParameter("pageNo");
		int pageNo = (null != page && !"".equals(page)) ? Integer.parseInt(page) : 1;
		int pageNum = Constants.JSP_PAGENUM;
		//dkk
		//����service���ȡ����   �ȴ�tempgoods ����ȡֵ
		List<TempGoods> list = new AddTempGoodsServiceImpl().getTempGoods(pageNo,pageNum);
		
		//�ж��Ƿ�Ϊ��
		if(null != list && list.size() != 0){
			JSONArray ja = JSONArray.fromObject(list);
			response.getWriter().write(ja.toString());
			return;
		}
		//���Ϊ�յĻ��򷵻�  ""
		response.getWriter().write("");
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
