package com.gift.data;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.tools.DateTools;

public class ExportXML {

	public Document writeXml(List<Gift_items> items_list,
			List<Gift_fujian> fujian_list, List<Gift_peoples> people_list,
			List<Gift_cangku> cangku_list) {

		Document document = DocumentHelper.createDocument();

		Element element_root = document.addElement("gifts");

		if (items_list != null) {
			for (Gift_items items : items_list) {

				Element element_items = element_root.addElement("items");

				element_items.addComment("礼品编号");
				Element element_i_no = element_items.addElement("i_no");
				element_i_no.addCDATA(strFormat(items.getI_no()));

				element_items.addComment("礼品名称");
				Element element_i_name = element_items.addElement("i_name");
				element_i_name.addCDATA(strFormat(items.getI_name()));

				element_items.addComment("赠礼人");
				Element element_i_zlr = element_items.addElement("i_zlr");
				element_i_zlr.addCDATA(intFormat(items.getI_zlr()));

				element_items.addComment("受礼人");
				Element element_i_slr = element_items.addElement("i_slr");
				element_i_slr.addCDATA(intFormat(items.getI_slr()));

				element_items.addComment("受赠时间");
				Element element_i_sztime = element_items.addElement("i_sztime");
				if (null == items.getI_sztime()) {
					element_i_sztime.addCDATA("");
				} else {
					element_i_sztime.addCDATA(DateTools
							.getDisplayTime((long) items.getI_sztime()));
				}

				element_items.addComment("单位(套，件)");
				Element element_i_unit = element_items.addElement("i_unit");
				element_i_unit.addCDATA(strFormat(items.getI_unit()));

				element_items.addComment("礼品数量");
				Element element_i_num = element_items.addElement("i_num");
				element_i_num.addCDATA(intFormat(items.getI_num()));

				element_items.addComment("礼品质地");
				Element element_i_zhidi = element_items.addElement("i_zhidi");
				element_i_zhidi.addCDATA(strFormat(items.getI_zhidi()));

				element_items.addComment("礼品类型");
				Element element_i_type = element_items.addElement("i_type");
				element_i_type.addCDATA(strFormat(items.getI_type()));

				element_items.addComment("礼品产地");
				Element element_i_chandi = element_items.addElement("i_chandi");
				element_i_chandi.addCDATA(strFormat(items.getI_chandi()));

				element_items.addComment("礼品现状");
				Element element_i_status = element_items.addElement("i_status");
				element_i_status.addCDATA(strFormat(items.getI_status()));

				element_items.addComment("礼品工艺");
				Element element_i_gongyi = element_items.addElement("i_gongyi");
				element_i_gongyi.addCDATA(strFormat(items.getI_gongyi()));

				element_items.addComment("赠送背景");
				Element element_i_background = element_items
						.addElement("i_background");
				element_i_background
						.addCDATA(strFormat(items.getI_background()));

				element_items.addComment("礼品说明");
				Element element_i_desc = element_items.addElement("i_desc");
				element_i_desc.addCDATA(strFormat(items.getI_desc()));

				element_items.addComment("礼品备注");
				Element element_i_memo = element_items.addElement("i_memo");
				element_i_memo.addCDATA(strFormat(items.getI_memo()));

				element_items.addComment("礼品额外属性1");
				Element element_i_attribute1 = element_items
						.addElement("i_attribute1");
				element_i_attribute1
						.addCDATA(strFormat(items.getI_attribute1()));

				element_items.addComment("礼品额外属性2");
				Element element_i_attribute2 = element_items
						.addElement("i_attribute2");
				element_i_attribute2
						.addCDATA(strFormat(items.getI_attribute2()));

				element_items.addComment("礼品额外属性3");
				Element element_i_attribute3 = element_items
						.addElement("i_attribute3");
				element_i_attribute3
						.addCDATA(strFormat(items.getI_attribute3()));

				element_items.addComment("礼品额外属性4");
				Element element_i_attribute4 = element_items
						.addElement("i_attribute4");
				element_i_attribute4
						.addCDATA(strFormat(items.getI_attribute4()));

				element_items.addComment("礼品额外属性5");
				Element element_i_attribute5 = element_items
						.addElement("i_attribute5");
				element_i_attribute5
						.addCDATA(strFormat(items.getI_attribute5()));
			}
		}

		if (fujian_list != null) {
			for (Gift_fujian fujian : fujian_list) {

				Element element_fujian = element_root.addElement("fujian");

				element_fujian.addComment("附件对应礼品编号");
				Element element_fj_i_no = element_fujian.addElement("fj_i_no");
				element_fj_i_no.addCDATA(strFormat(fujian.getFj_i_no()));

				element_fujian.addComment("附件名称");
				Element element_fj_name = element_fujian.addElement("fj_name");
				element_fj_name.addCDATA(strFormat(fujian.getFj_name()));

				element_fujian.addComment("附件地址");
				Element element_fj_path = element_fujian.addElement("fj_path");
				element_fj_path.addCDATA(strFormat(fujian.getFj_path()));

				element_fujian.addComment("附件说明");
				Element element_fj_desc = element_fujian.addElement("fj_desc");
				element_fj_desc.addCDATA(strFormat(fujian.getFj_desc()));

				element_fujian.addComment("附件额外属性1");
				Element element_fj_attribute1 = element_fujian
						.addElement("fj_attribute1");
				element_fj_attribute1.addCDATA(strFormat(fujian
						.getFj_attribute1()));

				element_fujian.addComment("附件额外属性2");
				Element element_fj_attribute2 = element_fujian
						.addElement("fj_attribute2");
				element_fj_attribute2.addCDATA(strFormat(fujian
						.getFj_attribute2()));

				element_fujian.addComment("附件额外属性3");
				Element element_fj_attribute3 = element_fujian
						.addElement("fj_attribute3");
				element_fj_attribute3.addCDATA(strFormat(fujian
						.getFj_attribute3()));

				element_fujian.addComment("附件额外属性4");
				Element element_fj_attribute4 = element_fujian
						.addElement("fj_attribute4");
				element_fj_attribute4.addCDATA(strFormat(fujian
						.getFj_attribute4()));

				element_fujian.addComment("附件额外属性5");
				Element element_fj_attribute5 = element_fujian
						.addElement("fj_attribute5");
				element_fj_attribute5.addCDATA(strFormat(fujian
						.getFj_attribute5()));
			}
		}

		if (cangku_list != null) {
			for (Gift_cangku cangku : cangku_list) {

				Element element_cangku = element_root.addElement("cangku");

				element_cangku.addComment("仓库对应礼品编号");
				Element element_ck_i_no = element_cangku.addElement("ck_i_no");
				element_ck_i_no.addCDATA(strFormat(cangku.getCk_i_no()));

				element_cangku.addComment("库房号");
				Element element_ck_kufang = element_cangku
						.addElement("ck_kufang");
				element_ck_kufang.addCDATA(strFormat(cangku.getCk_kufang()));

				element_cangku.addComment("货架号");
				Element element_ck_huojia = element_cangku
						.addElement("ck_huojia");
				element_ck_huojia.addCDATA(strFormat(cangku.getCk_huojia()));

				element_cangku.addComment("层数");
				Element element_ck_ceng = element_cangku.addElement("ck_ceng");
				element_ck_ceng.addCDATA(strFormat(cangku.getCk_ceng()));

				element_cangku.addComment("仓库额外属性1");
				Element element_ck_attribute1 = element_cangku
						.addElement("ck_attribute1");
				element_ck_attribute1.addCDATA(strFormat(cangku
						.getCk_attribute1()));

				element_cangku.addComment("仓库额外属性2");
				Element element_ck_attribute2 = element_cangku
						.addElement("ck_attribute2");
				element_ck_attribute2.addCDATA(strFormat(cangku
						.getCk_attribute2()));

				element_cangku.addComment("仓库额外属性3");
				Element element_ck_attribute3 = element_cangku
						.addElement("ck_attribute3");
				element_ck_attribute3.addCDATA(strFormat(cangku
						.getCk_attribute3()));

				element_cangku.addComment("仓库额外属性4");
				Element element_ck_attribute4 = element_cangku
						.addElement("ck_attribute4");
				element_ck_attribute4.addCDATA(strFormat(cangku
						.getCk_attribute4()));

				element_cangku.addComment("仓库额外属性5");
				Element element_ck_attribute5 = element_cangku
						.addElement("ck_attribute5");
				element_ck_attribute5.addCDATA(strFormat(cangku
						.getCk_attribute5()));
			}
		}

		if (people_list != null) {
			for (Gift_peoples people : people_list) {

				Element element_peoples = element_root.addElement("peoples");

				element_peoples.addComment("姓名");
				Element element_p_name = element_peoples.addElement("p_name");
				element_p_name.addCDATA(strFormat(people.getP_name()));

				element_peoples.addComment("0赠礼人，1受礼人");
				Element element_p_flag = element_peoples.addElement("p_flag");
				element_p_flag.addCDATA(intFormat(people.getP_flag()));

				element_peoples.addComment("0本人，1夫人，2夫妇");
				Element element_p_type = element_peoples.addElement("p_type");
				element_p_type.addCDATA(intFormat(people.getP_type()));

				element_peoples.addComment("配偶姓名");
				Element element_p_spouse = element_peoples
						.addElement("p_spouse");
				element_p_spouse.addCDATA(strFormat(people.getP_spouse()));

				element_peoples.addComment("所属国籍");
				Element element_p_country = element_peoples
						.addElement("p_country");
				element_p_country.addCDATA(strFormat(people.getP_country()));

				element_peoples.addComment("所属部门");
				Element element_p_bm = element_peoples.addElement("p_bm");
				element_p_bm.addCDATA(strFormat(people.getP_bm()));

				element_peoples.addComment("头衔");
				Element element_p_title = element_peoples.addElement("p_title");
				element_p_title.addCDATA(strFormat(people.getP_title()));

				element_peoples.addComment("人额外属性1");
				Element element_p_attribute1 = element_peoples
						.addElement("p_attribute1");
				element_p_attribute1.addCDATA(strFormat(people
						.getP_attribute1()));

				element_peoples.addComment("人额外属性2");
				Element element_p_attribute2 = element_peoples
						.addElement("p_attribute2");
				element_p_attribute2.addCDATA(strFormat(people
						.getP_attribute2()));

				element_peoples.addComment("人额外属性3");
				Element element_p_attribute3 = element_peoples
						.addElement("p_attribute3");
				element_p_attribute3.addCDATA(strFormat(people
						.getP_attribute3()));

				element_peoples.addComment("人额外属性4");
				Element element_p_attribute4 = element_peoples
						.addElement("p_attribute4");
				element_p_attribute4.addCDATA(strFormat(people
						.getP_attribute4()));

				element_peoples.addComment("人额外属性5");
				Element element_p_attribute5 = element_peoples
						.addElement("p_attribute5");
				element_p_attribute5.addCDATA(strFormat(people
						.getP_attribute5()));
			}
		}

		return document;
	}

	private String strFormat(String string) {
		if (StringUtils.isNotEmpty(string)) {
			return string;
		} else {
			return "";
		}
	}

	private String intFormat(Integer integer) {
		if (null == integer) {
			return "";
		} else {
			return integer.toString();
		}
	}

}