package bnu.socketcommand;

import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import bnu.managerWeb.BnuManagerCghjWeb;
import bnu.managerWeb.BnuManagerGjhqkWeb;
import bnu.managerWeb.BnuManagerGjwxxjlWeb;
import bnu.managerWeb.BnuManagerGnxxjlWeb;
import bnu.managerWeb.BnuManagerGzjlWeb;
import bnu.managerWeb.BnuManagerJgxmWeb;
import bnu.managerWeb.BnuManagerJtcyWeb;
import bnu.managerWeb.BnuManagerJxhjWeb;
import bnu.managerWeb.BnuManagerKyxmWeb;
import bnu.managerWeb.BnuManagerMainWeb;
import bnu.managerWeb.BnuManagerPxjlWeb;
import bnu.managerWeb.BnuManagerQtcgWeb;
import bnu.managerWeb.BnuManagerRkqkWeb;
import bnu.managerWeb.BnuManagerXslwWeb;
import bnu.managerWeb.BnuManagerXszzWeb;
import bnu.managerWeb.BnuManagerYjbgWeb;
import bnu.managerWeb.BnuManagerZdxsWeb;
import bnu.managerWeb.BnuManagerZdzyWeb;
import bnu.managerWeb.BnuManagerZlxxWeb;
import bnu.web.BnuHRCghjWeb;
import bnu.web.BnuHRGjhqkWeb;
import bnu.web.BnuHRGjwxxjlWeb;
import bnu.web.BnuHRGnxxjlWeb;
import bnu.web.BnuHRGzjlWeb;
import bnu.web.BnuHRJbxxWeb;
import bnu.web.BnuHRJgxmWeb;
import bnu.web.BnuHRJtcyWeb;
import bnu.web.BnuHRJxhjWeb;
import bnu.web.BnuHRKyxmWeb;
import bnu.web.BnuHRPxjlWeb;
import bnu.web.BnuHRQtcgWeb;
import bnu.web.BnuHRRkqkWeb;
import bnu.web.BnuHRXslwWeb;
import bnu.web.BnuHRXszzWeb;
import bnu.web.BnuHRYjbgWeb;
import bnu.web.BnuHRZdxsWeb;
import bnu.web.BnuHRZdzyWeb;
import bnu.web.BnuHRZlxxWeb;
import bnu.web.BnuMainWeb;
import bnu.web.BnuReportWeb;
import bnu.web.BnuSelectAjaxWeb;

import com.actionsoft.application.server.BaseSocketCommand;
import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.awf.util.UtilCode;
import com.actionsoft.awf.util.UtilString;

/*

各模块流程ID：
名称                                                   流程组
12880   基本信息_填报                                基本信息填报组    
12777   教育经历_国内学习经历_填报                    基本信息统计流程组
12797	教育经历_国境外学习_填报
12802	教育经历_培训经历_填报
12807	工作经历_填报
12812	人才培养_任课情况_填报
12817	人才培养_指导专业_填报
12822	人才培养_指导学生_填报
12827	人才培养_教改项目_填报      share Data
12832	人才培养_教学获奖_填报      share Data
12783   科研情况_科研项目_填报      share Data
12837	科研情况_学术论文_填报      share Data
12843	科研情况_学术著作_填报      share Data
12849	科研情况_专利信息_填报      share Data
12855	科研情况_其他成果_填报      share Data
12862	科研情况_成果获奖_填报      share Data
12870	国际化情况_填报
12875	家庭成员_填报
*/

public class BnuSocketCommand implements BaseSocketCommand {
	
	public final int lineNumber = 15;//每页显示行数
	
