package com.insigma.guobo.tbk.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import com.insigma.guobo.tbk.log.LogStyle;
import com.insigma.guobo.tbk.pojo.TaskConf;
import com.insigma.guobo.tbk.pojo.TempGoods;
import com.insigma.guobo.tbk.services.taskservice.AddTempGoodsServiceImpl;
import com.insigma.guobo.tbk.utils.Configuration;
import com.insigma.guobo.tbk.utils.Constants;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.internal.ws.push.Logger;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.response.TaobaokeItemsGetResponse;

public class GetGoodsTask extends TimerTask {
	
	//����һ���������ö���
	private TaskConf taskConf;
	
	public GetGoodsTask(TaskConf task){
		this.taskConf = task;
	}
	
	
	@Override
	public void run() {
		
		LogStyle.taskLog(GetGoodsTask.class, "============"+taskConf.getName() + "�Ѿ�����====="+ Calendar.getInstance().getTimeInMillis() +"=======", "error");
		
		//����������  ����Ҫִ�е������Լ���װ���͵�����
		//�ж������Ƿ���
		if(null == taskConf){
			LogStyle.taskLog(GetGoodsTask.class, "���񲻴���", "warn");
			return;
		}
		//����taobaoclient
		TaobaoClient client = new DefaultTaobaoClient(Configuration.getUrl(), Configuration.getAppKey2(), Configuration.getAppSecret2());
		//������ȡ��Ʒapi����
		TaobaokeItemsGetRequest request = new TaobaokeItemsGetRequest();
		//������Ӧ��api����
		TaobaokeItemsGetResponse response = new TaobaokeItemsGetResponse();
		
		
		//�жϴ����keyword 
		String[] keyWords = null;
		if(null == taskConf.getKeyword() || "".equals(taskConf.getKeyword())){
			keyWords = new String[]{""};
		}else{
			keyWords = taskConf.getKeyword().split(",");
		}

		//���÷����ֶ�  volum  30���ڽ�����
		request.setFields("num_iid,title,nick,pic_url,price,click_url,commission,commission_rate,commission_num,commission_volume,shop_click_url,seller_credit_score,item_location,volume");

		//������Ʒ�����Ӷ��  �����Ӷ��Ĭ��
		if(taskConf.getMinRate() == null){  //Double  �� null   double ��  0.0
			request.setStartCommissionRate("0000");   //�˴���������д
		}else{
			
			String t = Math.floor(taskConf.getMinRate()*100)+"";
			
			request.setStartCommissionRate(t.substring(0,t.indexOf('.')));   //�˴���������д
		}
		//����Ĭ�ϵ����Ӷ��
		request.setEndCommissionRate("10000");
		
		//��������
		if(taskConf.getMinNum() == null){
			request.setStartCommissionNum("10");
		}else{
			request.setStartCommissionNum(taskConf.getMinNum() + "");
		}
		
		if(taskConf.getMaxNum() == null){
			request.setEndCommissionNum("500000");  //
		}else{
			request.setEndCommissionNum(taskConf.getMaxNum() + "");
		}
		
		
		//���ݴ���Ĺؼ��ֽ���ץȡ��Ʒ
		for (String key : keyWords) {
			//���ùؼ���
			request.setKeyword(key);
			//ȡǰ400��  һҳĬ��10�� ȡʮ��
			for(int i = 1;i <= 10; i++){
				
				request.setPageNo((long)i);
				//��������
				try {
					response = client.execute(request);
					List<TaobaokeItem> list = response.getTaobaokeItems();
					if(null == list){
						break;
					}
					List<TempGoods> listTemGoods = new ArrayList<TempGoods>();
					//�����ֵ ����б�����װ
					for (TaobaokeItem taobaokeitems : list) {
						TempGoods goods = new TempGoods();
						
						goods.setTaobaoId(taobaokeitems.getNumIid().toString());
						//������Ʒ������
						goods.setName(taobaokeitems.getTitle());
						//������Ʒ�ļ۸�
						goods.setPrice(Double.parseDouble(taobaokeitems.getPrice()));
						//������Ʒ�����ӵ�ַ
						goods.setUrl(taobaokeitems.getClickUrl());
						//����Ӷ�����
						goods.setCommissionRate(Double.parseDouble(taobaokeitems.getCommissionRate()) / 100);
						//����30���ڵ��ƹ���
						goods.setCommissionNum(Integer.parseInt(taobaokeitems.getCommissionNum()));
						
						//����30������֧����Ӷ��
						goods.setCommissionVolumn(Double.parseDouble(taobaokeitems.getCommissionVolume()));
						//������Ʒ�Ľ���
						goods.setContent(taobaokeitems.getItemLocation());
						//������Ʒ����¼״̬
						goods.setStatus(Constants.TEMP_GOODS_STATUS_NOT_COMMIT);
						//ͼƬ·��
						goods.setPicUrl(taobaokeitems.getPicUrl());
						//30���ڽ�����
						goods.setVolum(Integer.parseInt((taobaokeitems.getVolume().toString())));
						
						listTemGoods.add(goods);

					}
					//�ѻ�ȡ�����ݷ������ݿ���
					new AddTempGoodsServiceImpl().addTempGood(listTemGoods);
					
					
				} catch (ApiException e) {
					
					e.printStackTrace();
					LogStyle.taskLog(GetGoodsTask.class, "����ʧ�ܣ�����", "error");
				}
				
				
			}
			
		}
		
		
		
		
		
	}

}
