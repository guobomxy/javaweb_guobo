package com.insigma.guobo.tbk.utils;

import javax.persistence.Entity;

@Entity
public class Constants {
	//�û���ɫ
	public static final String USER_ROLE_NORMAL = "1";
	public static final String USER_ROLE_ADMIN = "0";
	//�û�����״̬
	public static final String USER_ACTIVE_NO = "0";
	public static final String USER_ACTIVE_YES = "1";
	//����״̬
	public static final String TEMP_GOODS_STATUS_NOT_COMMIT="0";
	public static final String TEMP_GOODS_STATUS_COMMIT="1";
	public static final String TEMP_GOODS_diuqi="2";
	
	//jsp ��ҳչʾ����ƷͼƬ ÿ�μ��ػ�ȡ������
	public static final int JSP_PAGENUM = 20;
}
