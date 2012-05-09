package com.platform.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class ImageUtil{

	//type:"COVER"按width,height缩放。"ARTICLE"按width和原图等比例缩放
	public static void saveResizeImage( String sourceFilePath, String saveFilePath, int width, int hight, String type) throws Exception {
		
		File sourceFile = new File(sourceFilePath);
		BufferedImage srcImage = ImageIO.read(sourceFile);
		int srcWidth = srcImage.getWidth();
		
		if (srcWidth > width) { //原图大于要修的标准
			srcImage = resize(srcImage, width, hight, type);
			File saveFile = new File(saveFilePath);
			ImageIO.write(srcImage, "JPEG", saveFile);
		} else {
			File tFile = new File(saveFilePath);
			FileUtils.copyFile(sourceFile, tFile);
		}
	}

	
	public static BufferedImage resize(BufferedImage srcBufImage, int width, int height, String type) {
		BufferedImage bufTarget = null;
		int imageType = srcBufImage.getType();
		int srcWidth = srcBufImage.getWidth();
		int srcHeight = srcBufImage.getHeight();
		
		if(type!=null && type.equals("COVER")) {
			//do nothing
		} else if(type!=null && type.equals("ARTICLE")) {
			//按原图等比例
			height = (srcHeight*width) / srcWidth;
		} 
		
		double sx = (double) width / srcWidth;
		double sy = (double) height / srcHeight;

		//这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放则将下面的if else语句注释即可
        if(sx > sy) {
            sx = sy;
            width = (int)(sx * srcBufImage.getWidth());
        } else{
            sy = sx;
            height = (int)(sy * srcBufImage.getHeight());
        }
		
		if (imageType == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			bufTarget = new BufferedImage(width, height, imageType);

		Graphics2D g = bufTarget.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufImage, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return bufTarget;
	}
	
	/*******************************************************************/
	public static void main(String[] args) {
		String filePath = "E:\\107961502.jpg"; //160*210
		String saveFilePath = "E:\\test.jpg";
		try {
			ImageUtil.saveResizeImage(filePath, saveFilePath, 400, 210, "ARTICLE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
