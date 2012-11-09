package bnu.web;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;


/**
 * ����ļ�ѹ���Ĳ������ܹ���ȡѹ���ļ��Ĵ洢�ĵ�ַ
 * @author  tangyao
 * 2011-10-28
 */
public class Zipfile {

	String fileName=""; //ѹ��������
	String path="";  //ѹ������ȡ·��
	
	
	public Zipfile(String fileName, String path) {
		super();
		this.fileName = fileName;
		this.path = path;
		
	}


	/**
	 * @author TangYao
	 * @param list �����ļ��б�	 
	 * @throws Exception
	 */
	public String getZip(List list) throws Exception{		
		
		   byte[] buffer = new byte[1024];
		    
		   String strZipName = this.fileName + ".zip";
		   org.apache.tools.zip.ZipOutputStream out = new org.apache.tools.zip.ZipOutputStream(new FileOutputStream(path
		                                      + strZipName));
		   for (int j = 0; j < list.size(); j++) {
		    String name = list.get(j).toString();
		    FileInputStream fis = new FileInputStream(this.path + name);
		    out.putNextEntry(new org.apache.tools.zip.ZipEntry(name));
		    int len;
		    while ((len = fis.read(buffer)) > 0) {
		     out.write(buffer, 0, len);
		    }
		    out.closeEntry();
		    fis.close();
		   }
		   out.close();
		   System.out.println("����Demo.zip�ɹ�");
		   return path+strZipName;
	}

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
}

