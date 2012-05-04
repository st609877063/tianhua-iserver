package com.platform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.BASE64Encoder;

import com.framework.util.DateTime;
import com.platform.domain.Article;
import com.platform.domain.Comment;
import com.platform.domain.User;
import com.platform.service.ArticleService;
import com.platform.service.CommentService;
import com.platform.service.UserService;
import com.platform.utils.MD5;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	CommentService commentService ;
	ArticleService articleService;
	UserService userService ;
	
	private static List<User> list = new ArrayList<User>();
	@RequestMapping(method = RequestMethod.GET)
	public void getCommnets(HttpServletRequest request ,HttpServletResponse response,Comment comment) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		String sortorder = request.getParameter("sortorder");
		String rp        = request.getParameter("rp");
		String pageNo    = request.getParameter("page");
		int pagesize = (rp == null || rp.equals(""))?10:Integer.parseInt(rp);
		int page_no  = (pageNo == null || pageNo.equals(""))?1:Integer.parseInt(pageNo);
		int startRow = (page_no - 1)*pagesize;
		try {
			writer = response.getWriter();
			String str = commentService.getComments(1,startRow, pagesize,sortorder);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value ="/iphone/sendComment",method = RequestMethod.POST)
	public void sendCommnet(HttpServletRequest request ,HttpServletResponse response,Comment comment) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		boolean flag = false;
		Article article = null;
		StringBuffer sb = new StringBuffer();
		PrintWriter writer = response.getWriter();
		
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String nid = request.getParameter("fid");
		String content = request.getParameter("message");
		
		if(content!=null && !content.equals("")) {
			content = new String(content.getBytes("iso-8859-1"), "utf-8"); 
		}
		
		if(!"".equals(nid)&&nid!=null)
		article = articleService.getArticle(nid);
		//-3:留言内容为空 
		if("".equals(content)||content==null){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
					"<root>\n " +
					"<result>fail</result>\n" +
					"<data>\n " +
					"<errorCode>-3</errorCode>\n  " +
					"</data>\n " +
					"</root>\n");
		}
		//-4:文章id为空或该文章不允许留言 
		else if("".equals(nid)||nid==null||(article!=null&&article.getCommentState()==0)){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
					"<root>\n " +
					"<result>fail</result>\n" +
					"<data>\n " +
					"<errorCode>-4</errorCode>\n  " +
					"</data>\n " +
					"</root>\n");
		}
		else{
			User u = null;
			if(username != null && !username.equals("")) {
				list = userService.getListUsers();
				Iterator<User> it = list.iterator();
				for(;it.hasNext();){
					u = it.next();
					if(u.getName().equals(username)&&u.getPassword().equals(MD5.getMD5(password))){
						flag = true;
						break;
					}
				}
			} else {
				//允许匿名用户。DB设计中有外键关联。user表中存在一个用户为“匿名用户”
				u = userService.getUserByName("匿名用户");
				flag = true;
			}
				
				if(flag){
					comment.setArticle(article);
					comment.setContent(content);
					comment.setReviewState(0);
					comment.setUser(u);
					String commentId = commentService.saveComment(comment);
				// success:保存成功
					if(commentId!=null&&!"".endsWith(commentId)){
						sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
								"<root>\n " +
								"<result>success</result>\n" +
								"<data>\n " +
								"<tid>"+commentId+"</tid>\n " +
								"</data>\n" +
								"</root>\n");
					}
				//-5:留言保存到数据库时出错
					else{
						sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
								"<root>\n " +
								"<result>fail</result>\n" +
								"<data>\n " +
								"<errorCode>-5</errorCode>\n  " +
								"</data>\n " +
								"</root>\n");
					}
				}
				//-1:用户验证失败 
				else{
					sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
							"<root>\n " +
							"<result>fail</result>\n" +
							"<data>\n " +
							"<errorCode>-1</errorCode>\n  " +
							"</data>\n " +
							"</root>\n");
				}
		}
		writer.print(sb.toString());
	}
	
	@RequestMapping(value ="/iphone/detail",method = RequestMethod.GET)
	public void getIphoneCommentDetail(HttpServletRequest request ,HttpServletResponse response,Comment comment){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer;
		String commentId = request.getParameter("tid");
		try {
			writer = response.getWriter();
			String str = commentService.getIphoneComment(commentId);
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/iphone/comment/typelist",method = RequestMethod.GET)
	public void getIphoneCommentType(HttpServletRequest request ,HttpServletResponse response,Comment comment){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer;
		try {
			writer = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n " +
					"<root>\n " +
					"<data>\n " +
					"<typeid>1</typeid>\n  " +
					"<name>1</name>\n  " +
					"</data>\n " +
					"</root>\n");
			
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	@RequestMapping(value ="/iphone",method = RequestMethod.GET)
	public void getIphoneCommnets(HttpServletRequest request ,HttpServletResponse response,Comment comment) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String startRow        = request.getParameter("begin");
		String pagesize    = request.getParameter("count");
		String nid = request.getParameter("nid");
		int begin = (startRow == null || startRow.equals(""))?10:Integer.parseInt(startRow);
		int count = (pagesize == null || pagesize.equals(""))?10:Integer.parseInt(pagesize);
		try {
			writer = response.getWriter();
			//审核状态暂设为0，可全部查看
			String str = commentService.getIphoneComments(nid,0,begin,count,"");
			writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteComments(HttpServletRequest request,HttpServletResponse response,Comment comment) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		String rows=(String)request.getParameter("rowid");
		String[] rowids=rows.split(",");
		boolean flag=false;
		for(int i=0;i<rowids.length;i++){
			flag=commentService.removeComment(comment, rowids[i]);
		}
		JSONObject json = new JSONObject();
		json.put("success",flag);
		if(flag){
			json.put("msg","删除评论成功");
		}else{
			json.put("msg","删除评论失败,请重新删除");
		}
		writer.print(json);
	}
	
	@RequestMapping(value="/comments",method = RequestMethod.PUT)
	public void reviewComments(HttpServletRequest request,HttpServletResponse response,Comment comment) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;
		String rows = request.getParameter("rowid");
		String[] rowids=rows.split(",");
		for(int i=0;i<rowids.length;i++){
			Comment c = commentService.getComment(rowids[i]);
			c.setReviewState(1);
			comment.setUpdateDate(DateTime.getCurrentDateByString());
			changeOK = commentService.reviewComment(c);
		}
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","审核成功");
		}else{
			json.put("msg","审核失败,请重新审核");
		}
		writer.print(json);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void reviewComment(HttpServletRequest request,HttpServletResponse response,Comment comment) throws IOException, JSONException{
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");	
		PrintWriter writer = response.getWriter();
		boolean changeOK =false;
		String commentId = request.getParameter("commentId");
		comment.setCommentId(commentId);
		comment.setUpdateDate(DateTime.getCurrentDateByString());
		changeOK = commentService.reviewComment(comment);
		JSONObject json = new JSONObject();
		json.put("success",true);
		json.put("changeOK",changeOK);
		if(changeOK){
			json.put("msg","审核成功");
		}else{
			json.put("msg","审核失败,请重新审核");
		}
		writer.print(json);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
}
