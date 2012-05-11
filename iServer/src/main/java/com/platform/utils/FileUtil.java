package com.platform.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileUtil {
    public static String Encode(String str)
    {
        str = str.replace("'", "''");
        str = str.replace("\"", "&quot;");
        str = str.replace("<", "&lt;");
        str = str.replace(">", "&gt;");
        str = str.replace("\n", "<br>");
        str = str.replace("“", "&ldquo;");
        str = str.replace("”", "&rdquo;");
        return str;
    }

    /// <summary>
    /// 取SQL值时还原字符
    /// </summary>
    /// <param name="str"></param>
    /// <returns></returns>
    public static String Decode(String str)
    {
        str = str.replace("&rdquo;", "”");
        str = str.replace("&ldquo;", "“");
        str = str.replace("<br>", "\n");
        str = str.replace("&gt;", ">");
        str = str.replace("&lt;", "<");
        str = str.replace("&quot;", "\"");
        str = str.replace("''", "'");
        return str;
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
				System.out.println("The folder exists.");
			} else {
				System.out
						.println("The folder do not exist，now trying to create a one...");
				bFile = dirFile.mkdir();
				if (bFile) {
					System.out.println("Create successfully!");
					System.out.println("创建文件夹");
				} else {
					System.out
							.println("Disable to make the folder，please check the disk is full or not.");
					System.out.println(" 文件夹创建失败，清确认磁盘没有写保护并且空件足够");
					System.exit(1);
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart : 文件夹创建发生异常");
			err.printStackTrace();
		}
		return dirFile;
	}
	
	public  static void resize(File originalFile, File resizedFile, int newWidth, float quality) throws IOException {   
	    if (quality < 0 || quality > 1) {   
	        throw new IllegalArgumentException("Quality has to be between 0 and 1");   
	    }    
	    ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());   
	    Image i = ii.getImage();   
	    Image resizedImage = null;    
	    int iWidth = i.getWidth(null);   
	    int iHeight = i.getHeight(null);    
	    if (iWidth > iHeight) {   
	        resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);   
	    } else {   
	        resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);   
	    }    
	    // This code ensures that all the pixels in the image are loaded.   
	    Image temp = new ImageIcon(resizedImage).getImage();    
	    // Create the buffered image.   
	    BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),   
	                                                    BufferedImage.TYPE_INT_RGB);    
	    // Copy image to buffered image.   
	    Graphics g = bufferedImage.createGraphics();    
	    // Clear background and paint the image.   
	    g.setColor(Color.white);   
	    g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));   
	    g.drawImage(temp, 0, 0, null);   
	    g.dispose();    
	    // Soften.   
	    float softenFactor = 0.05f;   
	    float[] softenArray = {0, softenFactor, 0, softenFactor, 1-(softenFactor*4), softenFactor, 0, softenFactor, 0};   
	    Kernel kernel = new Kernel(3, 3, softenArray);   
	    ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);   
	    bufferedImage = cOp.filter(bufferedImage, null);    
	    // Write the jpeg to a file.   
	    FileOutputStream out = new FileOutputStream(resizedFile);           
	    // Encodes image as a JPEG data stream   
	    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
	    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);    
	    param.setQuality(quality, true);    
	    encoder.setJPEGEncodeParam(param);   
	    encoder.encode(bufferedImage);   
	}   
	
	public static void main(String[] args){
		try {
			resize(new File("E:/111.jpg"),new File("E:/test.jpg"),400,1.0f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
