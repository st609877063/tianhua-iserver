package com.platform.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class ImageUtil{

	/**
	 * 
	 * @param basepath
	 * @param tempimage
	 * @param sourceFile
	 * @param fileType
	 *            文件类型
	 * @return
	 */
	public boolean bigZoomImage(String basepath, String tempimage, File sourceFile) {
		try {

			String fileUrl = basepath + "uploadimage/" + tempimage;
			String newUrl_b = basepath + "uploadimage/b_" + tempimage;
			BufferedImage image = ImageIO.read(sourceFile);

			int imgWidth = image.getWidth();
			int imgHeight = image.getHeight();
			if (imgWidth > imgHeight) {// 宽>高,长图
				Double bigzomm = imgWidth / 452.0;
				if (bigzomm > 1) {
					ZoomImage zimg = new ZoomImage();
					Double realheight = (imgHeight / bigzomm);

					//zimg.ZoomTheImage(fileUrl, newUrl_b, 452, realheight.intValue());
				}
				else {
					ZoomImage zimg = new ZoomImage();
					//zimg.ZoomTheImage(fileUrl, newUrl_b, imgWidth, imgHeight);
				}
			}
			else {// 高>宽,高图
				//modified by mayq
				//暂时将高度放宽到2000以上，再对宽度进行处理
				if (imgHeight > 2000) {
					if (imgWidth >= 452) {
						Double bigzomm = imgWidth / 452.0;
						ZoomImage zimg = new ZoomImage();
						Double realheight = 0.0;
						// 大图
						realheight = (imgHeight / bigzomm);
						if (realheight > 2500) {
							bigzomm = (imgHeight / 2500.0);
							Double realwidth = (imgWidth / bigzomm);
							//zimg.ZoomTheImage(fileUrl, newUrl_b, realwidth.intValue(), 2500);
						}
						else {
							//zimg.ZoomTheImage(fileUrl, newUrl_b, 452, realheight.intValue());
						}
					}
					else {
						Double bigzomm = imgHeight / 500.0;
						ZoomImage zimg = new ZoomImage();
						Double realwidth = 0.0;
						// 大图
						realwidth = (imgWidth / bigzomm);

						//zimg.ZoomTheImage(fileUrl, newUrl_b, imgWidth, imgHeight);
					}
				}
				else {
					if (imgWidth >= 452) {
						Double bigzomm = imgWidth / 452.0;
						ZoomImage zimg = new ZoomImage();
						Double realheight = 0.0;
						// 大图
						realheight = (imgHeight / bigzomm);

						//zimg.ZoomTheImage(fileUrl, newUrl_b, 452, realheight.intValue());
					}
					else {//
						// 大图
						ZoomImage zimg = new ZoomImage();

						//zimg.ZoomTheImage(fileUrl, newUrl_b, imgWidth, imgHeight);
					}
				}
			}

			return true;
			
		} catch (IOException e) {
			return false;
		}

	}


	/*******************************************************************/
	public static void main(String[] args) {
		ImageUtil mcm = new ImageUtil();
		// mcm.getAddressByIp("22018111186");
		String basepath = "G:\\MyEclipse\\workspace\\microblog-v2\\WebRoot\\";
		String tempimage = "chenwen15.jpg";
		File sourceFile = new File("G:\\MyEclipse\\workspace\\microblog-v2\\WebRoot\\uploadimage\\chenwen15.jpg");
		String fileType = "JPG";
		//mcm.bigZoomImage(basepath, tempimage, sourceFile, fileType);
	}
}
