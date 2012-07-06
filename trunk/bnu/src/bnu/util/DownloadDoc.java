package bnu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.actionsoft.awf.organization.control.UserContext;
import com.actionsoft.loader.core.WorkFlowStepButtonClickRTClassA;

public class DownloadDoc extends WorkFlowStepButtonClickRTClassA {

	public DownloadDoc(UserContext uc){		
		super(uc);
		super.setProvider("maxwell");
		super.setDescription("������������Ϊdoc�ļ�");
	}
	
	@Override
	public String onClick() {
		int bindid = super.getParameter(super.PARAMETER_INSTANCE_ID).toInt();
		System.out.println("_____________________DownloadDoc begin_________________");
		String docName = "";

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
		
		NEIR11 = "test";
		NEIR12 = "test";
		NEIR13 = "NEIR13NEIR13";
		NEIR14 = "NEIR14NEIR14";
		NEIR15 = "NEIR15NEIR15";
		NEIR16 = "NEIR16NEIR16";
		NEIR21 = "NEIR21NEIR21";
		NEIR22 = "NEIR22NEIR22";
		NEIR23 = "NEIR23NEIR23";
		NEIR24 = "NEIR24NEIR24";
		NEIR25 = "NEIR25NEIR25";
		NEIR31 = "NEIR31NEIR31";
		NEIR32 = "NEIR32NEIR32";
		NEIR33 = "NEIR33NEIR33";
		NEIR34 = "NEIR34NEIR34";
		NEIR41 = "NEIR41NEIR41";
		NEIR42 = "NEIR42NEIR42";
		CHENGX1 = "CHENGX1CHENGX1";
		CHENGX2 = "CHENGX2CHENGX2";
		CHENGX3 = "CHENGX3CHENGX3";
		CHENGX4 = "CHENGX4CHENGX4";
		CHENGX5 = "CHENGX5CHENGX5";
		CHENGX6 = "CHENGX6CHENGX6";
		
		docName = "��ʦ���˼���";
		
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
		
		String targetDir = "../webserver\\webapps\\portal\\techerResume";
		String fileName = "";
		String zipFileName = docName + ".zip";;
		fileName = docName + ".doc";
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
		
		return "����Ŀ�ĵ����ص�ַΪ��</br><a href='http://"+ip+"/portal/techerResume/"+docName+".doc'>"+docName+".doc</a>";
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