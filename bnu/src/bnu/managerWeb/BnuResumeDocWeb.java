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
			return "��ѡ������������ݣ�";
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
		
		String docName = awsuid+"_��ʦ���˼���_"+getTimestamp(); //����Ψһ

		String content = "��ʦ���˼��� \r\n \r\n \r\n";
		for(int i=0; i<ouputAreaList.size(); i++) {
			String optionNm = (String)ouputAreaList.get(i);
			content = content + getConent(awsuid, optionNm);
		}
		
		String targetDir = "";
		targetDir = "../webserver\\webapps\\fe\\techerResume"; //��ʽ����������
//		targetDir = "../webserver\\webapps\\portal\\techerResume"; //���Է���������
		String fileName = "";
		String zipFileName = docName + ".zip";;
		fileName = docName + ".doc";
		String targetZipFile = targetDir + zipFileName;
		File fileDir = new File(targetDir);
		//������ڸ��ļ��У�����ɾ�������ļ��е��ļ�
		if (fileDir.canRead()) {
//			String[] fileList = fileDir.list();
//			for (int j = 0; j < fileList.length; j++) {
//				File f = new File(fileDir.getAbsolutePath() + "\\"+fileList[j]);
//				f.delete();
//			}
		} else {
			fileDir.mkdirs();
		}
		
		//�����ֽ���д�ļ�
		writeFile(content.getBytes(), targetDir, fileName);
		
		InetAddress addr;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();//��ñ���IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
