package bnu.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Hashtable;

import bnu.util.HRUtil;

import com.actionsoft.awf.organization.control.MessageQueue;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.AjaxUtil;
import com.actionsoft.awf.util.DBSql;
import com.actionsoft.awf.util.PageIndex;
import com.actionsoft.awf.util.Sequence;
import com.actionsoft.htmlframework.htmlmodel.HtmlModelFactory;
import com.actionsoft.htmlframework.htmlmodel.RepleaseKey;
import com.actionsoft.htmlframework.web.ActionsoftWeb;

/**
 * Using
*  标题:基本信息
 * @author 
 * @version V1.0
*/
public class BnuHRJbxxWeb extends ActionsoftWeb {
	/**
	 * 构造方法
	 * @param userContext 用户上下文对象
	 */
	public BnuHRJbxxWeb(UserContext userContext) {
		super(userContext);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 构造方法
	 */
	public BnuHRJbxxWeb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getReport(String awsuid) {
		StringBuffer sb = new StringBuffer();
		Hashtable hashTags=new Hashtable();
		
		int id = 0;
		int bindId = 0;
		
		String ZGH = "";          // 教工号
		String XM= "";     // 姓名 
		String XB = "";   // 性别
		String CSNY = ""; // 出生年月
		String MZ = "";   // 民族
		String JG = ""; // 籍贯 
		String JGSHI = ""; // 籍贯（市）
		String JGOTHER = ""; // 籍贯（市）
		String CJGZSJ = ""; // 参加工作时间
		String RXGZSJ = ""; // 入校时间
		String BMMC = "";   // 所在单位
		String JSZW = "";   // 现职称
		String JSZW2 = "";   // 现职称2
		String JSZWRMSJ = "";   // 评职时间
		String GWLB = ""; // 岗位类别
		String GWLB2 = "";   // 岗位类别2
		String SSXK = "";   // 学科方向
		String SSXK2 = "";   // 学科方向2
		String PGJB = "";     // 聘位等级
		String DSLX = "";          // 导师类型
		String YHZZC = "";   // 学术专长
		String GJ = "";          // 国籍
		String GJ2 = ""; // 国籍2
		String ZGQK= "";     // 在岗情况
		String ZGQK2 = "";   // 在岗位置2
		String ZJLX = "";   // 证件类型
		String ZJLX2 = "";     // 证件类型2
		String SFZHM = ""; // 证件号码
		String HKSZDCS = "";   // 户口所在地
		String HYZK = ""; // 婚姻状况
		String XZZ = ""; // 家庭住址
		String SJ = ""; // 移动电话
		String BGSDH = "";   // 办公电话
		String EMAIL = "";   // 电子邮箱
		String ZGXW = "";   // 最终学位
		String ZGXW2 = "";          // 最后学位2
		String ZGXW3 = "";          // 最后学位3
		String ZGXWHDSJ = "";   // 毕业时间
		String WYSP = ""; // 外语水平
		String WYYZ = "";   // 外语语种
		String WYYZ2 = "";   // 外语语种2
		String ZGXL = "";     // 最后学历
		int LXGL = 0;          // 连续工龄（年）
		String ZZMM = "";   // 政治面貌
		String RDSJ = "";          // 入党时间
		String DZZ= "";     // 党总支/分党委/.. 
		String DZZ2 = "";   // 下一级党组织
		String DZB = "";   // 党支部
		int DFJS = 0; // 党费基数
		String DNZW = "";   // 党内职务
		String XZZW = ""; // 职务名称 
		String XZZWJB = ""; // 职务级别
		String YJLY = "";
		String ZYJZ = ""; 
		String BGDD = "";
		String BGSJ = ""; 
		String CZ = "";
		String TXDZ = ""; 
		String ZYWZ = "";
		String BYXX = "";
		String XXLB = "";
		String XSJG = "";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bg = "",bg1 = "",b1 = "", b2 = "";
		bg = " style=\"padding:5px;\"";  
		bg1 = "../aws_img/report-bg-blue2.gif";
		try {
//			String sqlw = "select top 1 * from BO_RSTEMP where 1=1 and LOGINID='" + awsuid + "'   order by CREATEDATE desc";
			String sqlw = "select * from BO_RSTEMP where 1=1 and LOGINID='" + awsuid + "'   order by CREATEDATE desc";
			conn=DBSql.open();
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sqlw);
			rs = ps.executeQuery();
			if (rs.next()) {
			   id = rs.getInt("ID");
			   bindId = rs.getInt("BINDID");
			   
			   ZGH = rs.getString("ZGH")==null?"":rs.getString("ZGH"); //教工号
			   XM = rs.getString("XM")==null?"":rs.getString("XM"); //姓名
			   XB = rs.getString("XB")==null?"":rs.getString("XB"); //性别
			   CSNY = rs.getString("CSNY")==null?"":rs.getString("CSNY"); //出生年月
			   MZ = rs.getString("MZ")==null?"":rs.getString("MZ"); //民族
			   JG = rs.getString("JG")==null?"":rs.getString("JG"); //籍贯
			   JGSHI = rs.getString("JGSHI")==null?"":rs.getString("JGSHI"); //籍贯（市）
			   JGOTHER = rs.getString("JGOTHER")==null?"":rs.getString("JGOTHER");
			   CJGZSJ = rs.getString("CJGZSJ")==null?"":rs.getString("CJGZSJ"); //参加工作时间
			   RXGZSJ = rs.getString("RXGZSJ")==null?"":rs.getString("RXGZSJ"); //入校时间
			   BMMC = rs.getString("BMMC")==null?"":rs.getString("BMMC"); //所在单位
			   JSZW = rs.getString("JSZW")==null?"":rs.getString("JSZW"); //现职称
			   JSZW2 = rs.getString("JSZW2")==null?"":rs.getString("JSZW2"); //现职称2
			   JSZWRMSJ = rs.getString("JSZWRMSJ")==null?"":rs.getString("JSZWRMSJ"); //评职时间
			   GWLB = rs.getString("GWLB")==null?"":rs.getString("GWLB"); //岗位类别
			   GWLB2 = rs.getString("GWLB2")==null?"":rs.getString("GWLB2"); //岗位类别2
			   SSXK = rs.getString("SSXK")==null?"":rs.getString("SSXK"); //学科方向
			   SSXK2 = rs.getString("SSXK2")==null?"":rs.getString("SSXK2"); //学科方向2 
			   PGJB = rs.getString("PGJB")==null?"":rs.getString("PGJB"); //聘位等级
			   DSLX = rs.getString("DSLX")==null?"":rs.getString("DSLX"); //导师类型
			   YHZZC = rs.getString("YHZZC")==null?"":rs.getString("YHZZC"); //学术专长 
			   GJ = rs.getString("GJ")==null?"":rs.getString("GJ"); //国籍
			   GJ2 = rs.getString("GJ2")==null?"":rs.getString("GJ2"); //国籍2
			   ZGQK = rs.getString("ZGQK")==null?"":rs.getString("ZGQK"); //在岗情况 
			   ZGQK2 = rs.getString("ZGQK2")==null?"":rs.getString("ZGQK2"); //在岗位置2 
			   ZJLX = rs.getString("ZJLX")==null?"":rs.getString("ZJLX"); //证件类型
			   ZJLX2 = rs.getString("ZJLX2")==null?"":rs.getString("ZJLX2"); //证件类型2
			   SFZHM = rs.getString("SFZHM")==null?"":rs.getString("SFZHM"); //证件号码 
			   HKSZDCS = rs.getString("HKSZDCS")==null?"":rs.getString("HKSZDCS"); //户口所在地
			   HYZK = rs.getString("HYZK")==null?"":rs.getString("HYZK"); //婚姻状况 
			   XZZ = rs.getString("XZZ")==null?"":rs.getString("XZZ"); //家庭住址
			   SJ = rs.getString("SJ")==null?"":rs.getString("SJ"); //移动电话
			   BGSDH = rs.getString("BGSDH")==null?"":rs.getString("BGSDH"); //办公电话
			   EMAIL = rs.getString("EMAIL")==null?"":rs.getString("EMAIL"); //电子邮箱
			   ZGXW = rs.getString("ZGXW")==null?"":rs.getString("ZGXW"); //最终学位
			   ZGXW2 = rs.getString("ZGXW2")==null?"":rs.getString("ZGXW2"); //最后学位2 
			   ZGXW3 = rs.getString("ZGXW3")==null?"":rs.getString("ZGXW3"); //最后学位3
			   ZGXWHDSJ = rs.getString("ZGXWHDSJ")==null?"":rs.getString("ZGXWHDSJ"); //毕业时间
			   WYSP = rs.getString("WYSP")==null?"":rs.getString("WYSP"); //外语水平
			   WYYZ = rs.getString("WYYZ")==null?"":rs.getString("WYYZ"); //外语语种
			   WYYZ2 = rs.getString("WYYZ2")==null?"":rs.getString("WYYZ2"); //外语语种2 
			   ZGXL = rs.getString("ZGXL")==null?"":rs.getString("ZGXL"); //最后学历
			   LXGL = rs.getInt("LXGL"); //连续工龄（年）
			   ZZMM = rs.getString("ZZMM")==null?"":rs.getString("ZZMM"); //政治面貌
			   RDSJ = rs.getString("RDSJ")==null?"":rs.getString("RDSJ"); //入党时间 
			   DZZ = rs.getString("DZZ")==null?"":rs.getString("DZZ"); //党总支/分党委/..
			   DZZ2 = rs.getString("DZZ2")==null?"":rs.getString("DZZ2"); //下一级党组织
			   DZB = rs.getString("DZB")==null?"":rs.getString("DZB"); //党支部 
			   DFJS = rs.getInt("DFJS"); //党费基数
			   DNZW = rs.getString("DNZW")==null?"":rs.getString("DNZW"); //党内职务 
			   XZZW = rs.getString("XZZW")==null?"":rs.getString("XZZW"); //职务名称
			   XZZWJB = rs.getString("XZZWJB")==null?"":rs.getString("XZZWJB"); //职务级别 
			   YJLY = rs.getString("YJLY")==null?"":rs.getString("YJLY").trim();
			   ZYJZ = rs.getString("ZYJZ")==null?"":rs.getString("ZYJZ").trim();
			   BGDD = rs.getString("BGDD")==null?"":rs.getString("BGDD").trim();
			   BGSJ = rs.getString("BGSJ")==null?"":rs.getString("BGSJ").trim();
			   CZ = rs.getString("CZ")==null?"":rs.getString("CZ").trim();
			   TXDZ = rs.getString("TXDZ")==null?"":rs.getString("TXDZ").trim();
			   ZYWZ = rs.getString("ZYWZ")==null?"":rs.getString("ZYWZ").trim();
			   
			   BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX").trim();
			   XXLB = rs.getString("XXLB")==null?"":rs.getString("XXLB").trim();
			   XSJG = rs.getString("XSJG")==null?"":rs.getString("XSJG").trim();
			   
			   if(CSNY.length() >= 10) {
				   CSNY = CSNY.substring(0,10);
			   }
			   if(CJGZSJ.length() >= 10) {
				   CJGZSJ = CJGZSJ.substring(0,10);
			   }
			   if(RXGZSJ.length() >= 10) {
				   RXGZSJ = RXGZSJ.substring(0,10);
			   }
			   if(JSZWRMSJ.length() >= 10) {
				   JSZWRMSJ = JSZWRMSJ.substring(0,10);
			   }
			   if(ZGXWHDSJ.length() >= 10) {
				   ZGXWHDSJ = ZGXWHDSJ.substring(0,10);
			   }
			   if(RDSJ.length() >= 10) {
				   RDSJ = RDSJ.substring(0,10);
			   }
		   }
		   ps.close();
		   rs.close();

		   sb.append("<tr bgcolor='#CCCCCC'>\n");
		   sb.append("<td><table><td align='left' style='word-break:break-all'='style='word-break:break-all'' style='line-height: 25px; width: 200px' >");
		   sb.append("<a href='#' onclick='modify("+id+");return false;'>修改</a></td></table></td>");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>教工号</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(ZGH)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>姓名</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(XM)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>性别</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(XB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>出生年月</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CSNY)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>国籍</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(GJ)+" "+GJ2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>民族</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(MZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>籍贯</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JG)+" "+JGSHI+b2+JGOTHER+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>证件类型/号码</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZJLX)+" "+ZJLX2+b2+"  "+SFZHM+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>外语水平</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(WYSP)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>外语语种</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(WYYZ)+" "+WYYZ2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>最后学历</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGXL)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>最终学位</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGXW)+" "+ZGXW2+" "+ZGXW3+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>毕业时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(ZGXWHDSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>毕业学校</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(BYXX)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>学校类别</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(XXLB)+b2+"</td>\n");
		   
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>参加工作时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CJGZSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>入校时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(RXGZSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>所在单位</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(BMMC)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>现职称</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JSZW)+" "+JSZW2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>评职时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(JSZWRMSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>岗位类别</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(GWLB)+" "+GWLB2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>学科方向</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(SSXK)+" "+SSXK2+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>聘位等级</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(PGJB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>导师类型</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DSLX)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>学术专长</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(YHZZC)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>学术机构</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(XSJG)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>研究领域</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(YJLY)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>主要兼职</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZYJZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>在岗情况</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZGQK)+" "+ZGQK2+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>办公地点/时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(BGDD)+" "+BGSJ+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>连续工龄/年</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(LXGL)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>婚姻状况</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(HYZK)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>户口所在地</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(HKSZDCS)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>家庭住址</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(XZZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>通讯地址</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(TXDZ)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>移动电话</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(SJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>办公电话</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(BGSDH)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>传真</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(CZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>电子邮箱</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(EMAIL)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>主页网址</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZYWZ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>政治面貌</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(ZZMM)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>入党时间</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(RDSJ)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>党总支/分党委</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DZZ)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>下一级党组织</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+(DZZ2)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>党支部</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DZB)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>党费基数</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DFJS)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>党内职务</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' style='word-break:break-all'>"+b1+(DNZW)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   sb.append("<tr bgcolor='#ffffff'>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>职务名称</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+(XZZW)+b2+"</td>\n");
		   sb.append("<td width='9%' align='center' style='word-break:break-all' background='../aws_img/report-bg-blue2.gif'>职务级别</td>\n");
		   sb.append("<td"+bg+" align='left' width='14%' colspan='3' style='word-break:break-all'>"+b1+(XZZWJB)+b2+"</td>\n");
		   sb.append("</tr>\n");
		   
		   hashTags.put("sid",super.getSIDFlag());
		   hashTags.put("awsuid",awsuid);
		   hashTags.put("list",sb.toString());
		}catch(Exception e){
			e.printStackTrace(System.err);
			return "#个人基本信息_数据查询异常";
		}finally{
			DBSql.close(conn, ps, rs);
		}
		return RepleaseKey.replace(HtmlModelFactory.getHtmlModel("BNU_HR_JBXX.html"),hashTags);
	}
	
}

