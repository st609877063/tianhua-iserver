package com.gift.action.gift_data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;

import com.gift.action.BaseAction;
import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.service.Gift_cangkuService;
import com.gift.service.Gift_fujianService;
import com.gift.service.Gift_itemsService;
import com.gift.service.Gift_peoplesService;
import com.gift.tools.DateTools;
import com.gift.tools.GiftTools;
import com.gift.vo.dataVo;

@Controller
public class Gift_dataAction extends BaseAction
{
	private static final long	serialVersionUID	= 1L;
	private File				uploadFile;
	private InputStream			fileInputStream;
	private Gift_itemsService	service_gift_items;
	private Gift_fujianService	service_gift_fujian;
	private Gift_peoplesService	service_gift_peoples;
	private Gift_cangkuService	service_gift_cangku;

	/**
	 * action exportXMLPage
	 * 
	 * @return
	 */
	public String exportXMLPage()
	{
		return SUCCESS;
	}

	/**
	 * action exportXML
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public String exportXML() throws Exception
	{
		// 准备数据
		List<Gift_items> items_list = new ArrayList<Gift_items>();
		items_list = service_gift_items.findAll();

		List<Gift_fujian> fujian_list = new ArrayList<Gift_fujian>();
		fujian_list = service_gift_fujian.findAll();

		List<Gift_peoples> people_list = new ArrayList<Gift_peoples>();
		people_list = service_gift_peoples.findAll();

		List<Gift_cangku> cangku_list = new ArrayList<Gift_cangku>();
		cangku_list = service_gift_cangku.findAll(0, 0);

		// 生成xml
		GiftTools tools = new GiftTools();
		List<dataVo> list_dataVo = new ArrayList<dataVo>();
		list_dataVo = tools.processDataVoList(items_list, cangku_list, fujian_list, people_list);

		if (list_dataVo != null && list_dataVo.size() > 0)
		{
			Document document = DocumentHelper.createDocument();
			Element element_root = document.addElement("gifts");

			for (dataVo vo : list_dataVo)
			{
				Element element_items = element_root.addElement("items");

				element_items.addComment("礼品编号");
				Element element_i_no = element_items.addElement("i_no");
				element_i_no.addCDATA(strFormat(vo.getI_no()));

				element_items.addComment("礼品名称");
				Element element_i_name = element_items.addElement("i_name");
				element_i_name.addCDATA(strFormat(vo.getI_name()));

				element_items.addComment("赠礼人信息");
				Element element_zlr_info = element_items.addElement("zlr_info");

				element_zlr_info.addComment("赠礼人姓名");
				Element element_zlr_p_name = element_zlr_info.addElement("p_name");
				element_zlr_p_name.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_name()));

				element_zlr_info.addComment("0赠礼人");
				Element element_zlr_p_flag = element_zlr_info.addElement("p_flag");
				element_zlr_p_flag.addCDATA(vo.getZlr_info() == null ? "" : intFormat(vo.getZlr_info().getP_flag()));

				element_zlr_info.addComment("0本人，1夫人，2夫妇");
				Element element_zlr_p_type = element_zlr_info.addElement("p_type");
				element_zlr_p_type.addCDATA(vo.getZlr_info() == null ? "" : intFormat(vo.getZlr_info().getP_type()));

				element_zlr_info.addComment("配偶姓名");
				Element element_zlr_p_spouse = element_zlr_info.addElement("p_spouse");
				element_zlr_p_spouse.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_spouse()));

				element_zlr_info.addComment("国籍");
				Element element_zlr_p_country = element_zlr_info.addElement("p_country");
				element_zlr_p_country.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_country()));

				element_zlr_info.addComment("部门");
				Element element_zlr_p_bm = element_zlr_info.addElement("p_bm");
				element_zlr_p_bm.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_bm()));

				element_zlr_info.addComment("头衔");
				Element element_zlr_p_title = element_zlr_info.addElement("p_title");
				element_zlr_p_title.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_title()));

				element_zlr_info.addComment("赠礼人额外属性1");
				Element element_zlr_p_attribute1 = element_zlr_info.addElement("p_attribute1");
				element_zlr_p_attribute1.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_attribute1()));

				element_zlr_info.addComment("赠礼人额外属性2");
				Element element_zlr_p_attribute2 = element_zlr_info.addElement("p_attribute2");
				element_zlr_p_attribute2.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_attribute2()));

				element_zlr_info.addComment("赠礼人额外属性3");
				Element element_zlr_p_attribute3 = element_zlr_info.addElement("p_attribute3");
				element_zlr_p_attribute3.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_attribute3()));

				element_zlr_info.addComment("赠礼人额外属性4");
				Element element_zlr_p_attribute4 = element_zlr_info.addElement("p_attribute4");
				element_zlr_p_attribute4.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_attribute4()));

				element_zlr_info.addComment("赠礼人额外属性5");
				Element element_zlr_p_attribute5 = element_zlr_info.addElement("p_attribute5");
				element_zlr_p_attribute5.addCDATA(vo.getZlr_info() == null ? "" : strFormat(vo.getZlr_info().getP_attribute5()));

				element_items.addComment("受礼人信息");
				Element element_slr_info = element_items.addElement("slr_info");

				element_slr_info.addComment("受礼人姓名");
				Element element_slr_p_name = element_slr_info.addElement("p_name");
				element_slr_p_name.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_name()));

				element_slr_info.addComment("1受礼人");
				Element element_slr_p_flag = element_slr_info.addElement("p_flag");
				element_slr_p_flag.addCDATA(vo.getSlr_info() == null ? "" : intFormat(vo.getSlr_info().getP_flag()));

				element_slr_info.addComment("0本人，1夫人，2夫妇");
				Element element_slr_p_type = element_slr_info.addElement("p_type");
				element_slr_p_type.addCDATA(vo.getSlr_info() == null ? "" : intFormat(vo.getSlr_info().getP_type()));

				element_slr_info.addComment("配偶姓名");
				Element element_slr_p_spouse = element_slr_info.addElement("p_spouse");
				element_slr_p_spouse.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_spouse()));

				element_slr_info.addComment("国籍");
				Element element_slr_p_country = element_slr_info.addElement("p_country");
				element_slr_p_country.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_country()));

				element_slr_info.addComment("部门");
				Element element_slr_p_bm = element_slr_info.addElement("p_bm");
				element_slr_p_bm.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_bm()));

				element_slr_info.addComment("头衔");
				Element element_slr_p_title = element_slr_info.addElement("p_title");
				element_slr_p_title.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_title()));

				element_slr_info.addComment("受礼人额外属性1");
				Element element_slr_p_attribute1 = element_slr_info.addElement("p_attribute1");
				element_slr_p_attribute1.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_attribute1()));

				element_slr_info.addComment("受礼人额外属性2");
				Element element_slr_p_attribute2 = element_slr_info.addElement("p_attribute2");
				element_slr_p_attribute2.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_attribute2()));

				element_slr_info.addComment("受礼人额外属性3");
				Element element_slr_p_attribute3 = element_slr_info.addElement("p_attribute3");
				element_slr_p_attribute3.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_attribute3()));

				element_slr_info.addComment("受礼人额外属性4");
				Element element_slr_p_attribute4 = element_slr_info.addElement("p_attribute4");
				element_slr_p_attribute4.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_attribute4()));

				element_slr_info.addComment("受礼人额外属性5");
				Element element_slr_p_attribute5 = element_slr_info.addElement("p_attribute5");
				element_slr_p_attribute5.addCDATA(vo.getSlr_info() == null ? "" : strFormat(vo.getSlr_info().getP_attribute5()));

				element_items.addComment("受赠时间");
				Element element_i_sztime = element_items.addElement("i_sztime");
				String str_i_sztime = DateTools.getDisplayTime((long) vo.getI_sztime());
				element_i_sztime.addCDATA(str_i_sztime);

				element_items.addComment("单位");
				Element element_i_unit = element_items.addElement("i_unit");
				element_i_unit.addCDATA(strFormat(vo.getI_unit()));

				element_items.addComment("数量");
				Element element_i_num = element_items.addElement("i_num");
				element_i_num.addCDATA(intFormat(vo.getI_num()));

				element_items.addComment("质地");
				Element element_i_zhidi = element_items.addElement("i_zhidi");
				element_i_zhidi.addCDATA(strFormat(vo.getI_zhidi()));

				element_items.addComment("类型");
				Element element_i_type = element_items.addElement("i_type");
				element_i_type.addCDATA(strFormat(vo.getI_type()));

				element_items.addComment("产地");
				Element element_i_chandi = element_items.addElement("i_chandi");
				element_i_chandi.addCDATA(strFormat(vo.getI_chandi()));

				element_items.addComment("现状");
				Element element_i_status = element_items.addElement("i_status");
				element_i_status.addCDATA(strFormat(vo.getI_status()));

				element_items.addComment("工艺");
				Element element_i_gongyi = element_items.addElement("i_gongyi");
				element_i_gongyi.addCDATA(strFormat(vo.getI_gongyi()));

				element_items.addComment("背景");
				Element element_i_background = element_items.addElement("i_background");
				element_i_background.addCDATA(strFormat(vo.getI_background()));

				element_items.addComment("说明");
				Element element_i_desc = element_items.addElement("i_desc");
				element_i_desc.addCDATA(strFormat(vo.getI_desc()));

				element_items.addComment("备注");
				Element element_i_memo = element_items.addElement("i_memo");
				element_i_memo.addCDATA(strFormat(vo.getI_memo()));

				element_items.addComment("礼品额外属性1");
				Element element_i_attribute1 = element_items.addElement("i_attribute1");
				element_i_attribute1.addCDATA(strFormat(vo.getI_attribute1()));

				element_items.addComment("礼品额外属性2");
				Element element_i_attribute2 = element_items.addElement("i_attribute2");
				element_i_attribute2.addCDATA(strFormat(vo.getI_attribute2()));

				element_items.addComment("礼品额外属性3");
				Element element_i_attribute3 = element_items.addElement("i_attribute3");
				element_i_attribute3.addCDATA(strFormat(vo.getI_attribute3()));

				element_items.addComment("礼品额外属性4");
				Element element_i_attribute4 = element_items.addElement("i_attribute4");
				element_i_attribute4.addCDATA(strFormat(vo.getI_attribute4()));

				element_items.addComment("礼品额外属性5");
				Element element_i_attribute5 = element_items.addElement("i_attribute5");
				element_i_attribute5.addCDATA(strFormat(vo.getI_attribute5()));

				element_items.addComment("二维码");
				Element element_i_qrcode = element_items.addElement("i_qrcode");
				element_i_qrcode.addCDATA(strFormat(vo.getI_qrcode()));

				element_items.addComment("仓库信息");
				Element element_cangku_info = element_items.addElement("cangku_info");

				element_cangku_info.addComment("礼品编号(对应gift_items中i_no)");
				Element element_ck_i_no = element_cangku_info.addElement("ck_i_no");
				element_ck_i_no.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_i_no()));

				element_cangku_info.addComment("库房号");
				Element element_ck_kufang = element_cangku_info.addElement("ck_kufang");
				element_ck_kufang.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_kufang()));

				element_cangku_info.addComment("货架号");
				Element element_ck_huojia = element_cangku_info.addElement("ck_huojia");
				element_ck_huojia.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_huojia()));

				element_cangku_info.addComment("层数");
				Element element_ck_ceng = element_cangku_info.addElement("ck_ceng");
				element_ck_ceng.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_ceng()));

				element_cangku_info.addComment("仓库额外属性1");
				Element element_ck_attribute1 = element_cangku_info.addElement("ck_attribute1");
				element_ck_attribute1.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_attribute1()));

				element_cangku_info.addComment("仓库额外属性2");
				Element element_ck_attribute2 = element_cangku_info.addElement("ck_attribute2");
				element_ck_attribute2.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_attribute2()));

				element_cangku_info.addComment("仓库额外属性3");
				Element element_ck_attribute3 = element_cangku_info.addElement("ck_attribute3");
				element_ck_attribute3.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_attribute3()));

				element_cangku_info.addComment("仓库额外属性4");
				Element element_ck_attribute4 = element_cangku_info.addElement("ck_attribute4");
				element_ck_attribute4.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_attribute4()));

				element_cangku_info.addComment("仓库额外属性5");
				Element element_ck_attribute5 = element_cangku_info.addElement("ck_attribute5");
				element_ck_attribute5.addCDATA(vo.getCangku_info() == null ? "" : strFormat(vo.getCangku_info().getCk_attribute5()));

				if (vo.getFujian_info() != null && vo.getFujian_info().size() > 0)
				{
					for (Gift_fujian fujian : vo.getFujian_info())
					{
						element_items.addComment("附件信息");
						Element element_fujian_info = element_items.addElement("fujian_info");

						element_fujian_info.addComment("礼品编号(对应gift_items中i_no)");
						Element element_fj_i_no = element_fujian_info.addElement("fj_i_no");
						element_fj_i_no.addCDATA(strFormat(fujian.getFj_i_no()));

						element_fujian_info.addComment("附件名称");
						Element element_fj_name = element_fujian_info.addElement("fj_name");
						element_fj_name.addCDATA(strFormat(fujian.getFj_name()));

						element_fujian_info.addComment("附件地址");
						Element element_fj_path = element_fujian_info.addElement("fj_path");
						element_fj_path.addCDATA(strFormat(fujian.getFj_path()));

						element_fujian_info.addComment("附件类型");
						Element element_fj_type = element_fujian_info.addElement("fj_type");
						element_fj_type.addCDATA(strFormat(fujian.getFj_type()));

						element_fujian_info.addComment("附件说明");
						Element element_fj_desc = element_fujian_info.addElement("fj_desc");
						element_fj_desc.addCDATA(strFormat(fujian.getFj_desc()));

						element_fujian_info.addComment("附件额外属性1");
						Element element_fj_attribute1 = element_fujian_info.addElement("fj_attribute1");
						element_fj_attribute1.addCDATA(strFormat(fujian.getFj_attribute1()));

						element_fujian_info.addComment("附件额外属性2");
						Element element_fj_attribute2 = element_fujian_info.addElement("fj_attribute2");
						element_fj_attribute2.addCDATA(strFormat(fujian.getFj_attribute2()));

						element_fujian_info.addComment("附件额外属性3");
						Element element_fj_attribute3 = element_fujian_info.addElement("fj_attribute3");
						element_fj_attribute3.addCDATA(strFormat(fujian.getFj_attribute3()));

						element_fujian_info.addComment("附件额外属性4");
						Element element_fj_attribute4 = element_fujian_info.addElement("fj_attribute4");
						element_fj_attribute4.addCDATA(strFormat(fujian.getFj_attribute4()));

						element_fujian_info.addComment("附件额外属性5");
						Element element_fj_attribute5 = element_fujian_info.addElement("fj_attribute5");
						element_fj_attribute5.addCDATA(strFormat(fujian.getFj_attribute5()));
					}
				}
			}

			fileInputStream = new ByteArrayInputStream(document.asXML().getBytes("utf-8"));

			return SUCCESS;
		}
		else
		{
			return ERROR;
		}
	}

	/**
	 * action importXML
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public String importXML()
	{
		try
		{
			if (uploadFile != null)
			{
				List<dataVo> list_dataVo = new ArrayList<dataVo>();
				List<Gift_fujian> list_fujian = new ArrayList<Gift_fujian>();

				SAXReader reader = new SAXReader();
				Document xmlDoc = reader.read(uploadFile);
				Element root = xmlDoc.getRootElement();
				List itemsNodesList = xmlDoc.selectNodes("//gifts/items");
				for (Iterator iterator1 = itemsNodesList.iterator(); iterator1.hasNext();)
				{
					Element element1 = (Element) iterator1.next();
					Iterator iterator2 = element1.elementIterator();

					dataVo vo = new dataVo();

					while (iterator2.hasNext())
					{
						Element element2 = (Element) iterator2.next();

						if (element2 == null) break;

						if ("i_no".equals(element2.getName())) vo.setI_no(element2.getText());
						else if ("i_name".equals(element2.getName())) vo.setI_name(element2.getText());
						else if ("zlr_info".equals(element2.getName()))
						{
							Iterator iterator_zlr_info = element2.elementIterator();

							Gift_peoples zlr = new Gift_peoples();

							while (iterator_zlr_info.hasNext())
							{
								Element element_zlr_info = (Element) iterator_zlr_info.next();

								if (element_zlr_info == null) break;

								if ("p_name".equals(element_zlr_info.getName())) zlr.setP_name(element_zlr_info.getText());
								else if ("p_flag".equals(element_zlr_info.getName())) zlr.setP_flag(getInteger(element_zlr_info.getText()));
								else if ("p_type".equals(element_zlr_info.getName())) zlr.setP_type(getInteger(element_zlr_info.getText()));
								else if ("p_spouse".equals(element_zlr_info.getName())) zlr.setP_spouse(element_zlr_info.getText());
								else if ("p_country".equals(element_zlr_info.getName())) zlr.setP_country(element_zlr_info.getText());
								else if ("p_bm".equals(element_zlr_info.getName())) zlr.setP_bm(element_zlr_info.getText());
								else if ("p_title".equals(element_zlr_info.getName())) zlr.setP_title(element_zlr_info.getText());
								else if ("p_attribute1".equals(element_zlr_info.getName())) zlr.setP_attribute1(element_zlr_info.getText());
								else if ("p_attribute2".equals(element_zlr_info.getName())) zlr.setP_attribute2(element_zlr_info.getText());
								else if ("p_attribute3".equals(element_zlr_info.getName())) zlr.setP_attribute3(element_zlr_info.getText());
								else if ("p_attribute4".equals(element_zlr_info.getName())) zlr.setP_attribute4(element_zlr_info.getText());
								else if ("p_attribute5".equals(element_zlr_info.getName())) zlr.setP_attribute5(element_zlr_info.getText());
							}

							vo.setZlr_info(zlr);
						}
						else if ("slr_info".equals(element2.getName()))
						{
							Iterator iterator_slr_info = element2.elementIterator();

							Gift_peoples slr = new Gift_peoples();

							while (iterator_slr_info.hasNext())
							{
								Element element_slr_info = (Element) iterator_slr_info.next();

								if (element_slr_info == null) break;

								if ("p_name".equals(element_slr_info.getName())) slr.setP_name(element_slr_info.getText());
								else if ("p_flag".equals(element_slr_info.getName())) slr.setP_flag(getInteger(element_slr_info.getText()));
								else if ("p_type".equals(element_slr_info.getName())) slr.setP_type(getInteger(element_slr_info.getText()));
								else if ("p_spouse".equals(element_slr_info.getName())) slr.setP_spouse(element_slr_info.getText());
								else if ("p_country".equals(element_slr_info.getName())) slr.setP_country(element_slr_info.getText());
								else if ("p_bm".equals(element_slr_info.getName())) slr.setP_bm(element_slr_info.getText());
								else if ("p_title".equals(element_slr_info.getName())) slr.setP_title(element_slr_info.getText());
								else if ("p_attribute1".equals(element_slr_info.getName())) slr.setP_attribute1(element_slr_info.getText());
								else if ("p_attribute2".equals(element_slr_info.getName())) slr.setP_attribute2(element_slr_info.getText());
								else if ("p_attribute3".equals(element_slr_info.getName())) slr.setP_attribute3(element_slr_info.getText());
								else if ("p_attribute4".equals(element_slr_info.getName())) slr.setP_attribute4(element_slr_info.getText());
								else if ("p_attribute5".equals(element_slr_info.getName())) slr.setP_attribute5(element_slr_info.getText());
							}

							vo.setSlr_info(slr);
						}
						else if ("i_sztime".equals(element2.getName()))
						{
							String i_sztime = element2.getText();
							Integer int_i_sztime = DateTools.getTimestamp(i_sztime).intValue();
							vo.setI_sztime(int_i_sztime);
						}
						else if ("i_unit".equals(element2.getName())) vo.setI_unit(element2.getText());
						else if ("i_num".equals(element2.getName())) vo.setI_num(getInteger(element2.getText()));
						else if ("i_zhidi".equals(element2.getName())) vo.setI_zhidi(element2.getText());
						else if ("i_type".equals(element2.getName())) vo.setI_type(element2.getText());
						else if ("i_chandi".equals(element2.getName())) vo.setI_chandi(element2.getText());
						else if ("i_status".equals(element2.getName())) vo.setI_status(element2.getText());
						else if ("i_gongyi".equals(element2.getName())) vo.setI_gongyi(element2.getText());
						else if ("i_background".equals(element2.getName())) vo.setI_background(element2.getText());
						else if ("i_desc".equals(element2.getName())) vo.setI_desc(element2.getText());
						else if ("i_memo".equals(element2.getName())) vo.setI_memo(element2.getText());
						else if ("i_attribute1".equals(element2.getName())) vo.setI_attribute1(element2.getText());
						else if ("i_attribute2".equals(element2.getName())) vo.setI_attribute2(element2.getText());
						else if ("i_attribute3".equals(element2.getName())) vo.setI_attribute3(element2.getText());
						else if ("i_attribute4".equals(element2.getName())) vo.setI_attribute4(element2.getText());
						else if ("i_attribute5".equals(element2.getName())) vo.setI_attribute5(element2.getText());
						else if ("i_qrcode".equals(element2.getName())) vo.setI_qrcode(element2.getText());
						else if ("cangku_info".equals(element2.getName()))
						{
							Iterator iterator_cangku_info = element2.elementIterator();

							Gift_cangku cangku = new Gift_cangku();

							while (iterator_cangku_info.hasNext())
							{
								Element element_cangku_info = (Element) iterator_cangku_info.next();

								if (element_cangku_info == null) break;

								if ("ck_i_no".equals(element_cangku_info.getName())) cangku.setCk_i_no(vo.getI_no());
								else if ("ck_kufang".equals(element_cangku_info.getName())) cangku.setCk_kufang(element_cangku_info.getText());
								else if ("ck_huojia".equals(element_cangku_info.getName())) cangku.setCk_huojia(element_cangku_info.getText());
								else if ("ck_ceng".equals(element_cangku_info.getName())) cangku.setCk_ceng(element_cangku_info.getText());
								else if ("ck_attribute1".equals(element_cangku_info.getName())) cangku.setCk_attribute1(element_cangku_info.getText());
								else if ("ck_attribute2".equals(element_cangku_info.getName())) cangku.setCk_attribute2(element_cangku_info.getText());
								else if ("ck_attribute3".equals(element_cangku_info.getName())) cangku.setCk_attribute3(element_cangku_info.getText());
								else if ("ck_attribute4".equals(element_cangku_info.getName())) cangku.setCk_attribute4(element_cangku_info.getText());
								else if ("ck_attribute5".equals(element_cangku_info.getName())) cangku.setCk_attribute5(element_cangku_info.getText());
							}

							vo.setCangku_info(cangku);
						}
						else if ("fujian_info".equals(element2.getName()))
						{
							Iterator iterator_fujian_info = element2.elementIterator();

							Gift_fujian fujian = new Gift_fujian();

							while (iterator_fujian_info.hasNext())
							{
								Element element_fujian_info = (Element) iterator_fujian_info.next();

								if (element_fujian_info == null) break;

								if ("fj_i_no".equals(element_fujian_info.getName())) fujian.setFj_i_no(vo.getI_no());
								else if ("fj_name".equals(element_fujian_info.getName())) fujian.setFj_name(element_fujian_info.getText());
								else if ("fj_path".equals(element_fujian_info.getName())) fujian.setFj_path(element_fujian_info.getText());
								else if ("fj_type".equals(element_fujian_info.getName())) fujian.setFj_type(element_fujian_info.getText());
								else if ("fj_desc".equals(element_fujian_info.getName())) fujian.setFj_desc(element_fujian_info.getText());
								else if ("fj_attribute1".equals(element_fujian_info.getName())) fujian.setFj_attribute1(element_fujian_info.getText());
								else if ("fj_attribute2".equals(element_fujian_info.getName())) fujian.setFj_attribute2(element_fujian_info.getText());
								else if ("fj_attribute3".equals(element_fujian_info.getName())) fujian.setFj_attribute3(element_fujian_info.getText());
								else if ("fj_attribute4".equals(element_fujian_info.getName())) fujian.setFj_attribute4(element_fujian_info.getText());
								else if ("fj_attribute5".equals(element_fujian_info.getName())) fujian.setFj_attribute5(element_fujian_info.getText());
							}

							list_fujian.add(fujian);
						}
					}
					vo.setFujian_info(list_fujian);
					list_dataVo.add(vo);
				}

				// 添加礼品到数据库
				for (dataVo vo : list_dataVo)
				{
					// 添加赠礼人信息到数据库
					if (vo.getZlr_info() != null)
					{
						// 检查是否是已有的赠礼人
						List<Gift_peoples> list_zlr = new ArrayList<Gift_peoples>();
						list_zlr = service_gift_peoples.findByName(vo.getZlr_info().getP_name());
						if (list_zlr != null && list_zlr.size() > 0)
						{
							// 有赠礼人的情况
							// 更新赠礼人到数据库
							service_gift_peoples.update(vo.getZlr_info());
							// 更新礼品信息赠礼人的ID
							vo.setI_zlr(list_zlr.get(0).getP_id());
						}
						else
						{
							// 没有赠礼人的情况
							// 添加赠礼人到数据库
							service_gift_peoples.save(vo.getZlr_info());
							// 更新礼品信息赠礼人的ID
							list_zlr.clear();
							list_zlr = service_gift_peoples.findByName(vo.getZlr_info().getP_name());
							if (list_zlr != null && list_zlr.size() > 0) vo.setI_zlr(list_zlr.get(0).getP_id());
						}
					}

					// 添加受礼人信息到数据库
					if (vo.getSlr_info() != null)
					{
						// 检查是否是已有的受礼人
						List<Gift_peoples> list_slr = new ArrayList<Gift_peoples>();
						list_slr = service_gift_peoples.findByName(vo.getSlr_info().getP_name());
						if (list_slr != null && list_slr.size() > 0)
						{
							// 有受礼人的情况
							// 更新受礼人到数据库
							service_gift_peoples.update(vo.getSlr_info());
							// 更新礼品信息受礼人的ID
							vo.setI_zlr(list_slr.get(0).getP_id());
						}
						else
						{
							// 没有受礼人的情况
							// 添加受礼人到数据库
							service_gift_peoples.save(vo.getSlr_info());
							// 更新礼品信息受礼人的ID
							list_slr.clear();
							list_slr = service_gift_peoples.findByName(vo.getSlr_info().getP_name());
							if (list_slr != null && list_slr.size() > 0) vo.setI_slr(list_slr.get(0).getP_id());
						}
					}

					// 添加礼品信息到数据库
					// 判断是否有重复的礼品编号
					Gift_items items = new Gift_items();
					items = service_gift_items.findByItemNo(vo.getI_no());
					if (items != null)
					{
						// 有礼品的情况
						items.setI_no(vo.getI_no());
						items.setI_name(vo.getI_name());
						items.setI_zlr(vo.getI_zlr());
						items.setI_slr(vo.getI_slr());
						items.setI_sztime(vo.getI_sztime());
						items.setI_unit(vo.getI_unit());
						items.setI_num(vo.getI_num());
						items.setI_zhidi(vo.getI_zhidi());
						items.setI_type(vo.getI_type());
						items.setI_chandi(vo.getI_chandi());
						items.setI_status(vo.getI_status());
						items.setI_gongyi(vo.getI_gongyi());
						items.setI_background(vo.getI_background());
						items.setI_desc(vo.getI_desc());
						items.setI_memo(vo.getI_memo());
						items.setI_attribute1(vo.getI_attribute1());
						items.setI_attribute2(vo.getI_attribute2());
						items.setI_attribute3(vo.getI_attribute3());
						items.setI_attribute4(vo.getI_attribute4());
						items.setI_attribute5(vo.getI_attribute5());
						items.setI_qrcode(vo.getI_qrcode());

						service_gift_items.update(items);
					}
					else
					{
						// 没有礼品的情况
						service_gift_items.save(vo);
					}

					// 添加仓库信息到数据库
					if (vo.getCangku_info() != null)
					{
						Gift_cangku cangku = new Gift_cangku();
						cangku = service_gift_cangku.findByItemNo(vo.getI_no());
						if (cangku != null)
						{
							// 有仓库信息的情况
							service_gift_cangku.update(vo.getCangku_info());
						}
						else
						{
							// 没有仓库信息的情况
							service_gift_cangku.save(vo.getCangku_info());
						}
					}

					// 添加附件信息到数据库
					if (vo.getFujian_info() != null && vo.getFujian_info().size() > 0)
					{
						List<Gift_fujian> list_fujian1 = new ArrayList<Gift_fujian>();
						list_fujian1 = service_gift_fujian.findByItemNo(vo.getI_no());
						if (list_fujian1 != null && list_fujian1.size() > 0)
						{
							// 有附件信息的情况
							for (Gift_fujian fujian : list_fujian1)
							{
								service_gift_fujian.update(fujian);
							}
						}
						else
						{
							// 没有附件信息的情况
							for (Gift_fujian fujian : list_fujian1)
							{
								service_gift_fujian.save(fujian);
							}
						}
					}

				}

				return SUCCESS;
			}
			else
			{
				return ERROR;
			}
		}
		catch (Exception e)
		{
			return ERROR;
		}
	}

	private String strFormat(String string)
	{
		if (StringUtils.isNotEmpty(string)) return string;
		else return "";
	}

	private String intFormat(Integer integer)
	{
		if (null == integer) return "";
		else return integer.toString();
	}

	private Integer getInteger(String val)
	{
		try
		{
			Integer tmp = Integer.parseInt(val);
			return tmp;
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	public InputStream getFileInputStream()
	{
		return fileInputStream;
	}

	public Gift_itemsService getService_gift_items()
	{
		return service_gift_items;
	}

	@Resource(name = "gift_itemsServiceImpl")
	public void setService_Gift_items(Gift_itemsService service_gift_items)
	{
		this.service_gift_items = service_gift_items;
	}

	public Gift_fujianService getService_gift_fujian()
	{
		return service_gift_fujian;
	}

	@Resource(name = "gift_fujianServiceImpl")
	public void setService_gift_fujian(Gift_fujianService service_gift_fujian)
	{
		this.service_gift_fujian = service_gift_fujian;
	}

	public Gift_peoplesService getService_gift_peoples()
	{
		return service_gift_peoples;
	}

	@Resource(name = "gift_peoplesServiceImpl")
	public void setService_gift_peoples(Gift_peoplesService service_gift_peoples)
	{
		this.service_gift_peoples = service_gift_peoples;
	}

	public Gift_cangkuService getService_gift_cangku()
	{
		return service_gift_cangku;
	}

	@Resource(name = "gift_cangkuServiceImpl")
	public void setService_gift_cangku(Gift_cangkuService service_gift_cangku)
	{
		this.service_gift_cangku = service_gift_cangku;
	}

	public File getUploadFile()
	{
		return uploadFile;
	}

	public void setUploadFile(File uploadFile)
	{
		this.uploadFile = uploadFile;
	}

}