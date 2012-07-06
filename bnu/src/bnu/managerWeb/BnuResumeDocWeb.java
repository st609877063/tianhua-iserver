package bnu.managerWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

public class BnuResumeDocWeb extends ActionsoftWeb {
	
	public BnuResumeDocWeb(UserContext userContext) {
		super(userContext);
	}
	public BnuResumeDocWeb() {
		super();
	}
	
	public String getDownloadMain(String pageType,String awsuid, String option) {
		Hashtable<String,String> hashTags = new Hashtable<String,String>();
		String cgi = "./login.wf";
		String sid = super.getContext().getSessionId();
		
		if ("DOWNLOAD".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RESUMEDOC_DOWNLOAD&option="+option+"&awsuid="+option);
		} else if ("NOTHING".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RESUMEDOC_DOWNLOAD&option=NULL&awsuid="+option);
		}
		
		hashTags.put("sid", "<input type=hidden name=sid value=" + super.getContext().getSessionId() + ">\n");
		hashTags.put("pageType", pageType);
		hashTags.put("awsuid", awsuid);
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_RESUMEDOC_MAIN.htm"), hashTags);
		//end
	}
	
	
	public String getDownloadDoc(String awsuid, String option) {
		
		System.out.println("__________DownloadDoc begin___________option="+option);
		
		List<String> ouputAreaList = new ArrayList<String>();
		if(option == null || option.equals("") || option.equalsIgnoreCase("NULL")) {
			return "请选择输出简历内容！";
		} else {
			String [] optionArray = option.split(":");
			if(optionArray!=null && optionArray.length>0) {
				for(int i=0; i<optionArray.length; i++) {
					if(!optionArray[i].equals("")) {
						ouputAreaList.add(optionArray[i]);
					}
				}
			}
		}
		
		String docName = awsuid+"_教师个人简历_"+getTimestamp(); //此名唯一

		String content = "教师个人简历 \r\n \r\n \r\n";
		for(int i=0; i<ouputAreaList.size(); i++) {
			String optionNm = (String)ouputAreaList.get(i);
			content = content + getConent(awsuid, optionNm);
		}
		
		String targetDir = "";
		targetDir = "../webserver\\webapps\\fe\\techerResume"; //正式服务器环境
//		targetDir = "../webserver\\webapps\\portal\\techerResume"; //测试服务器环境
		String fileName = "";
		String zipFileName = docName + ".zip";;
		fileName = docName + ".doc";
		String targetZipFile = targetDir + zipFileName;
		File fileDir = new File(targetDir);
		//如果存在该文件夹，遍历删除现有文件夹的文件
		if (fileDir.canRead()) {
//			String[] fileList = fileDir.list();
//			for (int j = 0; j < fileList.length; j++) {
//				File f = new File(fileDir.getAbsolutePath() + "\\"+fileList[j]);
//				f.delete();
//			}
		} else {
			fileDir.mkdirs();
		}
		
		//利用字节流写文件
		writeFile(content.getBytes(), targetDir, fileName);
		
		InetAddress addr;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
//		return "此项目文档下载地址为：</br><a href='http://"+ip+"/portal/techerResume/"+docName+".doc'>"+docName+".doc</a>"; //测试服务器
		return "此项目文档下载地址为：</br><a href='http://"+ip+"/fe/techerResume/"+docName+".doc'>"+docName+".doc</a>"; //正式服务器环境
	}

	private String writeFile(byte[] fileBytes, String dir, String fileName) {
		String fullFileName = dir + "\\"+fileName;
		try {
			byte tag_bytes[] = fileBytes;
			FileOutputStream fileoutputstream = new FileOutputStream(fullFileName);// 建立文件输出流
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return "-1";
		}
		return fullFileName;
	}
	
	/**
	 * 将当前时间戳转化成数据库对应位数
	 * 
	 * @return 10位时间戳，精确到秒
	 */
	public static Long getTimestamp() {
		Long timestamp = 0L;
		Long temptime_l = System.currentTimeMillis();
		Double temptime_d = Math.floor((temptime_l.doubleValue() / 1000));
		timestamp = temptime_d.longValue();
		return timestamp;
	}
	
