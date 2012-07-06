package bnu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.loader.core.WorkFlowStepButtonClickRTClassA;
import com.actionsoft.sdk.local.level0.BOInstanceAPI;

public class DownloadDocExample extends WorkFlowStepButtonClickRTClassA {

	public DownloadDocExample(UserContext uc){		
		super(uc);
		super.setProvider("zhuz");
		super.setDescription("将表单内容下载为doc文件");
	}
	
	@Override
	public String onClick() {
		int bindid = super.getParameter(super.PARAMETER_INSTANCE_ID).toInt();
		
		String NEIR11 = "";
		String NEIR12 = "";
		String NEIR13 = "";
		String NEIR14 = "";
		String NEIR15 = "";
		String NEIR16 = "";
		String NEIR21 = "";
		String NEIR22 = "";
		String NEIR23 = "";
		String NEIR24 = "";
		String NEIR25 = "";
		String NEIR31 = "";
		String NEIR32 = "";
		String NEIR33 = "";
		String NEIR34 = "";
		String NEIR41 = "";
		String NEIR42 = "";
		
		String CHENGX1 = "";
		String CHENGX2 = "";
		String CHENGX3 = "";
		String CHENGX4 = "";
		String CHENGX5 = "";
		String CHENGX6 = "";
		
		String XMMC = "";
		
		Hashtable BO_BNUOA_PINGSH_985=BOInstanceAPI.getInstance().getBOData("BO_BNUOA_PINGSH_985", bindid);
		if(BO_BNUOA_PINGSH_985!=null) {
			NEIR11 = BO_BNUOA_PINGSH_985.get("NEIR11").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR11").toString();
			NEIR12 = BO_BNUOA_PINGSH_985.get("NEIR12").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR12").toString();
			NEIR13 = BO_BNUOA_PINGSH_985.get("NEIR13").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR13").toString();
			NEIR14 = BO_BNUOA_PINGSH_985.get("NEIR14").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR14").toString();
			NEIR15 = BO_BNUOA_PINGSH_985.get("NEIR15").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR15").toString();
			NEIR16 = BO_BNUOA_PINGSH_985.get("NEIR16").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR16").toString();
			NEIR21 = BO_BNUOA_PINGSH_985.get("NEIR21").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR21").toString();
			NEIR22 = BO_BNUOA_PINGSH_985.get("NEIR22").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR22").toString();
			NEIR23 = BO_BNUOA_PINGSH_985.get("NEIR23").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR23").toString();
			NEIR24 = BO_BNUOA_PINGSH_985.get("NEIR24").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR24").toString();
			NEIR25 = BO_BNUOA_PINGSH_985.get("NEIR25").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR25").toString();
			NEIR31 = BO_BNUOA_PINGSH_985.get("NEIR31").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR31").toString();
			NEIR32 = BO_BNUOA_PINGSH_985.get("NEIR32").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR32").toString();
			NEIR33 = BO_BNUOA_PINGSH_985.get("NEIR33").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR33").toString();
			NEIR34 = BO_BNUOA_PINGSH_985.get("NEIR34").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR34").toString();
			NEIR41 = BO_BNUOA_PINGSH_985.get("NEIR41").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR41").toString();
			NEIR42 = BO_BNUOA_PINGSH_985.get("NEIR42").toString()==null?"":BO_BNUOA_PINGSH_985.get("NEIR42").toString();
			
			CHENGX1 = BO_BNUOA_PINGSH_985.get("CHENGX1").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX1").toString();
			CHENGX2 = BO_BNUOA_PINGSH_985.get("CHENGX2").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX2").toString();
			CHENGX3 = BO_BNUOA_PINGSH_985.get("CHENGX3").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX3").toString();
			CHENGX4 = BO_BNUOA_PINGSH_985.get("CHENGX4").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX4").toString();
			CHENGX5 = BO_BNUOA_PINGSH_985.get("CHENGX5").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX5").toString();
			CHENGX6 = BO_BNUOA_PINGSH_985.get("CHENGX6").toString()==null?"":BO_BNUOA_PINGSH_985.get("CHENGX6").toString();
			
			XMMC = BO_BNUOA_PINGSH_985.get("XMMC").toString()==null?"":BO_BNUOA_PINGSH_985.get("XMMC").toString();
		}
		String content = 
		"一、本年度建设内容：\r\n"+
		"1，人才培养改革与发展计划：\r\n"+
		  "1.1 人才培养体系改革：\r\n"+
		  	NEIR11+"\r\n"+
		 "1.2 国家级教学团队建设：\r\n"+
		 NEIR12+"\r\n"+
		 "1.3 学校公共课教研建设与支持项目：\r\n"+
		 NEIR13+"\r\n"+
		 "1.4 精品课程与精品教材培育计划：\r\n"+
		 NEIR14+"\r\n"+
		 "1.5 研究生核心课程建设计划：\r\n"+
		 NEIR15+"\r\n"+
		 "1.6 国际教育学位开发计划：\r\n"+
		 NEIR16+"\r\n"+
		 "－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\r\n"+
		 "2、教育科研能力建设和项目开发计划：\r\n"+
		 "2.1 教师科研能力建设：\r\n"+
		 NEIR21+"\r\n"+
		 "2.2 学生科研能力建设：\r\n"+
		 NEIR22+"\r\n"+
		 "2.3 信息化与数据库建设：\r\n"+
		 NEIR23+"\r\n"+
		 "2.4 实验室建设：\r\n"+
		 NEIR24+"\r\n"+
		 "2.5 《规划纲要》重点项目自主研究计划：\r\n"+
		 NEIR25+"\r\n"+
		" －－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\r\n"+
		 "3、国际交流与发展计划：\r\n"+
		 "3.1 教师流动建设：\r\n"+
		 NEIR31+"\r\n"+
		 "3.2 学生流动计划：\r\n"+
		 NEIR32+"\r\n"+
		 "3.3 国际学术交流平台建设计划：\r\n"+
		 NEIR33+"\r\n"+
		 "3.4 学术成果移介计划：\r\n"+
		 NEIR34+"\r\n"+
		" －－－－－－－－－－－－－－－－－－－－－－－－－－－－－－\r\n"+
		 "4、教育学部基础建设计划：\r\n"+
		 "4.1 基建工程：\r\n"+
		 NEIR41+"\r\n"+
		 "4.2 数字博物馆建设：\r\n"+
		 NEIR42+"\r\n"+


		 "二、年度预期建设成效：\r\n"+
		 "1，人才培养：\r\n"+
		 CHENGX1+"\r\n"+
		 "2，科学研究：\r\n"+
		 CHENGX2+"\r\n"+
		 "3，队伍建设：\r\n"+
		 CHENGX3+"\r\n"+
		 "4，国际交流与合作：\r\n"+
		 CHENGX4+"\r\n"+
		 "5，实验室建设：\r\n"+
		 CHENGX5+"\r\n"+
		 "6，办公条件改善：\r\n"+
		 CHENGX6;
		
		String targetDir = "../webserver\\webapps\\portal\\pingshen";
		String fileName = "";
		String zipFileName = XMMC + ".zip";;
		fileName = XMMC + ".doc";
		String targetZipFile = targetDir + zipFileName;
		File fileDir = new File(targetDir);
		//如果存在该文件夹，遍历删除现有文件夹的文件
		if (fileDir.canRead()) {
			String[] fileList = fileDir.list();
			for (int j = 0; j < fileList.length; j++) {
				File f = new File(fileDir.getAbsolutePath() + "\\"+fileList[j]);
				f.delete();
			}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "此项目文档下载地址为：</br><a href='http://"+ip+"/portal/pingshen/"+XMMC+".doc'>"+XMMC+".doc</a>";
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
}