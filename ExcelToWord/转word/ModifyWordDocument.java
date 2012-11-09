package bnu.web;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ModifyWordDocument {
	
	WordTable[] wt; 
	public ModifyWordDocument(WordTable[] w){
		wt=w;		
	} 
	
	public String getWord(){

        // ��ʼ��com���̣߳��ǳ���Ҫ����ʹ�ý�����Ҫ���� realease����
        ComThread.InitSTA();

        // ʵ����ActiveX������󣺶�word���в���
        ActiveXComponent wrdCom = new ActiveXComponent("Word.Application");

        // ��ȡDispatch��Documents����
        Dispatch wrdDocs = wrdCom.getProperty("Documents").toDispatch();

        // ���ô򿪵�wordӦ�ó����Ƿ�ɼ�
        wrdCom.setProperty("Visible", new Variant(false));
        //������
        //String modelLocation="E:"+File.separator+"FEfiles"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;
//��������
        
        String modelLocation="D:"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;

        // ��һ���Ѿ����ڵ��ĵ����������Ե�ַ
        Dispatch doc = Dispatch.call(wrdDocs, "Open", modelLocation+"��ʦ���˱�.doc").toDispatch();
//D:\20100930-bnuoa\webserver\webapps\fe\techerResume\achievements  ��ʽ��������
        
       // Dispatch doc = Dispatch.call(wrdDocs, "Open", "D:\\FEfiles\\20100930-bnuoa\\webserver\\webapps\\fe\\techerResume\\achievements\\��ʦ���˱�.doc")
       // .toDispatch();
        // ��õ�ǰword�ĵ��ı�
        Dispatch docSelection = Dispatch.get(wrdCom, "Selection").toDispatch();
        // ��selection����λ�ÿ�ʼ��ѯ
        Dispatch find = Dispatch.call(docSelection, "Find").toDispatch();
              
        for(int i=0;i<wt.length;i++){
        	String originalText=wt[i].getOriginalText();
        	String finalText=wt[i].getFinalText();
        	// ����Ҫ���ҵ�����
            Dispatch.put(find, "Text", originalText);
            // ��ǰ����
            Dispatch.put(find, "Forward", "True");
            // ���ø�ʽ
            Dispatch.put(find, "Format", "True");
            // ��Сдƥ��
            Dispatch.put(find, "MatchCase", "True");
            // ȫ��ƥ��
            Dispatch.put(find, "MatchWholeWord", "True");

            Dispatch.call(find, "Execute").getBoolean();
            Dispatch.put(docSelection, "Text", finalText);
            Dispatch.call(docSelection,"MoveRight");
        }
       
        String temp_str="";
        Date dt = new Date();      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        temp_str=sdf.format(dt);
        int thisYear=Integer.parseInt(temp_str)-1;
        
        //����
        //String location="E:"+File.separator+"FEfiles"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;
        //��������
        String location="D:"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;

        String fileName=""+thisYear+"���ʦ���˱�_"+wt[2].finalText+"_"+wt[1].finalText+".doc";
        String finalWord=location+fileName;
        // String location="E:\\FEfiles\\20100930-bnuoa\\webserver\\webapps\\fe\\techerResume\\��ʦ���˱����.doc";
        
        Dispatch.call(doc, "SaveAs", new Variant(finalWord)); // ����һ�����ĵ�
        
        // ����ر�
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(true));
            doc = null;
        }

        // �ر�word�ļ�
        wrdCom.invoke("Quit", new Variant[] {});
        // �ͷ�com�̡߳�����jacob�İ����ĵ���com���̻߳��ղ���java����������������
        ComThread.Release();
		return fileName;
    
	} 
}