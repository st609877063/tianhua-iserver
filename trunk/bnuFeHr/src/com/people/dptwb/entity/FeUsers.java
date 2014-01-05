package com.people.dptwb.entity;

import java.io.Serializable;

// 人员基本信息表
public class FeUsers implements Serializable {
	private static final long serialVersionUID = -3960373303046764193L;
	private int userid; // 用户记录主键，系统自动生成
	private String bianhao; // BH
	private String truename; // 姓名
	private int sex; // 性别 0女；1:男；2：未填
	private String birthday; // 出生日期
	private String shenfenzhenghao; // 身份证号
	private String mingzu; // 民族
	private String jiguan; // 籍贯

	private String jigou; // 机构名称
	private String jibie; // 级别
	private String daoxiaoshijian; // 到校时间
	private String susuoxueke; // 所属学科
	private String gangweileibie; // 岗位类别
	private String zhicheng; // 职称
	private String renzhishijian; // 任职时间
	private String xupinshijian; // 最近续聘时间
	private String gangweidengji; // 岗位等级
	private String gangweishijian; // 岗位聘用时间
	private String xueyuan; // 学缘
	private String zuigaoxuewei; // 最高学位
	private String boshishijian; // 博士毕业时间
	private String boshixuexiao; // 博士毕业院校
	private String boshizhuanye; // 博士专业

	private String shuoshishijian; // 硕士毕业时间
	private String shuoshixuexiao; // 硕士毕业学校
	private String shuoshizhuanye; // 硕士专业
	private String benkeshijian; // 本科毕业时间
	private String benkexuexiao; // 本科毕业学校
	private String benkezhuanye; // 本科专业
	private String boshihou; // 博士后备注
	private String shehuijianzhi; // 社会兼职备注（理事长、秘书长、会长以上）
	private String zhengzhimianmao; // 政治面貌备注
	private String chuguo; // 出国进修月数
	private String zaiwaixuewei; // 在外攻读学位月数
	private String zaiwaiyueshu; // 合计月数
	private String lianxi; // 联系方式
	private String email; // email

	private String adddate; // 添加时间

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

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getShenfenzhenghao() {
		return shenfenzhenghao;
	}

	public void setShenfenzhenghao(String shenfenzhenghao) {
		this.shenfenzhenghao = shenfenzhenghao;
	}

	public String getMingzu() {
		return mingzu;
	}

	public void setMingzu(String mingzu) {
		this.mingzu = mingzu;
	}

	public String getJiguan() {
		return jiguan;
	}

	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}

	public String getJigou() {
		return jigou;
	}

	public void setJigou(String jigou) {
		this.jigou = jigou;
	}

	public String getJibie() {
		return jibie;
	}

	public void setJibie(String jibie) {
		this.jibie = jibie;
	}

	public String getDaoxiaoshijian() {
		return daoxiaoshijian;
	}

	public void setDaoxiaoshijian(String daoxiaoshijian) {
		this.daoxiaoshijian = daoxiaoshijian;
	}

	public String getSusuoxueke() {
		return susuoxueke;
	}

	public void setSusuoxueke(String susuoxueke) {
		this.susuoxueke = susuoxueke;
	}

	public String getGangweileibie() {
		return gangweileibie;
	}

	public void setGangweileibie(String gangweileibie) {
		this.gangweileibie = gangweileibie;
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

	public String getXupinshijian() {
		return xupinshijian;
	}

	public void setXupinshijian(String xupinshijian) {
		this.xupinshijian = xupinshijian;
	}

	public String getGangweidengji() {
		return gangweidengji;
	}

	public void setGangweidengji(String gangweidengji) {
		this.gangweidengji = gangweidengji;
	}

	public String getGangweishijian() {
		return gangweishijian;
	}

	public void setGangweishijian(String gangweishijian) {
		this.gangweishijian = gangweishijian;
	}

	public String getXueyuan() {
		return xueyuan;
	}

	public void setXueyuan(String xueyuan) {
		this.xueyuan = xueyuan;
	}

	public String getZuigaoxuewei() {
		return zuigaoxuewei;
	}

	public void setZuigaoxuewei(String zuigaoxuewei) {
		this.zuigaoxuewei = zuigaoxuewei;
	}

	public String getBoshishijian() {
		return boshishijian;
	}

	public void setBoshishijian(String boshishijian) {
		this.boshishijian = boshishijian;
	}

	public String getBoshixuexiao() {
		return boshixuexiao;
	}

	public void setBoshixuexiao(String boshixuexiao) {
		this.boshixuexiao = boshixuexiao;
	}

	public String getBoshizhuanye() {
		return boshizhuanye;
	}

	public void setBoshizhuanye(String boshizhuanye) {
		this.boshizhuanye = boshizhuanye;
	}

	public String getShuoshishijian() {
		return shuoshishijian;
	}

	public void setShuoshishijian(String shuoshishijian) {
		this.shuoshishijian = shuoshishijian;
	}

	public String getShuoshixuexiao() {
		return shuoshixuexiao;
	}

	public void setShuoshixuexiao(String shuoshixuexiao) {
		this.shuoshixuexiao = shuoshixuexiao;
	}

	public String getShuoshizhuanye() {
		return shuoshizhuanye;
	}

	public void setShuoshizhuanye(String shuoshizhuanye) {
		this.shuoshizhuanye = shuoshizhuanye;
	}

	public String getBenkeshijian() {
		return benkeshijian;
	}

	public void setBenkeshijian(String benkeshijian) {
		this.benkeshijian = benkeshijian;
	}

	public String getBenkexuexiao() {
		return benkexuexiao;
	}

	public void setBenkexuexiao(String benkexuexiao) {
		this.benkexuexiao = benkexuexiao;
	}

	public String getBenkezhuanye() {
		return benkezhuanye;
	}

	public void setBenkezhuanye(String benkezhuanye) {
		this.benkezhuanye = benkezhuanye;
	}

	public String getBoshihou() {
		return boshihou;
	}

	public void setBoshihou(String boshihou) {
		this.boshihou = boshihou;
	}

	public String getShehuijianzhi() {
		return shehuijianzhi;
	}

	public void setShehuijianzhi(String shehuijianzhi) {
		this.shehuijianzhi = shehuijianzhi;
	}

	public String getZhengzhimianmao() {
		return zhengzhimianmao;
	}

	public void setZhengzhimianmao(String zhengzhimianmao) {
		this.zhengzhimianmao = zhengzhimianmao;
	}

	public String getChuguo() {
		return chuguo;
	}

	public void setChuguo(String chuguo) {
		this.chuguo = chuguo;
	}

	public String getZaiwaixuewei() {
		return zaiwaixuewei;
	}

	public void setZaiwaixuewei(String zaiwaixuewei) {
		this.zaiwaixuewei = zaiwaixuewei;
	}

	public String getZaiwaiyueshu() {
		return zaiwaiyueshu;
	}

	public void setZaiwaiyueshu(String zaiwaiyueshu) {
		this.zaiwaiyueshu = zaiwaiyueshu;
	}

	public String getLianxi() {
		return lianxi;
	}

	public void setLianxi(String lianxi) {
		this.lianxi = lianxi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

}