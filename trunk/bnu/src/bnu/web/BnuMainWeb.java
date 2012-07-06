package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import bnu.util.HRCardButton;
import bnu.util.HRCardButton2;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.UpFile;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

/**
 * Using
 * @author maxwell
 * @version V1.0
*/
public class BnuMainWeb extends ActionsoftWeb {
	
	private static final String _returnButton = "<input type=button value='关  闭'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuMainWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuMainWeb() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获得员工自助首页
	 * @param pageType tab类型
	 * @param awsuid   AWS系统用户帐号
	 * @return
	 */
	public String getMain(String pageType,String awsuid) {
		Hashtable<String,String> hashTags = new Hashtable<String,String>();
		String cgi = "./login.wf";
		String sid = super.getContext().getSessionId();
//		System.out.println("BnuMainWeb=====>getMain=====>sid="+sid);
//		System.out.println("BnuMainWeb=====>getMain=====>pageType="+pageType);
//		System.out.println("BnuMainWeb=====>getMain=====>awsuid="+awsuid);
		
		String button1 = "", button2 = "";
		String ZGH = null;//教工号
		String XM = null;//姓名
		String BMMC = null;//所在单位
		String JSZW = null;//现职称
		String JSZW2 = null;//现职称2
		String SSXK = null;
		String SSXK2 = null;
		String ZGQK = null;
		String ZGXW = null;
		String picture = null;//员工照片
		String GWLB = null;
		String GWLB2 = null;
		String XXLB = null;
		
		//员工照片附件
//		String attacheList="./login.wf?sid="+super.getContext().getSessionId()+"&cmd=Kingsoft_HR_Attach_Open&awsuid="+awsuid;
		String attacheList="员工照片附件";

		/*
		int workflowInstanceId = Integer.parseInt(sid);
		Hashtable rsTable = BOInstanceAPI.getInstance().getBOData("BO_RSTEMP", workflowInstanceId);
		String zgh = rsTable.get("ZGH").toString() == null ? "" : rsTable.get("ZGH").toString();
		System.out.println("getBOData@@@@@@@@@@@@@@@@@@@zgh="+zgh);
		*/
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int dataId = 0;
		int bindId = 0;
		try {
			conn = DBSql.open();
			conn.setAutoCommit(true);
			String sql = "select * from BO_RSTEMP where LOGINID='"+awsuid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				dataId = rs.getInt("ID");
				bindId = rs.getInt("BINDID");
				ZGH = rs.getString("ZGH");
				XM = rs.getString("XM");
				BMMC = rs.getString("BMMC");
				JSZW = rs.getString("JSZW");
				JSZW2 = rs.getString("JSZW2");
				SSXK = rs.getString("SSXK");
				SSXK2 = rs.getString("SSXK2");
				ZGQK = rs.getString("ZGQK");
				ZGXW = rs.getString("ZGXW");
				GWLB = rs.getString("GWLB");
				GWLB2 = rs.getString("GWLB2");
				XXLB = rs.getString("XXLB");
			}
			ps.close();
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}finally {
			DBSql.close(conn, ps, rs);
		}
		
		
		if ("1".equals(pageType)) { //基本信息
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_JBXX&awsuid=" + awsuid);
		} else if ("2".equals(pageType)) { //教育经历_国内学习
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_GNXXJL&awsuid="+ awsuid);
		} else if ("3".equals(pageType)) { //教育经历_国境外学习
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_GJWXXJL&awsuid="+ awsuid);
		} else if ("4".equals(pageType)) { //教育经历_培训经历
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_PXJL&awsuid="+ awsuid);
		} else if ("5".equals(pageType)) { //工作经历
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_GZJL&awsuid="+ awsuid);
		} else if ("6".equals(pageType)) { //人才培养_任课情况
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_RKQK&awsuid="+ awsuid);
		} else if ("7".equals(pageType)) { //人才培养_指导专业
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_ZDZY&awsuid="+ awsuid);
		} else if ("8".equals(pageType)) { //人才培养_指导学生
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_ZDXS&awsuid="+ awsuid);
		} else if ("9".equals(pageType)) { //人才培养_教改项目
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_JGXM&awsuid="+ awsuid);
		} else if ("10".equals(pageType)) { //人才培养_教学获奖
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_JXHJ&awsuid="+ awsuid);
		} else if ("11".equals(pageType)) { //科研情况_科研项目
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_KYXM&awsuid="+ awsuid);
		} else if ("12".equals(pageType)) { //科研情况_学术论文
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_XSLW&awsuid="+ awsuid);
		} else if ("13".equals(pageType)) { //科研情况_学术著作
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_XSZZ&awsuid="+ awsuid);
		} else if ("14".equals(pageType)) { //科研情况_专利信息
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_ZLXX&awsuid="+ awsuid);
		} else if ("15".equals(pageType)) { //科研情况_其他成果
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_QTCG&awsuid="+ awsuid);
		} else if ("16".equals(pageType)) { //科研情况_科研获奖
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_CGHJ&awsuid="+ awsuid);
		} else if ("17".equals(pageType)) { //国际化情况
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_GJHQK&awsuid="+ awsuid);
		} else if ("18".equals(pageType)) { //家庭成员
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_JTCY&awsuid="+ awsuid);
		} 
		else if ("19".equals(pageType)) { //科研情况_研究报告
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_HR_YJBG&awsuid="+ awsuid);
		} 
		else {
			return "PageType param is error!";
		}

