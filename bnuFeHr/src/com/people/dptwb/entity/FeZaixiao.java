package com.people.dptwb.entity;

import java.io.Serializable;

public class FeZaixiao implements Serializable {
	private static final long serialVersionUID = -1574483653985055787L;
	private int pkid;// 主键，系统自动生成
	private int userid;
	private String bianhao;// BH

	private String jinxiaoshijian;// 进校时间
	private String lixiaoshijian;// 离校时间

	private String adddate; // 添加时间

	public int getPkid() {
		return pkid;
	}

	public void setPkid(int pkid) {
		this.pkid = pkid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public String getJinxiaoshijian() {
		return jinxiaoshijian;
	}

	public void setJinxiaoshijian(String jinxiaoshijian) {
		this.jinxiaoshijian = jinxiaoshijian;
	}

	public String getLixiaoshijian() {
		return lixiaoshijian;
	}

	public void setLixiaoshijian(String lixiaoshijian) {
		this.lixiaoshijian = lixiaoshijian;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
}
