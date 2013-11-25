package com.insigma.guobo.tbk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insigma.guobo.tbk.log.LogStyle;
import com.insigma.guobo.tbk.pojo.TaskConf;
import com.insigma.guobo.tbk.services.taskservice.TaskOptServiceImpl;
import com.insigma.guobo.tbk.task.GetGoodsTask;
import com.insigma.guobo.tbk.vo.TaskInfo;

public class TaskServlet extends HttpServlet {

	private Map<String, GetGoodsTask> taskMap = new HashMap<String, GetGoodsTask>();
	private Timer timer = new Timer();
	

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//����  ����Ա��������� �� ����           /taobaoke/src/com/insigma/guobo/tbk/servlet/TaskServlet.java
		//��ȡ  type  ������
		String type = request.getParameter("type");
		if("stop".equals(type)){
			//ֹͣ�������
			//��ȡ  Ҫ������  ��������������
			String name = request.getParameter("name");
			String[] names = null; 
			if(null != name && !"".equals(name)){
				names = name.split(";");
			}
			String message = "";
			for (String taskName : names) {  //ע���������ajax�������Ĳ���  ������ ����ǰ�߱��루2�Σ� �ڴ˽���һ��
				taskName = URLDecoder.decode(taskName, "utf-8");
				System.out.println(taskName);
				if(!taskMap.get(taskName).cancel()){
					//�������ֹͣʧ�� ���¼����
					message += taskName + ",";
				}else{
					//TODO  Ҫ�����ݿ���ɾ������
					LogStyle.taskLog(TaskServlet.class, taskName + "�Ѿ���ֹͣ������", "warn");
				}
			}
			if(!"".equals(message)){  //�������ʧ�ܵ��� ��װΪ ��������ʧ��
				message = message.substring(0, message.lastIndexOf(',')) + "ִ��ʧ�ܣ����飡";
			}
			response.getWriter().write(message);
			
			
		}else if("edit".equals(type)){
			//���������
			
			
		}else if("add".equals(type)){
			String message = "";
			//�����������
			//��ȡ����������Ϣ  ��װ
			TaskConf tc = new TaskConf();
			tc.setName(URLDecoder.decode(request.getParameter("name"), "utf-8"));
			tc.setCid(request.getParameter("cid"));
			tc.setEndTime(request.getParameter("endTime"));
			tc.setStartTime(request.getParameter("startTime"));
			tc.setMinNum(Integer.parseInt(request.getParameter("minNum")));
			tc.setMaxNum(Integer.parseInt(request.getParameter("maxNum")));
			tc.setStatus("1");
			tc.setMinRate(Double.parseDouble(request.getParameter("minRate")));
			tc.setKeyword(URLDecoder.decode(request.getParameter("keyword"), "utf-8"));
			
			//�Ȱ�����ӵ��б���
			GetGoodsTask ggt = new GetGoodsTask(tc);
			//������������  ���ж���
			//��ȡ��ʼ�����ʱ��
			TaskInfo ti = this.getRunTime(tc);
			try{
				timer.scheduleAtFixedRate(ggt, ti.getRunTime(), ti.getYanChiTime()); //
				taskMap.put(tc.getName(), ggt);
				
				LogStyle.taskLog(TaskServlet.class, tc.getName() + "  �����Ѷ��ƣ�", "warn");
				
				//������������ɹ�  ���ŵ����ݿ�
				new TaskOptServiceImpl().addTaskConf(tc);  //��Ҫ�ж��Ƿ���ӳɹ�  
				message = "������ӳɹ���";
			}catch(Exception e){
				message = "�������ʧ�ܣ�";
			}
			message = URLEncoder.encode(message, "utf-8");
			response.getWriter().write(message);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


	public void init() throws ServletException {
		//��ʱ����
		//��ȡ���ݿ��е�   taskconf  
		List<TaskConf> list = new ArrayList<TaskConf>();
		list = new TaskOptServiceImpl().getTaskConf();
		if(null == list){
			LogStyle.taskLog(TaskServlet.class, "��ǰδ��������", "warn");
			return;
		}
		//��������  Ҫ���ǵ�һ���������   �ڶ���Ͱ�����������  ���web��Ŀֻ��һ��  ��ô���servletֻ����һ��
		
		for (TaskConf taskConf : list) {
			if("1".equals(taskConf.getStatus())){
				//����timertask
				GetGoodsTask ggt = new GetGoodsTask(taskConf);
				//������������  ���ж���
				//��ȡ��ʼ�����ʱ��
				TaskInfo ti = this.getRunTime(taskConf);
				
				timer.scheduleAtFixedRate(ggt, ti.getRunTime(), ti.getYanChiTime()); //
				taskMap.put(taskConf.getName(), ggt);
				
				LogStyle.taskLog(TaskServlet.class, taskConf.getName() + "  �����Ѷ��ƣ�", "warn");
			}
			
		}
		
	}

	private TaskInfo getRunTime(TaskConf tc) {
		
		TaskInfo ti = new TaskInfo();
		//���ݿ��е�ʱ���ʽ��09:09
		//��ȡ����ʼʱ�� �� �������ʱ��
		String startTimeHH = tc.getStartTime().substring(0, tc.getStartTime().indexOf(':'));
		String startTimeMM= tc.getStartTime().substring(tc.getStartTime().indexOf(':')+1);
		String endTimeHH = tc.getEndTime().substring(0, tc.getStartTime().indexOf(':'));
		String endTimeMM = tc.getEndTime().substring(tc.getStartTime().indexOf(':')+1);
		
		//��ȡ��ǰ��ʱ��
		Calendar currCAL = Calendar.getInstance();
		long currTime = currCAL.getTimeInMillis();  // ��ǰ��ʱ��
		
		//��ȡ�� �� ��
//		int year = currCAL.get(Calendar.YEAR);
//		int month = currCAL.get(Calendar.MONTH);
//		int day = currCAL.get(Calendar.DAY_OF_MONTH);
		
		//��ȡ����ʼʱ��ĺ���ֵ
		currCAL.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTimeHH));
		currCAL.set(Calendar.MINUTE, Integer.parseInt(startTimeMM));
		long startTime = currCAL.getTimeInMillis();
		
		//��ȡ�������ʱ�ĺ���ֵ
		currCAL.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTimeHH));
		currCAL.set(Calendar.MINUTE, Integer.parseInt(endTimeMM));
		long endTime = currCAL.getTimeInMillis();
		
		if(startTime < currTime){
			//��ǰʱ��   ��   ��ʼʱ��    ֮ǰ
			ti.setRunTime(new Date(startTime));
			ti.setYanChiTime(24 * 3600 * 1000);
			
			System.out.println(tc.getName() + "   " + Calendar.getInstance().getTimeInMillis());
			
		}else if(endTime < currTime){
			//��ǰʱ����  ����ʱ��֮��
			ti.setRunTime(new Date(currTime - endTime + 60 * 1000));//24 * 3600 * 1000 TODO
			ti.setYanChiTime(24 * 3600 * 1000);
			
			System.out.println(tc.getName() + "   " + Calendar.getInstance().getTimeInMillis());
			
		}else{
			//��ǰʱ����   ����ʱ����
			ti.setRunTime(new Date(currTime));
			ti.setYanChiTime(currTime - startTime + 24 * 3600 * 1000);// ��һ��������  �ڶ��λ���ô��  ���� �� new Timer().scheduleAtFixedRate(task, delay, period)
			
			System.out.println(tc.getName() + "   " + Calendar.getInstance().getTimeInMillis());
			
		}
		
		return ti;
	}

}
