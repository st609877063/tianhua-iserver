package Bnu;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import word.ModifyWordDocument;
import word.WordTable;

public class ToWord {

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
		System.out.println("====================================个人成果导出begin================================================");

		System.out.println("============获取人员信息================================================");
		ToWord toWord = new ToWord();
		
		InputStream is = new FileInputStream("E:/temp/people.xls");
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
			dept = "教育学部"+rs.getCell(4, i).getContents(); //部门
			title =  rs.getCell(14, i).getContents();
			grade =  rs.getCell(16, i).getContents();
			work = rs.getCell(18, i).getContents();
			
			if(!userId.trim().equals("") && !userName.trim().equals("")) {
				
//				System.out.println("============成果获奖begin===================");
				StringBuffer cghjContent = new StringBuffer();
				InputStream cghjIs = new FileInputStream("E:/temp/成果获奖.xls");
				Workbook cghjRwb = Workbook.getWorkbook(cghjIs);
				Sheet cghjRs = (Sheet) cghjRwb.getSheet(0);
				int cghjRowNum = cghjRs.getRows();
				
				String cghjName1 = ""; String cghjId1 = "";String cghjName2 = "";	String cghjId2 = "";
				String cghjName3 = ""; String cghjId3 = "";String cghjName4 = "";	String cghjId4 = "";
				String cghjName5 = "";String cghjId5 = ""; String cghjName6 = ""; String cghjId6 = "";
				int cghjNum = 1; String cghjOutName = "";
				for(int cghjRowIndex=1; cghjRowIndex<cghjRowNum-1; cghjRowIndex++) {
					String cghjYear = cghjRs.getCell(20, cghjRowIndex).getContents();
					if(cghjYear.equals("2010") || cghjYear.equals("2010年")) {
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
						cghjOutName = toWord.getNameOutput(cghjName1, cghjName2, cghjName3, cghjName4, cghjName5, cghjName6);
						
						//导出项目
						String temp1 = cghjRs.getCell(0, cghjRowIndex).getContents(); 
						String temp2 = cghjRs.getCell(4, cghjRowIndex).getContents();
						String temp3 = cghjRs.getCell(6, cghjRowIndex).getContents();
						String temp4 = cghjRs.getCell(20, cghjRowIndex).getContents();
						
						if(cghjId1.equals(userId) || cghjName1.equals(userName) || cghjId2.equals(userId) || cghjName2.equals(userName)
								|| cghjId3.equals(userId) || cghjName3.equals(userName) || cghjId4.equals(userId) || cghjName4.equals(userName)
								|| cghjId5.equals(userId) || cghjName5.equals(userName) || cghjId6.equals(userId) || cghjName6.equals(userName)
						) {
							cghjContent.append(cghjNum+". "+cghjOutName);
							if(!temp1.equals("")) {
								cghjContent.append(", \""+temp1+"\"");
							}
							if(!temp2.equals("")) {
								cghjContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								cghjContent.append(","+temp3);
							}
							if(!temp4.equals("")) {
								if(temp4.contains("年")) {
									cghjContent.append(","+temp4);
								} else {
									cghjContent.append(","+temp4+"年");
								}
							}
							cghjContent.append("\n");
							cghjNum++;
						}
					}
				}
				//cghjContent 整理完毕
//				System.out.println("============成果获奖end===================");
			
				
				
//				System.out.println("============科研项目begin===================");
				StringBuffer kyxmContent = new StringBuffer();
				InputStream kyxmIs = new FileInputStream("E:/temp/科研项目.xls");
				Workbook kyxmRwb = Workbook.getWorkbook(kyxmIs);
				Sheet kyxmRs = (Sheet) kyxmRwb.getSheet(0);
				int kyxmRowNum = kyxmRs.getRows();
				
				String kyxmName1 = ""; String kyxmId1 = "";String kyxmName2 = "";	String kyxmId2 = "";
				String kyxmName3 = ""; String kyxmId3 = "";String kyxmName4 = "";	String kyxmId4 = "";
				String kyxmName5 = "";String kyxmId5 = ""; String kyxmName6 = ""; String kyxmId6 = "";
				int kyxmNum = 1; String kyxmOutName = "";
				for(int kyxmRowIndex=1; kyxmRowIndex<kyxmRowNum-1; kyxmRowIndex++) {
					String kyxmYear = kyxmRs.getCell(26, kyxmRowIndex).getContents();
					if(kyxmYear.equals("2010") || kyxmYear.equals("2010年")) {
						kyxmName1 =  kyxmRs.getCell(4, kyxmRowIndex).getContents().trim() ; //第1完成人姓名
						kyxmId1 =  kyxmRs.getCell(5, kyxmRowIndex).getContents().trim() ; //第1完成人ID
//					kyxmName2 =  kyxmRs.getCell(27, kyxmRowIndex).getContents().trim() ; //第2完成人姓名
//					kyxmId2 =  kyxmRs.getCell(28, kyxmRowIndex).getContents().trim() ; //第2完成人ID
//					kyxmName3 =  kyxmRs.getCell(29, kyxmRowIndex).getContents().trim() ; //第3完成人姓名
//					kyxmId3 =  kyxmRs.getCell(30, kyxmRowIndex).getContents().trim() ; //第3完成人ID
//					kyxmName4 =  kyxmRs.getCell(31, kyxmRowIndex).getContents().trim() ; //第4完成人姓名
//					kyxmId4 =  kyxmRs.getCell(32, kyxmRowIndex).getContents().trim() ; //第4完成人ID
//					kyxmName5 =  kyxmRs.getCell(33, kyxmRowIndex).getContents().trim() ; //第5完成人姓名
//					kyxmId5 =  kyxmRs.getCell(34, kyxmRowIndex).getContents().trim() ; //第5完成人ID
//					kyxmName5 =  kyxmRs.getCell(35, kyxmRowIndex).getContents().trim() ; //第6完成人姓名
//					kyxmId5 =  kyxmRs.getCell(36, kyxmRowIndex).getContents().trim() ; //第6完成人ID
//					kyxmOutName = toWord.getNameOutput(kyxmName1, kyxmName2, kyxmName3, kyxmName4, kyxmName5, kyxmName6);
						
						//导出项目
						String temp1 = kyxmRs.getCell(0, kyxmRowIndex).getContents(); 
						String temp2 = kyxmRs.getCell(11, kyxmRowIndex).getContents();
						String temp3 = kyxmRs.getCell(10, kyxmRowIndex).getContents();
						String temp4 = kyxmRs.getCell(26, kyxmRowIndex).getContents();
						
						if(kyxmId1.equals(userId) || kyxmName1.equals(userName) ) {
							kyxmContent.append(kyxmNum+". "+kyxmName1);
							if(!temp1.equals("")) {
								kyxmContent.append(", \""+temp1+"\" ");
							}
							if(!temp2.equals("")) {
								kyxmContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								kyxmContent.append(",资助项目类别："+temp3);
							}
							if(!temp4.equals("")) {
								if(temp4.contains("年")) {
									kyxmContent.append(",立项时间："+temp4);
								} else {
									kyxmContent.append(",立项时间："+temp4+"年");
								}
							}
							kyxmContent.append("\n");
							kyxmNum++;
						}
					}
				}
				//kyxmContent 整理完毕
//				System.out.println("============科研项目end===================");
				
				
				
//				System.out.println("============其他成果begin===================");
				StringBuffer qtcgContent = new StringBuffer();
				InputStream qtcgIs = new FileInputStream("E:/temp/其他成果.xls");
				Workbook qtcgRwb = Workbook.getWorkbook(qtcgIs);
				Sheet qtcgRs = (Sheet) qtcgRwb.getSheet(0);
				int qtcgRowNum = qtcgRs.getRows();
				
				String qtcgName1 = ""; String qtcgId1 = "";String qtcgName2 = "";	String qtcgId2 = "";
				String qtcgName3 = ""; String qtcgId3 = "";String qtcgName4 = "";	String qtcgId4 = "";
				String qtcgName5 = "";String qtcgId5 = ""; String qtcgName6 = ""; String qtcgId6 = "";
				int qtcgNum = 1; String qtcgOutName = "";
				for(int qtcgRowIndex=1; qtcgRowIndex<qtcgRowNum-1; qtcgRowIndex++) {
					String qtcgYear = qtcgRs.getCell(21, qtcgRowIndex).getContents();
					if(qtcgYear.equals("2010") || qtcgYear.equals("2010年")) {
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
						qtcgOutName = toWord.getNameOutput(qtcgName1, qtcgName2, qtcgName3, qtcgName4, qtcgName5, qtcgName6);
						
						//导出项目
						String temp1 = qtcgRs.getCell(0, qtcgRowIndex).getContents(); 
						String temp2 = qtcgRs.getCell(4, qtcgRowIndex).getContents();
						String temp3 = qtcgRs.getCell(6, qtcgRowIndex).getContents();
						String temp4 = qtcgRs.getCell(12, qtcgRowIndex).getContents();
						String temp5 = qtcgRs.getCell(14, qtcgRowIndex).getContents();
						String temp6 = qtcgRs.getCell(17, qtcgRowIndex).getContents();
						String temp7 = qtcgRs.getCell(21, qtcgRowIndex).getContents();
						
						if(qtcgId1.equals(userId) || qtcgName1.equals(userName) || qtcgId2.equals(userId) || qtcgName2.equals(userName)
								|| qtcgId3.equals(userId) || qtcgName3.equals(userName) || qtcgId4.equals(userId) || qtcgName4.equals(userName)
								|| qtcgId5.equals(userId) || qtcgName5.equals(userName) || qtcgId6.equals(userId) || qtcgName6.equals(userName)
						) {
							qtcgContent.append(qtcgNum+". "+qtcgOutName);
							if(!temp1.equals("")) {
								qtcgContent.append(", \""+temp1+"\"");
							}
							if(!temp2.equals("")) {
								qtcgContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								qtcgContent.append(","+temp3);
							}
							if(!temp4.equals("")) {
								qtcgContent.append(","+temp4);
							}
							if(!temp5.equals("")) {
								qtcgContent.append(",<<"+temp5+">>");
							}
							if(!temp6.equals("")) {
								qtcgContent.append(","+temp6);
							}
							if(!temp7.equals("")) {
								if(temp7.contains("年")) {
									qtcgContent.append(","+temp7);
								} else {
									qtcgContent.append(","+temp7+"年");
								}
							}
							qtcgContent.append("\n");
							qtcgNum++;
						}
					}
				}
				//qtcgContent 整理完毕
//				System.out.println("============其他成果end===================");
				
				
//				System.out.println("============学术论文begin===================");
				StringBuffer xslwContent = new StringBuffer();
				InputStream xslwIs = new FileInputStream("E:/temp/学术论文.xls");
				Workbook xslwRwb = Workbook.getWorkbook(xslwIs);
				Sheet xslwRs = (Sheet) xslwRwb.getSheet(0);
				int xslwRowNum = xslwRs.getRows();
				
				String xslwName1 = ""; String xslwId1 = "";String xslwName2 = "";	String xslwId2 = "";
				String xslwName3 = ""; String xslwId3 = "";String xslwName4 = "";	String xslwId4 = "";
				String xslwName5 = "";String xslwId5 = ""; String xslwName6 = ""; String xslwId6 = "";
				int xslwNum = 1; String xslwOutName = "";
				for(int xslwRowIndex=1; xslwRowIndex<xslwRowNum-1; xslwRowIndex++) {
					String xslwYear = xslwRs.getCell(19, xslwRowIndex).getContents();
					if(xslwYear.equals("2010") || xslwYear.equals("2010年")) {
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
						xslwOutName = toWord.getNameOutput(xslwName1, xslwName2, xslwName3, xslwName4, xslwName5, xslwName6);
						
						//导出项目
						String temp1 = xslwRs.getCell(0, xslwRowIndex).getContents(); 
						String temp2 = xslwRs.getCell(13, xslwRowIndex).getContents();
						String temp3 = xslwRs.getCell(19, xslwRowIndex).getContents();
						String temp4 = xslwRs.getCell(20, xslwRowIndex).getContents();
						String temp5 = xslwRs.getCell(21, xslwRowIndex).getContents();
						String temp6 = xslwRs.getCell(22, xslwRowIndex).getContents();
						
						if(xslwId1.equals(userId) || xslwName1.equals(userName) || xslwId2.equals(userId) || xslwName2.equals(userName)
								|| xslwId3.equals(userId) || xslwName3.equals(userName) || xslwId4.equals(userId) || xslwName4.equals(userName)
								|| xslwId5.equals(userId) || xslwName5.equals(userName) || xslwId6.equals(userId) || xslwName6.equals(userName)
						) {
							xslwContent.append(xslwNum+". "+xslwOutName);
							if(!temp1.equals("")) {
								xslwContent.append(". "+temp1);
							}
							if(!temp2.equals("")) {
								xslwContent.append(". "+temp2);
							}
							if(!temp3.equals("")) {
								if(temp3.contains("年")) {
									xslwContent.append(". "+temp3);
								} else {
									xslwContent.append(". "+temp3+"年");
								}
							}
							if(!temp4.equals("")) {
								xslwContent.append(". 卷<"+temp4+">");
							} else {
								xslwContent.append(". 卷< >");
							}
							if(!temp5.equals("")) {
								xslwContent.append("期<"+temp5+">");
							} else {
								xslwContent.append("期< >");
							}
							if(!temp6.equals("")) {
								xslwContent.append("页码<"+temp6+">");
							} else {
								xslwContent.append("页码< >");
							}
							xslwContent.append("\n");
							xslwNum++;
						}
					}
				}
				//xslwContent 整理完毕
//				System.out.println("============学术论文end===================");
				
				
				
//				System.out.println("============研究报告begin===================");
				StringBuffer yjbgContent = new StringBuffer();
				InputStream yjbgIs = new FileInputStream("E:/temp/研究报告.xls");
				Workbook yjbgRwb = Workbook.getWorkbook(yjbgIs);
				Sheet yjbgRs = (Sheet) yjbgRwb.getSheet(0);
				int yjbgRowNum = yjbgRs.getRows();
				
				String yjbgName1 = ""; String yjbgId1 = "";String yjbgName2 = "";	String yjbgId2 = "";
				String yjbgName3 = ""; String yjbgId3 = "";String yjbgName4 = "";	String yjbgId4 = "";
				String yjbgName5 = "";String yjbgId5 = ""; String yjbgName6 = ""; String yjbgId6 = "";
				int yjbgNum = 1; String yjbgOutName = "";
				for(int yjbgRowIndex=1; yjbgRowIndex<yjbgRowNum-1; yjbgRowIndex++) {
					String yjbgYear = yjbgRs.getCell(19, yjbgRowIndex).getContents();
					if(yjbgYear.equals("2010") || yjbgYear.equals("2010年")) {
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
						yjbgOutName = toWord.getNameOutput(yjbgName1, yjbgName2, yjbgName3, yjbgName4, yjbgName5, yjbgName6);
						
						//导出项目
						String temp1 = yjbgRs.getCell(0, yjbgRowIndex).getContents(); 
						String temp2 = yjbgRs.getCell(8, yjbgRowIndex).getContents();
						String temp3 = yjbgRs.getCell(10, yjbgRowIndex).getContents();
						
						if(yjbgId1.equals(userId) || yjbgName1.equals(userName) || yjbgId2.equals(userId) || yjbgName2.equals(userName)
								|| yjbgId3.equals(userId) || yjbgName3.equals(userName) || yjbgId4.equals(userId) || yjbgName4.equals(userName)
								|| yjbgId5.equals(userId) || yjbgName5.equals(userName) || yjbgId6.equals(userId) || yjbgName6.equals(userName)
						) {
							yjbgContent.append(yjbgNum+". "+yjbgOutName);
							if(!temp1.equals("")) {
								yjbgContent.append(", \""+temp1+"\"");
							}
							if(!temp2.equals("")) {
								yjbgContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								yjbgContent.append(",认定日期："+temp3);
							}
							yjbgContent.append("\n");
							yjbgNum++;
						}
					}
				}
				//yjbgContent 整理完毕
//				System.out.println("============研究报告end===================");
				
//				System.out.println("============专利信息begin===================");
				StringBuffer zlxxContent = new StringBuffer();
				InputStream zlxxIs = new FileInputStream("E:/temp/专利信息.xls");
				Workbook zlxxRwb = Workbook.getWorkbook(zlxxIs);
				Sheet zlxxRs = (Sheet) zlxxRwb.getSheet(0);
				int zlxxRowNum = zlxxRs.getRows();
				
				String zlxxName1 = ""; String zlxxId1 = "";String zlxxName2 = "";	String zlxxId2 = "";
				String zlxxName3 = ""; String zlxxId3 = "";String zlxxName4 = "";	String zlxxId4 = "";
				String zlxxName5 = "";String zlxxId5 = ""; String zlxxName6 = ""; String zlxxId6 = "";
				int zlxxNum = 1; String zlxxOutName = "";
				for(int zlxxRowIndex=1; zlxxRowIndex<zlxxRowNum-1; zlxxRowIndex++) {
					String zlxxYear = zlxxRs.getCell(12, zlxxRowIndex).getContents();
					if(zlxxYear.equals("2010") || zlxxYear.equals("2010年")) {
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
						zlxxOutName = toWord.getNameOutput(zlxxName1, zlxxName2, zlxxName3, zlxxName4, zlxxName5, zlxxName6);
						
						//导出项目
						String temp1 = zlxxRs.getCell(0, zlxxRowIndex).getContents(); 
						String temp2 = zlxxRs.getCell(10, zlxxRowIndex).getContents();
						String temp3 = zlxxRs.getCell(8, zlxxRowIndex).getContents();
						
						if(zlxxId1.equals(userId) || zlxxName1.equals(userName) || zlxxId2.equals(userId) || zlxxName2.equals(userName)
								|| zlxxId3.equals(userId) || zlxxName3.equals(userName) || zlxxId4.equals(userId) || zlxxName4.equals(userName)
								|| zlxxId5.equals(userId) || zlxxName5.equals(userName) || zlxxId6.equals(userId) || zlxxName6.equals(userName)
						) {
							zlxxContent.append(zlxxNum+". "+zlxxOutName);
							if(!temp1.equals("")) {
								zlxxContent.append("\""+temp1+"\"");
							}
							if(!temp2.equals("")) {
								zlxxContent.append(","+temp2);
							}
							if(!temp3.equals("")) {
								zlxxContent.append(","+temp3);
							}
							zlxxContent.append("\n");
							zlxxNum++;
						}
					}
				}
				//zlxxContent 整理完毕
//				System.out.println("============专利信息end===================");
				
//				System.out.println("============学术著作begin===================");
				StringBuffer xszzContent = new StringBuffer();
				InputStream xszzIs = new FileInputStream("E:/temp/学术著作.xls");
				Workbook xszzRwb = Workbook.getWorkbook(xszzIs);
				Sheet xszzRs = (Sheet) xszzRwb.getSheet(0);
				int xszzRowNum = xszzRs.getRows();
				
				String xszzName1 = ""; String xszzId1 = "";String xszzName2 = "";	String xszzId2 = "";
				String xszzName3 = ""; String xszzId3 = "";String xszzName4 = "";	String xszzId4 = "";
				String xszzName5 = "";String xszzId5 = ""; String xszzName6 = ""; String xszzId6 = "";
				int xszzNum = 1; String xszzOutName = "";
				for(int xszzRowIndex=1; xszzRowIndex<xszzRowNum-1; xszzRowIndex++) {
					String xszzYear = xszzRs.getCell(23, xszzRowIndex).getContents();
					if(xszzYear.equals("2010") || xszzYear.equals("2010年")) {
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

				
				
//				System.out.println("============导师带学生汇总begin===================");
				StringBuffer dsxsContent = new StringBuffer();
				InputStream dsxsIs = new FileInputStream("E:/temp/导师带学生汇总表.xls");
				Workbook dsxsRwb = Workbook.getWorkbook(dsxsIs);
				Sheet dsxsRs = (Sheet) dsxsRwb.getSheet(0);
				int dsxsRowNum = dsxsRs.getRows();
				
				String dsxsName1 = ""; String dsxsId1 = "";
				int dsxsNum = 1;
				for(int dsxsRowIndex=1; dsxsRowIndex<dsxsRowNum-1; dsxsRowIndex++) {
					dsxsName1 =  dsxsRs.getCell(1, dsxsRowIndex).getContents().trim() ; //第1完成人姓名
					dsxsId1 =  dsxsRs.getCell(0, dsxsRowIndex).getContents().trim() ; //第1完成人ID
					
					//导出项目
					String temp1 = dsxsRs.getCell(2, dsxsRowIndex).getContents(); 
					String temp2 = dsxsRs.getCell(3, dsxsRowIndex).getContents();
					String temp3 = dsxsRs.getCell(4, dsxsRowIndex).getContents();
					String temp4 = dsxsRs.getCell(5, dsxsRowIndex).getContents();
					String temp5 = dsxsRs.getCell(6, dsxsRowIndex).getContents(); 
					String temp6 = dsxsRs.getCell(7, dsxsRowIndex).getContents();
					String temp7 = dsxsRs.getCell(8, dsxsRowIndex).getContents();
					String temp8 = dsxsRs.getCell(9, dsxsRowIndex).getContents();
					String temp9 = dsxsRs.getCell(10, dsxsRowIndex).getContents(); 
					String temp10 = dsxsRs.getCell(11, dsxsRowIndex).getContents();
					String temp11 = dsxsRs.getCell(12, dsxsRowIndex).getContents();

					if(dsxsId1.equals(userId) || dsxsName1.equals(userName)) {
//						dsxsContent.append(dsxsNum+". ");
						int tempIndex = 1;
						if(!temp1.equals("") && !temp1.equals("0") && !temp1.equals("#N/A!") && !temp1.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2008级学术型硕士："+temp1+"\n");
							tempIndex++;
						}
						if(!temp2.equals("") && !temp2.equals("0") && !temp2.equals("#N/A!") && !temp2.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2008级学术型博士："+temp2+"\n");
							tempIndex++;
						}
						if(!temp3.equals("") && !temp3.equals("0") && !temp3.equals("#N/A!") && !temp3.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级学术型硕士："+temp3+"\n");
							tempIndex++;
						}
						if(!temp4.equals("") && !temp4.equals("0") && !temp4.equals("#N/A!") && !temp4.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级学术型博士："+temp4+"\n");
							tempIndex++;
						}
						if(!temp5.equals("") && !temp5.equals("0") && !temp5.equals("#N/A!") && !temp5.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级学术型硕士："+temp5+"\n");
							tempIndex++;
						}
						if(!temp6.equals("") && !temp6.equals("0") && !temp6.equals("#N/A!") && !temp6.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级学术型博士："+temp6+"\n");
							tempIndex++;
						}
						if(!temp7.equals("") && !temp7.equals("0") && !temp7.equals("#N/A!") && !temp7.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2008级在职硕士："+temp7+"\n");
							tempIndex++;
						}
						if(!temp8.equals("") && !temp8.equals("0") && !temp8.equals("#N/A!") && !temp8.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级在职硕士："+temp8+"\n");
							tempIndex++;
						}
						if(!temp9.equals("") && !temp9.equals("0") && !temp9.equals("#N/A!") && !temp9.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级在职硕士："+temp9+"\n");
							tempIndex++;
						}
						if(!temp10.equals("") && !temp10.equals("0") && !temp10.equals("#N/A!") && !temp10.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级全日制教育硕士："+temp10+"\n");
							tempIndex++;
						}
						if(!temp11.equals("") && !temp11.equals("0") && !temp11.equals("#N/A!") && !temp11.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 中职、高校学生："+temp11+"\n");
							tempIndex++;
						}
						dsxsContent.append("\n");
						dsxsNum++;
					}
				}
				//dsxsContent 整理完毕
//				System.out.println("============导师带学生汇总end===================");

//				System.out.println("============课程信息汇总begin===================");
				StringBuffer kcxxContent = new StringBuffer();
				InputStream kcxxIs = new FileInputStream("E:/temp/课程信息汇总.xls");
				Workbook kcxxRwb = Workbook.getWorkbook(kcxxIs);
				Sheet kcxxRs = (Sheet) kcxxRwb.getSheet(1); //区别
				int kcxxRowNum = kcxxRs.getRows();
				
				String kcxxName1 = "";
				int kcxxNum = 1;
				for(int kcxxRowIndex=1; kcxxRowIndex<kcxxRowNum-1; kcxxRowIndex++) {
					kcxxName1 =  kcxxRs.getCell(0, kcxxRowIndex).getContents().trim() ; //第1完成人姓名
					
					//导出项目
					String mcTemp = kcxxRs.getCell(2, kcxxRowIndex).getContents();
					String xfTemp = kcxxRs.getCell(3, kcxxRowIndex).getContents();
					String bhTemp = kcxxRs.getCell(4, kcxxRowIndex).getContents();
					String xqTemp = kcxxRs.getCell(13, kcxxRowIndex).getContents();
					String xkTemp = kcxxRs.getCell(12, kcxxRowIndex).getContents();

					if(kcxxName1.equals(userName)) {
						kcxxContent.append(kcxxNum+". ");
						if(!mcTemp.equals("")) {
							kcxxContent.append("\""+mcTemp+"\"");						
						}
						if(!xfTemp.equals("")) {
							kcxxContent.append(","+xfTemp);
						}
						if(!bhTemp.equals("")) {
							kcxxContent.append(","+bhTemp);
						}
						if(!xqTemp.equals("")) {
							kcxxContent.append(","+xqTemp);
						}
						if(!xkTemp.equals("")) {
							kcxxContent.append(","+xkTemp);
						}
						kcxxContent.append("\n");
						kcxxNum++;
					}
				}
				//kcxxContent 整理完毕
//				System.out.println("============课程信息汇总end===================");
				
				
//				System.out.println("============社会服务汇总信息begin===================");
				StringBuffer shfwContent = new StringBuffer();
				InputStream shfwIs = new FileInputStream("E:/temp/社会服务汇总信息.xls");
				Workbook shfwRwb = Workbook.getWorkbook(shfwIs);
				Sheet shfwRs = (Sheet) shfwRwb.getSheet(0);
				int shfwRowNum = shfwRs.getRows();
				
				String shfwName1 = "";
				int shfwNum = 1;
				for(int shfwRowIndex=1; shfwRowIndex<shfwRowNum-1; shfwRowIndex++) {
					shfwName1 =  shfwRs.getCell(0, shfwRowIndex).getContents().trim() ; //第1完成人姓名
					
					//导出项目
					String dwTemp = shfwRs.getCell(1, shfwRowIndex).getContents(); 
					String zwTemp = shfwRs.getCell(2, shfwRowIndex).getContents();

					if(shfwName1.equals(userName)) {
//						shfwContent.append(shfwNum+". ");
						if(!dwTemp.equals("")) {
							shfwContent.append(dwTemp);
						}
						if(!zwTemp.equals("")) {
							shfwContent.append(zwTemp);
						}
						shfwContent.append("\n");
						shfwNum++;
					}
				}
				//shfwContent 整理完毕
//				System.out.println("============社会服务汇总信息end===================");

				
				System.out.println("============输出简历begin===================:"+userId+";"+userName);
//				System.out.println("成果获奖："+cghjContent.toString());
//				System.out.println("科研项目："+kyxmContent.toString());
//				System.out.println("其他成果："+qtcgContent.toString());
//				System.out.println("学术论文："+xslwContent.toString());
//				System.out.println("研究报告："+yjbgContent.toString());
//				System.out.println("专利信息："+zlxxContent.toString());
//				System.out.println("著作信息："+xszzContent.toString());
//				System.out.println("导师带学生汇总："+dsxsContent.toString());
//				System.out.println("社会服务汇总："+shfwContent.toString());
//				System.out.println("课程信息汇总："+kcxxContent.toString());
				
				StringBuffer sb1 = new StringBuffer();
				if(!xslwContent.toString().trim().equals("")) {
					sb1.append("论文：\n");
					sb1.append(xslwContent+"\n");
				}
				if(!xszzContent.toString().trim().equals("")) {
					sb1.append("著作：\n");
					sb1.append(xszzContent+"\n");
				}
				if(!yjbgContent.toString().trim().equals("")) {
					sb1.append("研究（咨询）报告：\n");
					sb1.append(yjbgContent+"\n");
				}
				if(!cghjContent.toString().trim().equals("")) {
					sb1.append("科研奖励：\n");
					sb1.append(cghjContent+"\n");
				}
				if(!zlxxContent.toString().trim().equals("")) {
					sb1.append("专利：\n");
					sb1.append(zlxxContent+"\n");
				}
				if(!zlxxContent.toString().trim().equals("")) {
					sb1.append("专利：\n");
					sb1.append(zlxxContent+"\n");
				}
				if(!qtcgContent.toString().trim().equals("")) {
					sb1.append("其他成果：\n");
					sb1.append(qtcgContent+"\n");
				}
				if(!kyxmContent.toString().trim().equals("")) {
					sb1.append("主持项目：\n");
					sb1.append(kyxmContent);
				}
				
				StringBuffer sb2 = new StringBuffer();
				if(!kcxxContent.toString().trim().equals("")) {
					sb2.append("讲授课程：\n");
					sb2.append(kcxxContent+"\n");
				}
				if(!dsxsContent.toString().trim().equals("")) {
					sb2.append("指导学生：\n");
					sb2.append(dsxsContent);
				}
				
				WordTable[] wt=new WordTable[9];
				for(int arrayIndex=0; arrayIndex<wt.length; arrayIndex++){
		        	wt[arrayIndex]=new WordTable();
		        	switch(arrayIndex){
		        		case 0://部门
			    			wt[arrayIndex].originalText="depart";
			    			wt[arrayIndex].finalText=dept;
			    			break;
		        		case 1://姓名
		        			wt[arrayIndex].originalText="name";
		        			wt[arrayIndex].finalText=userName;
		        			break;
		        		case 2://工作证号
		        			wt[arrayIndex].originalText="number";
		        			wt[arrayIndex].finalText=userId;
		        			break;
		        		case 3://职称
		        			wt[arrayIndex].originalText="title";
		        			wt[arrayIndex].finalText=title;
		        			break;
		        		case 4://岗位级别
		        			wt[arrayIndex].originalText="grade";
		        			wt[arrayIndex].finalText=grade;
		        			break;	        		       		
		        		case 5:
		        			wt[arrayIndex].originalText="work";
		        			wt[arrayIndex].finalText=work;
		        			break;
		        		case 6://教学情况
		        			wt[arrayIndex].originalText="instructionalState";
		        			wt[arrayIndex].finalText=sb2.toString();
		        			break;
		        		case 7://项目
		        			wt[arrayIndex].originalText="projects";
		        			wt[arrayIndex].finalText=sb1.toString();
		        			break;
		        		case 8:
		        			wt[arrayIndex].originalText="papers";
		        			wt[arrayIndex].finalText=shfwContent.toString();
		        			break;
		        		
		        	}
				}
				ModifyWordDocument word = new ModifyWordDocument(wt);
				word.getWord();
				System.out.println("============输出简历end==================="+i);
			}
		}
		System.out.println("============Finally....end...happy happy===================");
	}
}
