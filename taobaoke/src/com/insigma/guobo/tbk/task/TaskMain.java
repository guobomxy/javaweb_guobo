package com.insigma.guobo.tbk.task;

import java.util.Date;
import java.util.Timer;

import com.insigma.guobo.tbk.pojo.TaskConf;

public class TaskMain {


	public static void main(String[] args) {
		
		//��������taskconf
		TaskConf tc = new TaskConf();
		tc.setKeyword("�ֻ�,����Ь");
		tc.setMaxNum(100000);
		tc.setMinNum(10);
		tc.setMinRate(0.22);
		GetGoodsTask ggt = new GetGoodsTask(tc);
		Timer timer = new Timer();
		timer.schedule(ggt, new Date());

	}

}