		StringBuffer pageButton = new StringBuffer();
//		pageButton.append("<table width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#ACBCC9\" style=\"border-collapse:collapse\">");
//		pageButton.append("<tr>");
//		pageButton.append("<td colspan=3 height=3></td>");
//		pageButton.append("</tr>");
//		pageButton.append("<tr>");
//		pageButton.append(new HRCardButton("基本信息", "changePage(frmHrMain,'" + 1 + "')", ("1".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("教育经历_国内学习", "changePage(frmHrMain,'" + 2 + "')", ("2".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("教育经历_国境外学习", "changePage(frmHrMain,'" + 3 + "')", ("3".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append("</tr>");
//		pageButton.append("<tr>");
//		pageButton.append(new HRCardButton("教育经历_培训经历", "changePage(frmHrMain,'" + 4 + "')", ("4".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("工作经历", "changePage(frmHrMain,'" + 5 + "')", ("5".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("人才培养_任课情况", "changePage(frmHrMain,'" + 6 + "')", ("6".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append("</tr>");
//		pageButton.append("<tr>");		
//		pageButton.append(new HRCardButton("人才培养_指导专业", "changePage(frmHrMain,'" + 7 + "')", ("7".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("人才培养_指导学生", "changePage(frmHrMain,'" + 8 + "')", ("8".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//	    pageButton.append(new HRCardButton("人才培养_教改项目", "changePage(frmHrMain,'" + 9 + "')", ("9".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//	    pageButton.append("</tr>");
//		pageButton.append("<tr>");		
//		pageButton.append(new HRCardButton("人才培养_教学获奖", "changePage(frmHrMain,'" + 10 + "')", ("10".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("科研情况_科研项目", "changePage(frmHrMain,'" + 11 + "')", ("11".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("科研情况_学术论文", "changePage(frmHrMain,'" + 12 + "')", ("12".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append("</tr>");
//		pageButton.append("<tr>");
//	    pageButton.append(new HRCardButton("科研情况_学术著作", "changePage(frmHrMain,'" + 13 + "')", ("13".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("科研情况_专利信息", "changePage(frmHrMain,'" + 14 + "')", ("14".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("科研情况_其他成果", "changePage(frmHrMain,'" + 15 + "')", ("15".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));	
//		pageButton.append("</tr>");
//		pageButton.append("<tr>");
//		pageButton.append(new HRCardButton("科研情况_科研获奖", "changePage(frmHrMain,'" + 16 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
//	    pageButton.append(new HRCardButton("国际化情况", "changePage(frmHrMain,'" + 17 + "')", ("17".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
//	    pageButton.append(new HRCardButton("家庭成员", "changePage(frmHrMain,'" + 18 + "')", ("18".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
//		pageButton.append("</tr>");
//		pageButton.append("</table>");
		
//		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		
		