	private String getConent(String awsuid, String option) {
		option = option.toUpperCase();
		String tableName = "BO_"+option+"TEMP";
		HashMap dbNameMap = new HashMap();
		dbNameMap.put("RS", "※ 个人基本信息");
		dbNameMap.put("XSLW", "※ 科研情况_学术论文");
		dbNameMap.put("XSZZ", "※ 科研情况_学术著作");
		dbNameMap.put("KYXM", "※ 科研情况_科研项目");
		dbNameMap.put("YJBG", "※ 科研情况_研究报告");
		dbNameMap.put("CGHJ", "※ 科研情况_科研获奖");//
		dbNameMap.put("ZLXX", "※ 科研情况_专利信息");
		dbNameMap.put("QTCG", "※ 科研情况_其他成果");
		dbNameMap.put("RKQK", "※ 人才培养_任课情况");
		dbNameMap.put("ZDXS", "※ 人才培养_指导学生");
		dbNameMap.put("JGXM", "※ 人才培养_教改项目");
		dbNameMap.put("JXHJ", "※ 人才培养_教学获奖");
		dbNameMap.put("GZJL", "※ 工作经历");
		dbNameMap.put("ZDZY", "※ 人才培养_指导专业");
		dbNameMap.put("GNXXJL", "※ 教育经历_国内学习");
		dbNameMap.put("GJWXXJL", "※ 教育经历_国境外学习");
		dbNameMap.put("PXJL", "※ 教育经历_培训经历");
		dbNameMap.put("GJHQK", "※ 国际化情况");
		dbNameMap.put("JTCY", "※ 家庭成员");
		
		StringBuffer content = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		content.append((String)dbNameMap.get(option)+"\r\n");
		
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			if(option.equals("RS")) {
				sql = "select * from BO_RSTEMP where LOGINID='"+awsuid+"'";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()){
					String XM = rs.getString("XM")==null?"":rs.getString("XM").trim();
					String BMMC = rs.getString("BMMC")==null?"":rs.getString("BMMC").trim();
					String JSZW = rs.getString("JSZW")==null?"":rs.getString("JSZW").trim();
					String JSZW2 = rs.getString("JSZW2")==null?"":rs.getString("JSZW2").trim();
					String SSXK = rs.getString("SSXK")==null?"":rs.getString("SSXK").trim();
					String SSXK2 = rs.getString("SSXK2")==null?"":rs.getString("SSXK2").trim();
					String ZGQK = rs.getString("ZGQK")==null?"":rs.getString("ZGQK").trim();
					String ZGXW = rs.getString("ZGXW")==null?"":rs.getString("ZGXW").trim();
					String GWLB = rs.getString("GWLB")==null?"":rs.getString("GWLB").trim();
					String GWLB2 = rs.getString("GWLB2")==null?"":rs.getString("GWLB2").trim();
					String XXLB = rs.getString("XXLB")==null?"":rs.getString("XXLB").trim();
					
					content.append("姓名："+XM+"\r\n");
					content.append("所在单位："+BMMC+"\r\n");
					content.append("现 职 称："+JSZW+" "+JSZW2+"\r\n");
					content.append("学科方向："+SSXK+" "+SSXK2+"\r\n");
					content.append("岗位类别："+GWLB+" "+GWLB2+"\r\n");
					if(!ZGQK.equals("")) {
						content.append("在岗情况："+ZGQK+"\r\n");
					}
					if(!XXLB.equals("")) {
						content.append("学缘："+XXLB+"\r\n");
					}
					if(!ZGXW.equals("")) {
						content.append("最终学位："+ZGXW+"\r\n");
					}
				}
			} 
			
