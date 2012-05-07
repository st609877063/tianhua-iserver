package com.platform.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ZoomImage{
	   
	public void ZoomEqualImage(String fileUrl, String newUrl) {
		File file = new File(fileUrl); //读入刚才上传的文件

		BufferedImage img = null;
		Image src = null;
		try {
			img = ImageIO.read(file);
			src =img;
			src = src.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);

			//构造Image对象

			BufferedImage tag = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, img.getWidth(), img.getHeight(), null); //绘制缩小后的图
			FileOutputStream newimage = new FileOutputStream(newUrl); //输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			encoder.encode(tag); //近JPEG编码
			newimage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void standardSize(String fileUrl, String newUrl, int stwidth, int stheight) {
		try {
			File file = new File(fileUrl);
			BufferedImage src = null;
			BufferedImage tag = null;
			src = ImageIO.read(file);
			
			int width = src.getWidth();
			int height = src.getHeight();
			
			Image img = src.getScaledInstance(-1, -1, Image.SCALE_SMOOTH);
			
			if (width < stwidth && height >= stheight) {
				//宽小于标准尺寸
				//画长宽一样的图 
				tag = new BufferedImage(height, height,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)tag.getGraphics();      
		        g2.setBackground(Color.WHITE);      
		        g2.clearRect(0, 0, height, height);
		        
		        g2.drawImage(img, (height - width) / 2, 0, null); //绘制缩小后的图
				
			} else if (height < stheight && width >= stwidth) {
				//高小于标准尺寸
				//画长宽一样的图
				tag = new BufferedImage(width, width,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)tag.getGraphics();      
		        g2.setBackground(Color.WHITE);      
		        g2.clearRect(0, 0, width, width);
				
		        g2.drawImage(img, 0, (width - height) / 2,width, height, null); //绘制缩小后的图
				
			} else if (height < stheight && width < stwidth) {
				//宽小于标准尺寸,高小于标准尺寸
				//画填充整个框的图
				tag = new BufferedImage(stwidth ,stheight , BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)tag.getGraphics();      
		        g2.setBackground(Color.WHITE);      
		        g2.clearRect(0, 0, stwidth , stheight );
				
				g2.drawImage(img, (stwidth  - width) / 2,(stheight - height) / 2, width, height, null); //绘制缩小后的图
			}
			else if(width > stwidth && height > stheight && width < height){
				//符合尺寸宽小于高
				//画长宽一样的图
				tag = new BufferedImage(height, height,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)tag.getGraphics();      
		        g2.setBackground(Color.WHITE);      
		        g2.clearRect(0, 0, height, height);
				
		        g2.drawImage(img, (height - width) / 2, 0, null); //绘制缩小后的图
			}
			else if(width > stwidth && height > stheight && width > height){
				//符合尺寸宽大于高
				//画长宽一样的图
				tag = new BufferedImage(width, width,BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = (Graphics2D)tag.getGraphics();      
		        g2.setBackground(Color.WHITE);      
		        g2.clearRect(0, 0, width, width);
				
		        g2.drawImage(img, 0, (width - height) / 2,width, height, null); //绘制缩小后的图
			}
			if(tag != null){
				FileOutputStream newimage = new FileOutputStream(newUrl); //输出到文件流
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
				encoder.encode(tag); //近JPEG编码
				newimage.close();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
