package bnu.managerWeb;

import java.util.Hashtable;

import bnu.util.HRCardButton;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.AjaxUtil;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

public class BnuManagerMainWeb extends ActionsoftWeb {
	
	private static final String _returnButton = "<input type=button value='关  闭'  class ='actionsoftButton' onClick=\"window.close();return false;\"  border='0'>";

	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuManagerMainWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuManagerMainWeb() {
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
		
		if ("1".equals(pageType)) { //SQL窗口
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_BACKDOOR_SQL_WIN&awsuid=" + awsuid);
		} else if ("2".equals(pageType)) { //教育经历_国内学习
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_GNXXJL&awsuid="+ awsuid);
		} else if ("3".equals(pageType)) { //教育经历_国境外学习
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_GJWXXJL&awsuid="+ awsuid);
		} else if ("4".equals(pageType)) { //教育经历_培训经历
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_PXJL&awsuid="+ awsuid);
		} else if ("5".equals(pageType)) { //工作经历
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_GZJL&awsuid="+ awsuid);
		} else if ("6".equals(pageType)) { //人才培养_任课情况
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_RKQK&awsuid="+ awsuid);
		} else if ("7".equals(pageType)) { //人才培养_指导专业
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_ZDZY&awsuid="+ awsuid);
		} else if ("8".equals(pageType)) { //人才培养_指导学生
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_ZDXS&awsuid="+ awsuid);
		} else if ("9".equals(pageType)) { //人才培养_教改项目
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_JGXM&awsuid="+ awsuid);
		} else if ("10".equals(pageType)) { //人才培养_教学获奖
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_JXHJ&awsuid="+ awsuid);
		} else if ("11".equals(pageType)) { //科研情况_科研项目
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_KYXM&awsuid="+ awsuid);
		} else if ("12".equals(pageType)) { //科研情况_学术论文
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_XSLW&awsuid="+ awsuid);
		} else if ("13".equals(pageType)) { //科研情况_学术著作
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_XSZZ&awsuid="+ awsuid);
		} else if ("14".equals(pageType)) { //科研情况_专利信息
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_ZLXX&awsuid="+ awsuid);
		} else if ("15".equals(pageType)) { //科研情况_其他成果
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_QTCG&awsuid="+ awsuid);
		} else if ("16".equals(pageType)) { //科研情况_科研获奖
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_CGHJ&awsuid="+ awsuid);
		} else if ("17".equals(pageType)) { //国际化情况
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_GJHQK&awsuid="+ awsuid);
		} else if ("18".equals(pageType)) { //家庭成员
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_JTCY&awsuid="+ awsuid);
		} else if ("19".equals(pageType)) { //科研情况_研究报告
			hashTags.put("pageUrl", cgi + "?sid=" + sid + "&cmd=BNU_MANAGER_YJBG&awsuid="+ awsuid);
		} 
		else {
			return "PageType param is error!";
		}

		StringBuffer pageButton = new StringBuffer();
		
		pageButton.append("<table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#ACBCC9\" style=\"border-collapse:collapse\">");
		pageButton.append("<tr>");
		pageButton.append("<td colspan=6 height=3></td>");
		pageButton.append("</tr>");

		pageButton.append("<tr>");
		pageButton.append("<td colspan=6 align=\"center\" nowrap=\"nowrap\" style='line-height: 25px;' class=\"bg4\">此为管理员管理数据后台。请谨慎使用！</td>");
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("教育经历_国内学习经历", "changePage(frmMain,'" + 2 + "')", ("2".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("教育经历_国境外学习", "changePage(frmMain,'" + 3 + "')", ("3".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("教育经历_培训经历", "changePage(frmMain,'" + 4 + "')", ("4".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("工作情况", "changePage(frmMain,'" + 5 + "')", ("5".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("国际化情况", "changePage(frmMain,'" + 17 + "')", ("17".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
		pageButton.append(new HRCardButton("家庭成员", "changePage(frmMain,'" + 18 + "')", ("18".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));		
		pageButton.append("</tr>");

		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("人才培养_任课情况", "changePage(frmMain,'" + 6 + "')", ("6".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_指导学生", "changePage(frmMain,'" + 8 + "')", ("8".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_指导专业", "changePage(frmMain,'" + 7 + "')", ("7".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_教改项目", "changePage(frmMain,'" + 9 + "')", ("9".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("人才培养_教学获奖", "changePage(frmMain,'" + 10 + "')", ("10".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");		
		pageButton.append(new HRCardButton("科研情况_学术论文", "changePage(frmMain,'" + 12 + "')", ("12".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_学术著作", "changePage(frmMain,'" + 13 + "')", ("13".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_研究报告", "changePage(frmMain,'" + 19 + "')", ("19".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_科研项目", "changePage(frmMain,'" + 11 + "')", ("11".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_专利信息", "changePage(frmMain,'" + 14 + "')", ("14".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("科研情况_科研获奖", "changePage(frmMain,'" + 16 + "')", ("16".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("<tr>");
		pageButton.append(new HRCardButton("科研情况_其他成果", "changePage(frmMain,'" + 15 + "')", ("15".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));	
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
		pageButton.append(new HRCardButton("","",false,super.getContext().getLookAndFeelType()));
//		pageButton.append(new HRCardButton("SQL窗口", "changePage(frmMain,'" + 1 + "')", ("1".equals(pageType)) ? true : false, super.getContext().getLookAndFeelType()));
		pageButton.append("</tr>");
		
		pageButton.append("</table>");
		
		hashTags.put("button1", button1);
		hashTags.put("button2", button2);
		hashTags.put("button3", _returnButton);
		hashTags.put("pageButton", pageButton.toString());
		hashTags.put("sid", "<input type=hidden name=sid value=" + super.getContext().getSessionId() + ">\n");
		hashTags.put("pageType", pageType);
		hashTags.put("awsuid", awsuid);
		
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_MANAGER_MAIN.htm"), hashTags);
		//end
	}
	
	public String showWin(String awsuid) {
		StringBuffer response = new StringBuffer();
		String sqlWin = "<textarea cols=80 rows=10 name='sqlString'></textarea>";
		
		response.append("<table width=98% height=90% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr><td><b><font size='3'>请输入您的SQL语句(仅限update和delete语句)：</font></b><br><br>");
		response.append("<table width=98% align=center border=0 cellspacing=0 cellpadding=0>");
		response.append("<tr>\n");
		response.append("<td width=90% align=left>").append(sqlWin).append("</td>\n");
		response.append("</tr>\n");
		response.append("</table>");
		response.append("</td></tr>");
		response.append("<tr><td><b>执行SQL语句：</b>[<a href='' onclick=\"save(frmMain,'BNU_MANAGER_BACKDOOR_SQL_SAVE');return false;\"><img src=../aws_img/save.gif border=0>&nbsp;执行</a>]<br>");
		response.append("</td></tr></table>");
		
		return AjaxUtil.responseDialog("SQL执行窗口", 150, 70, 600, 400, response.toString(), "" ); //left,top,width,height
	}
	
	public String executeSql(String awsuid, String sqlString) {
		StringBuffer response = new StringBuffer();
		try{
		   System.out.println("sqlString=================\n"+sqlString);
		   
		   int result = 0;
		   if(sqlString.startsWith("update") || sqlString.startsWith("delete")) {
			   result = DBSql.executeUpdate(sqlString);
		   }
		   
		   response.append("<table width=98% height=98% align=center border=0 cellspacing=0 cellpadding=0>");
		   response.append("<tr><td><b><font size='5'>SQL:"+sqlString+"\n 执行成功。执行结果为:"+result+"</font></b><br><br>");
		   response.append("</td></tr></table>");
		}catch(Exception e){
			e.printStackTrace(System.err);
			response.append("<table width=98% height=98% align=center border=0 cellspacing=0 cellpadding=0>");
			response.append("<tr><td><b><font size='5'>SQL:"+sqlString+"\n 执行失败。请检查SQL语法</font></b><br><br>");
			response.append("</td></tr></table>");
		}
		
//		return AjaxUtil.responseDialog("SQL执行结果", 200, 50, 200, 60, response.toString(), "" ); //left,top,width,height
		return AjaxUtil.responseHtml(response.toString(), null);
	}
	
	
}
