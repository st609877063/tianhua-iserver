package com.people.dptwb.tools;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileUploadUtil {
	private static final Logger logger = Logger.getLogger(FileUploadUtil.class);

	/**
	 * 使用JAVA提供的FileChannel的方法上传文件
	 * 
	 * @param upload
	 * @param descFilePath
	 * @return true:成功；false:失败
	 */
	public static boolean uploadFile(File upload, String descFilePath) throws FileNotFoundException {
		boolean reVal = false;
		// 上传文件
		FileOutputStream fos = new FileOutputStream(descFilePath);
		FileInputStream fis = new FileInputStream(upload);
		FileChannel foc = null;
		FileChannel fic = null;
		try {
			foc = fos.getChannel();
			fic = fis.getChannel();
			long sourceSize = fic.size();
			long destSize = foc.transferFrom(fic, 0, sourceSize);
			if (destSize == sourceSize) {
				reVal = true;
			} else {
				logger.error("文件传输失败，目标文件字节数与源文件字节数不相等");
			}
		} catch (Exception e) {
			logger.error("uploadFile(File, String) ===> 上传到目标文件时出现异常", e);
		} finally {
			try {
				if (foc != null) {
					foc.close();
				}
			} catch (Exception e2) {
				logger.error(e2);
			}
			try {
				if (fic != null) {
					fic.close();
				}
			} catch (Exception e2) {
				logger.error(e2);
			}
		}

		return reVal;

	}

	/**
	 * 使用apache commons的方法上传文件
	 * 
	 * @param upload
	 * @param descFilePath
	 * @return true:成功；false:失败
	 */
	public static boolean uploadFileUseCommons(File upload, String descFilePath) {
		File descFile = new File(descFilePath);
		boolean reVal = false;
		try {
			FileUtils.copyFile(upload, descFile);
			reVal = true;
		} catch (IOException e1) {
			logger.error("uploadFileUseCommons(File, String) ===> 上传到目标文件时出现异常", e1);
		}
		return reVal;
	}

	/**
	 * 获取图片类型，参数为FILE对象
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getImageType(File file) {
		int length = 10;
		if (!file.exists()) {
			return null;
		}
		InputStream is = null;
		byte[] data = null;
		String type = null;
		try {
			is = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

			data = new byte[length];
			is.read(data);
			is.close();
		} catch (Exception e) {
			logger.error("getImagType(File) ====> 读取图片时出现异常", e);
		}

		if (data != null && data.length == 10) {

			// Png test:   
			if (data[1] == 'P' && data[2] == 'N' && data[3] == 'G') {
				type = "PNG";

			}
			// Gif test:   
			else if (data[0] == 'G' && data[1] == 'I' && data[2] == 'F') {
				type = "GIF";

			}
			// JPG test:   
			else if (data[6] == 'J' && data[7] == 'F' && data[8] == 'I' && data[9] == 'F') {
				type = "JPG";

			}
		}
		return type;
	}
	
}
