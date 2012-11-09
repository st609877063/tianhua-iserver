package Excel;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {

	public void outputExcel(Map dataMap) {

		System.out.println("dataMap size:" + dataMap.size());
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		try {
			String path = "E:\\data\\result.xls";
			
			book = Workbook.createWorkbook(new File(path));
			
			if(dataMap.size() != 0) {
				String key=null;
				List valueList=null;
				Set dataSet = dataMap.keySet();
				Iterator dataKey = dataSet.iterator();
				while(dataKey.hasNext()) {
					key = (String) dataKey.next();

					valueList = (List)dataMap.get(key);
					//每一个学校为一张sheet
					//设置表名
					sheet = book.createSheet(key, 0);
					
					//学校类别
					Label labe1 = new Label(0, 0, "学校类别: ");
					sheet.addCell(labe1);
					Label label1 = new Label(1, 0, (String)((List)valueList.get(0)).get(0));
					sheet.addCell(label1);
					
					//生成表格题头
					Label labe2 = new Label(0, 2, "学校名称 ");
					Label labe3 = new Label(1, 2, "匿名账号 ");
					Label labe4 = new Label(2, 2, "账号密码 ");
					
					//将生成的单元格添加到工作表中        
					sheet.addCell(labe2);
					sheet.addCell(labe3);
					sheet.addCell(labe4);
					
					int sheet_flag = 3;
					for(int n=0; n<valueList.size(); n++) {
						List temp = (List)valueList.get(n);
						
						//取得数据生成单元格
						Label label2 = new Label(0, sheet_flag, (String)temp.get(1));
						Label label3 = new Label(1, sheet_flag, (String)temp.get(2));
						Label label4 = new Label(2, sheet_flag, (String)temp.get(3));
						//将生成的单元格添加到工作表中        
						sheet.addCell(label2);
						sheet.addCell(label3);
						sheet.addCell(label4);
						sheet_flag++;
					}
				}
			}
			
			book.write();
			book.close();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
