package com.gift.bean;import org.springframework.context.annotation.Scope;import org.springframework.stereotype.Repository;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Repository@Scope("prototype")public class Gift_cangku implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer i_id;// 唯一ID	private String i_no;// 礼品编号	private String i_name;// 礼品名称	private Integer i_zlr;// 赠礼人信息(对应受赠礼人表gift_peoples的p_id)	private Integer i_slr;// 受礼人信息(对应受赠礼人表gift_peoples的p_id)	private Integer i_sztime;// 受赠时间	private String i_unit;// 单位(套，件)	private Integer i_num;// 礼品数量	private String i_zhidi;// 礼品质地	private String i_type;// 礼品类型	private String i_chandi;// 礼品产地	private String i_status;// 礼品现状	private String i_gongyi;// 礼品工艺	private String i_background;// 赠送背景	private String i_desc;// 礼品说明	private String i_memo;// 礼品备注	private String i_attribute1;// 礼品额外属性1	private String i_attribute2;// 礼品额外属性2	private String i_attribute3;// 礼品额外属性3	private String i_attribute4;// 礼品额外属性4	private String i_attribute5;// 礼品额外属性5	private Integer i_createtime;// 创建时间	private Integer i_adduser;// 创建人	private Integer ck_id;// 仓库唯一ID	private String ck_i_no;// 礼品编号(对应gift_items中i_no)	private String ck_kufang;// 库房号	private String ck_huojia;// 货架号	private String ck_ceng;// 层数	private String ck_attribute1;// 仓库额外属性1	private String ck_attribute2;// 仓库额外属性2	private String ck_attribute3;// 仓库额外属性3	private String ck_attribute4;// 仓库额外属性4	private String ck_attribute5;// 仓库额外属性5	private Integer ck_createtime;// 创建时间	private Integer ck_adduser;// 创建人	public Integer getI_id() {		return i_id;	}	public void setI_id(Integer i_id) {		this.i_id = i_id;	}	public String getI_no() {		return i_no;	}	public void setI_no(String i_no) {		this.i_no = i_no;	}	public String getI_name() {		return i_name;	}	public void setI_name(String i_name) {		this.i_name = i_name;	}	public Integer getI_zlr() {		return i_zlr;	}	public void setI_zlr(Integer i_zlr) {		this.i_zlr = i_zlr;	}	public Integer getI_slr() {		return i_slr;	}	public void setI_slr(Integer i_slr) {		this.i_slr = i_slr;	}	public Integer getI_sztime() {		return i_sztime;	}	public void setI_sztime(Integer i_sztime) {		this.i_sztime = i_sztime;	}	public String getI_unit() {		return i_unit;	}	public void setI_unit(String i_unit) {		this.i_unit = i_unit;	}	public Integer getI_num() {		return i_num;	}	public void setI_num(Integer i_num) {		this.i_num = i_num;	}	public String getI_zhidi() {		return i_zhidi;	}	public void setI_zhidi(String i_zhidi) {		this.i_zhidi = i_zhidi;	}	public String getI_type() {		return i_type;	}	public void setI_type(String i_type) {		this.i_type = i_type;	}	public String getI_chandi() {		return i_chandi;	}	public void setI_chandi(String i_chandi) {		this.i_chandi = i_chandi;	}	public String getI_status() {		return i_status;	}	public void setI_status(String i_status) {		this.i_status = i_status;	}	public String getI_gongyi() {		return i_gongyi;	}	public void setI_gongyi(String i_gongyi) {		this.i_gongyi = i_gongyi;	}	public String getI_background() {		return i_background;	}	public void setI_background(String i_background) {		this.i_background = i_background;	}	public String getI_desc() {		return i_desc;	}	public void setI_desc(String i_desc) {		this.i_desc = i_desc;	}	public String getI_memo() {		return i_memo;	}	public void setI_memo(String i_memo) {		this.i_memo = i_memo;	}	public String getI_attribute1() {		return i_attribute1;	}	public void setI_attribute1(String i_attribute1) {		this.i_attribute1 = i_attribute1;	}	public String getI_attribute2() {		return i_attribute2;	}	public void setI_attribute2(String i_attribute2) {		this.i_attribute2 = i_attribute2;	}	public String getI_attribute3() {		return i_attribute3;	}	public void setI_attribute3(String i_attribute3) {		this.i_attribute3 = i_attribute3;	}	public String getI_attribute4() {		return i_attribute4;	}	public void setI_attribute4(String i_attribute4) {		this.i_attribute4 = i_attribute4;	}	public String getI_attribute5() {		return i_attribute5;	}	public void setI_attribute5(String i_attribute5) {		this.i_attribute5 = i_attribute5;	}	public Integer getI_createtime() {		return i_createtime;	}	public void setI_createtime(Integer i_createtime) {		this.i_createtime = i_createtime;	}	public Integer getI_adduser() {		return i_adduser;	}	public void setI_adduser(Integer i_adduser) {		this.i_adduser = i_adduser;	}	public Integer getCk_id() {		return ck_id;	}	public void setCk_id(Integer ck_id) {		this.ck_id = ck_id;	}	public String getCk_i_no() {		return ck_i_no;	}	public void setCk_i_no(String ck_i_no) {		this.ck_i_no = ck_i_no;	}	public String getCk_kufang() {		return ck_kufang;	}	public void setCk_kufang(String ck_kufang) {		this.ck_kufang = ck_kufang;	}	public String getCk_huojia() {		return ck_huojia;	}	public void setCk_huojia(String ck_huojia) {		this.ck_huojia = ck_huojia;	}	public String getCk_ceng() {		return ck_ceng;	}	public void setCk_ceng(String ck_ceng) {		this.ck_ceng = ck_ceng;	}	public String getCk_attribute1() {		return ck_attribute1;	}	public void setCk_attribute1(String ck_attribute1) {		this.ck_attribute1 = ck_attribute1;	}	public String getCk_attribute2() {		return ck_attribute2;	}	public void setCk_attribute2(String ck_attribute2) {		this.ck_attribute2 = ck_attribute2;	}	public String getCk_attribute3() {		return ck_attribute3;	}	public void setCk_attribute3(String ck_attribute3) {		this.ck_attribute3 = ck_attribute3;	}	public String getCk_attribute4() {		return ck_attribute4;	}	public void setCk_attribute4(String ck_attribute4) {		this.ck_attribute4 = ck_attribute4;	}	public String getCk_attribute5() {		return ck_attribute5;	}	public void setCk_attribute5(String ck_attribute5) {		this.ck_attribute5 = ck_attribute5;	}	public Integer getCk_createtime() {		return ck_createtime;	}	public void setCk_createtime(Integer ck_createtime) {		this.ck_createtime = ck_createtime;	}	public Integer getCk_adduser() {		return ck_adduser;	}	public void setCk_adduser(Integer ck_adduser) {		this.ck_adduser = ck_adduser;	}}