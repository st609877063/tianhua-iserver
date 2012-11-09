package Excel;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ImportDataJxl {

	public static void main(String[] args) {
		// Java Excel API既可以从本地文件系统的一个文件(.xls)，也可以从输入流中读取Excel数据表。
		// 读取Excel数据表的第一步是创建Workbook(术语：工作薄)，下面的代码片段举例说明了应该如何操作：
		// (完整代码见ExcelReading.java)
		try {
			InputStream is = new FileInputStream("E:/temp/成果获奖/01039_于述胜_成果获奖模板（本人第一作者）.xls");
			// File is = new File("D:/test.xls");
			Workbook rwb = Workbook.getWorkbook(is);
			Sheet rs = (Sheet) rwb.getSheet(0);
			int columnNum = rs.getColumns();
			int rowNum = rs.getRows();
			System.out.println("columnNum="+columnNum);
			System.out.println("rowNum="+rowNum);
			
			Cell cell = null;
			String contentTemp = "";
			for (int i = 5; i <= rowNum; i++) {
				for(int j = 0; j <= columnNum; j++) {
					cell = rs.getCell(j, i);
					contentTemp = cell.getContents();
					if(contentTemp.equals("")) {
						break;
					}
					System.out.print(j+","+i+":"+contentTemp+" ");
				}
				System.out.print("\n");
			}
			// 如果仅仅是取得Cell的值，我们可以方便地通过getContents()方法，
			// 它可以将任何类型的Cell值都作为一个字符串返回。示例代码中Cell(0, 0)是文本型，
			// Cell(1, 0)是数字型，Cell(1,1)是日期型，通过getContents()，三种类型的返回值都是字符型。

			// 如果有需要知道Cell内容的确切类型，API也提供了一系列的方法。参考下面的代码片段：
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Java Excel API提供了许多访问Excel数据表的方法，在这里我只简要地介绍几个常用的方法，
		// 其它的方法请参考附录中的Java Excel API Document。
		// Workbook类提供的方法
		// 1. int getNumberOfSheets();
		// 获得工作薄（Workbook）中工作表（Sheet）的个数，示例：
		//
		// jxl.Workbook rwb = jxl.Workbook.getWorkbook(new File("D:/user.xls"));
		// int sheets = rwb.getNumberOfSheets();
		// 2. Sheet[] getSheets();
		// 返回工作薄（Workbook）中工作表（Sheet）对象数组，示例：
		// jxl.Workbook rwb2 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// Sheet[] sheets2 = (Sheet[]) rwb2.getSheets();
		// 3. String getVersion();
		// 返回正在使用的API的版本号，好像是没什么太大的作用。
		// jxl.Workbook rwb3 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// String apiVersion = rwb3.getVersion();
		// Sheet接口提供的方法
		// 1) String getName();
		// 获取Sheet的名称，示例：
		// jxl.Workbook rwb4 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs = rwb4.getSheet(0);
		// String sheetName = rs.getName();
		// 2) int getColumns()
		// 获取Sheet表中所包含的总列数，示例：
		// jxl.Workbook rwb5 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs2 = rwb5.getSheet(0);
		// int rsColumns = rs2.getColumns();
		// 3) Cell[] getColumn(int column)
		// 获取某一列的所有单元格，返回的是单元格对象数组，示例：
		// jxl.Workbook rwb6 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs3 = rwb6.getSheet(0);
		// Cell[] cell = rs3.getColumn(0);
		// 4) int getRows()
		// 获取Sheet表中所包含的总行数，示例：
		// jxl.Workbook rwb7 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs4 = rwb7.getSheet(0);
		// int rsRows = rs4.getRows();
		// 5) Cell[] getRow(int row)
		// 获取某一行的所有单元格，返回的是单元格对象数组，示例子：
		// jxl.Workbook rwb8 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs5 = rwb8.getSheet(0);
		// Cell[] cell5 = rs5.getRow(0);
		// 6) Cell getCell(int column, int row)
		// 获取指定单元格的对象引用，需要注意的是它的两个参数，第一个是列数，第二个是行数，
		// 这与通常的行、列组合有些不同。

		// jxl.Workbook rwb9 = jxl.Workbook.getWorkbook(new
		// File("D:/user.xls"));
		// jxl.Sheet rs6 = rwb9.getSheet(0);
		// Cell cell6 = rs6.getCell(0, 0);
	}

}