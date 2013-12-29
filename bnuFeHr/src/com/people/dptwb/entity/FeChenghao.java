package com.people.dptwb.entity;

import java.io.Serializable;

//高层次人才称号
public class FeChenghao implements Serializable {
	private static final long serialVersionUID = -3320513748763065520L;
	private int pkid;// 主键，系统自动生成
	private int userid;
	private String bianhao;// BH

	private String rencaichenghao;// 人才称号
	private String shijian;// 时间
	private int niandu; // 年度

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

	public String getRencaichenghao() {
		return rencaichenghao;
	}

	public void setRencaichenghao(String rencaichenghao) {
		this.rencaichenghao = rencaichenghao;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
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
