package com.res.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileTools {

	private static final int BUFFER_SIZE = 16 * 1024;

	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(" . ");
		return fileName.substring(pos);
	}

	public static boolean fileExist(String filePath) {
    	File file = new File(filePath);
    	if(file.exists()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
	public static File mkdir(String path) {
		File dirFile = null;
		try {
			dirFile = new File(path);
			boolean bFile = dirFile.exists();
			if (bFile) {
				//System.out.println("The folder exists.");
			} else {
				//System.out.println("The folder do not exist，now trying to create a one...");
				bFile = dirFile.mkdir();
				if (bFile) {
					System.out.println("创建文件夹");
				} else {
					System.out.println(" 文件夹创建失败，清确认磁盘没有写保护并且空件足够");
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart : 文件夹创建发生异常");
			err.printStackTrace();
		}
		return dirFile;
	}
	
}