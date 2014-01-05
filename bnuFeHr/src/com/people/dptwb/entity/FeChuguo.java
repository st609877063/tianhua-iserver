package com.people.dptwb.entity;

import java.io.Serializable;

public class FeChuguo implements Serializable {
	private static final long serialVersionUID = 8392345273633796676L;
	private int pkid;// 主键，系统自动生成
	private int userid;
	private String bianhao;// BH

	private String danwei;// 单位
	private String guojia;// 出访国家
	private String chufangdanwei;// 出访单位
	private String zhuanye;// 所学专业
	private String fangshi;// 出国方式
	private String xiangmuming;// 项目名称
	private String chuguoshenfen;// 出国身份
	private String chuguoshijian;// 出国日期
	private String yingguishijian;// 应归日期
	private String huiguoshijian;// 回国日期
	private String chuguotianshu;// 出国天数
	private String suohuoxuewei;// 所获学位
	private String beizhu;// 备注
	private int niandu; // 出国年份

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

	public String getGuojia() {
		return guojia;
	}

	public void setGuojia(String guojia) {
		this.guojia = guojia;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getZhuanye() {
		return zhuanye;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}

	public String getFangshi() {
		return fangshi;
	}

	public void setFangshi(String fangshi) {
		this.fangshi = fangshi;
	}

	public String getXiangmuming() {
		return xiangmuming;
	}

	public void setXiangmuming(String xiangmuming) {
		this.xiangmuming = xiangmuming;
	}

	public String getChuguoshenfen() {
		return chuguoshenfen;
	}

	public void setChuguoshenfen(String chuguoshenfen) {
		this.chuguoshenfen = chuguoshenfen;
	}

	public String getChuguoshijian() {
		return chuguoshijian;
	}

	public void setChuguoshijian(String chuguoshijian) {
		this.chuguoshijian = chuguoshijian;
	}

	public String getYingguishijian() {
		return yingguishijian;
	}

	public void setYingguishijian(String yingguishijian) {
		this.yingguishijian = yingguishijian;
	}

	public String getHuiguoshijian() {
		return huiguoshijian;
	}

	public void setHuiguoshijian(String huiguoshijian) {
		this.huiguoshijian = huiguoshijian;
	}

	public String getChuguotianshu() {
		return chuguotianshu;
	}

	public void setChuguotianshu(String chuguotianshu) {
		this.chuguotianshu = chuguotianshu;
	}

	public String getSuohuoxuewei() {
		return suohuoxuewei;
	}

	public void setSuohuoxuewei(String suohuoxuewei) {
		this.suohuoxuewei = suohuoxuewei;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
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

	public String getChufangdanwei() {
		return chufangdanwei;
	}

	public void setChufangdanwei(String chufangdanwei) {
		this.chufangdanwei = chufangdanwei;
	}

}
