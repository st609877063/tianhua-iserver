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
		super.setDescription("������������Ϊdoc�ļ�");
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
		"һ������Ƚ������ݣ�\r\n"+
		"1���˲������ĸ��뷢չ�ƻ���\r\n"+
		  "1.1 �˲�������ϵ�ĸ\r\n"+
		  	NEIR11+"\r\n"+
		 "1.2 ���Ҽ���ѧ�Ŷӽ��裺\r\n"+
		 NEIR12+"\r\n"+
		 "1.3 ѧУ�����ν��н�����֧����Ŀ��\r\n"+
		 NEIR13+"\r\n"+
		 "1.4 ��Ʒ�γ��뾫Ʒ�̲������ƻ���\r\n"+
		 NEIR14+"\r\n"+
		 "1.5 �о������Ŀγ̽���ƻ���\r\n"+
		 NEIR15+"\r\n"+
		 "1.6 ���ʽ���ѧλ�����ƻ���\r\n"+
		 NEIR16+"\r\n"+
		 "������������������������������������������������������������\r\n"+
		 "2���������������������Ŀ�����ƻ���\r\n"+
		 "2.1 ��ʦ�����������裺\r\n"+
		 NEIR21+"\r\n"+
		 "2.2 ѧ�������������裺\r\n"+
		 NEIR22+"\r\n"+
		 "2.3 ��Ϣ�������ݿ⽨�裺\r\n"+
		 NEIR23+"\r\n"+
		 "2.4 ʵ���ҽ��裺\r\n"+
		 NEIR24+"\r\n"+
		 "2.5 ���滮��Ҫ���ص���Ŀ�����о��ƻ���\r\n"+
		 NEIR25+"\r\n"+
		" ������������������������������������������������������������\r\n"+
		 "3�����ʽ����뷢չ�ƻ���\r\n"+
		 "3.1 ��ʦ�������裺\r\n"+
		 NEIR31+"\r\n"+
		 "3.2 ѧ�������ƻ���\r\n"+
		 NEIR32+"\r\n"+
		 "3.3 ����ѧ������ƽ̨����ƻ���\r\n"+
		 NEIR33+"\r\n"+
		 "3.4 ѧ���ɹ��ƽ�ƻ���\r\n"+
		 NEIR34+"\r\n"+
		" ������������������������������������������������������������\r\n"+
		 "4������ѧ����������ƻ���\r\n"+
		 "4.1 �������̣�\r\n"+
		 NEIR41+"\r\n"+
		 "4.2 ���ֲ���ݽ��裺\r\n"+
		 NEIR42+"\r\n"+


		 "�������Ԥ�ڽ����Ч��\r\n"+
		 "1���˲�������\r\n"+
		 CHENGX1+"\r\n"+
		 "2����ѧ�о���\r\n"+
		 CHENGX2+"\r\n"+
		 "3�����齨�裺\r\n"+
		 CHENGX3+"\r\n"+
		 "4�����ʽ����������\r\n"+
		 CHENGX4+"\r\n"+
		 "5��ʵ���ҽ��裺\r\n"+
		 CHENGX5+"\r\n"+
		 "6���칫�������ƣ�\r\n"+
		 CHENGX6;
		
		String targetDir = "../webserver\\webapps\\portal\\pingshen";
		String fileName = "";
		String zipFileName = XMMC + ".zip";;
		fileName = XMMC + ".doc";
		String targetZipFile = targetDir + zipFileName;
		File fileDir = new File(targetDir);
		//������ڸ��ļ��У�����ɾ�������ļ��е��ļ�
		if (fileDir.canRead()) {
			String[] fileList = fileDir.list();
			for (int j = 0; j < fileList.length; j++) {
				File f = new File(fileDir.getAbsolutePath() + "\\"+fileList[j]);
				f.delete();
			}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "����Ŀ�ĵ����ص�ַΪ��</br><a href='http://"+ip+"/portal/pingshen/"+XMMC+".doc'>"+XMMC+".doc</a>";
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
}