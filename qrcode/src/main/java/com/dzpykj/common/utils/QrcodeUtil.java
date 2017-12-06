package com.dzpykj.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QrcodeUtil {
	
	public static void encoderQRCode(String content, String imgPath, String imgType) throws Exception {  
		Qrcode qrcodeHandler = new Qrcode();  
		// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
		qrcodeHandler.setQrcodeErrorCorrect('M');  
		qrcodeHandler.setQrcodeEncodeMode('B');  
		// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
		qrcodeHandler.setQrcodeVersion(15);  
		// 获得内容的字节数组，设置编码格式  
		byte[] contentBytes = content.getBytes("utf-8");  
		// 图片尺寸  
		int width = 235;
		int height = 235;
		BufferedImage bufImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
		Graphics2D gs = bufImg.createGraphics();  
		// 设置背景颜色  
		gs.setBackground(Color.WHITE);  
		gs.clearRect(0, 0, width, height);  
		
		// 设定图像颜色> BLACK  
		gs.setColor(Color.BLACK);  
		// 设置偏移量，不设置可能导致解析出错  
		int pixoff = 2;  
		// 输出内容> 二维码  
		if (contentBytes.length > 0 && contentBytes.length < 800) {  
		    boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
		    for (int i = 0; i < codeOut.length; i++) {  
		        for (int j = 0; j < codeOut.length; j++) {  
		            if (codeOut[j][i]) {  
		                gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
		            }  
		        }  
		    }  
		} else {  
		    throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
		}  
		gs.dispose();  
		bufImg.flush();  
		File imgFile = new File(imgPath);  
		// 生成二维码QRCode图片  
		ImageIO.write(bufImg, imgType, imgFile);  
    }  
    
    public static void main(String[] args) {
        String encoderContent = "BEGIN:VCARD\n" +
        	    "VERSION:3.0\n" +
        	    "N:柴晓阳\n" +
        	    "EMAIL:1530944605@qq.com\n" +
        	    "TEL;WORK:111111\n" +//工作电话
        	    "TEL;HOME:222222\n" +//工作电话
        	    "TEL;CELL:3333333\n" +//移动电话
        	    "ADR:北七家;北京;中国\n" +//家庭地址
        	    "ORG:北京市知春路联银通\n" +//值
        	    "TITLE:Java工程师\n" +//职位
//        	    "URL:http://blog.csdn.net/lidew521\n" +
        	    "NOTE:哼哼哼哼哼哼\n" +
        	    "BDAY:1991年4月3日\n" +
        	    "END:VCARD";
        
		String imgPath = "F:/QRCode.png";
		QrcodeUtil handler = new QrcodeUtil();  
		try {
			handler.encoderQRCode(encoderContent, imgPath, "png");	
		} catch (Exception e) {
			e.printStackTrace();
		}  
    	
	}
}
