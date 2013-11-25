package com.insigma.guobo.tbk.log;

import org.apache.log4j.Logger;

public class LogStyle {
	private static final String DEBUG = "debug";
	private static final String INFO = "info";
	private static final String WARN = "warn";
	private static final String ERROR = "error";
	private static final String FATAL = "fatal";
	/**
	 * ��ӡservice�����־��Ϣ
	 * @param clazz  ���ڵ���
	 * @param message  Ҫ��ӡ����Ϣ
	 * @param level  ��־����
	 */
	public static void serviceLog(Class clazz, String message, String level){
		Logger logger = Logger.getLogger(clazz);
		if(DEBUG.equals(level)){
			logger.debug("  service��  "+message);
		}else if(INFO.equals(level)){
			logger.info("  service��  " + message);
		}else if(WARN.equals(level)){
			logger.warn("  service��  " + message);
		}else if(ERROR.equals(level)){
			logger.error("  service��  " + message);
		}else if(FATAL.equals(level)){
			logger.fatal("  service��  " + message);
		}
	}
	
	/**
	 * ��ӡda��Ϣo�����־
	 * @param clazz  ��������
	 * @param message  ��Ҫ��ӡ����Ϣ
	 * @param level   ��־����
	 */
	public static void daoLog(Class clazz, String message, String level){
		Logger logger = Logger.getLogger(clazz);
		if(DEBUG.equals(level)){
			logger.debug("dao��  "+message);
		}else if(INFO.equals(level)){
			logger.info("dao��  " + message);
		}else if(WARN.equals(level)){
			logger.warn("dao��  " + message);
		}else if(ERROR.equals(level)){
			logger.error("dao��  " + message);
		}else if(FATAL.equals(level)){
			logger.fatal("dao��  " + message);
		}
	}
	/**
	 * ��ӡ�������ʱ����־��Ϣ
	 * @param clazz   ��ǰ��־����һ������
	 * @param message  ��־��Ϣ
	 * @param level    ��־����
	 */
	public static void taskLog(Class clazz, String message, String level){
		Logger logger = Logger.getLogger(clazz);
		if(DEBUG.equals(level)){
			logger.debug("�����  "+message);
		}else if(INFO.equals(level)){
			logger.info("�����  " + message);
		}else if(WARN.equals(level)){
			logger.warn("�����  " + message);
		}else if(ERROR.equals(level)){
			logger.error("�����  " + message);
		}else if(FATAL.equals(level)){
			logger.fatal("�����  " + message);
		}
	}
	

}