//		return "����Ŀ�ĵ����ص�ַΪ��</br><a href='http://"+ip+"/portal/techerResume/"+docName+".doc'>"+docName+".doc</a>"; //���Է�����
		return "����Ŀ�ĵ����ص�ַΪ��</br><a href='http://"+ip+"/fe/techerResume/"+docName+".doc'>"+docName+".doc</a>"; //��ʽ����������
	}

	private String writeFile(byte[] fileBytes, String dir, String fileName) {
		String fullFileName = dir + "\\"+fileName;
		try {
			byte tag_bytes[] = fileBytes;
			FileOutputStream fileoutputstream = new FileOutputStream(fullFileName);// �����ļ������
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return "-1";
		}
		return fullFileName;
	}
	
	/**
	 * ����ǰʱ���ת�������ݿ��Ӧλ��
	 * 
	 * @return 10λʱ�������ȷ����
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
		dbNameMap.put("RS", "�� ���˻�����Ϣ");
		dbNameMap.put("XSLW", "�� �������_ѧ������");
		dbNameMap.put("XSZZ", "�� �������_ѧ������");
		dbNameMap.put("KYXM", "�� �������_������Ŀ");
		dbNameMap.put("YJBG", "�� �������_�о�����");
		dbNameMap.put("CGHJ", "�� �������_���л�");//
		dbNameMap.put("ZLXX", "�� �������_ר����Ϣ");
		dbNameMap.put("QTCG", "�� �������_�����ɹ�");
		dbNameMap.put("RKQK", "�� �˲�����_�ο����");
		dbNameMap.put("ZDXS", "�� �˲�����_ָ��ѧ��");
		dbNameMap.put("JGXM", "�� �˲�����_�̸���Ŀ");
		dbNameMap.put("JXHJ", "�� �˲�����_��ѧ��");
		dbNameMap.put("GZJL", "�� ��������");
		dbNameMap.put("ZDZY", "�� �˲�����_ָ��רҵ");
		dbNameMap.put("GNXXJL", "�� ��������_����ѧϰ");
		dbNameMap.put("GJWXXJL", "�� ��������_������ѧϰ");
		dbNameMap.put("PXJL", "�� ��������_��ѵ����");
		dbNameMap.put("GJHQK", "�� ���ʻ����");
		dbNameMap.put("JTCY", "�� ��ͥ��Ա");
		
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
					
					content.append("������"+XM+"\r\n");
					content.append("���ڵ�λ��"+BMMC+"\r\n");
					content.append("�� ְ �ƣ�"+JSZW+" "+JSZW2+"\r\n");
					content.append("ѧ�Ʒ���"+SSXK+" "+SSXK2+"\r\n");
					content.append("��λ���"+GWLB+" "+GWLB2+"\r\n");
					if(!ZGQK.equals("")) {
						content.append("�ڸ������"+ZGQK+"\r\n");
					}
					if(!XXLB.equals("")) {
						content.append("ѧԵ��"+XXLB+"\r\n");
					}
					if(!ZGXW.equals("")) {
						content.append("����ѧλ��"+ZGXW+"\r\n");
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
						CHARGENM = rs.getString("APPLICANT")==null?"":rs.getString("APPLICANT").trim(); // ������
					} else if(option.equals("CGHJ")) {
						CHARGENM = rs.getString("MAN")==null?"":rs.getString("MAN").trim(); // ������
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
					String workPositon = "�������ִ���Ŀ��";
					if (AUTOADD == 1) {
						workPositon = "���˲������Ŀ��";
					}
					
					String datayear = rs.getString("DATAYEAR")==null?"":rs.getString("DATAYEAR").trim(); //<�������>
					
					String workName = "";
					if(option.equals("XSLW")) {
						workName = rs.getString("LWMC")==null?"":rs.getString("LWMC").trim(); // ��������
						String lwlx = rs.getString("LWLX")==null?"":rs.getString("LWLX").trim(); //��������
						String ktly = rs.getString("KTLY")==null?"":rs.getString("KTLY").trim(); //<������Ŀ>
						String kwlb = rs.getString("KWLB")==null?"":rs.getString("KWLB").trim(); //<�������>
						
						content.append(num+". ");
						content.append("�������ƣ�"+workName+"���������ͣ�"+lwlx+"��������Ŀ��"+ktly+"���������"+kwlb+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("XSZZ")) {
						workName = rs.getString("ZZMC")==null?"":rs.getString("ZZMC").trim();
						String xmly = rs.getString("XMLY")==null?"":rs.getString("XMLY").trim(); //XMLY<������Ŀ>
						String lzlb = rs.getString("LZLB")==null?"":rs.getString("LZLB").trim(); //LZLB<�������>
						String cbrq = rs.getString("CBRQ")==null?"":rs.getString("CBRQ").trim(); //CBRQ<��������>
						if(cbrq!=null && cbrq.length() >= 10) {
							cbrq = cbrq.substring(0,10);
						}
						 
						content.append(num+". ");
						content.append("�������ƣ�"+workName+"��������Ŀ��"+xmly+"���������"+lzlb+"���������ڣ�"+cbrq+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("KYXM")) {
						workName = rs.getString("XMMC")==null?"":rs.getString("XMMC").trim();
						String xmjb = rs.getString("XMJB")==null?"":rs.getString("XMJB").trim(); //XMJB<������Ŀ>
						
						content.append(num+". ");
						content.append("��Ŀ���ƣ�"+workName+"��������Ŀ��"+xmjb+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("YJBG")) {
						workName = rs.getString("CGMC")==null?"":rs.getString("CGMC").trim();
						String cglx = rs.getString("CGLX")==null?"":rs.getString("CGLX").trim(); //CGLX<�ɹ�����>
						String rdrq = rs.getString("RDRQ")==null?"":rs.getString("RDRQ").trim(); //RDRQ<�϶�����>
						String sfcn = rs.getString("SFCN")==null?"":rs.getString("SFCN").trim(); //SFCN<�Ƿ�˾�����ϲ���>
						if(rdrq!=null && rdrq.length() >= 10) {
							rdrq = rdrq.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("�о����棺"+workName+"���ɹ����ͣ�"+cglx+"���϶����ڣ�"+rdrq+"���Ƿ�˾�����ϲ��ɣ�"+sfcn+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("CGHJ")) {
						workName = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME").trim();
						String awarddate = rs.getString("AWARDDATE")==null?"":rs.getString("AWARDDATE").trim(); //AWARDDATE<��������>
						if(awarddate!=null && awarddate.length() >= 10) {
							awarddate = awarddate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("�������ƣ�"+workName+"���������ڣ�"+awarddate+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("ZLXX")) {
						workName = rs.getString("PATENTNAME")==null?"":rs.getString("PATENTNAME").trim();
						String authorizedate = rs.getString("AUTHORIZEDATE")==null?"":rs.getString("AUTHORIZEDATE").trim(); //AUTHORIZEDATE<��Ȩ����>
						if(authorizedate!=null && authorizedate.length() >= 10) {
							authorizedate = authorizedate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("�������ƣ�"+workName+"����Ȩ���ڣ�"+authorizedate+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					} else if(option.equals("QTCG")) {
						workName = rs.getString("HAVNAME")==null?"":rs.getString("HAVNAME").trim();
						String havkindsymbol = rs.getString("HAVKINDSYMBOL")==null?"":rs.getString("HAVKINDSYMBOL").trim(); //HAVKINDSYMBOL<�ɹ�����>
						String appraisedate = rs.getString("APPRAISEDATE")==null?"":rs.getString("APPRAISEDATE").trim(); //APPRAISEDATE<�������>
						if(appraisedate!=null && appraisedate.length() >= 10) {
							appraisedate = appraisedate.substring(0,10);
						}
						
						content.append(num+". ");
						content.append("�������ƣ�"+workName+"���ɹ����ͣ�"+havkindsymbol+"��������ڣ�"+appraisedate+"��������ȣ�"+datayear+"\r\n");
						content.append("���ߣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
					}
				}
			} else if(option.equals("RKQK")) {
				sql = "select * from BO_RKQKTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String KCMC = rs.getString("KCMC")==null?"":rs.getString("KCMC").trim();  //�γ���
					String KCLB = rs.getString("KCLB")==null?"":rs.getString("KCLB"); //��ѧ���
					String SKDX = rs.getString("SKDX")==null?"":rs.getString("SKDX"); //�ڿζ���
					content.append(num+". ");
					content.append(KCMC+"�� ��ѧ���"+KCLB+"���ڿζ���"+SKDX+"\r\n");
				}
			} else if(option.equals("ZDXS")) {
				sql = "select * from BO_ZDXSTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String XM = rs.getString("XM")==null?"":rs.getString("XM"); //ѧ������
					String XY = rs.getString("XY")==null?"":rs.getString("XY"); //ѧԺ����
					String ZY = rs.getString("ZY")==null?"":rs.getString("ZY"); //רҵ����
					content.append(num+". ");
					content.append(XM+"�� ѧԺ���ƣ�"+XY+"��רҵ���ƣ�"+ZY+"\r\n");
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
					String XMMC = rs.getString("XMMC")==null?"":rs.getString("XMMC"); // ��Ŀ����
					String CHARGENM = rs.getString("ZCR")==null?"":rs.getString("ZCR").trim(); 
					String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1").trim(); 
					String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2").trim(); 
					String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3").trim(); 
					String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4").trim(); 
					String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5").trim(); 
					int AUTOADD = 0; 
					AUTOADD = rs.getInt("AUTOADD");
					String workPositon = "�������ִ���Ŀ��";
					if (AUTOADD == 1) {
						workPositon = "���˲������Ŀ��";
					}
					content.append(num+". ");
					content.append(XMMC+"�� �����ˣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
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
					String CGMC = rs.getString("CGMC")==null?"":rs.getString("CGMC"); //�ɹ�����
					String CHARGENM = rs.getString("FZR")==null?"":rs.getString("FZR").trim(); 
					String NAME1 = rs.getString("NAME1")==null?"":rs.getString("NAME1").trim(); 
					String NAME2 = rs.getString("NAME2")==null?"":rs.getString("NAME2").trim(); 
					String NAME3 = rs.getString("NAME3")==null?"":rs.getString("NAME3").trim(); 
					String NAME4 = rs.getString("NAME4")==null?"":rs.getString("NAME4").trim(); 
					String NAME5 = rs.getString("NAME5")==null?"":rs.getString("NAME5").trim(); 
					int AUTOADD = 0; 
					AUTOADD = rs.getInt("AUTOADD");
					String workPositon = "�������ִ���Ŀ��";
					if (AUTOADD == 1) {
						workPositon = "���˲������Ŀ��";
					}
					content.append(num+". ");
					content.append(CGMC+"�� �����ˣ�"+CHARGENM+" "+NAME1+" "+NAME2+" "+NAME3+" "+NAME4+" "+NAME5+"��"+workPositon+"��\r\n");
				}
			} else if(option.equals("GZJL")) {
				sql = "select * from BO_GZJLTEMP where 1=1 and CHARGEID='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String QSSJ = rs.getString("QSSJ")==null?"":rs.getString("QSSJ"); //��ʼʱ��
					String JSSJ = rs.getString("JSSJ")==null?"":rs.getString("JSSJ"); //����ʱ��
					String SZDW = rs.getString("SZDW")==null?"":rs.getString("SZDW"); //���ڵ�λ
					String GZZW = rs.getString("GZZW")==null?"":rs.getString("GZZW"); //����ְ��
					content.append(num+". ");
					content.append(QSSJ+" -- "+JSSJ+", ��'"+SZDW+"'��"+GZZW+"\r\n");
				}
			} else if(option.equals("ZDZY")) {
				sql = "select * from BO_ZDZYTEMP where 1=1 and DSBH='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String XYMC = rs.getString("XYMC")==null?"":rs.getString("XYMC"); //���ڵ�λ
					String DRSDRQ = rs.getString("DRSDRQ")==null?"":rs.getString("DRSDRQ"); //����˶������
					String DRBDRQ = rs.getString("DRBDRQ")==null?"":rs.getString("DRBDRQ"); //���β�������
					content.append(num+". ");
					if(!DRSDRQ.equals("")) {
						content.append(DRSDRQ+" ��"+XYMC+"����˶���� \r\n");
					}
					if(!DRBDRQ.equals("")) {
						content.append(DRBDRQ+" ��"+XYMC+"���β����� \r\n");
					}
				}
			} else if(option.equals("GNXXJL")) {
				sql = "select * from BO_GNXXJLTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String RXSJ = rs.getString("RXSJ")==null?"":rs.getString("RXSJ"); //��ѧʱ��
					String BYSJ = rs.getString("BYSJ")==null?"":rs.getString("BYSJ"); //��ҵʱ��
					String BYXX = rs.getString("BYXX")==null?"":rs.getString("BYXX"); //ѧУ/��λ
					String SXZY = rs.getString("SXZY")==null?"":rs.getString("SXZY"); //��ѧרҵ
					String YJFX = rs.getString("YJFX")==null?"":rs.getString("YJFX"); //�о�����
					String XW = rs.getString("XW")==null?"":rs.getString("XW"); //����ѧλ
					String XWSYSJ = rs.getString("XWSYSJ")==null?"":rs.getString("XWSYSJ"); //ѧλ����ʱ��
					content.append(num+". ");
					content.append(RXSJ+" -- "+BYSJ+" ��"+BYXX+" ѧϰ��"+SXZY+" "+YJFX+"������"+XWSYSJ+" ���� "+XW+"\r\n");
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
					content.append(RXSJ+" -- "+BYSJ+" ��"+BYXX+" ѧϰ��"+SXZY+" "+YJFX+"������"+XWSYSJ+" ���� "+XW+"\r\n");
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
					content.append(RXSJ+" -- "+BYSJ+"�� ��ѵ��"+PXNR+"\r\n");
				}
			} else if(option.equals("GJHQK")) {
				sql = "select * from BO_GJHQKTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String QSRQ = rs.getString("QSRQ")==null?"":rs.getString("QSRQ");          // ��ʼ����
					String JZRQ= rs.getString("JZRQ")==null?"":rs.getString("JZRQ");     // ��ֹ���� 
					String CFGJ1 = rs.getString("CFGJ1")==null?"":rs.getString("CFGJ1");   // ���ҵ���
					String CFDW = rs.getString("CFDW")==null?"":rs.getString("CFDW"); // ѧϰ�ɷõ�λ
					String CFLX = rs.getString("CFLX")==null?"":rs.getString("CFLX");   // �ɷ�����
					String CFHDNR = rs.getString("CFHDNR")==null?"":rs.getString("CFHDNR"); // �ɹ� 
					content.append(num+". ");
					content.append(QSRQ+" -- "+JZRQ+" ��"+CFGJ1+"��"+CFDW+"��"+CFLX+"��"+CFHDNR+"\r\n");
				}
			} else if(option.equals("JTCY")) {
				sql = "select * from BO_JTCYTEMP where 1=1 and CREATEUSER='"+awsuid+"' order by CREATEDATE desc";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				int num = 0;
				while(rs.next()){
					num++;
					String CYXM = rs.getString("CYXM")==null?"":rs.getString("CYXM");          // ��Ա����
					String YBRGX= rs.getString("YBRGX")==null?"":rs.getString("YBRGX");     // �뱾�˹�ϵ 
					String GZDW = rs.getString("GZDW")==null?"":rs.getString("GZDW");   // ����ѧϰ��λ
					String ZW = rs.getString("ZW")==null?"":rs.getString("ZW"); // ְ��
					content.append(num+". ");
					content.append(CYXM+"��"+YBRGX+"��"+GZDW+"��"+ZW+"\r\n");
				}
			} 
			content.append("������������������������������������������������������������\r\n\r\n");
			
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
