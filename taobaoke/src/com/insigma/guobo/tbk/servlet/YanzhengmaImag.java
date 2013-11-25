package com.insigma.guobo.tbk.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Entity
public class YanzhengmaImag extends HttpServlet {

	@Id
	@GeneratedValue
	private static final int WIDTH = 120;
	private static final int HEIGHT = 25;
	//����������¼���ɵĺ���
	private String validataImag = "";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//response.setHeader("refresh", "3"); �����������3��ˢ��һ��
		
		
		//����ͼ�� ָ�� ��� �Լ���ʾ��ʽ
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//�õ���ͼ��Ļ��ʹ���    ��ͼ���ϻ�  ����  �߿�  ������  �Լ������ַ�
		Graphics g = img.getGraphics();
		
		//1.������
		setBackground(g);
		
		//2.���ñ߿�
		setBorder(g);
		
		//3.��������
		drawLine(g);
		
		//4.���ַ�
		drawRadomNum((Graphics2D) g);
		request.getSession().setAttribute("validataImag", validataImag);
		System.out.println(validataImag);
		validataImag = "";
		//5.������������ͼƬ   ʹ��  ImageIo.write();д��response.getoutputstream
		response.setContentType("image/jpeg");
		//��ͷ��������� ��Ҫ���� 
		response.setDateHeader("exprise", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		ImageIO.write(img, "jpg", response.getOutputStream());//д��response.getoutputstream�� ���������������ʾ��ʽ
	}

	//���� ����  [\u4e00-\u9fa5] 
	// 
	private void drawRadomNum(Graphics2D g) {//Graphics2D������ת
		// TODO Auto-generated method stub
		
		//���õ���  
		String base ="\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3";
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("����", Font.ITALIC, 20));
		int x = 5;
		for(int i =0;i<4;i++){
			int jiaodu = new Random().nextInt()%30;//��ת  -30�� - 30��֮��
			String hanZi = base.charAt(new Random().nextInt(base.length()))+"";
			g.rotate(jiaodu * Math.PI / 180, x, 20);//�ڻ�֮ǰ  ������ת�Ƕ�
			g.drawString(hanZi, x, 20);
			g.rotate(-jiaodu * Math.PI / 180, x, 20);//�ٰѽǶȵ�������   
			x += 30;
			validataImag += hanZi;
		}
	}


	private void drawLine(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.RED);

		for(int i = 0;i<4;i++){
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			
			g.drawLine(x1, y1, x2, y2);
		}
	}


	private void setBorder(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.drawRect(1, 1, WIDTH-2, HEIGHT-2);
		//g.drawRect(0, 0, WIDTH, HEIGHT);
	}


	private void setBackground(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//g.fillRect(1, 1, WIDTH-2, HEIGHT-2);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
