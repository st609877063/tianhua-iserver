package bnu.managerWeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import bnu.importBiz.CalMark;
import bnu.util.HRCardButton;
import bnu.web.BnuMainWeb;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class BnuReCalculateWeb extends ActionsoftWeb {
	
	private static final String _returnButton = "<input type=button value='关  闭'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuReCalculateWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuReCalculateWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMain(String pageType,String awsuid) {
		Hashtable<String,String> hashTags = new Hashtable<String,String>();
		String cgi = "./login.wf";
		String sid = super.getContext().getSessionId();
//		System.out.println("BnuMainWeb=====>getMain=====>sid="+sid);
//		System.out.println("BnuMainWeb=====>getMain=====>pageType="+pageType);
//		System.out.println("BnuMainWeb=====>getMain=====>awsuid="+awsuid);
		
		String button1 = "", button2 = "";
		
		if ("1".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=XSLW&awsuid="+awsuid); //科研情况_学术论文
		} else if ("2".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=XSZZ&awsuid="+awsuid); //科研情况_学术著作
		} else if ("3".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=KYXM&awsuid="+awsuid); //科研情况_科研项目
		} else if ("4".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=YJBG&awsuid="+awsuid); //科研情况_研究报告
		} else if ("5".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=CGHJ&awsuid="+awsuid); //科研情况_科研获奖
		} else if ("6".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZLXX&awsuid="+awsuid); //科研情况_专利信息
		} else if ("7".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=QTCG&awsuid="+awsuid); //科研情况_其他成果
		} else if ("8".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=RKQK&awsuid="+awsuid); //人才培养_任课情况
		} else if ("9".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZDXS&awsuid="+awsuid); //人才培养_指导学生
		} else if ("10".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JGXM&awsuid="+awsuid); //人才培养_教改项目
		} else if ("11".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JXHJ&awsuid="+awsuid); //人才培养_教学获奖
		} else if ("12".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GZJL&awsuid="+awsuid); //工作经历
		} else if ("13".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=ZDZY&awsuid="+awsuid); //人才培养_指导专业
		} else if ("14".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GNXXJL&awsuid="+awsuid); //教育经历_国内学习
		} else if ("15".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GJWXXJL&awsuid="+awsuid); //教育经历_国境外学习
		} else if ("16".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=PXJL&awsuid="+awsuid); //教育经历_培训经历
		} else if ("17".equals(pageType)) {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=GJHQK&awsuid="+awsuid);  //国际化情况
		} else if ("18".equals(pageType)) { 
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=JTCY&awsuid="+awsuid); //家庭成员
		} 
		else if ("2009".equals(pageType)) {  //重置旧数据为2009数据专用
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=2009&awsuid="+awsuid);
		}
		else {
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_RECALCULATE_PROCESS&type=NULL&awsuid="+awsuid);
		}

		StringBuffer pageButton = new StringBuffer();
		
		pageButton.append("<table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#ACBCC9\" style=\"border-collapse:collapse\">");
		pageButton.append("<tr>");
		pageButton.append("<td colspan=3 height=3></td>");
		pageButton.append("</tr>");

		pageButton.append("<tr>");
		pageButton.append("<td colspan=3 align=\"center\" nowrap=\"nowrap\" style='line-height: 25px;' class=\"bg4\">此功能为清空分值，重新计算分值。请谨慎使用！</td>");
		pageButton.append("</tr>");

//		pageButton.append(new HRCardButton("人才培养_教学获奖|| ", "changePage(frmRecalMain,'" + 11 + "')", ("11".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("人才培养_指导专业|| ", "changePage(frmRecalMain,'" + 13 + "')", ("13".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("教育经历_培训经历|| ", "changePage(frmRecalMain,'" + 16 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("教育经历_国境外学习| ", "changePage(frmRecalMain,'" + 15 + "')", ("15".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("国际化情况", "changePage(frmRecalMain,'" + 17 + "')", ("17".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
//		pageButton.append(new HRCardButton("教育经历_国内学习经历|| ", "changePage(frmRecalMain,'" + 14 + "')", ("14".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("家庭成员", "changePage(frmRecalMain,'" + 18 + "')", ("18".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		

		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("科研情况_学术论文", "changePage(frmRecalMain,'" + 1 + "')", ("1".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_专利信息", "changePage(frmRecalMain,'" + 6 + "')", ("6".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_任课情况", "changePage(frmRecalMain,'" + 8 + "')", ("8".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");

		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("科研情况_学术著作", "changePage(frmRecalMain,'" + 2 + "')", ("2".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_科研获奖", "changePage(frmRecalMain,'" + 5 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_教改项目", "changePage(frmRecalMain,'" + 10 + "')", ("10".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("科研情况_其他成果", "changePage(frmRecalMain,'" + 7 + "')", ("7".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));	
		pageButton.append(new HRCardButton("科研情况_科研项目", "changePage(frmRecalMain,'" + 3 + "')", ("3".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_指导学生", "changePage(frmRecalMain,'" + 9 + "')", ("9".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("科研情况_研究报告", "changePage(frmRecalMain,'" + 4 + "')", ("4".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("工作情况", "changePage(frmRecalMain,'" + 12 + "')", ("12".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("重置旧数据为2009数据", "changePage(frmRecalMain,'" + 2009 + "')", ("2009".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("</table>");
		
		hashTags.put("button1", button1);
		hashTags.put("button2", button2);
		hashTags.put("button3", _returnButton);
		hashTags.put("pageButton", pageButton.toString());
		hashTags.put("sid", "<input type=hidden name=sid value=" + super.getContext().getSessionId() + ">\n");
		hashTags.put("pageType", pageType);
		hashTags.put("awsuid", awsuid);
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_RECALCULATE_MAIN.htm"), hashTags);
		//end
	}
	
	
	public String reCalculate(String awsuid, String type) {
		/*
		TYPE:
		XSLW //科研情况_学术论文
		XSZZ //科研情况_学术著作
		KYXM //科研情况_科研项目
		YJBG //科研情况_研究报告
		CGHJ //科研情况_科研获奖
		ZLXX //科研情况_专利信息
		QTCG //科研情况_其他成果
		RKQK //人才培养_任课情况
		ZDXS //人才培养_指导学生
		JGXM //人才培养_教改项目
		JXHJ //人才培养_教学获奖
		GZJL //工作经历
		ZDZY //人才培养_指导专业
		GNXXJL //教育经历_国内学习
		GJWXXJL //教育经历_国境外学习
		PXJL //教育经历_培训经历
		GJHQK //国际化情况
		JTCY //家庭成员
		*/
		
		System.out.println(".....................重新计算分值................"+type);
		
		String tableChName = "";
		if(type.equalsIgnoreCase("XSLW")) {
			tableChName = "科研情况_学术论文";
		} else if(type.equalsIgnoreCase("XSZZ")) {
			tableChName = "科研情况_学术著作";
		} else if(type.equalsIgnoreCase("KYXM")) {
			tableChName = "科研情况_科研项目";
		} else if(type.equalsIgnoreCase("YJBG")) {
			tableChName = "科研情况_研究报告";
		} else if(type.equalsIgnoreCase("CGHJ")) {
			tableChName = "科研情况_科研获奖";
		} else if(type.equalsIgnoreCase("ZLXX")) {
			tableChName = "科研情况_专利信息";
		} else if(type.equalsIgnoreCase("QTCG")) {
			tableChName = "科研情况_其他成果";
		} else if(type.equalsIgnoreCase("RKQK")) {
			tableChName = "人才培养_任课情况";
		} else if(type.equalsIgnoreCase("ZDXS")) {
			tableChName = "人才培养_指导学生";
		} else if(type.equalsIgnoreCase("JGXM")) {
			tableChName = "人才培养_教改项目";
		} else if(type.equalsIgnoreCase("JXHJ")) {
			tableChName = "人才培养_教学获奖";
		} else if(type.equalsIgnoreCase("GZJL")) {
			tableChName = "工作经历";
		} else if(type.equalsIgnoreCase("ZDZY")) {
			tableChName = "人才培养_指导专业";
		} else if(type.equalsIgnoreCase("GNXXJL")) {
			tableChName = "教育经历_国内学习";
		} else if(type.equalsIgnoreCase("GJWXXJL")) {
			tableChName = "教育经历_国境外学习";
		} else if(type.equalsIgnoreCase("PXJL")) {
			tableChName = "教育经历_培训经历";
		} else if(type.equalsIgnoreCase("GJHQK")) {
			tableChName = "国际化情况";
		} else if(type.equalsIgnoreCase("JTCY")) {
			tableChName = "家庭成员";
		} else if(type.equalsIgnoreCase("NULL")) {
			tableChName = "未选择操作。";
		} 
		
		
		BnuReCalculateWeb web = new BnuReCalculateWeb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		if(type == null || type.equalsIgnoreCase("NULL")) {
//			return web.getMain("0",awsuid);
			return "请选择区域进行重置分值！请谨慎操作！";
		} else if(type.equals("2009")) { //重置旧数据为2009数据
			web.setDataYear2009();
			return "重置旧数据为2009数据成功！";
		}
		
		String tableName = "BO_"+type.toUpperCase()+"TEMP";
		
		try{
			
			/**********步骤一:清空数据
			 * 考虑，数据共享部分。AUTOADD=1 数据
			***********************/
			String clearSql = "";
			if(type.equalsIgnoreCase("RKQK") || type.equalsIgnoreCase("ZDXS") || type.equalsIgnoreCase("GZJL")) {
				clearSql = " UPDATE "+tableName+" SET FINALMARK=0";
			} else {
				clearSql = " UPDATE "+tableName+" SET FINALMARK=0 WHERE AUTOADD=1";
			}
			DBSql.executeUpdate(clearSql);
			
			//ZFM, DATAYEAR, JXZFM(教学总分值)
			String clearSql2 = " UPDATE BO_FINALMARK  SET "+type.toUpperCase()+"=0";
			DBSql.executeUpdate(clearSql2);
			String updateSql = " UPDATE BO_FINALMARK  SET ZFM = NVL(GNXXJL,0)+NVL(GJWXXJL,0)" +
					"+NVL(PXJL,0)+NVL(GZJL,0)+NVL(RKQK,0)+NVL(ZDZY,0)+NVL(ZDXS,0)" +
					"+NVL(JGXM,0)+NVL(JXHJ,0)+NVL(KYXM,0)+NVL(XSLW,0)+NVL(XSZZ,0)" +
					"+NVL(ZLXX,0)+NVL(QTCG,0)+NVL(CGHJ,0)+NVL(GJHQK,0)+NVL(JTCY,0)+NVL(YJBG,0)";
			DBSql.executeUpdate(updateSql);
			//如果“工作经历”, “人才培养_任课情况”, “人才培养_指导学生” 三类特殊处理。叠加JXZFM（教学总分值）
			String updateSql2 = " UPDATE BO_FINALMARK  SET JXZFM = NVL(GZJL,0)+NVL(RKQK,0)+NVL(ZDXS,0)";
			DBSql.executeUpdate(updateSql2);
			//叠加KYZFM（科研总分值） XSLW 科研情况_学术论文; XSZZ 科研情况_学术著作;	KYXM 科研情况_科研项目; YJBG科研情况_研究报告;CGHJ 科研情况_科研获奖;ZLXX 科研情况_专利信息;QTCG 科研情况_其他成果
			String updateSql3 = " UPDATE BO_FINALMARK  SET KYZFM = NVL(XSLW,0)+NVL(XSZZ,0)+NVL(KYXM,0)+NVL(YJBG,0)+NVL(CGHJ,0)+NVL(ZLXX,0)+NVL(QTCG,0)";
			DBSql.executeUpdate(updateSql3);
			
			/**********步骤二：重新计算分值入库*********************************/
			conn = DBSql.open();
			conn.setAutoCommit(true);
			
			String sql = "SELECT * FROM "+tableName;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int dataId = 0;
			String jgh = "";
			
			while(rs.next()){
				dataId = rs.getInt("ID");
				if(type.equalsIgnoreCase("RKQK") || type.equalsIgnoreCase("ZDXS") || type.equalsIgnoreCase("GZJL")) {
					jgh = rs.getString("CHARGEID")==null?"":rs.getString("CHARGEID").trim();
				} else {
					jgh = rs.getString("JGH")==null?"":rs.getString("JGH").trim();
				}
				String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim();
				
				CalMark calMark = new CalMark();
				int finalMark = calMark.getCalMark(tableName, dataId);
				
				String setMarkSql = "UPDATE "+tableName+" set FINALMARK="+finalMark+" where id="+dataId;
				DBSql.executeUpdate(setMarkSql);
				if(jgh!=null && !jgh.equals("")) { //存在没有教工号的数据。即为垃圾数据
					calMark.setFinalMarkWithYear("", "", jgh, tableName, finalMark, datayear);
				}
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
			MessageQueue.getInstance().putMessage(super.getContext().getUID(),tableChName+" 重置分值出错！");
//			return web.getMain("0",awsuid);
			return tableChName+" 重置分值出错！";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		
		MessageQueue.getInstance().putMessage(super.getContext().getUID(),tableChName+" 重置分值成功！");
//		return web.getMain("0",awsuid);
		return tableChName+" 重置分值成功！";
	}
	
	
	private void setDataYear2009() {
		//前台设定了每个都有DATAYEAR字段后，后台跑批来修改旧数据
		
		String updateBOFINALSql = "UPDATE BO_FINALMARK SET DATAYEAR='2009'";
		System.out.println("处理BO_FINALMARK表。sql="+updateBOFINALSql);
		DBSql.executeUpdate(updateBOFINALSql);
		
		List tableList = new ArrayList();
		tableList.add("BO_XSZZTEMP");
		tableList.add("BO_ZLXXTEMP");
		tableList.add("BO_QTCGTEMP");
		tableList.add("BO_CGHJTEMP");
		tableList.add("BO_JGXMTEMP");
		tableList.add("BO_RKQKTEMP");
		tableList.add("BO_ZDXSTEMP");
		tableList.add("BO_GZJLTEMP");
		tableList.add("BO_YJBGTEMP"); //
		tableList.add("BO_XSLWTEMP"); //
		tableList.add("BO_KYXMTEMP"); //
		
		for(int i=0; i<tableList.size(); i++) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String tableName = (String)tableList.get(i);
			
			/**********原来数据中没有表示年度的字段。直接update语句****************/
			if(tableName.equals("BO_XSZZTEMP") || tableName.equals("BO_ZLXXTEMP") || tableName.equals("BO_QTCGTEMP") || tableName.equals("BO_CGHJTEMP") || tableName.equals("BO_JGXMTEMP") || tableName.equals("BO_RKQKTEMP") || tableName.equals("BO_ZDXSTEMP")|| tableName.equals("BO_GZJLTEMP")) {
				String updateDataYearSql = "UPDATE "+tableName+" SET DATAYEAR='2009'";
				System.out.println("原表无年度字段。sql="+updateDataYearSql);
				DBSql.executeUpdate(updateDataYearSql);
			}
			
			/**********如果原来里面有字段表示年度，那复制其数据。****************/
			// 科研情况_研究报告(FBND), 科研情况_学术论文(FBND), 科研情况_科研项目(LXND)
			if(tableName.equals("BO_YJBGTEMP") || tableName.equals("BO_XSLWTEMP") || tableName.equals("BO_KYXMTEMP")) {
				try{
					conn = DBSql.open();
					conn.setAutoCommit(true);
					String sql = "SELECT * FROM "+tableName;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					
					int dataId = 0;
					while(rs.next()){
						dataId = rs.getInt("ID");
						String datayearOld = "";
						if(tableName.equals("BO_YJBGTEMP") || tableName.equals("BO_XSLWTEMP")) {
							datayearOld = rs.getString("FBND")==null?"2009":rs.getString("FBND").trim();
						} else if(tableName.equals("BO_KYXMTEMP")) {
							datayearOld = rs.getString("LXND")==null?"2009":rs.getString("FBND").trim();
						}
						String updateDataYearSql2 = "UPDATE "+tableName+" SET DATAYEAR='"+datayearOld+"' WHERE ID="+dataId;
						DBSql.executeUpdate(updateDataYearSql2);
					}
				} catch(Exception e){
					e.printStackTrace(System.err);
				} finally{
					DBSql.close(conn, ps, rs);
				}
			}
		}
	}
	
}
