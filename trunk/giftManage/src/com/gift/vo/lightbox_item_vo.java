package com.gift.vo;

import java.util.List;

public class lightbox_item_vo {

	private String i_no;// 礼品编号
	private String i_name;// 礼品名称
	private String i_zlr_name;// 赠礼人信息(前台显示)
	private String i_slr_name;// 受礼人信息(前台显示)
	private String i_sztime_show;// 受赠时间(前台显示)
	private String i_unit;// 单位(套，件)
	private Integer i_num;// 礼品数量
	private String i_zhidi;// 礼品质地
	private String i_type;// 礼品类型
	private String i_chandi;// 礼品产地
	private String i_status;// 礼品现状
	private String i_gongyi;// 礼品工艺
	private String i_background;// 赠送背景
	private String i_desc;// 礼品说明
	private String i_memo;// 礼品备注

	private List<lightbox_fujian_vo> lightbox_fujian_vo_list;

	public String getI_no() {
		return i_no;
	}

	public void setI_no(String i_no) {
		this.i_no = i_no;
	}

	public String getI_name() {
		return i_name;
	}

	public void setI_name(String i_name) {
		this.i_name = i_name;
	}

	public String getI_zlr_name() {
		return i_zlr_name;
	}

	public void setI_zlr_name(String i_zlr_name) {
		this.i_zlr_name = i_zlr_name;
	}

	public String getI_slr_name() {
		return i_slr_name;
	}

	public void setI_slr_name(String i_slr_name) {
		this.i_slr_name = i_slr_name;
	}

	public String getI_sztime_show() {
		return i_sztime_show;
	}

	public void setI_sztime_show(String i_sztime_show) {
		this.i_sztime_show = i_sztime_show;
	}

	public String getI_unit() {
		return i_unit;
	}

	public void setI_unit(String i_unit) {
		this.i_unit = i_unit;
	}

	public Integer getI_num() {
		return i_num;
	}

	public void setI_num(Integer i_num) {
		this.i_num = i_num;
	}

	public String getI_zhidi() {
		return i_zhidi;
	}

	public void setI_zhidi(String i_zhidi) {
		this.i_zhidi = i_zhidi;
	}

	public String getI_type() {
		return i_type;
	}

	public void setI_type(String i_type) {
		this.i_type = i_type;
	}

	public String getI_chandi() {
		return i_chandi;
	}

	public void setI_chandi(String i_chandi) {
		this.i_chandi = i_chandi;
	}

	public String getI_status() {
		return i_status;
	}

	public void setI_status(String i_status) {
		this.i_status = i_status;
	}

	public String getI_gongyi() {
		return i_gongyi;
	}

	public void setI_gongyi(String i_gongyi) {
		this.i_gongyi = i_gongyi;
	}

	public String getI_background() {
		return i_background;
	}

	public void setI_background(String i_background) {
		this.i_background = i_background;
	}

	public String getI_desc() {
		return i_desc;
	}

	public void setI_desc(String i_desc) {
		this.i_desc = i_desc;
	}

	public String getI_memo() {
		return i_memo;
	}

	public void setI_memo(String i_memo) {
		this.i_memo = i_memo;
	}

	public List<lightbox_fujian_vo> getLightbox_fujian_vo_list() {
		return lightbox_fujian_vo_list;
	}

	public void setLightbox_fujian_vo_list(
			List<lightbox_fujian_vo> lightbox_fujian_vo_list) {
		this.lightbox_fujian_vo_list = lightbox_fujian_vo_list;
	}

}