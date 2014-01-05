package com.people.dptwb.common;

import java.util.List;

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

public class PageList {

	private String pageShowString;
	private List<Object> objectList;
	private Pages pages;

	public PageList() {
	}

	public String getPageShowString() {
		return pageShowString;
	}

	public void setPageShowString(String pageShowString) {
		this.pageShowString = pageShowString;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

}
