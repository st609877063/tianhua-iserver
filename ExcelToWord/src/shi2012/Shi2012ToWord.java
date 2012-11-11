package shi2012;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import word.ModifyWordDocument;
import word.WordTable;

public class Shi2012ToWord {

	public static void main(String[] args) throws Exception {
		System.out.println("====================================个人成果导出begin================================================");

		InputStream is = new FileInputStream("E:/shi/2012/people.xls");
		// File is = new File("D:/test.xls");
		Workbook rwb = Workbook.getWorkbook(is);
		Sheet rs = (Sheet) rwb.getSheet(0);
		int rowNum = rs.getRows();
		System.out.println("============获取人员信息========="+rowNum);
		
		String dept = ""; //部门
		String userId = "";
		String userName = "";
		String title = ""; //职称
		String grade = ""; //岗位级别
//		for (int i = 1; i <= rowNum-1; i++) {
		for (int i = 1; i <= 5; i++) {
			dept = "教育学部"+rs.getCell(0, i).getContents(); // //部门
			userId = rs.getCell(1, i).getContents(); //  //ID
			userName = rs.getCell(2, i).getContents(); // //name
			title =  rs.getCell(3, i).getContents(); //
			grade =  rs.getCell(4, i).getContents(); //
			
			if(!userId.trim().equals("") && !userName.trim().equals("")) {
				
//				System.out.println("============课程信息汇总begin===================");
				StringBuffer kcxxContent = new StringBuffer();
				InputStream kcxxIs = new FileInputStream("E:/shi/2012/教学工作量/课程信息汇总(2012年度).xls");
				//姓名	职称	课程名称	学分	上课班号	选课人数	学期	负责人
				Workbook kcxxRwb = Workbook.getWorkbook(kcxxIs);
				Sheet kcxxRs = (Sheet) kcxxRwb.getSheet(0); //区别
				int kcxxRowNum = kcxxRs.getRows();
				if(kcxxRowNum <=0) {
					System.out.println("课程信息汇总(2012年度).xls!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				
				String kcxxName1 = "";
				int kcxxNum = 1;
				for(int kcxxRowIndex=1; kcxxRowIndex<kcxxRowNum-1; kcxxRowIndex++) {
					kcxxName1 =  kcxxRs.getCell(0, kcxxRowIndex).getContents().trim() ; //第1完成人姓名
					
					//导出项目
					String mcTemp = kcxxRs.getCell(2, kcxxRowIndex).getContents(); // //课程名称
					String xfTemp = kcxxRs.getCell(3, kcxxRowIndex).getContents(); // //学分
					String bhTemp = kcxxRs.getCell(4, kcxxRowIndex).getContents(); // //上课班号
					String xqTemp = kcxxRs.getCell(6, kcxxRowIndex).getContents(); // //学期
					String fzrTemp = kcxxRs.getCell(7, kcxxRowIndex).getContents(); // //负责人

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
						if(!fzrTemp.equals("")) {
							kcxxContent.append(","+fzrTemp);
						}
						kcxxContent.append("\n");
						kcxxNum++;
					}
				}
				//kcxxContent 整理完毕
//				System.out.println("============课程信息汇总end===================");
				
				
//				System.out.println("============导师带学生汇总begin===================");
				StringBuffer dsxsContent = new StringBuffer();
				InputStream dsxsIs = new FileInputStream("E:/shi/2012/教学工作量/导师带学生汇总表.xls");
				//BH	姓名	2009级学术型硕士	2009级学术型博士	2010级学术型硕士	2010级学术型博士	2011级学术型硕士	2011级学术型博士	2009级在职硕士	2010级在职硕士	2011级在职硕士	2010级全日制教育硕士	2011级全日制教育硕士	2011级国际硕士	中职、高校学生
				Workbook dsxsRwb = Workbook.getWorkbook(dsxsIs);
				Sheet dsxsRs = (Sheet) dsxsRwb.getSheet(0);
				int dsxsRowNum = dsxsRs.getRows();
				if(kcxxRowNum <=0) {
					System.out.println("导师带学生汇总表.xls!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				
				String dsxsName1 = ""; String dsxsId1 = "";
				int dsxsNum = 1;
				for(int dsxsRowIndex=1; dsxsRowIndex<dsxsRowNum-1; dsxsRowIndex++) {
					dsxsName1 =  dsxsRs.getCell(1, dsxsRowIndex).getContents().trim() ; //第1完成人姓名
					dsxsId1 =  dsxsRs.getCell(0, dsxsRowIndex).getContents().trim() ; //第1完成人ID
					
					//导出项目
					String temp1 = dsxsRs.getCell(2, dsxsRowIndex).getContents(); // 2009级学术型硕士
					String temp2 = dsxsRs.getCell(3, dsxsRowIndex).getContents(); //2009级学术型博士
					String temp3 = dsxsRs.getCell(4, dsxsRowIndex).getContents(); //2010级学术型硕士
					String temp4 = dsxsRs.getCell(5, dsxsRowIndex).getContents(); //2010级学术型博士
					String temp5 = dsxsRs.getCell(6, dsxsRowIndex).getContents(); // 2011级学术型硕士
					String temp6 = dsxsRs.getCell(7, dsxsRowIndex).getContents(); //2011级学术型博士
					String temp7 = dsxsRs.getCell(8, dsxsRowIndex).getContents(); //2009级在职硕士
					String temp8 = dsxsRs.getCell(9, dsxsRowIndex).getContents(); //2010级在职硕士
					String temp9 = dsxsRs.getCell(10, dsxsRowIndex).getContents(); // 2011级在职硕士
					String temp10 = dsxsRs.getCell(11, dsxsRowIndex).getContents(); //2010级全日制教育硕士
					String temp11 = dsxsRs.getCell(12, dsxsRowIndex).getContents(); //2011级全日制教育硕士
					String temp12 = dsxsRs.getCell(13, dsxsRowIndex).getContents();// 2011级国际硕士
					String temp13 = dsxsRs.getCell(14, dsxsRowIndex).getContents();// 中职、高校学生

					if(dsxsId1.equals(userId) || dsxsName1.equals(userName)) {
//						dsxsContent.append(dsxsNum+". ");
						int tempIndex = 1;
						if(!temp1.equals("") && !temp1.equals("0") && !temp1.equals("#N/A!") && !temp1.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级学术型硕士："+temp1+"\n");
							tempIndex++;
						}
						if(!temp2.equals("") && !temp2.equals("0") && !temp2.equals("#N/A!") && !temp2.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级学术型博士："+temp2+"\n");
							tempIndex++;
						}
						if(!temp3.equals("") && !temp3.equals("0") && !temp3.equals("#N/A!") && !temp3.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级学术型硕士："+temp3+"\n");
							tempIndex++;
						}
						if(!temp4.equals("") && !temp4.equals("0") && !temp4.equals("#N/A!") && !temp4.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级学术型博士："+temp4+"\n");
							tempIndex++;
						}
						if(!temp5.equals("") && !temp5.equals("0") && !temp5.equals("#N/A!") && !temp5.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2011级学术型硕士："+temp5+"\n");
							tempIndex++;
						}
						if(!temp6.equals("") && !temp6.equals("0") && !temp6.equals("#N/A!") && !temp6.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2011级学术型博士："+temp6+"\n");
							tempIndex++;
						}
						if(!temp7.equals("") && !temp7.equals("0") && !temp7.equals("#N/A!") && !temp7.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2009级在职硕士："+temp7+"\n");
							tempIndex++;
						}
						if(!temp8.equals("") && !temp8.equals("0") && !temp8.equals("#N/A!") && !temp8.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级在职硕士："+temp8+"\n");
							tempIndex++;
						}
						if(!temp9.equals("") && !temp9.equals("0") && !temp9.equals("#N/A!") && !temp9.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2011级在职硕士："+temp9+"\n");
							tempIndex++;
						}
						if(!temp10.equals("") && !temp10.equals("0") && !temp10.equals("#N/A!") && !temp10.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2010级全日制教育硕士："+temp10+"\n");
							tempIndex++;
						}
						if(!temp11.equals("") && !temp11.equals("0") && !temp11.equals("#N/A!") && !temp11.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2011级全日制教育硕士："+temp11+"\n");
							tempIndex++;
						}
						if(!temp12.equals("") && !temp12.equals("0") && !temp12.equals("#N/A!") && !temp12.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 2011级国际硕士："+temp12+"\n");
							tempIndex++;
						}
						if(!temp13.equals("") && !temp13.equals("0") && !temp13.equals("#N/A!") && !temp13.equals("#N/A")) {
							dsxsContent.append(tempIndex+". 中职、高校学生："+temp13+"\n");
							tempIndex++;
						}
						dsxsContent.append("\n");
						dsxsNum++;
					}
				}
				//dsxsContent 整理完毕
//				System.out.println("============导师带学生汇总end===================");


//				System.out.println("============其他工作汇总begin===================");
				StringBuffer qtgzContent = new StringBuffer();
				InputStream shfwIs = new FileInputStream("E:/shi/2012/社会服务工作量/其他工作汇总.xls");
				//姓名	2011.9～2012.8期间  	职务	  数据提供者
				Workbook shfwRwb = Workbook.getWorkbook(shfwIs);
				Sheet shfwRs = (Sheet) shfwRwb.getSheet(0);
				int shfwRowNum = shfwRs.getRows();
				if(shfwRowNum <=0) {
					System.out.println("其他工作汇总.xls!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				
				String shfwName1 = ""; String shfwId1 = "";
				int shfwNum = 1;
				for(int shfwRowIndex=1; shfwRowIndex<shfwRowNum-1; shfwRowIndex++) {
					shfwName1 =  shfwRs.getCell(0, shfwRowIndex).getContents().trim() ; //第1完成人姓名
					shfwId1 =  shfwRs.getCell(1, shfwRowIndex).getContents().trim() ; //第1完成人ID
					
					//导出项目
					String dwTemp = shfwRs.getCell(2, shfwRowIndex).getContents(); //2011.9～2012.8期间 
					String zwTemp = shfwRs.getCell(3, shfwRowIndex).getContents(); //职务
					String sjtgzTemp = shfwRs.getCell(4, shfwRowIndex).getContents(); //数据提供者

					if(shfwId1.equals(userId) || shfwName1.equals(userName) ) {
//						qtgzContent.append(shfwNum+". ");
						if(!dwTemp.equals("")) {
							qtgzContent.append(dwTemp+", ");
						}
						if(!zwTemp.equals("")) {
							qtgzContent.append(zwTemp);
						}
//						if(!sjtgzTemp.equals("")) {
//							qtgzContent.append(sjtgzTemp);
//						}
						qtgzContent.append("\n");
						shfwNum++;
					}
				}
//				System.out.println("============其他工作汇总end===================");
				

//				System.out.println("============行政职务begin===================");
				StringBuffer xzzwContent = new StringBuffer();
				InputStream xzzwIs = new FileInputStream("E:/shi/2012/社会服务工作量/行政职务.xls");
				Workbook xzzwRwb = Workbook.getWorkbook(xzzwIs);
				Sheet xzzwRs = (Sheet) xzzwRwb.getSheet(0);
				int xzzwRowNum = xzzwRs.getRows();
				if(xzzwRowNum <=0) {
					System.out.println("行政职务.xls!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				
				String xzzwName1 = ""; String xzzwId1 = "";
				int xzzwNum = 1;
				for(int xzzwRowIndex=1; xzzwRowIndex<xzzwRowNum-1; xzzwRowIndex++) {
					xzzwName1 =  shfwRs.getCell(0, xzzwRowIndex).getContents().trim() ; //第1完成人姓名
					xzzwId1 =  shfwRs.getCell(1, xzzwRowIndex).getContents().trim() ; //第1完成人ID
					
					String zwTemp = shfwRs.getCell(2, xzzwRowIndex).getContents(); //职务
					String qtTemp = shfwRs.getCell(3, xzzwRowIndex).getContents(); //其他工作

					if(xzzwName1.equals(userName) || xzzwId1.equals(userId)) {
//						xzzwContent.append(xzzwNum+". ");
						if(!zwTemp.equals("")) {
							xzzwContent.append(zwTemp+"： ");
						}
						if(!qtTemp.equals("")) {
							xzzwContent.append(qtTemp);
						}
						xzzwContent.append("\n");
						xzzwNum++;
					}
				}
//				System.out.println("============行政职务end===================");
				

//				System.out.println("============担任学校社会工作begin===================");
				StringBuffer xxshgzContent = new StringBuffer();
				InputStream xxshgzIs = new FileInputStream("E:/shi/2012/社会服务工作量/担任学校社会工作.xls");
				Workbook xxshgzRwb = Workbook.getWorkbook(xxshgzIs);
				Sheet xxshgzRs = (Sheet) xxshgzRwb.getSheet(0);
				int xxshgzRowNum = xxshgzRs.getRows();
				
				String xxshgzName1 = "";
				int xxshgzNum = 1;
				for(int xxshgzRowIndex=1; xxshgzRowIndex<xxshgzRowNum-1; xxshgzRowIndex++) {
					xxshgzName1 =  xxshgzRs.getCell(0, xxshgzRowIndex).getContents().trim() ; //第1完成人姓名
					
					//导出项目
					String temp1 = xxshgzRs.getCell(1, xxshgzRowIndex).getContents(); 
					if(xxshgzName1.equals(userName)) {
						if(!temp1.equals("")) {
							xxshgzContent.append(temp1);
						}
						xxshgzContent.append("\n");
						xxshgzNum++;
					}
				}
//				System.out.println("============其他成果end===================");
				
				

//				System.out.println("============学术论文begin===================");
				StringBuffer xslwContent = new StringBuffer();
				InputStream xslwIs = new FileInputStream("E:/shi/2012/科研工作量/学术论文（终稿）.xls");
				Workbook xslwRwb = Workbook.getWorkbook(xslwIs);
				Sheet xslwRs = (Sheet) xslwRwb.getSheet(0);
				int xslwRowNum = xslwRs.getRows();
				
				int xslwNum = 1;
				String xslwName = "";
				String xslwId = "";
				for(int xslwRowIndex=0; xslwRowIndex<xslwRowNum-1; xslwRowIndex++) {
					xslwName = xslwRs.getCell(0, xslwRowIndex).getContents(); //姓名
					xslwId = xslwRs.getCell(1, xslwRowIndex).getContents(); //ID
					
					String xslwPaper = xslwRs.getCell(2, xslwRowIndex).getContents(); //文章
					
					if(xslwName.equals(userName) || xslwId.equals(userId)) {
						if(!xslwPaper.equals("")) {
							xslwContent.append(xslwNum+". "+xslwPaper);
						}
						xslwContent.append("\n");
						xslwNum++;
					}
				}
//				System.out.println("============学术论文end===================");
				
				
//				System.out.println("============著作begin===================");
				StringBuffer zzContent = new StringBuffer();
				InputStream zzIs = new FileInputStream("E:/shi/2012/科研工作量/著作（终稿）.xls");
				//作者	工作证号	全部作者	著作名称	出版社	出版年	ISBN号	出版日期	译本出版社	著作类型	语种	所属学术机构	备注	说明
				Workbook zzRwb = Workbook.getWorkbook(zzIs);
				Sheet zzRs = (Sheet) zzRwb.getSheet(0);
				int zzRowNum = zzRs.getRows();
				
				int zzNum = 1;
				String zzName = "";
				String zzId = "";
				for(int zzRowIndex=1; zzRowIndex<zzRowNum-1; zzRowIndex++) {
					zzName = zzRs.getCell(0, zzRowIndex).getContents(); //姓名
					zzId = zzRs.getCell(1, zzRowIndex).getContents(); //ID
					
					String temp1 = zzRs.getCell(2, zzRowIndex).getContents(); //全部作者
					String temp2 = zzRs.getCell(3, zzRowIndex).getContents(); //著作名称
					String temp3 = zzRs.getCell(4, zzRowIndex).getContents(); //出版社
					String temp4 = zzRs.getCell(5, zzRowIndex).getContents(); //出版年	
					String temp5 = zzRs.getCell(6, zzRowIndex).getContents(); //ISBN号
					
					
					if(zzName.equals(userName) || zzId.equals(userId)) {
						if(!temp1.equals("")) {
							zzContent.append(zzNum+". "+temp1);
						}
						if(!temp2.equals("")) {
							zzContent.append(", <<"+temp2+">>");
						}
						if(!temp3.equals("")) {
							zzContent.append(","+temp3);
						}
						if(!temp4.equals("")) {
							zzContent.append(","+temp4);
						}
						if(!temp5.equals("")) {
							zzContent.append(","+temp5);
						}
						zzContent.append("\n");
						zzNum++;
					}
				}
//				System.out.println("============著作end===================");
				
				
//				System.out.println("============科研获奖（终稿）begin===================");
				StringBuffer kyhjContent = new StringBuffer();
				InputStream kyhjIs = new FileInputStream("E:/shi/2012/科研工作量/科研获奖（终稿）.xls");
				//完成人	工作证号	完成人（全部）	获奖类型	获奖项目名称	奖励名称
				Workbook kyhjRwb = Workbook.getWorkbook(kyhjIs);
				Sheet kyhjRs = (Sheet) kyhjRwb.getSheet(0);
				int kyhjRowNum = kyhjRs.getRows();
				
				int kyhjNum = 1;
				String kyhjName = "";
				String kyhjId = "";
				for(int kyhjRowIndex=1; kyhjRowIndex<kyhjRowNum-1; kyhjRowIndex++) {
					kyhjName = kyhjRs.getCell(0, kyhjRowIndex).getContents(); //姓名
					kyhjId = kyhjRs.getCell(1, kyhjRowIndex).getContents(); //ID
					
					String temp1 = kyhjRs.getCell(2, kyhjRowIndex).getContents(); //完成人（全部）
					String temp3 = kyhjRs.getCell(4, kyhjRowIndex).getContents(); //获奖项目名称
					String temp4 = kyhjRs.getCell(5, kyhjRowIndex).getContents(); //获奖项目名称
					String temp5 = kyhjRs.getCell(6, kyhjRowIndex).getContents(); //奖励名称
					
					
					if(kyhjName.equals(userName) || kyhjId.equals(userId)) {
						if(!temp1.equals("")) {
							kyhjContent.append(kyhjNum+". "+temp1);
						}
						if(!temp3.equals("")) {
							kyhjContent.append(","+temp3);
						}
						if(!temp4.equals("")) {
							kyhjContent.append(","+temp4);
						}
						if(!temp5.equals("")) {
							kyhjContent.append(","+temp5);
						}
						kyhjContent.append("\n");
						kyhjNum++;
					}
				}
//				System.out.println("============科研获奖（终稿）end===================");
				
				
//				System.out.println("============研究咨询报告（终稿）.xlsbegin===================");
				StringBuffer yjbgContent = new StringBuffer();
				InputStream yjbgIs = new FileInputStream("E:/shi/2012/科研工作量/研究咨询报告（终稿）.xls.xls");
				//CHARGENM<负责人姓名>	工作证号	全部作者	CGMC<成果名称>	CNDW<采纳单位>
				Workbook yjbgRwb = Workbook.getWorkbook(yjbgIs);
				Sheet yjbgRs = (Sheet) yjbgRwb.getSheet(0);
				int yjbgRowNum = yjbgRs.getRows();
				
				int yjbgNum = 1;
				String yjbgName = "";
				String yjbgId = "";
				for(int yjbgRowIndex=1; yjbgRowIndex<yjbgRowNum-1; yjbgRowIndex++) {
					yjbgName = yjbgRs.getCell(0, yjbgRowIndex).getContents(); //姓名
					yjbgId = yjbgRs.getCell(1, yjbgRowIndex).getContents(); //ID
					
					String temp1 = yjbgRs.getCell(2, yjbgRowIndex).getContents(); //全部作者
					String temp3 = yjbgRs.getCell(3, yjbgRowIndex).getContents(); //CGMC<成果名称>	
					String temp4 = yjbgRs.getCell(4, yjbgRowIndex).getContents(); //CNDW<采纳单位>
					
					
					if(yjbgName.equals(userName) || yjbgId.equals(userId)) {
						if(!temp1.equals("")) {
							yjbgContent.append(yjbgNum+". "+temp1);
						}
						if(!temp3.equals("")) {
							yjbgContent.append(","+temp3);
						}
						if(!temp4.equals("")) {
							yjbgContent.append(","+temp4);
						}
						yjbgContent.append("\n");
						yjbgNum++;
					}
				}
//				System.out.println("============研究咨询报告（终稿）.xlsend===================");
				
				
//				System.out.println("============科研项目begin===================");
				StringBuffer kyxmContent = new StringBuffer();
				InputStream kyxmIs = new FileInputStream("E:/shi/2012/科研工作量/2011新立项国家、省部、纵向、境外合作科研项目（终稿）.xls");
				Workbook kyxmRwb = Workbook.getWorkbook(kyxmIs);
				Sheet kyxmRs = (Sheet) kyxmRwb.getSheet(0);
				int kyxmRowNum = kyxmRs.getRows();
				
				String kyxmName1 = ""; String kyxmId = "";
				int kyxmNum = 1;
				for(int kyxmRowIndex=1; kyxmRowIndex<kyxmRowNum-1; kyxmRowIndex++) {
					kyxmName1 =  kyxmRs.getCell(0, kyxmRowIndex).getContents().trim() ; //作者
					kyxmId = kyxmRs.getCell(1, kyxmRowIndex).getContents(); //ID
					
					String temp1 = kyxmRs.getCell(2, kyxmRowIndex).getContents(); //项目来源
					String temp3 = kyxmRs.getCell(4, kyxmRowIndex).getContents(); //项目类型
					String temp4 = kyxmRs.getCell(5, kyxmRowIndex).getContents(); //项目名称
					
					if(kyxmName1.equals(userName) ) {
						if(!temp1.equals("")) {
							kyxmContent.append(kyxmNum+". "+temp1);
						}
						if(!temp3.equals("")) {
							kyxmContent.append(","+temp3);
						}
						if(!temp4.equals("")) {
							kyxmContent.append(","+temp4);
						}
						
						kyxmContent.append("\n");
						kyxmNum++;
					}
				}
				//kyxmContent 整理完毕
//				System.out.println("============科研项目end===================");

				
				System.out.println("============输出简历begin===================:"+userId+";"+userName);
				
				
				StringBuffer jxgz = new StringBuffer();
				if(!kcxxContent.toString().trim().equals("")) {
					jxgz.append("讲授课程：\n");
					jxgz.append(kcxxContent+"\n");
				}
				if(!dsxsContent.toString().trim().equals("")) {
					jxgz.append("指导学生：\n");
					jxgz.append(dsxsContent);
				}
				
				
				WordTable[] wt=new WordTable[14];
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
		        		case 5://行政职务
		        			wt[arrayIndex].originalText="xzzw";
		        			wt[arrayIndex].finalText=xzzwContent.toString();
		        			break;
		        		case 6://担任学校      社会工作
		        			wt[arrayIndex].originalText="work";
		        			wt[arrayIndex].finalText=xxshgzContent.toString();
		        			break;
		        		case 7://教学情况
		        			wt[arrayIndex].originalText="instructionalState";
		        			wt[arrayIndex].finalText=jxgz.toString();
		        			break;
		        		case 8://科研工作:学术论文
		        			wt[arrayIndex].originalText="keyan1";
		        			wt[arrayIndex].finalText=xslwContent.toString();
		        			break;
		        		case 9://科研工作:著作
		        			wt[arrayIndex].originalText="keyan2";
		        			wt[arrayIndex].finalText=zzContent.toString();
		        			break;
		        		case 10://科研工作：奖励
		        			wt[arrayIndex].originalText="keyan3";
		        			wt[arrayIndex].finalText=kyhjContent.toString();
		        			break;
		        		case 11://科研工作：研究（咨询）报告
		        			wt[arrayIndex].originalText="keyan4";
		        			wt[arrayIndex].finalText=yjbgContent.toString();
		        			break;
		        		case 12://科研工作：2011年科研立项
		        			wt[arrayIndex].originalText="keyan5";
		        			wt[arrayIndex].finalText=kyxmContent.toString();
		        			break;
		        		case 13: //其它工作
		        			wt[arrayIndex].originalText="otherwork";
		        			wt[arrayIndex].finalText=qtgzContent.toString();
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
