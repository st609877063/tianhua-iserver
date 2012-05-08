package com.platform.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil{

	public static void saveResizeImage( String sourceFilePath, String saveFilePath, int width, int hight) throws Exception {
		
		File sourceFile = new File(sourceFilePath);
		BufferedImage srcImage = ImageIO.read(sourceFile);

		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		
		File saveFile = new File(saveFilePath);
		ImageIO.write(srcImage, "JPEG", saveFile);
	}

	public static BufferedImage resize(BufferedImage srcBufImage, int width, int height) {

		BufferedImage bufTarget = null;

		int type = srcBufImage.getType();
		double sx = (double) width / srcBufImage.getWidth();
		double sy = (double) height / srcBufImage.getHeight();

		//这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        //则将下面的if else语句注释即可
        if(sx > sy) {
            sx = sy;
            width = (int)(sx * srcBufImage.getWidth());
        } else{
            sy = sx;
            height = (int)(sy * srcBufImage.getHeight());
        }
		
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			bufTarget = new BufferedImage(width, height, type);

		Graphics2D g = bufTarget.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufImage, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return bufTarget;
	}
	
	/*******************************************************************/
	public static void main(String[] args) {
		String filePath = "E:\\1s.jpg"; //160*210
		String filePath2 = "E:\\1b.jpg"; //2492*3201
		
		String saveFilePath = "E:\\test.jpg";
		try {
			ImageUtil.saveResizeImage(filePath, saveFilePath, 1600, 2100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
