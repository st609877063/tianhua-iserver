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

        // 初始化com的线程，非常重要！！使用结束后要调用 realease方法
        ComThread.InitSTA();

        // 实例化ActiveX组件对象：对word进行操作
        ActiveXComponent wrdCom = new ActiveXComponent("Word.Application");

        // 获取Dispatch的Documents对象
        Dispatch wrdDocs = wrdCom.getProperty("Documents").toDispatch();

        // 设置打开的word应用程序是否可见
        wrdCom.setProperty("Visible", new Variant(false));
        //本机上
        //String modelLocation="E:"+File.separator+"FEfiles"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;
//服务器上
        
        String modelLocation="D:"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;

        // 打开一个已经存在的文档，本机测试地址
        Dispatch doc = Dispatch.call(wrdDocs, "Open", modelLocation+"教师考核表.doc").toDispatch();
//D:\20100930-bnuoa\webserver\webapps\fe\techerResume\achievements  正式服务器上
        
       // Dispatch doc = Dispatch.call(wrdDocs, "Open", "D:\\FEfiles\\20100930-bnuoa\\webserver\\webapps\\fe\\techerResume\\achievements\\教师考核表.doc")
       // .toDispatch();
        // 获得当前word文档文本
        Dispatch docSelection = Dispatch.get(wrdCom, "Selection").toDispatch();
        // 从selection所在位置开始查询
        Dispatch find = Dispatch.call(docSelection, "Find").toDispatch();
              
        for(int i=0;i<wt.length;i++){
        	String originalText=wt[i].getOriginalText();
        	String finalText=wt[i].getFinalText();
        	// 设置要查找的内容
            Dispatch.put(find, "Text", originalText);
            // 向前查找
            Dispatch.put(find, "Forward", "True");
            // 设置格式
            Dispatch.put(find, "Format", "True");
            // 大小写匹配
            Dispatch.put(find, "MatchCase", "True");
            // 全字匹配
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
        
        //本地
        //String location="E:"+File.separator+"FEfiles"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;
        //服务器上
        String location="D:"+File.separator+"20100930-bnuoa"+File.separator+"webserver"+File.separator+"webapps"+File.separator+"fe"+File.separator+"techerResume"+File.separator+"achievements"+File.separator;

        String fileName=""+thisYear+"年教师考核表_"+wt[2].finalText+"_"+wt[1].finalText+".doc";
        String finalWord=location+fileName;
        // String location="E:\\FEfiles\\20100930-bnuoa\\webserver\\webapps\\fe\\techerResume\\教师考核表蔡苏.doc";
        
        Dispatch.call(doc, "SaveAs", new Variant(finalWord)); // 保存一个新文档
        
        // 保存关闭
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(true));
            doc = null;
        }

        // 关闭word文件
        wrdCom.invoke("Quit", new Variant[] {});
        // 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
        ComThread.Release();
		return fileName;
    
	} 
}