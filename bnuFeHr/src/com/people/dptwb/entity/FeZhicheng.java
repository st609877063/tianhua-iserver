package com.people.dptwb.entity;

import java.io.Serializable;

public class FeZhicheng implements Serializable {
	private static final long serialVersionUID = -3002937179936298190L;
	private int pkid;// 主键，系统自动生成
	private int userid;
	private String bianhao;// BH

	private String zhicheng;// 职称
	private String renzhishijian;// 任职时间
	private int niandu;// 年度

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

	public String getZhicheng() {
		return zhicheng;
	}

	public void setZhicheng(String zhicheng) {
		this.zhicheng = zhicheng;
	}

	public String getRenzhishijian() {
		return renzhishijian;
	}

	public void setRenzhishijian(String renzhishijian) {
		this.renzhishijian = renzhishijian;
	}

	public int getNiandu() {
		return niandu;
	}

	public void setNiandu(int niandu) {
		this.niandu = niandu;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

}