	public boolean executeCommand(UserContext me, Socket myProcessSocket, OutputStreamWriter myOut, Vector myCmdArray, UtilString myStr, String socketCmd) throws Exception {
//		System.out.println("#######################socketCmd="+socketCmd);
		
		if (socketCmd.equals("")) {
			System.out.println("socketCmd NULL");
		}
		
		//创建流程实例.
//		else if (socketCmd.equals("BNU_CreateFlow_Gnxxjl")) { //教育经历_国内学习经历
//			BnuCreateFlowWeb web = new BnuCreateFlowWeb(me);
//			String awsuid = myCmdArray.elementAt(3).toString();
//			myOut.write(web.startupFlow(awsuid, "gnxxjl"));
//			web = null;
//		} 
//		else if (socketCmd.equals("BNU_CreateFlow_Kyxm")) { //科研情况_科研项目
//			BnuCreateFlowWeb web = new BnuCreateFlowWeb(me);
//			String awsuid = myCmdArray.elementAt(3).toString();
//			myOut.write(web.startupFlow(awsuid, "kyxm"));
//			web = null;
//		} 
		
		// Bnu 个人基本信息。首页
		else if (socketCmd.equals("BNU_HR_MAIN")) {
			String pageType = myCmdArray.elementAt(3).toString();
			if (pageType == null || pageType.equals("")) {
				pageType = "1";
			}
			String awsuid = me.getUID();//AWS员工帐号
			BnuMainWeb web = new BnuMainWeb(me);
			myOut.write(web.getMain(pageType,awsuid));
			web = null;
		}
		
		//上传个人照片
		else if (socketCmd.equals("BNU_HR_Attach_Upload")) {
		}
		
		//基本信息
		else if (socketCmd.equals("BNU_HR_JBXX")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			BnuHRJbxxWeb web = new BnuHRJbxxWeb(me);
			myOut.write(web.getReport(awsuid));
			web = null;
		}
		
		//教育经历_国内学习
		else if (socketCmd.equals("BNU_HR_GNXXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRGnxxjlWeb web = new BnuHRGnxxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_GNXXJL_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_GNXXJL_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRGnxxjlWeb web = new BnuHRGnxxjlWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		
		//教育经历_国境外学习
		else if (socketCmd.equals("BNU_HR_GJWXXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRGjwxxjlWeb web = new BnuHRGjwxxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_GJWXXJL_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_GJWXXJL_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRGjwxxjlWeb web = new BnuHRGjwxxjlWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		
		//教育经历_培训经历
		else if (socketCmd.equals("BNU_HR_PXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRPxjlWeb web = new BnuHRPxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_PXJL_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_PXJL_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRPxjlWeb web = new BnuHRPxjlWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}		
		
		//工作经历
		else if (socketCmd.equals("BNU_HR_GZJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRGzjlWeb web = new BnuHRGzjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_GZJL_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_GZJL_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRGzjlWeb web = new BnuHRGzjlWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		
		//人才培养_任课情况
		else if (socketCmd.equals("BNU_HR_RKQK")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRRkqkWeb web = new BnuHRRkqkWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_RKQK_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_RKQK_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRRkqkWeb web = new BnuHRRkqkWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}

		//人才培养_指导专业
		else if (socketCmd.equals("BNU_HR_ZDZY")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRZdzyWeb web = new BnuHRZdzyWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_ZDZY_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZDZY_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRZdzyWeb web = new BnuHRZdzyWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		
		//人才培养_指导学生
		else if (socketCmd.equals("BNU_HR_ZDXS")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRZdxsWeb web = new BnuHRZdxsWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_ZDXS_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZDXS_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRZdxsWeb web = new BnuHRZdxsWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		
		//人才培养_教改项目
		else if (socketCmd.equals("BNU_HR_JGXM")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRJgxmWeb web = new BnuHRJgxmWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JGXM_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_JGXM_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRJgxmWeb web = new BnuHRJgxmWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JGXM_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_JGXM_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRJgxmWeb web = new BnuHRJgxmWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JGXM_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRJgxmWeb web = new BnuHRJgxmWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		

		//人才培养_教学获奖
		else if (socketCmd.equals("BNU_HR_JXHJ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRJxhjWeb web = new BnuHRJxhjWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JXHJ_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_JXHJ_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRJxhjWeb web = new BnuHRJxhjWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JXHJ_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_JXHJ_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRJxhjWeb web = new BnuHRJxhjWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JXHJ_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRJxhjWeb web = new BnuHRJxhjWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_科研项目
		else if (socketCmd.equals("BNU_HR_KYXM")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRKyxmWeb web = new BnuHRKyxmWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_KYXM_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_KYXM_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRKyxmWeb web = new BnuHRKyxmWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_KYXM_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_KYXM_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRKyxmWeb web = new BnuHRKyxmWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_KYXM_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRKyxmWeb web = new BnuHRKyxmWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_学术论文
		else if (socketCmd.equals("BNU_HR_XSLW")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRXslwWeb web = new BnuHRXslwWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSLW_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSLW_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRXslwWeb web = new BnuHRXslwWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSLW_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSLW_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRXslwWeb web = new BnuHRXslwWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSLW_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRXslwWeb web = new BnuHRXslwWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_研究报告
		else if (socketCmd.equals("BNU_HR_YJBG")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRYjbgWeb web = new BnuHRYjbgWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_YJBG_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSLW_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRYjbgWeb web = new BnuHRYjbgWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_YJBG_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSLW_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRYjbgWeb web = new BnuHRYjbgWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_YJBG_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRYjbgWeb web = new BnuHRYjbgWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_学术著作
		else if (socketCmd.equals("BNU_HR_XSZZ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRXszzWeb web = new BnuHRXszzWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSZZ_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSZZ_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRXszzWeb web = new BnuHRXszzWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSZZ_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_XSZZ_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRXszzWeb web = new BnuHRXszzWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_XSZZ_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRXszzWeb web = new BnuHRXszzWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_专利信息
		else if (socketCmd.equals("BNU_HR_ZLXX")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRZlxxWeb web = new BnuHRZlxxWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_ZLXX_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZLXX_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRZlxxWeb web = new BnuHRZlxxWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_ZLXX_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZLXX_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRZlxxWeb web = new BnuHRZlxxWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_ZLXX_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRZlxxWeb web = new BnuHRZlxxWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//科研情况_其他成果
		else if (socketCmd.equals("BNU_HR_QTCG")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRQtcgWeb web = new BnuHRQtcgWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_QTCG_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_QTCG_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRQtcgWeb web = new BnuHRQtcgWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_QTCG_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_QTCG_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRQtcgWeb web = new BnuHRQtcgWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_QTCG_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRQtcgWeb web = new BnuHRQtcgWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}

		//科研情况_成果获奖
		else if (socketCmd.equals("BNU_HR_CGHJ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRCghjWeb web = new BnuHRCghjWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_CGHJ_ViewDetail")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZLXX_ViewDetail#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRCghjWeb web = new BnuHRCghjWeb(me);
			myOut.write(web.viewDetail(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_CGHJ_Delete")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
//			System.out.println("BNU_HR_ZLXX_Delete#######################awsuid="+awsuid+",dataId="+dataId);
			BnuHRCghjWeb web = new BnuHRCghjWeb(me);
			myOut.write(web.deleteData(awsuid, dataId));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_CGHJ_Unlock")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			
			String dataId = UtilCode.decode(myStr.matchValue("_dataId[", "]dataId_"));
			BnuHRCghjWeb web = new BnuHRCghjWeb(me);
			myOut.write(web.unlockData(awsuid, dataId));
			web = null;
		}
		
		//国际化情况
		else if (socketCmd.equals("BNU_HR_GJHQK")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRGjhqkWeb web = new BnuHRGjhqkWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_GJHQK_Delete")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRGjhqkWeb web = new BnuHRGjhqkWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//家庭成员
		else if (socketCmd.equals("BNU_HR_JTCY")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRJtcyWeb web = new BnuHRJtcyWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_HR_JTCY_Delete")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuHRJtcyWeb web = new BnuHRJtcyWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		
		/*****************下拉列表Ajax****************/
		//籍贯 OK
		else if (socketCmd.equals("BNU_AJAX_JG")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "JG";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//现职称
		else if (socketCmd.equals("BNU_AJAX_JSZW")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "JSZW";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//岗位类别
		else if (socketCmd.equals("BNU_AJAX_GWLB")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "GWLB";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//学科方向 OK
		else if (socketCmd.equals("BNU_AJAX_SSXK")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "SSXK";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//最终学位
		else if (socketCmd.equals("BNU_AJAX_ZGXW")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "ZGXW";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//党支部 OK
		else if (socketCmd.equals("BNU_AJAX_DZZ")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "DZB";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		//课程类别（教学类别）
		else if (socketCmd.equals("BNU_AJAX_KCLB")) {
			BnuSelectAjaxWeb web =new BnuSelectAjaxWeb(me);
			String input= UtilCode.decode(myStr.matchValue("_INPUT[","]INPUT_"));//传入参数 
			String type = "KCLB";
			myOut.write(web.getResult(input, type)); 
			web=null;
		}
		/*****************下拉列表Ajax****************/
		
		
		/*******************自定义报表*******************/
		else if (socketCmd.equals("BNU_HR_REPORT")) {
			BnuReportWeb web =new BnuReportWeb(me);
			String pageNow = myCmdArray.elementAt(3).toString();
//			String awsuid = myCmdArray.elementAt(4).toString();
			if(pageNow.equals("")||pageNow==null)
				pageNow="1";			
			myOut.write(web.getResult(socketCmd, Integer.parseInt(pageNow)));
			web = null;
		}
		
		/*******************后台数据管理页面*******************/
		//后台管理员数据修改页面
		else if (socketCmd.equals("BNU_MANAGER_MAIN")) {
			String pageType = myCmdArray.elementAt(3).toString();
			if (pageType == null || pageType.equals("")) {
				pageType = "2";
			}
			String awsuid = me.getUID();//AWS员工帐号
			BnuManagerMainWeb web = new BnuManagerMainWeb(me);
			myOut.write(web.getMain(pageType,awsuid));
			web = null;
		}
		
		//教育经历_国内学习
		else if (socketCmd.equals("BNU_MANAGER_GNXXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerGnxxjlWeb web = new BnuManagerGnxxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//教育经历_国境外学习
		else if (socketCmd.equals("BNU_MANAGER_GJWXXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerGjwxxjlWeb web = new BnuManagerGjwxxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//教育经历_培训经历
		else if (socketCmd.equals("BNU_MANAGER_PXJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerPxjlWeb web = new BnuManagerPxjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//工作经历
		else if (socketCmd.equals("BNU_MANAGER_GZJL")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerGzjlWeb web = new BnuManagerGzjlWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//人才培养_任课情况
		else if (socketCmd.equals("BNU_MANAGER_RKQK")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerRkqkWeb web = new BnuManagerRkqkWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}

		//人才培养_指导专业
		else if (socketCmd.equals("BNU_MANAGER_ZDZY")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerZdzyWeb web = new BnuManagerZdzyWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//人才培养_指导学生
		else if (socketCmd.equals("BNU_MANAGER_ZDXS")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerZdxsWeb web = new BnuManagerZdxsWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//人才培养_教改项目
		else if (socketCmd.equals("BNU_MANAGER_JGXM")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerJgxmWeb web = new BnuManagerJgxmWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}

		//人才培养_教学获奖
		else if (socketCmd.equals("BNU_MANAGER_JXHJ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerJxhjWeb web = new BnuManagerJxhjWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_科研项目
		else if (socketCmd.equals("BNU_MANAGER_KYXM")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerKyxmWeb web = new BnuManagerKyxmWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_学术论文
		else if (socketCmd.equals("BNU_MANAGER_XSLW")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerXslwWeb web = new BnuManagerXslwWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_研究报告
		else if (socketCmd.equals("BNU_MANAGER_YJBG")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerYjbgWeb web = new BnuManagerYjbgWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_学术著作
		else if (socketCmd.equals("BNU_MANAGER_XSZZ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerXszzWeb web = new BnuManagerXszzWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_专利信息
		else if (socketCmd.equals("BNU_MANAGER_ZLXX")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerZlxxWeb web = new BnuManagerZlxxWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//科研情况_其他成果
		else if (socketCmd.equals("BNU_MANAGER_QTCG")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerQtcgWeb web = new BnuManagerQtcgWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}

		//科研情况_成果获奖
		else if (socketCmd.equals("BNU_MANAGER_CGHJ")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerCghjWeb web = new BnuManagerCghjWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//国际化情况
		else if (socketCmd.equals("BNU_MANAGER_GJHQK")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerGjhqkWeb web = new BnuManagerGjhqkWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		//家庭成员
		else if (socketCmd.equals("BNU_MANAGER_JTCY")) {
			String pageNow = myCmdArray.elementAt(3).toString();
			String awsuid = myCmdArray.elementAt(4).toString();
			BnuManagerJtcyWeb web = new BnuManagerJtcyWeb(me);
			if(pageNow==null || pageNow.length()==0)
				pageNow="1";
			myOut.write(web.getReport(Integer.parseInt(pageNow),lineNumber,awsuid));
			web = null;
		}
		
		else if (socketCmd.equals("BNU_MANAGER_BACKDOOR_SQL_WIN")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			BnuManagerMainWeb web = new BnuManagerMainWeb(me);
			myOut.write(web.showWin(awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_MANAGER_BACKDOOR_SQL_SAVE")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			String sqlString = UtilCode.decode(myStr.matchValue("_sqlString[", "]sqlString_"));;//sql语句
			BnuManagerMainWeb web = new BnuManagerMainWeb(me);
			myOut.write(web.executeSql(awsuid, sqlString));
			web = null;
		}
		/********************后台数据管理页面 end*******************/
		
		/*******************重新计算分值操作页面*******************/
		else if (socketCmd.equals("BNU_RECALCULATE_MAIN")) {
			String pageType = myCmdArray.elementAt(3).toString();
			if (pageType == null || pageType.equals("")) {
				pageType = "0";
			}
			String awsuid = me.getUID();//AWS员工帐号
			BnuReCalculateWeb web = new BnuReCalculateWeb(me);
			myOut.write(web.getMain(pageType,awsuid));
			web = null;
		}
		else if (socketCmd.equals("BNU_RECALCULATE_PROCESS")) {
			String awsuid = myCmdArray.elementAt(3).toString();
			String type = myCmdArray.elementAt(4).toString(); //实际为type。借用字段名
			BnuReCalculateWeb web = new BnuReCalculateWeb(me);
			myOut.write(web.reCalculate(awsuid, type));
			web = null;
		}
		/*******************重新计算分值操作页面 end*******************/
		
		/*******************个人简历导出页面*******************/
		else if (socketCmd.equals("BNU_RESUMEDOC_MAIN")) {
			String pageType = myCmdArray.elementAt(3).toString();
			if (pageType == null || pageType.equals("")) {
				pageType = "NOTHING";
			}
			String option = myCmdArray.elementAt(4).toString();
			String awsuid = me.getUID();//AWS员工帐号
			
			BnuResumeDocWeb web = new BnuResumeDocWeb(me);
			myOut.write(web.getDownloadMain(pageType,awsuid, option));
			web = null;
		}
		else if (socketCmd.equals("BNU_RESUMEDOC_DOWNLOAD")) {
			String awsuid = myCmdArray.elementAt(3).toString();  //暂存option值
//			String option = myCmdArray.elementAt(4).toString();
			String awsuid2 = me.getUID();//AWS员工帐号
			
			System.out.println("BNU_RESUMEDOC_DOWNLOAD; awsuid="+awsuid2+",option="+awsuid);
			BnuResumeDocWeb web = new BnuResumeDocWeb(me);
			myOut.write(web.getDownloadDoc(awsuid2, awsuid));
			web = null;
		}
		/*******************个人简历导出页面 end*******************/
		
		else {
			return false;
		}
		return true;
	}
	
}
