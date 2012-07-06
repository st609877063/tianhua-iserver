package com.gift.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import org.apache.struts2.ServletActionContext;

import com.swetake.util.Qrcode;

public class QRCodeTool {

	/**
	 * 二维码生成器
	 */
	public boolean generateQRCode(String itemNo, String qucodePath, String dirPath) {
		boolean result = false;

		if (!FileTools.fileExist(dirPath)) {
			FileTools.mkdir(dirPath);
		}

		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);

			byte[] contentBytes = itemNo.getBytes("UTF-8");

			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);

			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode itemNo bytes length = "+ contentBytes.length + " not in [ 0,120 ]. ");
			}

			gs.dispose();
			bufImg.flush();

			File imgFile = new File(qucodePath);

			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "png", imgFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 解码二维码
	 */
	public String decoderQRCode(String fileName) {
		String imgPath = ServletActionContext.getServletContext().getRealPath("/qrcode") + "/"+ fileName;
		if (!FileTools.fileExist(imgPath)) {
			return null;
		}
		
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);

		BufferedImage bufImg = null;
		String decodedData = null;
		try {
			bufImg = ImageIO.read(imageFile);

			QRCodeDecoder decoder = new QRCodeDecoder();
			decodedData = new String(decoder.decode(new J2SEImage(bufImg)));

			// try {
			// System.out.println(new String(decodedData.getBytes("gb2312"),
			// "gb2312"));
			// } catch (Exception e) {
			// }
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return decodedData;
	}

	class J2SEImage implements QRCodeImage {
		BufferedImage bufImg;

		public J2SEImage(BufferedImage bufImg) {
			this.bufImg = bufImg;
		}

		public int getWidth() {
			return bufImg.getWidth();
		}

		public int getHeight() {
			return bufImg.getHeight();
		}

		public int getPixel(int x, int y) {
			return bufImg.getRGB(x, y);
		}
	}

}
