package com.insigma.guobo.tbk.services.taskservice;

import java.util.List;

import com.insigma.guobo.tbk.dao.TempGoodsDao;
import com.insigma.guobo.tbk.dao.TempGoodsDaoImpl;
import com.insigma.guobo.tbk.pojo.TempGoods;

public class AddTempGoodsServiceImpl implements AddTempGoodsService {
	TempGoodsDao tmpGodsDAO = new TempGoodsDaoImpl();
	public void addTempGood(List<TempGoods> list) {
		
		for (TempGoods tg : list) {
			Long id = Long.parseLong(tg.getTaobaoId());
			//���ж��Ƿ�����������
			if(!tmpGodsDAO.isExitTg(tg.getTaobaoId())){  //���������  ���в������
				
				tmpGodsDAO.addTg(tg);  //����
				
			}
		}
		
	}
	public List<TempGoods> getTempGoods(int pageNo, int pageNum) {
		List<TempGoods> list = tmpGodsDAO.getTempGoods(pageNo,pageNum);
		return list;
	}

}
