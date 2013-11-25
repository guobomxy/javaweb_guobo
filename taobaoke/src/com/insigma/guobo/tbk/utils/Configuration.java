package com.insigma.guobo.tbk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;
import javax.persistence.Entity;

@Entity
public class Configuration {
	//��¼����
	private static final String CONFIG_FILE_NAME = "config.properties";
	private static Properties p = new Properties();
	//��̬��ʵ�ּ�����Դ�ļ�
	static{
		//��ȡ·��
		String path = Configuration.class.getResource("/").getPath() + CONFIG_FILE_NAME;

		try {
			//�ݴ�  ��ֹ·������������
			path = URLDecoder.decode(path, "UTF-8");
			p.load(new FileInputStream(new File(path)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUrl(){
		return p.getProperty("taobaourl");
	}
	
	public static String getAppKey1(){
		return p.getProperty("appkey1");
	}
	public static String getAppSecret1(){
		return p.getProperty("appsecret1");
	}
	public static String getAppKey2(){
		return p.getProperty("appkey2");
	}
	public static String getAppSecret2(){
		return p.getProperty("appsecret2");
	}	
	
	public static String getMailServer(){
		return p.getProperty("mailSMTPServer");
	}
	
	public static String getMailPort(){
		return p.getProperty("mailSMTPServerPort");
	}
	
	public static String getMailUser(){
		return p.getProperty("mailUser");
	}
	
	public static String getMailPassword(){
		return p.getProperty("mailPassword");
	}
	
	public static String getMailFrom(){
		return p.getProperty("mailFrom");
	}
	
	public static String getTaobaokeServerName(){
		return p.getProperty("taobaokeServerName");
	}
	
	public static String getTaobaokeServerPort(){
		return p.getProperty("taobaokeServerPort");
	}
	

}