		pageButton.append("<table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#ACBCC9\" style=\"border-collapse:collapse\">");
		pageButton.append("<tr>");
		pageButton.append("<td colspan=4 height=3></td>");
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton2("基本信息","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton2("教育经历","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton2("人才培养","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton2("科研情况","",false,super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("基本信息", "changePage(frmHrMain,'" + 1 + "')", ("1".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("国内学习", "changePage(frmHrMain,'" + 2 + "')", ("2".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("任课情况", "changePage(frmHrMain,'" + 6 + "')", ("6".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("学术论文", "changePage(frmHrMain,'" + 12 + "')", ("12".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("国际化情况", "changePage(frmHrMain,'" + 17 + "')", ("17".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
		pageButton.append(new HRCardButton("国境外学习", "changePage(frmHrMain,'" + 3 + "')", ("3".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("指导学生", "changePage(frmHrMain,'" + 8 + "')", ("8".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("学术著作", "changePage(frmHrMain,'" + 13 + "')", ("13".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
	    pageButton.append("<tr>");		
	    pageButton.append(new HRCardButton("家庭成员", "changePage(frmHrMain,'" + 18 + "')", ("18".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
	    pageButton.append(new HRCardButton("培训经历", "changePage(frmHrMain,'" + 4 + "')", ("4".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
	    pageButton.append(new HRCardButton("教改项目", "changePage(frmHrMain,'" + 9 + "')", ("9".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
	    pageButton.append(new HRCardButton("研究(咨询)报告", "changePage(frmHrMain,'" + 19 + "')", ("19".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("学术简历","showDetail("+dataId+")",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("教学获奖", "changePage(frmHrMain,'" + 10 + "')", ("10".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("专利成果", "changePage(frmHrMain,'" + 14 + "')", ("14".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton2("工作情况","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研项目", "changePage(frmHrMain,'" + 11 + "')", ("11".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("工作经历", "changePage(frmHrMain,'" + 5 + "')", ("5".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研获奖", "changePage(frmHrMain,'" + 16 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("社会任职", "changePage(frmHrMain,'" + 7 + "')", ("7".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("其他成果", "changePage(frmHrMain,'" + 15 + "')", ("15".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));	
		pageButton.append("</tr>");
		
		pageButton.append("</table>");
		
		hashTags.put("button1", button1);
		hashTags.put("button2", button2);
		hashTags.put("button3", _returnButton);
		hashTags.put("pageButton", pageButton.toString());
		hashTags.put("sid", "<input type=hidden name=sid value=" + super.getContext().getSessionId() + ">\n");
		hashTags.put("pageType", pageType);
		hashTags.put("awsuid", awsuid);
		
		hashTags.put("dataId", String.valueOf(dataId));
		hashTags.put("ZGH", ZGH==null?"":ZGH);
		hashTags.put("XM", XM==null?"":XM);
		hashTags.put("BMMC", BMMC==null?"":BMMC);
		hashTags.put("JSZW", JSZW==null?"":JSZW);
		hashTags.put("JSZW2", JSZW2==null?"":JSZW2);
		hashTags.put("SSXK", SSXK==null?"":SSXK);
		hashTags.put("SSXK2", SSXK2==null?"":SSXK2);
		hashTags.put("ZGQK", ZGQK==null?"":ZGQK);
		hashTags.put("ZGXW", ZGXW==null?"":ZGXW);
		hashTags.put("GWLB", GWLB==null?"":GWLB);
		hashTags.put("GWLB2", GWLB2==null?"":GWLB2);
		hashTags.put("XXLB", XXLB==null?"":XXLB);
		hashTags.put("attacheList", attacheList);
		if(picture != null) {
			String awsID = "";
			picture = "<img name=subPicture onload=\"javascript:imgSize(this);\" src =downfile.wf?flag1=" + awsID + "&flag2=" + awsID + "&sid=" + this.getContext().getSessionId() + "&rootDir=KINGSOFTHR&filename=" + UpFile.encryptFileName(picture) +">";
		}else{
			picture = "<img  src=../aws_img/nopic.gif border=0>";
		}
		hashTags.put("PICPREVIEW", picture==null?"":picture);//员工照片
		
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_MAIN.htm"), hashTags);
		//end
		
	}
}
