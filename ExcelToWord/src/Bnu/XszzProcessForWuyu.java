package Bnu;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;

public class XszzProcessForWuyu {

	private String getNameOutput(String name1, String name2, String name3, String name4, String name5, String name6) {
		String result = "";
		
		if(!name1.equals("")) {
			result = result + name1 + ",";
		}
		if(!name2.equals("")) {
			result = result + name2 + ",";
		}
		if(!name3.equals("")) {
			result = result + name3 + ",";
		}
		if(!name4.equals("")) {
			result = result + name4 + ",";
		}
		if(!name5.equals("")) {
			result = result + name5 + ",";
		}
		if(!name6.equals("")) {
			result = result + name6 + ",";
		}
		
		String temp = "";
		if(result.equals("")) {
			temp =  result;
		} else {
			if(result.endsWith(",")) {
				temp =  result.substring(0, result.length()-1);
			}
		}
		return temp;
	}
	
	public static void main(String[] args) throws Exception {

		System.out.println("============获取人员信息================================================");
		XszzProcessForWuyu toWord = new XszzProcessForWuyu();
		
		InputStream is = new FileInputStream("E:/wuyu/people.xls");
		// File is = new File("D:/test.xls");
		Workbook rwb = Workbook.getWorkbook(is);
		Sheet rs = (Sheet) rwb.getSheet(0);
		int rowNum = rs.getRows();
		
		String dept = ""; //部门
		String title = ""; //职称
		String grade = ""; //岗位级别
		String work = ""; //担任学校社会工作
		String userId = "";
		String userName = "";
		for (int i = 1; i <= rowNum-1; i++) {
//		for (int i = 1; i <= 5; i++) {
			userId = rs.getCell(5, i).getContents();  //ID
			userName = rs.getCell(6, i).getContents(); //name
			dept = rs.getCell(4, i).getContents(); //部门
			title =  rs.getCell(14, i).getContents();
			grade =  rs.getCell(16, i).getContents();
			work = rs.getCell(18, i).getContents();
			
			if(!userId.trim().equals("") && !userName.trim().equals("")) {
				
//				System.out.println("============学术著作begin===================");
				StringBuffer xszzContent = new StringBuffer();
				InputStream xszzIs = new FileInputStream("E:/wuyu/1.xls");
				Workbook xszzRwb = Workbook.getWorkbook(xszzIs);
				Sheet xszzRs = (Sheet) xszzRwb.getSheet(0);
				int xszzRowNum = xszzRs.getRows();
				
				String xszzName1 = ""; String xszzId1 = "";String xszzName2 = "";	String xszzId2 = "";
				String xszzName3 = ""; String xszzId3 = "";String xszzName4 = "";	String xszzId4 = "";
				String xszzName5 = "";String xszzId5 = ""; String xszzName6 = ""; String xszzId6 = "";
				int xszzNum = 1; String xszzOutName = "";
				for(int xszzRowIndex=1; xszzRowIndex<xszzRowNum-1; xszzRowIndex++) {
					String xszzYear = xszzRs.getCell(23, xszzRowIndex).getContents();
					if(xszzYear.equals("2011") || xszzYear.equals("2011年")) {
						xszzName1 =  xszzRs.getCell(3, xszzRowIndex).getContents().trim() ; //第1完成人姓名
						xszzId1 =  xszzRs.getCell(4, xszzRowIndex).getContents().trim() ; //第1完成人ID
						xszzName2 =  xszzRs.getCell(24, xszzRowIndex).getContents().trim() ; //第2完成人姓名
						xszzId2 =  xszzRs.getCell(25, xszzRowIndex).getContents().trim() ; //第2完成人ID
						xszzName3 =  xszzRs.getCell(26, xszzRowIndex).getContents().trim() ; //第3完成人姓名
						xszzId3 =  xszzRs.getCell(27, xszzRowIndex).getContents().trim() ; //第3完成人ID
						xszzName4 =  xszzRs.getCell(28, xszzRowIndex).getContents().trim() ; //第4完成人姓名
						xszzId4 =  xszzRs.getCell(29, xszzRowIndex).getContents().trim() ; //第4完成人ID
						xszzName5 =  xszzRs.getCell(30, xszzRowIndex).getContents().trim() ; //第5完成人姓名
						xszzId5 =  xszzRs.getCell(31, xszzRowIndex).getContents().trim() ; //第5完成人ID
						xszzName5 =  xszzRs.getCell(32, xszzRowIndex).getContents().trim() ; //第6完成人姓名
						xszzId5 =  xszzRs.getCell(33, xszzRowIndex).getContents().trim() ; //第6完成人ID
						xszzOutName = toWord.getNameOutput(xszzName1, xszzName2, xszzName3, xszzName4, xszzName5, xszzName6);
						
						//导出项目
						String temp1 = xszzRs.getCell(0, xszzRowIndex).getContents(); 
						String temp2 = xszzRs.getCell(16, xszzRowIndex).getContents();
						String temp3 = xszzRs.getCell(23, xszzRowIndex).getContents();
						String temp4 = xszzRs.getCell(8, xszzRowIndex).getContents();
						
						if(xszzId1.equals(userId) || xszzName1.equals(userName) || xszzId2.equals(userId) || xszzName2.equals(userName)
								|| xszzId3.equals(userId) || xszzName3.equals(userName) || xszzId4.equals(userId) || xszzName4.equals(userName)
								|| xszzId5.equals(userId) || xszzName5.equals(userName) || xszzId6.equals(userId) || xszzName6.equals(userName)
						) {
							xszzContent.append(xszzNum+". "+xszzOutName);
							if(!temp1.equals("")) {
								xszzContent.append(", <<"+temp1+">>");
							}
							if(!temp2.equals("")) {
								xszzContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								if(temp3.contains("年")) {
									xszzContent.append(","+temp3);
								} else {
									xszzContent.append(","+temp3+"年");
								}
							}
							if(!temp4.equals("")) {
								xszzContent.append(",("+temp4+")");
							}
							xszzContent.append("\n");
							xszzNum++;
						}
					}
				}
				//xszzContent 整理完毕
//				System.out.println("============学术著作end===================");

			}
		}
		System.out.println("============Finally....end...happy happy===================");
	}
}
