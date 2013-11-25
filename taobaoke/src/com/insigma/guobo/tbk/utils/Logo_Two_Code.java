package com.insigma.guobo.tbk.utils;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class Logo_Two_Code {
	/** 
     * ���ɶ�ά��(QRCode)ͼƬ 
     * @param content ��ά��ͼƬ������
     * @param imgPath ���ɶ�ά��ͼƬ������·��
     * @param ccbpath  ��ά��ͼƬ�м��logo·��
     */  
    public static int createQRCode(String content, String imgPath,String ccbPath) {  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            //���ö�ά���Ŵ��ʣ���ѡL(7%)��M(15%)��Q(25%)��H(30%)���Ŵ���Խ�߿ɴ洢����ϢԽ�٣����Զ�ά�������ȵ�Ҫ��ԽС  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            //N��������,A�����ַ�a-Z,B���������ַ�
            qrcodeHandler.setQrcodeEncodeMode('B'); 
            // �������ö�ά��汾��ȡֵ��Χ1-40��ֵԽ��ߴ�Խ�󣬿ɴ洢����ϢԽ��  
            qrcodeHandler.setQrcodeVersion(8);  
  
            byte[] contentBytes = content.getBytes("utf-8");  
            BufferedImage bufImg = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 150, 150);  
  
            // �趨ͼ����ɫ > BLACK  
            //gs.setColor(Color.BLACK);  
            gs.setColor(Color.RED);
  
            // ����ƫ���� �����ÿ��ܵ��½�������  
            int pixoff = 2;  
            // ������� > ��ά��  
            if (contentBytes.length > 0 && contentBytes.length <150) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  //��ά��  ����һ����ά����  boolean�͵�  Ȼ��ΪtrueʱͿ�ɺ�ɫ��
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = "  
                        + contentBytes.length + " not in [ 0,125]. ");  
                return -1;
            }
            Image img = ImageIO.read(new File(ccbPath));//ʵ����һ��Image����
            gs.drawImage(img, 62, 62, null);//5050
            gs.dispose();
            bufImg.flush();

            // ���ɶ�ά��QRCodeͼƬ 
            File imgFile = new File(imgPath);
            ImageIO.write(bufImg, "png", imgFile);

        } catch (Exception e)
        {
            e.printStackTrace();
            return -100;
        }
        
        return 0;
    }  

    public static void main(String[] args) {
    	 String imgPath = "D:/99.png"; 
    	 String imgPath1 = "D:/3.jpg"; 
    	 String encoderContent = "������";
    	 Logo_Two_Code logo_Two_Code = new Logo_Two_Code();
    	 logo_Two_Code.createQRCode(encoderContent, imgPath, imgPath1);
	}
}