			else if(option.equals("XSLW")||option.equals("XSZZ")||option.equals("KYXM")||
					option.equals("YJBG")||option.equals("CGHJ")||option.equals("ZLXX")||option.equals("QTCG")	) {
				sql = " select * from (" +
					" select * from "+tableName+" where  CHARGEID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
					" union select * from "+tableName+" where  ID1='"+awsuid+"' and AUTOADD=1 " +
					" union select * from "+tableName+" where  ID2='"+awsuid+"' and AUTOADD=1 " +
					" union select * from "+tableName+" where  ID3='"+awsuid+"' and AUTOADD=1 " +
					" union select * from "+tableName+" where  ID4='"+awsuid+"' and AUTOADD=1 " +
					" union select * from "+tableName+" where  ID5='"+awsuid+"' and AUTOADD=1 " +
					") order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					
					String CHARGENM = "";
					if(option.equals("ZLXX")) {
						CHARGENM = rs.getString("APPLICANT")==null?"":rs.getString("APPLICANT").trim(); // 申请人
					} else if(option.equals("CGHJ")) {
						CHARGENM = rs.getString("MAN")==null?"":rs.getString("MAN").trim(); // 申请人
					} else {
						CHARGENM = rs.getString("CHARGENM")==null?"":rs.getString("CHARGENM").trim();
					} 
					String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1").trim(); 
					String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2").trim(); 
					String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3").trim(); 
					String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4").trim(); 
					String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5").trim(); 
					int AUTOADD = 0; 
					AUTOADD = rs.getInt("AUTOADD");
					String workPositon = "本人主持此项目。";
					if (AUTOADD == 1) {
						workPositon = "本人参与此项目。";
					}
					
					String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim(); //<计算年度>
					
					String workName = "";
					if(option.equals("XSLW")) {
						workName = rs.getString("LWMC")==null?"":rs.getString("LWMC").trim(); // 论文名称
						String lwlx = rs.getString("LWLX")==null?"":rs.getString("LWLX").trim(); //论文类型
						String ktly = rs.getString("KTLY")==null?"":rs.getString("KTLY").trim(); //<资助项目>
						String kwlb = rs.getString("KWLB")==null?"":rs.getString("KWLB").trim(); //<刊物类别>
						
						content.append(num+". ");
						content.append("论文名称："+workName+"。论文类型："+lwlx+"。资助项目："+ktly+"。刊物类别："+kwlb+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("XSZZ")) {
						workName = rs.getString("ZZMC")==null?"":rs.getString("ZZMC").trim();
						String xmly = rs.getString("XMLY")==null?"":rs.getString("XMLY").trim(); //XMLY<资助项目>
						String lzlb = rs.getString("LZLB")==null?"":rs.getString("LZLB").trim(); //LZLB<论著类别>
						String cbrq = rs.getString("CBRQ")==null?"":rs.getString("CBRQ").trim(); //CBRQ<出版日期>
						if(cbrq!=null && cbrq.length() >= 10) {
							cbrq = cbrq.substring(0,10);
						}
						 
						content.append(num+". ");
						content.append("著作名称："+workName+"。资助项目："+xmly+"。论著类别："+lzlb+"。出版日期："+cbrq+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("KYXM")) {
						workName = rs.getString("XMMC")==null?"":rs.getString("XMMC").trim();
						String xmjb = rs.getString("XMJB")==null?"":rs.getString("XMJB").trim(); //XMJB<资助项目>
						
						content.append(num+". ");
						content.append("项目名称："+workName+"。资助项目："+xmjb+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("YJBG")) {
						workName = rs.getString("CGMC")==null?"":rs.getString("CGMC").trim();
						String cglx = rs.getString("CGLX")==null?"":rs.getString("CGLX").trim(); //CGLX<成果类型>
						String rdrq = rs.getString("RDRQ")==null?"":rs.getString("RDRQ").trim(); //RDRQ<认定日期>
						String sfcn = rs.getString("SFCN")==null?"":rs.getString("SFCN").trim(); //SFCN<是否司局以上采纳>
						if(rdrq!=null && rdrq.length() >= 10) {
							rdrq = rdrq.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("研究报告："+workName+"。成果类型："+cglx+"。认定日期："+rdrq+"。是否司局以上采纳："+sfcn+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("CGHJ")) {
						workName = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME").trim();
						String awarddate = rs.getString("AWARDDATE")==null?"":rs.getString("AWARDDATE").trim(); //AWARDDATE<奖励日期>
						if(awarddate!=null && awarddate.length() >= 10) {
							awarddate = awarddate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("论文名称："+workName+"。奖励日期："+awarddate+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("ZLXX")) {
						workName = rs.getString("PATENTNAME")==null?"":rs.getString("PATENTNAME").trim();
						String authorizedate = rs.getString("AUTHORIZEDATE")==null?"":rs.getString("AUTHORIZEDATE").trim(); //AUTHORIZEDATE<授权日期>
						if(authorizedate!=null && authorizedate.length() >= 10) {
							authorizedate = authorizedate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("论文名称："+workName+"。授权日期："+authorizedate+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					} else if(option.equals("QTCG")) {
						workName = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME").trim();
						String havkindsymbol = rs.getString("HAVKINDSYMBOL")==null?"":rs.getString("HAVKINDSYMBOL").trim(); //HAVKINDSYMBOL<成果类型>
						String appraisedate = rs.getString("APPRAISEDATE")==null?"":rs.getString("APPRAISEDATE").trim(); //APPRAISEDATE<完成日期>
						if(appraisedate!=null && appraisedate.length() >= 10) {
							appraisedate = appraisedate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("论文名称："+workName+"。成果类型："+havkindsymbol+"。完成日期："+appraisedate+"。归属年度："+datayear+"\r\n");
						content.append("作者："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
					}
				}
			} else if(option.equals("RKQK")) {
				sql = "select * from BO_RKQKTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String KCMC = rs.getString("KCMC")==null?"":rs.getString("KCMC").trim();  //课程名
					String KCLB = rs.getString("KCLB")==null?"":rs.getString("KCLB"); //教学类别
					String SKDX = rs.getString("SKDX")==null?"":rs.getString("SKDX"); //授课对象
					content.append(num+". ");
					content.append(KCMC+"。 教学类别："+KCLB+"。授课对象："+SKDX+"\r\n");
				}
			} else if(option.equals("ZDXS")) {
				sql = "select * from BO_ZDXSTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String XM = rs.getString("XM")==null?"":rs.getString("XM"); //学生姓名
					String XY = rs.getString("XY")==null?"":rs.getString("XY"); //学院名称
					String ZY = rs.getString("ZY")==null?"":rs.getString("ZY"); //专业名称
					content.append(num+". ");
					content.append(XM+"。 学院名称："+XY+"。专业名称："+ZY+"\r\n");
				}
			} else if(option.equals("JGXM")) {
				sql = " select * from (" +
					" select * from BO_JGXMTEMP where  ZCRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
					" union select * from BO_JGXMTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JGXMTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JGXMTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JGXMTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JGXMTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
					") order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); // 项目名称
					String CHARGENM = rs.getString("ZCR")==null?"":rs.getString("ZCR").trim(); 
					String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1").trim(); 
					String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2").trim(); 
					String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3").trim(); 
					String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4").trim(); 
					String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5").trim(); 
					int AUTOADD = 0; 
					AUTOADD = rs.getInt("AUTOADD");
					String workPositon = "本人主持此项目。";
					if (AUTOADD == 1) {
						workPositon = "本人参与此项目。";
					}
					content.append(num+". ");
					content.append(XMMC+"。 参与人："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
				}
			} else if(option.equals("JXHJ")) {
				sql = " select * from (" +
					" select * from BO_JXHJTEMP where  FZRID='"+awsuid+"'  and (AUTOADD is null or AUTOADD=0) " +
					" union select * from BO_JXHJTEMP where  ID1='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JXHJTEMP where  ID2='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JXHJTEMP where  ID3='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JXHJTEMP where  ID4='"+awsuid+"' and AUTOADD=1 " +
					" union select * from BO_JXHJTEMP where  ID5='"+awsuid+"' and AUTOADD=1 " +
					") order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String CGMC = rs.getString("CGMC")==null?"":rs.getString("CGMC"); //成果名称
					String CHARGENM = rs.getString("FZR")==null?"":rs.getString("FZR").trim(); 
					String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1").trim(); 
					String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2").trim(); 
					String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3").trim(); 
					String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4").trim(); 
					String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5").trim(); 
					int AUTOADD = 0; 
					AUTOADD = rs.getInt("AUTOADD");
					String workPositon = "本人主持此项目。";
					if (AUTOADD == 1) {
						workPositon = "本人参与此项目。";
					}
					content.append(num+". ");
					content.append(CGMC+"。 参与人："+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"（"+workPositon+"）\r\n");
				}
			} else if(option.equals("GZJL")) {
				sql = "select * from BO_GZJLTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String QSSJ = rs.getString("QSSJ")==null?"":rs.getString("QSSJ"); //起始时间
					String JSSJ = rs.getString("JSSJ")==null?"":rs.getString("JSSJ"); //结束时间
					String SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); //所在单位
					String GZZW = rs.getString("GZZW")==null?"":rs.getString("GZZW"); //工作职务
					content.append(num+". ");
					content.append(QSSJ+" -- "+JSSJ+", 在'"+SZDW+"'。"+GZZW+"\r\n");
				}
			} else if(option.equals("ZDZY")) {
				sql = "select * from BO_ZDZYTEMP where 1=1 and DSBH='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String XYMC = rs.getString("XYMC")==null?"":rs.getString("XYMC"); //所在单位
					String DRSDRQ = rs.getString("DRSDRQ")==null?"":rs.getString("DRSDRQ"); //担任硕导日期
					String DRBDRQ = rs.getString("DRBDRQ")==null?"":rs.getString("DRBDRQ"); //担任博导日期
					content.append(num+". ");
					if(!DRSDRQ.equals("")) {
						content.append(DRSDRQ+" 在"+XYMC+"担任硕导。 \r\n");
					}
					if(!DRBDRQ.equals("")) {
						content.append(DRBDRQ+" 在"+XYMC+"担任博导。 \r\n");
					}
				}
			} else if(option.equals("GNXXJL")) {
				sql = "select * from BO_GNXXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ"); //入学时间
					String BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ"); //毕业时间
					String BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX"); //学校/单位
					String SXZY = rs.getString("SXZY")==null?"":rs.getString("SXZY"); //所学专业
					String YJFX = rs.getString("YJFX")==null?"":rs.getString("YJFX"); //研究方向
					String XW = rs.getString("XW")==null?"":rs.getString("XW"); //授予学位
					String XWSYSJ = rs.getString("XWSYSJ")==null?"":rs.getString("XWSYSJ"); //学位授予时间
					content.append(num+". ");
					content.append(RXSJ+" -- "+BYSJ+" 在"+BYXX+" 学习："+SXZY+" "+YJFX+"。并在"+XWSYSJ+" 授予 "+XW+"\r\n");
				}
			} else if(option.equals("GJWXXJL")) {
				sql = "select * from BO_GJWXXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ");
					String BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ");
					String BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX");
					String SXZY = rs.getString("SXZY")==null?"":rs.getString("SXZY");
					String YJFX = rs.getString("YJFX")==null?"":rs.getString("YJFX");
					String XW = rs.getString("XW")==null?"":rs.getString("XW");
					String XWSYSJ = rs.getString("XWSYSJ")==null?"":rs.getString("XWSYSJ");
					content.append(num+". ");
					content.append(RXSJ+" -- "+BYSJ+" 在"+BYXX+" 学习："+SXZY+" "+YJFX+"。并在"+XWSYSJ+" 授予 "+XW+"\r\n");
				}
			} else if(option.equals("PXJL")) {
				sql = "select * from BO_PXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ");
					String BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ");
					String PXNR = rs.getString("PXNR")==null?"":rs.getString("PXNR");
					content.append(num+". ");
					content.append(RXSJ+" -- "+BYSJ+"。 培训："+PXNR+"\r\n");
				}
			} else if(option.equals("GJHQK")) {
				sql = "select * from BO_GJHQKTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String QSRQ = rs.getString("QSRQ")==null?"":rs.getString("QSRQ");          // 起始日期
					String JZRQ= rs.getString("JZRQ")==null?"":rs.getString("JZRQ");     // 截止日期 
					String CFGJ1 = rs.getString("CFGJ1")==null?"":rs.getString("CFGJ1");   // 国家地区
					String CFDW = rs.getString("CFDW")==null?"":rs.getString("CFDW"); // 学习采访单位
					String CFLX = rs.getString("CFLX")==null?"":rs.getString("CFLX");   // 采访类型
					String CFHDNR = rs.getString("CFHDNR")==null?"":rs.getString("CFHDNR"); // 成果 
					content.append(num+". ");
					content.append(QSRQ+" -- "+JZRQ+" 在"+CFGJ1+"，"+CFDW+"。"+CFLX+"。"+CFHDNR+"\r\n");
				}
			} else if(option.equals("JTCY")) {
				sql = "select * from BO_JTCYTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String CYXM = rs.getString("CYXM")==null?"":rs.getString("CYXM");          // 成员姓名
					String YBRGX= rs.getString("YBRGX")==null?"":rs.getString("YBRGX");     // 与本人关系 
					String GZDW = rs.getString("GZDW")==null?"":rs.getString("GZDW");   // 工作学习单位
					String ZW = rs.getString("ZW")==null?"":rs.getString("ZW"); // 职务
					content.append(num+". ");
					content.append(CYXM+"："+YBRGX+"。"+GZDW+"，"+ZW+"\r\n");
				}
			} 
			content.append("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\r\n\r\n");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBSql.close(conn, ps, rs);
		}
		
		return content.toString();
	}
	
}
