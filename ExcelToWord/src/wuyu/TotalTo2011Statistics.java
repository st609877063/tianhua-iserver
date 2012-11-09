package wuyu;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TotalTo2011Statistics {

	public static void main(String[] args) throws Exception {

		System.out.println("============获取人员信息================================================");
		InputStream is = new FileInputStream("E:/wuyu/people.xls");
		// File is = new File("D:/test.xls");
		Workbook rwb = Workbook.getWorkbook(is);
		Sheet rs = (Sheet) rwb.getSheet(0);
		int rowNum = rs.getRows();
		String userId = "";
		String userName = "";
		
		//写入的Excel
		boolean aloneFlag = false; //是否只统计第一作者的标记
		
		WritableWorkbook toBook = null;
		WritableSheet toSheet = null;
		String path = "E:\\wuyu\\2011统计.xls";
		toBook = Workbook.createWorkbook(new File(path));
		toSheet = toBook.createSheet("2011统计", 0);
		int rowIndex = 1;
		
		Label labelTemp = null;
		labelTemp = new Label(0, 0, "姓名");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(1, 0, "成果获奖");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(2, 0, "科研项目");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(3, 0, "其他成果");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(4, 0, "学术论文(来源刊)");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(5, 0, "学术论文(来源刊以外)");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(6, 0, "研究报告");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(7, 0, "专利信息");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(8, 0, "学术著作");
		toSheet.addCell(labelTemp);
		labelTemp = new Label(9, 0, "TOTAL");
		toSheet.addCell(labelTemp);
		
		for (int i = 1; i <= rowNum-1; i++) {
			userId = rs.getCell(5, i).getContents();  //ID
			userName = rs.getCell(6, i).getContents(); //name
			
			if(!userId.trim().equals("") && !userName.trim().equals("")) {
				
				int cghjStaNum = 0;
				int kyxmStaNum = 0;
				int qtcgStaNum = 0;
				int xslwStaNum = 0; //来源刊
				int xslwStaNum2 = 0; //来源刊以外
				int xszzStaNum = 0;
				int yjbgStaNum = 0;
				int zlxxStaNum = 0;
				int totalNum = 0;
				
				/***************************************************/
				InputStream cghjIs = new FileInputStream("E:/wuyu/成果获奖2011.xls");
				Workbook cghjRwb = Workbook.getWorkbook(cghjIs);
				Sheet cghjRs = (Sheet) cghjRwb.getSheet(0);
				int cghjRowNum = cghjRs.getRows();
				
				String cghjName1 = ""; String cghjId1 = "";String cghjName2 = "";	String cghjId2 = "";
				String cghjName3 = ""; String cghjId3 = "";String cghjName4 = "";	String cghjId4 = "";
				String cghjName5 = "";String cghjId5 = ""; 
				for(int cghjRowIndex=1; cghjRowIndex<cghjRowNum-1; cghjRowIndex++) {
					cghjName1 =  cghjRs.getCell(2, cghjRowIndex).getContents().trim() ; //第1完成人姓名
					cghjId1 =  cghjRs.getCell(3, cghjRowIndex).getContents().trim() ; //第1完成人ID
					cghjName2 =  cghjRs.getCell(21, cghjRowIndex).getContents().trim() ; //第2完成人姓名
					cghjId2 =  cghjRs.getCell(22, cghjRowIndex).getContents().trim() ; //第2完成人ID
					cghjName3 =  cghjRs.getCell(23, cghjRowIndex).getContents().trim() ; //第3完成人姓名
					cghjId3 =  cghjRs.getCell(24, cghjRowIndex).getContents().trim() ; //第3完成人ID
					cghjName4 =  cghjRs.getCell(25, cghjRowIndex).getContents().trim() ; //第4完成人姓名
					cghjId4 =  cghjRs.getCell(26, cghjRowIndex).getContents().trim() ; //第4完成人ID
					cghjName5 =  cghjRs.getCell(27, cghjRowIndex).getContents().trim() ; //第5完成人姓名
					cghjId5 =  cghjRs.getCell(28, cghjRowIndex).getContents().trim() ; //第5完成人ID
					cghjName5 =  cghjRs.getCell(29, cghjRowIndex).getContents().trim() ; //第6完成人姓名
					cghjId5 =  cghjRs.getCell(30, cghjRowIndex).getContents().trim() ; //第6完成人ID
					
					if(cghjId1.equals(userId) || cghjName1.equals(userName)) {
						cghjStaNum++;
					}
					if(aloneFlag) {
						if(cghjId2.equals(userId) || cghjName2.equals(userName)) {
							cghjStaNum++;
						}
						if(cghjId3.equals(userId) || cghjName3.equals(userName)) {
							cghjStaNum++;
						}
						if(cghjId4.equals(userId) || cghjName4.equals(userName)) {
							cghjStaNum++;
						}
						if(cghjId5.equals(userId) || cghjName5.equals(userName)) {
							cghjStaNum++;
						}
					}
				}
			
				
				/***************************************************/
				InputStream kyxmIs = new FileInputStream("E:/wuyu/科研项目2011.xls");
				Workbook kyxmRwb = Workbook.getWorkbook(kyxmIs);
				Sheet kyxmRs = (Sheet) kyxmRwb.getSheet(0);
				int kyxmRowNum = kyxmRs.getRows();
				
				String kyxmName1 = ""; String kyxmId1 = "";String kyxmName2 = "";	String kyxmId2 = "";
				String kyxmName3 = ""; String kyxmId3 = "";String kyxmName4 = "";	String kyxmId4 = "";
				String kyxmName5 = "";String kyxmId5 = "";
				for(int kyxmRowIndex=1; kyxmRowIndex<kyxmRowNum-1; kyxmRowIndex++) {
					kyxmName1 =  kyxmRs.getCell(4, kyxmRowIndex).getContents().trim() ; //第1完成人姓名
					kyxmId1 =  kyxmRs.getCell(5, kyxmRowIndex).getContents().trim() ; //第1完成人ID
					kyxmName2 =  kyxmRs.getCell(27, kyxmRowIndex).getContents().trim() ; //第2完成人姓名
					kyxmId2 =  kyxmRs.getCell(28, kyxmRowIndex).getContents().trim() ; //第2完成人ID
					kyxmName3 =  kyxmRs.getCell(29, kyxmRowIndex).getContents().trim() ; //第3完成人姓名
					kyxmId3 =  kyxmRs.getCell(30, kyxmRowIndex).getContents().trim() ; //第3完成人ID
					kyxmName4 =  kyxmRs.getCell(31, kyxmRowIndex).getContents().trim() ; //第4完成人姓名
					kyxmId4 =  kyxmRs.getCell(32, kyxmRowIndex).getContents().trim() ; //第4完成人ID
					kyxmName5 =  kyxmRs.getCell(33, kyxmRowIndex).getContents().trim() ; //第5完成人姓名
					kyxmId5 =  kyxmRs.getCell(34, kyxmRowIndex).getContents().trim() ; //第5完成人ID
					kyxmName5 =  kyxmRs.getCell(35, kyxmRowIndex).getContents().trim() ; //第6完成人姓名
					kyxmId5 =  kyxmRs.getCell(36, kyxmRowIndex).getContents().trim() ; //第6完成人ID
					
					if(kyxmId1.equals(userId) || kyxmName1.equals(userName)) {
						kyxmStaNum++;
					}
					if(aloneFlag) {
						if(kyxmId2.equals(userId) || kyxmName2.equals(userName)) {
							kyxmStaNum++;
						}
						if(kyxmId3.equals(userId) || kyxmName3.equals(userName)) {
							kyxmStaNum++;
						}
						if(kyxmId4.equals(userId) || kyxmName4.equals(userName)) {
							kyxmStaNum++;
						}
						if(kyxmId5.equals(userId) || kyxmName5.equals(userName)) {
							kyxmStaNum++;
						}
					}
				}
				
				/***************************************************/
				InputStream qtcgIs = new FileInputStream("E:/wuyu/其他成果2011.xls");
				Workbook qtcgRwb = Workbook.getWorkbook(qtcgIs);
				Sheet qtcgRs = (Sheet) qtcgRwb.getSheet(0);
				int qtcgRowNum = qtcgRs.getRows();
				
				String qtcgName1 = ""; String qtcgId1 = "";String qtcgName2 = "";	String qtcgId2 = "";
				String qtcgName3 = ""; String qtcgId3 = "";String qtcgName4 = "";	String qtcgId4 = "";
				String qtcgName5 = "";String qtcgId5 = "";
				for(int qtcgRowIndex=1; qtcgRowIndex<qtcgRowNum-1; qtcgRowIndex++) {
					qtcgName1 =  qtcgRs.getCell(2, qtcgRowIndex).getContents().trim() ; //第1完成人姓名
					qtcgId1 =  qtcgRs.getCell(3, qtcgRowIndex).getContents().trim() ; //第1完成人ID
					qtcgName2 =  qtcgRs.getCell(22, qtcgRowIndex).getContents().trim() ; //第2完成人姓名
					qtcgId2 =  qtcgRs.getCell(23, qtcgRowIndex).getContents().trim() ; //第2完成人ID
					qtcgName3 =  qtcgRs.getCell(24, qtcgRowIndex).getContents().trim() ; //第3完成人姓名
					qtcgId3 =  qtcgRs.getCell(25, qtcgRowIndex).getContents().trim() ; //第3完成人ID
					qtcgName4 =  qtcgRs.getCell(26, qtcgRowIndex).getContents().trim() ; //第4完成人姓名
					qtcgId4 =  qtcgRs.getCell(27, qtcgRowIndex).getContents().trim() ; //第4完成人ID
					qtcgName5 =  qtcgRs.getCell(28, qtcgRowIndex).getContents().trim() ; //第5完成人姓名
					qtcgId5 =  qtcgRs.getCell(29, qtcgRowIndex).getContents().trim() ; //第5完成人ID
					qtcgName5 =  qtcgRs.getCell(30, qtcgRowIndex).getContents().trim() ; //第6完成人姓名
					qtcgId5 =  qtcgRs.getCell(31, qtcgRowIndex).getContents().trim() ; //第6完成人ID
					
					
					if(qtcgId1.equals(userId) || qtcgName1.equals(userName)) {
						qtcgStaNum++;
					}
					if(aloneFlag) {
						if(qtcgId2.equals(userId) || qtcgName2.equals(userName)) {
							qtcgStaNum++;
						}
						if(qtcgId3.equals(userId) || qtcgName3.equals(userName)) {
							qtcgStaNum++;
						}
						if(qtcgId4.equals(userId) || qtcgName4.equals(userName)) {
							qtcgStaNum++;
						}
						if(qtcgId5.equals(userId) || qtcgName5.equals(userName)) {
							qtcgStaNum++;
						}
					}
				}
				
				/***************************************************/
				InputStream xslwIs = new FileInputStream("E:/wuyu/学术论文2011.xls");
				Workbook xslwRwb = Workbook.getWorkbook(xslwIs);
				Sheet xslwRs = (Sheet) xslwRwb.getSheet(0);
				int xslwRowNum = xslwRs.getRows();
				
				String xslwName1 = ""; String xslwId1 = "";String xslwName2 = "";	String xslwId2 = "";
				String xslwName3 = ""; String xslwId3 = "";String xslwName4 = "";	String xslwId4 = "";
				String xslwName5 = "";String xslwId5 = "";
				for(int xslwRowIndex=1; xslwRowIndex<xslwRowNum-1; xslwRowIndex++) {
					xslwName1 =  xslwRs.getCell(2, xslwRowIndex).getContents().trim() ; //第1完成人姓名
					xslwId1 =  xslwRs.getCell(3, xslwRowIndex).getContents().trim() ; //第1完成人ID
					xslwName2 =  xslwRs.getCell(34, xslwRowIndex).getContents().trim() ; //第2完成人姓名
					xslwId2 =  xslwRs.getCell(35, xslwRowIndex).getContents().trim() ; //第2完成人ID
					xslwName3 =  xslwRs.getCell(36, xslwRowIndex).getContents().trim() ; //第3完成人姓名
					xslwId3 =  xslwRs.getCell(37, xslwRowIndex).getContents().trim() ; //第3完成人ID
					xslwName4 =  xslwRs.getCell(38, xslwRowIndex).getContents().trim() ; //第4完成人姓名
					xslwId4 =  xslwRs.getCell(39, xslwRowIndex).getContents().trim() ; //第4完成人ID
					xslwName5 =  xslwRs.getCell(40, xslwRowIndex).getContents().trim() ; //第5完成人姓名
					xslwId5 =  xslwRs.getCell(41, xslwRowIndex).getContents().trim() ; //第5完成人ID
					xslwName5 =  xslwRs.getCell(42, xslwRowIndex).getContents().trim() ; //第6完成人姓名
					xslwId5 =  xslwRs.getCell(43, xslwRowIndex).getContents().trim() ; //第6完成人ID
					
					//导出项目
					String temp1 = xslwRs.getCell(16, xslwRowIndex).getContents(); 
					
					if(xslwId1.equals(userId) || xslwName1.equals(userName)) {
						if(temp1.trim().equals("来源刊")) { 	xslwStaNum++; }
						else {	xslwStaNum2++; }
					}
					if(aloneFlag) {
						if(xslwId2.equals(userId) || xslwName2.equals(userName)) {
							if(temp1.trim().equals("来源刊")) { 	xslwStaNum++; }
							else {	xslwStaNum2++; }
						}
						if(xslwId3.equals(userId) || xslwName3.equals(userName)) {
							if(temp1.trim().equals("来源刊")) { 	xslwStaNum++; }
							else {	xslwStaNum2++; }
						}
						if(xslwId4.equals(userId) || xslwName4.equals(userName)) {
							if(temp1.trim().equals("来源刊")) { 	xslwStaNum++; }
							else {	xslwStaNum2++; }
						}
						if(xslwId5.equals(userId) || xslwName5.equals(userName)) {
							if(temp1.trim().equals("来源刊")) { 	xslwStaNum++; }
							else {	xslwStaNum2++; }
						}
					}
				}
				
				/***************************************************/
				InputStream yjbgIs = new FileInputStream("E:/wuyu/研究报告2011.xls");
				Workbook yjbgRwb = Workbook.getWorkbook(yjbgIs);
				Sheet yjbgRs = (Sheet) yjbgRwb.getSheet(0);
				int yjbgRowNum = yjbgRs.getRows();
				
				String yjbgName1 = ""; String yjbgId1 = "";String yjbgName2 = "";	String yjbgId2 = "";
				String yjbgName3 = ""; String yjbgId3 = "";String yjbgName4 = "";	String yjbgId4 = "";
				String yjbgName5 = "";String yjbgId5 = "";
				for(int yjbgRowIndex=1; yjbgRowIndex<yjbgRowNum-1; yjbgRowIndex++) {
					yjbgName1 =  yjbgRs.getCell(2, yjbgRowIndex).getContents().trim() ; //第1完成人姓名
					yjbgId1 =  yjbgRs.getCell(3, yjbgRowIndex).getContents().trim() ; //第1完成人ID
					yjbgName2 =  yjbgRs.getCell(20, yjbgRowIndex).getContents().trim() ; //第2完成人姓名
					yjbgId2 =  yjbgRs.getCell(21, yjbgRowIndex).getContents().trim() ; //第2完成人ID
					yjbgName3 =  yjbgRs.getCell(22, yjbgRowIndex).getContents().trim() ; //第3完成人姓名
					yjbgId3 =  yjbgRs.getCell(23, yjbgRowIndex).getContents().trim() ; //第3完成人ID
					yjbgName4 =  yjbgRs.getCell(24, yjbgRowIndex).getContents().trim() ; //第4完成人姓名
					yjbgId4 =  yjbgRs.getCell(25, yjbgRowIndex).getContents().trim() ; //第4完成人ID
					yjbgName5 =  yjbgRs.getCell(26, yjbgRowIndex).getContents().trim() ; //第5完成人姓名
					yjbgId5 =  yjbgRs.getCell(27, yjbgRowIndex).getContents().trim() ; //第5完成人ID
					yjbgName5 =  yjbgRs.getCell(28, yjbgRowIndex).getContents().trim() ; //第6完成人姓名
					yjbgId5 =  yjbgRs.getCell(29, yjbgRowIndex).getContents().trim() ; //第6完成人ID
					
					if(yjbgId1.equals(userId) || yjbgName1.equals(userName)) {
						yjbgStaNum++;
					}
					if(aloneFlag) {
						if(yjbgId2.equals(userId) || yjbgName2.equals(userName)) {
							yjbgStaNum++;
						}
						if(yjbgId3.equals(userId) || yjbgName3.equals(userName)) {
							yjbgStaNum++;
						}
						if(yjbgId4.equals(userId) || yjbgName4.equals(userName)) {
							yjbgStaNum++;
						}
						if(yjbgId5.equals(userId) || yjbgName5.equals(userName)) {
							yjbgStaNum++;
						}
					}
				}
				
				/***************************************************/
				InputStream zlxxIs = new FileInputStream("E:/wuyu/专利信息2011.xls");
				Workbook zlxxRwb = Workbook.getWorkbook(zlxxIs);
				Sheet zlxxRs = (Sheet) zlxxRwb.getSheet(0);
				int zlxxRowNum = zlxxRs.getRows();
				
				String zlxxName1 = ""; String zlxxId1 = "";String zlxxName2 = "";	String zlxxId2 = "";
				String zlxxName3 = ""; String zlxxId3 = "";String zlxxName4 = "";	String zlxxId4 = "";
				String zlxxName5 = "";String zlxxId5 = "";
				for(int zlxxRowIndex=1; zlxxRowIndex<zlxxRowNum-1; zlxxRowIndex++) {
					zlxxName1 =  zlxxRs.getCell(1, zlxxRowIndex).getContents().trim() ; //第1完成人姓名
					zlxxId1 =  zlxxRs.getCell(2, zlxxRowIndex).getContents().trim() ; //第1完成人ID
					zlxxName2 =  zlxxRs.getCell(13, zlxxRowIndex).getContents().trim() ; //第2完成人姓名
					zlxxId2 =  zlxxRs.getCell(14, zlxxRowIndex).getContents().trim() ; //第2完成人ID
					zlxxName3 =  zlxxRs.getCell(15, zlxxRowIndex).getContents().trim() ; //第3完成人姓名
					zlxxId3 =  zlxxRs.getCell(16, zlxxRowIndex).getContents().trim() ; //第3完成人ID
					zlxxName4 =  zlxxRs.getCell(17, zlxxRowIndex).getContents().trim() ; //第4完成人姓名
					zlxxId4 =  zlxxRs.getCell(18, zlxxRowIndex).getContents().trim() ; //第4完成人ID
					zlxxName5 =  zlxxRs.getCell(19, zlxxRowIndex).getContents().trim() ; //第5完成人姓名
					zlxxId5 =  zlxxRs.getCell(20, zlxxRowIndex).getContents().trim() ; //第5完成人ID
					zlxxName5 =  zlxxRs.getCell(21, zlxxRowIndex).getContents().trim() ; //第6完成人姓名
					zlxxId5 =  zlxxRs.getCell(22, zlxxRowIndex).getContents().trim() ; //第6完成人ID
					
					if(zlxxId1.equals(userId) || zlxxName1.equals(userName)) {
						zlxxStaNum++;
					}
					if(aloneFlag) {
						if(zlxxId2.equals(userId) || zlxxName2.equals(userName)) {
							zlxxStaNum++;
						}
						if(zlxxId3.equals(userId) || zlxxName3.equals(userName)) {
							zlxxStaNum++;
						}
						if(zlxxId4.equals(userId) || zlxxName4.equals(userName)) {
							zlxxStaNum++;
						}
						if(zlxxId5.equals(userId) || zlxxName5.equals(userName)) {
							zlxxStaNum++;
						}
					}
				}
				
				/***************************************************/
				InputStream xszzIs = new FileInputStream("E:/wuyu/学术著作2011.xls");
				Workbook xszzRwb = Workbook.getWorkbook(xszzIs);
				Sheet xszzRs = (Sheet) xszzRwb.getSheet(0);
				int xszzRowNum = xszzRs.getRows();
				
				String xszzName1 = ""; String xszzId1 = "";String xszzName2 = "";	String xszzId2 = "";
				String xszzName3 = ""; String xszzId3 = "";String xszzName4 = "";	String xszzId4 = "";
				String xszzName5 = "";String xszzId5 = ""; 
				for(int xszzRowIndex=1; xszzRowIndex<xszzRowNum-1; xszzRowIndex++) {
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
					
					if(xszzId1.equals(userId) || xszzName1.equals(userName)) {
						xszzStaNum++;
					}
					if(aloneFlag) {
						if(xszzId2.equals(userId) || xszzName2.equals(userName)) {
							xszzStaNum++;
						}
						if(xszzId3.equals(userId) || xszzName3.equals(userName)) {
							xszzStaNum++;
						}
						if(xszzId4.equals(userId) || xszzName4.equals(userName)) {
							xszzStaNum++;
						}
						if(xszzId5.equals(userId) || xszzName5.equals(userName)) {
							xszzStaNum++;
						}
					}
				}
				
				totalNum = cghjStaNum+kyxmStaNum+qtcgStaNum+xslwStaNum+xslwStaNum2+yjbgStaNum+zlxxStaNum+xszzStaNum;
				
				//写入Excel
				Label label = null;
				label = new Label(0, rowIndex, userName);
				toSheet.addCell(label);
				label = new Label(1, rowIndex, String.valueOf(cghjStaNum));
				toSheet.addCell(label);
				label = new Label(2, rowIndex, String.valueOf(kyxmStaNum));
				toSheet.addCell(label);
				label = new Label(3, rowIndex, String.valueOf(qtcgStaNum));
				toSheet.addCell(label);
				label = new Label(4, rowIndex, String.valueOf(xslwStaNum));
				toSheet.addCell(label);
				label = new Label(5, rowIndex, String.valueOf(xslwStaNum2));
				toSheet.addCell(label);
				label = new Label(6, rowIndex, String.valueOf(yjbgStaNum));
				toSheet.addCell(label);
				label = new Label(7, rowIndex, String.valueOf(zlxxStaNum));
				toSheet.addCell(label);
				label = new Label(8, rowIndex, String.valueOf(xszzStaNum));
				toSheet.addCell(label);
				label = new Label(9, rowIndex, String.valueOf(totalNum));
				toSheet.addCell(label);
				rowIndex++;
			}
		}
		
		toBook.write();
		toBook.close();
		System.out.println("============Finally....end...happy happy===================");
	}
}
