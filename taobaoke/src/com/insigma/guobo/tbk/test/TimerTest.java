package com.insigma.guobo.tbk.test;

import java.util.Date;
import java.util.Timer;

public class TimerTest {

	public static void main(String[] args) {
		CounterTask tt = new CounterTask();
		Timer tmer = new Timer();
		tmer.schedule(tt, new Date());   //��timertaskִ����� �̲߳������
		
		//tmer.schedule(tt, 2000); //���е������ �ӳ�2��ִ��
		
		//tmer.schedule(tt, new Date(), 5000);//�ڵ�ǰʱ������  Ȼ��  ÿ��5������һ��  ʵ����tt���е���10�� ����ǣ�ûʲôЧ����
		//tmer.scheduleAtFixedRate(tt, new Date(), 5000);//�ڵ�ǰʱ������  Ȼ��  ÿ��5������һ��
		
	}
}
