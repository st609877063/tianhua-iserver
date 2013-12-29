package com.people.dptwb.common;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.people.dptwb.constant.Constant;

/**
 * <p>
 * Title: TianYi BBS
 * </p>
 * <p>
 * Description: TianYi BBS System
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: LAOER.COM/TIANYISOFT.NET
 * </p>
 * 
 * @author laoer
 * @version 6.0
 */

public class Pages {

	HttpServletRequest request = null;
	String filename = "";
	int page = 1;
	long totals = -1;
	int perpagenum = 20;
	int style = 0;
	int styleshow = 7;
	int allpage = 1;
	int cpage = 1;
	int spage = 1;
	String listPageBreak = "";
	String[] pagesign = null;

	public Pages() {
	}

	public static String[] getPagesign(HttpServletRequest request) {
		String[] pagessign = { LocalizedTextUtil.findDefaultText("twitter.pages.first", request.getLocale()), "上一页", "下一页", LocalizedTextUtil.findDefaultText("twitter.pages.end", request.getLocale()) };
		return pagessign;
	}

	public Pages(HttpServletRequest request) {
		this.request = request;
		this.pagesign = getPagesign(request);
	}

	public Pages(HttpServletRequest request, int page, int totals, int perpagenum, int style) {
		this.request = request;
		this.page = page;
		this.totals = totals;
		this.perpagenum = perpagenum;
		this.style = style;
		this.pagesign = getPagesign(request);
	}

	public Pages(HttpServletRequest request, int page, int totals, int perpagenum) {
		this.request = request;
		this.page = page;
		this.totals = totals;
		this.perpagenum = perpagenum;
		this.pagesign = getPagesign(request);
	}

	public Pages(HttpServletRequest request, int page, int perpagenum) {
		this.request = request;
		this.page = page;
		this.perpagenum = perpagenum;
		this.pagesign = getPagesign(request);
	}

	public String getFileName() {
		return this.filename;
	}

	public void setFileName(String aFileName) {
		this.filename = aFileName;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int aPage) {
		this.page = aPage;
	}

	public long getTotals() {
		return this.totals;
	}

	public void setTotals(long aTotals) {
		this.totals = aTotals;
	}

	public int getPerPageNum() {
		return this.perpagenum;
	}

	public void setPerPageNum(int aperpagenum) {
		this.perpagenum = aperpagenum;
	}

	public int getStyle() {
		return this.style;
	}

	public void setStyle(int aStyle) {
		this.style = aStyle;
	}

	/**
	 * @return the style4show
	 */
	public int getStyleshow() {
		return styleshow;
	}

	/**
	 * @param style4show
	 *            the style4show to set
	 */
	public void setStyleshow(int styleshow) {
		this.styleshow = styleshow;
	}

	public void setPagesign(String[] apagesign) {
		this.pagesign = apagesign;
	}

	public int getSpage() {
		return this.spage;
	}

