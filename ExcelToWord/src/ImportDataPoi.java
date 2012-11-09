import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ImportDataPoi {
	/** Excel文件的存放位置。注意是正斜线 */
	public static String fileToBeRead = "d:/test.xls";
	private static Connection conn = null;
	private static DataSource dataSource;
	FileOutputStream fileOut = null;

	// public static void main(String argv[]) {
	public static void importData() {
		try {
			// 创建对Excel工作簿文件的引用
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			BufferedImage bufferImg = ImageIO.read(new File("d:\\LOGO.jpg"));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			// 读进一个excel模版
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 512, 255, (short) 5, 5, (short) 10, 10);
			// patriarch.createPicture(anchor ,
			// workbook.addPicture(byteArrayOut.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
			
			FileOutputStream fileOut = new FileOutputStream(fileToBeRead);
			workbook.write(fileOut);
			for (int j = 1; j <= rowNum; j++) {
				StringBuffer sb = new StringBuffer();
				sb.append(" insert into test (id,name,age,org,position,password) VALUES (");
				HSSFRow row = sheet.getRow(j);
				int cellNum = row.getLastCellNum();
				int id = (int) row.getCell((short) 0).getNumericCellValue();
				String name = row.getCell((short) 1).getStringCellValue();
				int age = (int) row.getCell((short) 2).getNumericCellValue();
				String sex = row.getCell((short) 3).getStringCellValue();
				String org = row.getCell((short) 4).getStringCellValue();
				String position = row.getCell((short) 5).getStringCellValue();
				System.out.println(sb.toString());

			}
		}
		// HSSFRow row = sheet.getRow(0);//行
		// HSSFCell cell = row.getCell((short) 0);//单元格
		// cell.getStringCellValue() 就是取所在单元的值
		catch (Exception e) {
			System.out.println("已运行xlRead() : " + e);
		}
	}
}
