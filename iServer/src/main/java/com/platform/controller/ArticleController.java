package com.platform.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.framework.util.DateTime;
import com.platform.database.GlobalVariables;
import com.platform.domain.Article;
import com.platform.domain.Section;
import com.platform.service.ArticleService;
import com.platform.service.MagazineService;
import com.platform.service.SectionService;
import com.platform.service.UserService;
import com.platform.utils.ImageUtil;
import com.platform.utils.UploadFile;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	MagazineService magazineService ;
	SectionService sectionService ;
	ArticleService articleService ;
	UserService userService ;
	
	/**************客户端请求接口begin*********************/
	// NEWS_RECOMMEND_TOP_URL   http://localhost:8080/iServer/article/iphone/recommend/top?magazineId=#
	//重磅推荐(置顶)
	@RequestMapping(value ="/iphone/recommend/top",method = RequestMethod.GET)
	public void getRecommendTopArticle(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String magazineId = request.getParameter("magazineId");
		
		String magazineClass = request.getParameter("type");
		if(magazineClass == null) {
			magazineClass = "1";
		}
		String isIpad = request.getParameter("isIpad"); //判断是iphone还是ipad，取不一样的图
		if(isIpad == null || !isIpad.equals("1")) {
			isIpad = "0";
		}
		
		try {
			writer = response.getWriter();
			String str = articleService.getRecommendTopArticle(magazineId, isIpad, magazineClass);
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// NEWS_RECOMMEND_URL   		http://localhost:8080/iServer/article/iphone?isRecommend=1&magazineId=#
	// NEWS_RECOMMEND_NEXT_URL    	http://localhost:8080/iServer/article/iphone?isRecommend=1&magazineId=#&newsId=#
	// NEWS_KIND_URL   				http://localhost:8080/iServer/section/iphone?magazineId=#&rp=100
	// NEWS_KIND_DETAIL_URL   		http://localhost:8080/iServer/article/iphone?cid=#
	// NEWS_KIND_DETAIL_NEXT_URL   	http://localhost:8080/iServer/article/iphone?cid=#&newsId=#
	@RequestMapping(value ="/iphone",method = RequestMethod.GET)
	public void getIphoneArticles(HttpServletRequest request ,HttpServletResponse response,Article article) {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("getid");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		String magazineId = request.getParameter("magazineId");
		String sectionId = request.getParameter("cid");
		String articleId = request.getParameter("newsId");
		String isRecommend = request.getParameter("isRecommend");
		if(isRecommend == null) {
			isRecommend = "0";
		}
		
		String magazineClass = request.getParameter("type");
		if(magazineClass == null) {
			magazineClass = "1";
		}
		String isIpad = request.getParameter("isIpad"); //判断是iphone还是ipad，取不一样的图
		if(isIpad == null || !isIpad.equals("1")) {
			isIpad = "0";
		}
		
		try {
			writer = response.getWriter();
			String str = articleService.getIphoneArticles(magazineId,sectionId,articleId,startRow, pagesize,sortorder, isRecommend, isIpad, magazineClass);
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// NEWS_KIND_DETAIL_TOP_URL   http://localhost:8080/iServer/article/iphone/top?cid=#
	@RequestMapping(value ="/iphone/top",method = RequestMethod.GET)
	public void getTopArticle(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String cid = request.getParameter("cid");

		String magazineClass = request.getParameter("type");
		if(magazineClass == null) {
			magazineClass = "1";
		}
		String isIpad = request.getParameter("isIpad"); //判断是iphone还是ipad，取不一样的图
		if(isIpad == null || !isIpad.equals("1")) {
			isIpad = "0";
		}
		
		try {
			writer = response.getWriter();
			String str = articleService.getTopArticle(cid, isIpad, magazineClass);
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//  NEWS_KIND_DETAIL_CONTENT_URL  http://localhost:8080/iServer/article/name/iphone?nid=#
	@RequestMapping(value ="/name/iphone",method = RequestMethod.GET)
	public void getIphoneArticleContent(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;

		String articleId = request.getParameter("nid");
		String url = request.getRequestURL().toString()+"?nid="+articleId;

		String magazineClass = request.getParameter("type");
		if(magazineClass == null) {
			magazineClass = "1";
		}
		String isIpad = request.getParameter("isIpad"); //判断是iphone还是ipad，取不一样的图
		if(isIpad == null || !isIpad.equals("1")) {
			isIpad = "0";
		}
		
		try {
			writer = response.getWriter();
			String str = articleService.getIphoneArticle(articleId, isIpad, magazineClass);
			writer.write(str);
			//System.out.println("getIphoneArticleContent>>\n"+url+"\n"+str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value ="/name",method = RequestMethod.GET)
	public void getArticleContent(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String articleId = request.getParameter("articleId");
		try {
			writer = response.getWriter();
			Article article = articleService.getArticle(articleId);
			String str = "";
			if(article != null) {
				str = article.getArticleContent();
			}
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**************客户端请求接口end***********************/
	
	@RequestMapping(method = RequestMethod.GET)
	public void getArticles(HttpServletRequest request ,HttpServletResponse response,Article article) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		String magazineId = request.getParameter("magazineId");
		String sectionId = request.getParameter("sectionId");
		try {
			writer = response.getWriter();
			String str = articleService.getArticles(magazineId,sectionId,startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveArticle(HttpServletRequest request,HttpServletResponse response,Article article) throws JSONException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		writer = response.getWriter();
		boolean flag = false;
		String sectionId = request.getParameter("sectionId");
		Section section = sectionService.getSection(sectionId);
		article.setSection(section);	
		article.setCreateDate(DateTime.getCurrentDatetimeByString());
		String articleContent = request.getParameter("articleContent");
		article.setArticleContent(articleContent);

		flag = articleService.saveArticle(article);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",flag);
		if(flag){
			json.put("msg","添加文章成功");
		}else{
			json.put("msg","添加文章失败,请重新添加");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteArticle(HttpServletRequest request,HttpServletResponse response,Article article) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		for(int i=0;i<rowids.length;i++){
			flag=articleService.removeArticle(article, rowids[i]);
		}
		JSONObject json = new JSONObject();
		json.put("success",flag);
		if(flag){
			json.put("msg","删除文章成功");
		}else{
			json.put("msg","删除文章失败,请重新删除");
		}
		writer.print(json);
	}
	
	@RequestMapping(value ="/put",method = RequestMethod.POST)
	public void updateArticle(HttpServletRequest request,HttpServletResponse response,Article article) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;

		String sectionId = request.getParameter("sectionId");
		//String articleContent = (String)request.getAttribute("articleCont");
		//System.out.println("getArticleContent:"+article.getArticleContent());
		//System.out.println("getArticleCont:"+articleContent);
		
		Section section = sectionService.getSection(sectionId);
		article.setSection(section);
		article.setUpdateDate(DateTime.getCurrentDatetimeByString());
		String articleContent = request.getParameter("articleContent");
		//System.out.println("articleContent1:"+articleContent);
		//System.out.println("articleContent2:"+article.getArticleContent());
		article.setArticleContent(articleContent);
		changeOK = articleService.updateArticle(article);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","修改文章成功");
		}else{
			json.put("msg","修改文章失败,请重新修改");
		}
		writer.print(json);
	}

	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public void uploadMagazine(HttpServletRequest request,HttpServletResponse response){
		String timestamp = (String)request.getParameter("timestamp");
		UploadFile.upload(request,GlobalVariables.uri,GlobalVariables.fileLocation,timestamp);
		
		String fileName = GlobalVariables.uri + GlobalVariables.fileLocation + File.separator + timestamp + ".jpg";
		String sFileName = GlobalVariables.uri + GlobalVariables.fileLocation + File.separator + "s_" + timestamp + ".jpg"; //iphone使用small
		String bFileName = GlobalVariables.uri + GlobalVariables.fileLocation + File.separator + "b_" + timestamp +".jpg";; //ipad使用big
		try {
			ImageUtil.saveResizeImage(fileName, sFileName, 400, 400, "ARTICLE"); //iphone 图片生成
			ImageUtil.saveResizeImage(fileName, bFileName, 1000, 1000, "ARTICLE"); //ipad 图片生成
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setMagazineService(MagazineService magazineService) {
		this.magazineService = magazineService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}
}