	public int getAllpage() {
		return this.allpage;
	}

	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}

	public void doPageBreak() {
		this.allpage = (int) Math.ceil((this.totals + this.perpagenum - 1) / this.perpagenum);
		int intPage = this.page;
		if (intPage > this.allpage) { // pages == 0
			this.cpage = 1;
		} else {
			this.cpage = intPage;
		}
		this.spage = (this.cpage - 1) * this.perpagenum;
		getPageBreakStr();
	}

	public String getListPageBreak() {
		return this.listPageBreak;
	}

	private void getPageBreakStr() {

		if (this.filename.indexOf("?") == -1 && (this.filename.endsWith(".action") || this.filename.endsWith(Constant.URL_PREFIX))) {
			this.filename = this.filename + "?";
		} else {
			if (!this.filename.endsWith("&")) {
				this.filename = this.filename + "&#38;";
			}
		}

		StringBuffer sb = new StringBuffer();

		if (this.allpage <= 1) {
			this.listPageBreak = sb.toString();
			return;
		}

		if (this.style == 0) {
			if (this.cpage > 1) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("page=1' >");
				sb.append(pagesign[0]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append((cpage - 1));
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[1]);
				sb.append("</a> ");
				this.listPageBreak = sb.toString();

			}
			if (this.cpage < this.allpage) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append((cpage + 1));
				sb.append("' >");
				sb.append(pagesign[2]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append(this.allpage);
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new

				sb.append("' >");
				sb.append(pagesign[3]);
				sb.append("</a> ");
				this.listPageBreak = sb.toString();

			}
			return;
		}
		if (this.style == 1) {
			if (this.cpage > 1) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("page=1");
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[0]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append((cpage - 1));
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[1]);
				sb.append("</a> ");

			}
			if (this.cpage < this.allpage) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append((cpage + 1));
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[2]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("page=");
				sb.append(this.allpage);
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new

				sb.append("'>");
				sb.append(pagesign[3]);
				sb.append("</a> ");

			}
			int _cpage = 0;
			if (this.allpage == 0) {
				_cpage = 0;
			} else {
				_cpage = cpage;
			}
			sb.append(LocalizedTextUtil.findDefaultText("twitter.pages.str", this.request.getLocale(), new Object[] { String.valueOf(this.totals), String.valueOf(_cpage), String.valueOf(this.allpage) }));

			this.listPageBreak = sb.toString();
			return;
		}
		if (this.style == 2) {
			if (this.cpage > 1) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("inpages=1");
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[0]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("inpages=");
				sb.append((cpage - 1));
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[1]);
				sb.append("</a> ");

			}
			if (this.cpage < this.allpage) {
				sb.append("<a href='");
				sb.append(this.filename);
				sb.append("inpages=");
				sb.append((cpage + 1));
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[2]);
				sb.append("</a> <a href='");
				sb.append(this.filename);
				sb.append("inpages=");
				sb.append(this.allpage);
				// new
				sb.append("&#38;t=");
				sb.append(this.totals);
				// new
				sb.append("'>");
				sb.append(pagesign[3]);
				sb.append("</a> ");

			}
			int _cpage = 0;
			if (this.allpage == 0) {
				_cpage = 0;
			} else {
				_cpage = cpage;
			}
			sb.append(LocalizedTextUtil.findDefaultText("twitter.pages.str", this.request.getLocale(), new Object[] { String.valueOf(this.totals), String.valueOf(_cpage), String.valueOf(this.allpage) }));
			this.listPageBreak = sb.toString();

			return;
		}

		if (this.style == 3) {
			String postto;
			if (this.filename != null && this.filename.length() > 0) {

				sb.append("<div class=\"pages_style_3\"><table width=\"100%\"  border=\"0\" cellpadding=\"3\" cellspacing=\"0\" >\n");
				// filename = filename.toLowerCase();
				int index = filename.indexOf("?");
				if (index != -1) {
					postto = filename.substring(0, index);
					filename = filename.substring(index + 1, filename.length());
				} else {
					postto = filename;
					filename = "";
				}
				sb.append("<FORM METHOD=\"GET\" ACTION=\"" + postto + "\">\n");
				sb.append("<tr><td>\n");
				// System.out.println(filename);
				if (filename != null && filename.length() > 0) {
					String[] ss = filename.split("&");
					if (ss != null) {
						// System.out.println(ss.length);
						for (int i = 0; i < ss.length; i++) {
							String[] p = ss[i].split("=");
							if (p != null && p.length == 2) {
								sb.append("<INPUT TYPE=\"hidden\" name=\"" + p[0] + "\" value=\"" + p[1] + "\"/>\n");

							}
						}
					}
				}

				if (filename != null && filename.length() > 0) {
					filename = postto + "?" + filename;
				} else {
					filename = postto;
				}

				if (this.cpage > 1) {
					sb.append("<a href='");
					sb.append(this.filename);
					sb.append("page=1");
					// new
					sb.append("&#38;t=");
					sb.append(this.totals);
					// new
					sb.append("'>");
					sb.append(pagesign[0]);
					sb.append("</a> <a href='");
					sb.append(this.filename);
					sb.append("page=");
					sb.append((cpage - 1));
					// new
					sb.append("&#38;t=");
					sb.append(this.totals);
					// new
					sb.append("'>");
					sb.append(pagesign[1]);
					sb.append("</a> ");

				}
				if (this.cpage < this.allpage) {
					sb.append("<a href='");
					sb.append(this.filename);
					sb.append("page=");
					sb.append((cpage + 1));
					// new
					sb.append("&#38;t=");
					sb.append(this.totals);
					// new
					sb.append("'>");
					sb.append(pagesign[2]);
					sb.append("</a> <a href='");
					sb.append(this.filename);
					sb.append("page=");
					sb.append(this.allpage);
					// new
					sb.append("&#38;t=");
					sb.append(this.totals);
					// new
					sb.append("'>");
					sb.append(pagesign[3]);
					sb.append("</a> ");

				}
				int _cpage = 0;
				if (this.allpage == 0) {
					_cpage = 0;
				} else {
					_cpage = cpage;
				}
				sb.append(LocalizedTextUtil.findDefaultText("twitter.pages.str", this.request.getLocale(), new Object[] { String.valueOf(this.totals), String.valueOf(_cpage), String.valueOf(this.allpage) }));
				sb.append(" ");
				sb.append(LocalizedTextUtil.findDefaultText("twitter.pages.post", this.request.getLocale()));
				sb.append("</td></tr>\n");
				sb.append("</FORM>\n");
				sb.append("</table></div>\n");

				this.listPageBreak = sb.toString();
			}
			return;
		}

		if (this.style == 4) {
			if (styleshow < 5) {
				styleshow = 5;
			}
			int eachnum = styleshow / 2;

			// ��ҳ��
			if (this.cpage == 1) {
				// sb.append("<a>"+pagesign[1]+"</a>");
			} else {
				// sb.append("<a href='"+this.filename+"page=1&#38;t="+this.totals+"'>"+pagesign[0]+"</a>");
				sb.append("<a href='" + this.filename + "page=" + (cpage - 1) + "&#38;t=" + this.totals + "'>" + pagesign[1] + "</a>");
			}
			if (this.cpage < styleshow) {
				for (int i = 1; i < cpage; i++) {
					sb.append("<a href='" + this.filename + "page=" + i + "&#38;t=" + this.totals + "'>" + i + "</a>");
				}
				sb.append("<a style=\"font-weight:bold;font-size:16px\">" + cpage + "</a>");
				int end = (this.allpage > styleshow) ? styleshow : this.allpage;
				for (int j = cpage + 1; j <= end; j++) {
					sb.append("<a href='" + this.filename + "page=" + j + "&#38;t=" + this.totals + "'>" + j + "</a>");
				}

				if (this.allpage > styleshow) {
					if (this.allpage > (styleshow + 1)) {
						sb.append("...");
					}
					sb.append("<a href='" + this.filename + "page=" + this.allpage + "&#38;t=" + this.totals + "'>" + this.allpage + "</a>");
				}
			} else if (this.cpage > this.allpage - (styleshow - 1)) {
				sb.append("<a href='" + this.filename + "page=" + 1 + "&#38;t=" + this.totals + "'>1</a>");

				int start = cpage - (styleshow - (this.allpage - cpage)) + 1 >= 2 ? cpage - (styleshow - (this.allpage - cpage)) + 1 : 2;
				if (start > 2) {
					sb.append("...");
				}
				for (int i = start; i < cpage; i++) {
					sb.append("<a href='" + this.filename + "page=" + i + "&#38;t=" + this.totals + "'>" + i + "</a>");
				}
				sb.append("<a style=\"font-weight:bold;font-size:16px\">" + cpage + "</a>");

				for (int j = cpage + 1; j <= this.allpage; j++) {
					sb.append("<a href='" + this.filename + "page=" + j + "&#38;t=" + this.totals + "'>" + j + "</a>");
				}
			} else {
				sb.append("<a href='" + this.filename + "page=" + 1 + "&#38;t=" + this.totals + "'>1</a>");
				sb.append("...");
				for (int i = eachnum; i >= 1; i--) {
					sb.append("<a href='" + this.filename + "page=" + (cpage - i) + "&#38;t=" + this.totals + "'>" + (cpage - i) + "</a>");
				}
				sb.append("<a style=\"font-weight:bold;font-size:16px\">" + cpage + "</a>");

				for (int j = 1; j <= eachnum; j++) {
					sb.append("<a href='" + this.filename + "page=" + (cpage + j) + "&#38;t=" + this.totals + "'>" + (cpage + j) + "</a>");
				}
				sb.append("...");
				sb.append("<a href='" + this.filename + "page=" + this.allpage + "&#38;t=" + this.totals + "'>" + this.allpage + "</a>");
			}
			if (this.cpage == this.allpage) {
				// sb.append("<a>"+pagesign[2]+"</a>");
			} else {
				sb.append("<a href='" + this.filename + "page=" + (cpage + 1) + "&#38;t=" + this.totals + "'>" + pagesign[2] + "</a></span>");
				// sb.append("<a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+pagesign[3]+"</a>");
			}

			int _cpage = 0;
			if (this.allpage == 0) {
				_cpage = 0;
			} else {
				_cpage = cpage;
			}

			this.listPageBreak = sb.toString();

			return;
		}

		// ///////////////////////////////////for mobile
		// style////////////////////////////////////////////////////
		if (this.style == 10) {
			if (styleshow < 5) {
				styleshow = 5;
			}
			int eachnum = styleshow / 2;

			sb.append("<div class=\"pages_style_5\" >");

			// ��ת������
			String postto;
			int index = filename.indexOf("?");
			if (index != -1) {
				postto = filename.substring(0, index);
				filename = filename.substring(index + 1, filename.length());
			} else {
				postto = filename;
				filename = "";
			}
			sb.append("<FORM METHOD=\"GET\" ACTION=\"" + postto + "\">\n");

			// System.out.println(filename);
			if (filename != null && filename.length() > 0) {
				String[] ss = filename.split("&");
				if (ss != null) {
					// System.out.println(ss.length);
					for (int i = 0; i < ss.length; i++) {
						String[] p = ss[i].split("=");
						if (p != null && p.length == 2) {
							sb.append("<INPUT TYPE=\"hidden\" name=\"" + p[0] + "\" value=\"" + p[1] + "\"/>\n");

						}
					}
				}
			}

			if (filename != null && filename.length() > 0) {
				filename = postto + "?" + filename;
			} else {
				filename = postto;
			}

			sb.append("<div style=\"float:left\">");
			// ��ҳ��
			if (this.cpage == 1) {
				sb.append("<span class='disabled'>��һҳ</span>");
			} else {
				sb.append("<a href='" + this.filename + "page=" + (cpage - 1) + "'>��һҳ</a></span>");
			}
			if (this.cpage < styleshow) {
				for (int i = 1; i < cpage; i++) {
					sb.append("<a href='" + this.filename + "page=" + i + "&#38;t=" + this.totals + "'>" + i + "</a>");
				}
				sb.append("<span class='current'>" + cpage + "</span>");
				int end = (this.allpage > styleshow) ? styleshow : this.allpage;
				for (int j = cpage + 1; j <= end; j++) {
					sb.append("<a href='" + this.filename + "page=" + j + "&#38;t=" + this.totals + "'>" + j + "</a>");
				}

				if (this.allpage > styleshow) {
					if (this.allpage > (styleshow + 1)) {
						sb.append("...");
					}
					sb.append("<a href='" + this.filename + "page=" + this.allpage + "&#38;t=" + this.totals + "'>" + this.allpage + "</a>");
				}
			} else if (this.cpage > this.allpage - (styleshow - 1)) {
				sb.append("<a href='" + this.filename + "page=" + 1 + "&#38;t=" + this.totals + "'>1</a>");

				int start = cpage - (styleshow - (this.allpage - cpage)) + 1 >= 2 ? cpage - (styleshow - (this.allpage - cpage)) + 1 : 2;
				if (start > 2) {
					sb.append("...");
				}
				for (int i = start; i < cpage; i++) {
					sb.append("<a href='" + this.filename + "page=" + i + "&#38;t=" + this.totals + "'>" + i + "</a>");
				}
				sb.append("<span class='current'>" + cpage + "</span>");

				for (int j = cpage + 1; j <= this.allpage; j++) {
					sb.append("<a href='" + this.filename + "page=" + j + "&#38;t=" + this.totals + "'>" + j + "</a>");
				}
			} else {
				sb.append("<a href='" + this.filename + "page=" + 1 + "&#38;t=" + this.totals + "'>1</a>");
				sb.append("...");
				for (int i = eachnum; i >= 1; i--) {
					sb.append("<a href='" + this.filename + "page=" + (cpage - i) + "&#38;t=" + this.totals + "'>" + (cpage - i) + "</a>");
				}
				sb.append("<span class='current'>" + cpage + "</span>");

				for (int j = 1; j <= eachnum; j++) {
					sb.append("<a href='" + this.filename + "page=" + (cpage + j) + "&#38;t=" + this.totals + "'>" + (cpage + j) + "</a>");
				}
				sb.append("...");
				sb.append("<a href='" + this.filename + "page=" + this.allpage + "&#38;t=" + this.totals + "'>" + this.allpage + "</a>");
			}
			if (this.cpage == this.allpage) {
				sb.append("<span class='disabled'>��һҳ</span>");
			} else {
				sb.append("<a href='" + this.filename + "page=" + (cpage + 1) + "'>��һҳ</a></span>");
			}

			int _cpage = 0;
			if (this.allpage == 0) {
				_cpage = 0;
			} else {
				_cpage = cpage;
			}
			sb.append("</div>");

			sb.append("<div style=\"float:left;margin-left:2px\">");
			sb.append(LocalizedTextUtil.findDefaultText("twitter.pages.post.simple", this.request.getLocale()));
			sb.append("</div>");

			sb.append("</FORM>\n");
			sb.append("</div>");
			this.listPageBreak = sb.toString();

			return;
		}
		// //////////////////////////////for mobile style
		// end//////////////////////////////////////////////
		
		
		 /////////////////////////////////////新版微博分页start(add by xiayang.)////////////////////////////////////////////////////
	    if (this.style == 12) {
	    	if(styleshow < 5){
	    		styleshow = 5;
	    	}
	    	int eachnum = styleshow/2;
			
			//分页条
			if(this.cpage == 1){
			
				//sb.append("<a>"+pagesign[1]+"</a>");
				sb.append("<span>上一页</span>").append("\n");
			}else{
				//sb.append("<a href='"+this.filename+"page=1&#38;t="+this.totals+"'>"+pagesign[0]+"</a>");
				sb.append("<a href='"+this.filename+"page="+(cpage-1)+"&#38;t="+this.totals+"'>"+pagesign[1]+"</a>").append("\n");;
			}
			if(this.cpage < styleshow){
				for(int i=1;i<cpage;i++){
					sb.append("<a href='"+this.filename+"page="+i+"&#38;t="+this.totals+"'>"+i+"</a>").append("\n");;
				}
				sb.append("<span class='selected'>"+cpage+"</span>").append("\n");;
				int end = (this.allpage > styleshow )? styleshow : this.allpage;
				for(int j=cpage+1;j<=end;j++){
					sb.append("<a href='"+this.filename+"page="+j+"&#38;t="+this.totals+"'>"+j+"</a>").append("\n");;
				}
				
				if(this.allpage > styleshow){
					if(this.allpage > (styleshow+1)){
						sb.append("...").append("\n");;
					}
					sb.append("<a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+this.allpage+"</a>").append("\n");;
				}
			}
			else if(this.cpage > this.allpage-(styleshow-1)){
				sb.append("<a href='"+this.filename+"page="+1+"&#38;t="+this.totals+"'>1</a>").append("\n");;
				
				int start = cpage-(styleshow-(this.allpage - cpage))+1 >=2 ? cpage-(styleshow-(this.allpage - cpage))+1 : 2;
				if(start > 2){
					sb.append("...").append("\n");;
				}
				for(int i=start;i<cpage;i++){
					sb.append("<a href='"+this.filename+"page="+i+"&#38;t="+this.totals+"'>"+i+"</a>").append("\n");;
				}
				sb.append("<span class='selected'>"+cpage+"</span>").append("\n");;
				
				for(int j=cpage+1;j<=this.allpage;j++){
					sb.append("<a href='"+this.filename+"page="+j+"&#38;t="+this.totals+"'>"+j+"</a>").append("\n");;
				}	
			}
			else {
				sb.append("<a href='"+this.filename+"page="+1+"&#38;t="+this.totals+"'>1</a>").append("\n");;
				sb.append("...");
				for(int i=eachnum;i>=1;i--){
					sb.append("<a href='"+this.filename+"page="+(cpage-i)+"&#38;t="+this.totals+"'>"+(cpage-i)+"</a>").append("\n");;
				}
				sb.append("<span class='selected'>"+cpage+"</span>").append("\n");;

				for(int j=1;j<=eachnum;j++){
					sb.append("<a href='"+this.filename+"page="+(cpage+j)+"&#38;t="+this.totals+"'>"+(cpage+j)+"</a>").append("\n");;
				}
				sb.append("...").append("\n");;
				sb.append("<a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+this.allpage+"</a>").append("\n");;
			}
			if(this.cpage == this.allpage){
				//sb.append("<a>"+pagesign[2]+"</a>");
				sb.append("<span class='noLink'>下一页</span>").append("\n");;
			}else{
				sb.append("<a href='"+this.filename+"page="+(cpage+1)+"&#38;t="+this.totals+"'>"+pagesign[2]+"</a></span>").append("\n");;
				//sb.append("<a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+pagesign[3]+"</a>");
			}
			sb.append("&nbsp;<span>共").append(totals).append("条</span>").append("\n");
		
			int _cpage = 0;
			if (this.allpage == 0) {
			  _cpage = 0;
			}
			else {
			  _cpage = cpage;
			}		
			
			this.listPageBreak = sb.toString();
			     
			return;
		}
	    /////////////////////////////////////新版微博分页end(add by xiayang.)////////////////////////////////////////////////////
	    
	    
	    if (this.style == 13) {
	    	if(styleshow < 5){
	    		styleshow = 5;
	    	}
	    	int eachnum = styleshow/2;
			
			//分页条
			if(this.cpage == 1){
				sb.append("<li><a href='javascript:void(0)'>上一页</a></li>").append("\n");
			}else{
				sb.append("<li><a href='"+this.filename+"page="+(cpage-1)+"&#38;t="+this.totals+"'>"+pagesign[1]+"</a></li>").append("\n");;
			}
			if(this.cpage < styleshow){
				for(int i=1;i<cpage;i++){
					sb.append("<li><a href='"+this.filename+"page="+i+"&#38;t="+this.totals+"'>"+i+"</a></li>").append("\n");;
				}
				sb.append("<li class='selectedPage'><a href='javascript:void(0)'>"+cpage+"</a></li>").append("\n");;
				int end = (this.allpage > styleshow )? styleshow : this.allpage;
				for(int j=cpage+1;j<=end;j++){
					sb.append("<li><a href='"+this.filename+"page="+j+"&#38;t="+this.totals+"'>"+j+"</a></li>").append("\n");;
				}
				
				if(this.allpage > styleshow){
					if(this.allpage > (styleshow+1)){
						sb.append("...").append("\n");;
					}
					sb.append("<li><a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+this.allpage+"</a></li>").append("\n");;
				}
			}
			else if(this.cpage > this.allpage-(styleshow-1)){
				sb.append("<li><a href='"+this.filename+"page="+1+"&#38;t="+this.totals+"'>1</a></li>").append("\n");;
				
				int start = cpage-(styleshow-(this.allpage - cpage))+1 >=2 ? cpage-(styleshow-(this.allpage - cpage))+1 : 2;
				if(start > 2){
					sb.append("...").append("\n");;
				}
				for(int i=start;i<cpage;i++){
					sb.append("<li><a href='"+this.filename+"page="+i+"&#38;t="+this.totals+"'>"+i+"</a></li>").append("\n");;
				}
				sb.append("<li class='selectedPage'><a href='javascript:void(0)'>"+cpage+"</a></li>").append("\n");;
				
				for(int j=cpage+1;j<=this.allpage;j++){
					sb.append("<li><a href='"+this.filename+"page="+j+"&#38;t="+this.totals+"'>"+j+"</a></li>").append("\n");;
				}	
			}
			else {
				sb.append("<li><a href='"+this.filename+"page="+1+"&#38;t="+this.totals+"'>1</a></li>").append("\n");;
				sb.append("...");
				for(int i=eachnum;i>=1;i--){
					sb.append("<li><a href='"+this.filename+"page="+(cpage-i)+"&#38;t="+this.totals+"'>"+(cpage-i)+"</a></li>").append("\n");;
				}
				sb.append("<li class='selectedPage'><a href='javascript:void(0)'>"+cpage+"</a></li>").append("\n");;

				for(int j=1;j<=eachnum;j++){
					sb.append("<li><a href='"+this.filename+"page="+(cpage+j)+"&#38;t="+this.totals+"'>"+(cpage+j)+"</a></li>").append("\n");;
				}
				sb.append("...").append("\n");;
				sb.append("<li><a href='"+this.filename+"page="+this.allpage+"&#38;t="+this.totals+"'>"+this.allpage+"</a></li>").append("\n");;
			}
			if(this.cpage == this.allpage){
				sb.append("<li><a href='javascript:void(0)'>下一页</a></li>").append("\n");;
			}else{
				sb.append("<li><a href='"+this.filename+"page="+(cpage+1)+"&#38;t="+this.totals+"'>"+pagesign[2]+"</a></li></li>").append("\n");;
			}
			sb.append("&nbsp;<li><a href='javascript:void(0)'>共").append(totals).append("条</a></li>").append("\n");
		
			int _cpage = 0;
			if (this.allpage == 0) {
			  _cpage = 0;
			}
			else {
			  _cpage = cpage;
			}		
			
			this.listPageBreak = sb.toString();
			     
			return;
		}
		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
