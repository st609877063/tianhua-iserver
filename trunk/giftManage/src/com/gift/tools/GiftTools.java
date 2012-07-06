package com.gift.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;
import com.gift.vo.dataVo;
import com.gift.vo.lightbox_fujian_vo;
import com.gift.vo.lightbox_item_vo;

public class GiftTools
{

	public List<Gift_items> processGiftItemList(List<Gift_items> itemList, List<Gift_peoples> peopleList)
	{
		List<Gift_items> rtnItemsList = new ArrayList<Gift_items>();

		Map<Integer, String> peopleMap = new HashMap<Integer, String>();
		for (int i = 0; peopleList != null && peopleList.size() > 0 && i < peopleList.size(); i++)
		{
			peopleMap.put(peopleList.get(i).getP_id(), peopleList.get(i).getP_name());
		}

		Gift_items itemVo = null;
		for (int i = 0; itemList != null && itemList.size() > 0 && i < itemList.size(); i++)
		{
			itemVo = itemList.get(i);

			// 受赠时间（前台显示）i_sztime ==> i_sztime_show
			itemVo.setI_sztime_show(DateTools.getDisplayTime((long) itemVo.getI_sztime()));
			// 受礼人,赠礼人
			if (peopleMap != null && peopleMap.size() > 0)
			{
				itemVo.setI_zlr_name(peopleMap.get(itemVo.getI_zlr())); // 赠礼人
				itemVo.setI_slr_name(peopleMap.get(itemVo.getI_slr())); // 受礼人
			}

			rtnItemsList.add(itemVo);
		}

		return rtnItemsList;
	}

	/**
	 * 画册显示用,把List<Gift_items>和List<Gift_fujian>和List<Gift_peoples>
	 * 转换成List<lightbox_item_vo>
	 * 
	 * @param gift_items_list
	 * @param gift_fujian_list
	 * @param gift_peoples_list
	 * @return List<lightbox_item_vo>
	 */
	public List<lightbox_item_vo> processLightboxList(List<Gift_items> gift_items_list, List<Gift_fujian> gift_fujian_list, List<Gift_peoples> gift_peoples_list)
	{

		List<lightbox_item_vo> rtnList = new ArrayList<lightbox_item_vo>();

		Map<Integer, String> peopleMap = new HashMap<Integer, String>();
		for (int i = 0; gift_peoples_list != null && gift_peoples_list.size() > 0 && i < gift_peoples_list.size(); i++)
		{
			peopleMap.put(gift_peoples_list.get(i).getP_id(), gift_peoples_list.get(i).getP_name());
		}

		for (Gift_items item : gift_items_list)
		{

			// 礼品基本信息
			lightbox_item_vo item_vo = new lightbox_item_vo();
			item_vo.setI_no(item.getI_no());// 礼品编号
			item_vo.setI_name(item.getI_name());// 礼品名称
			if (peopleMap != null && peopleMap.size() > 0)
			{
				item_vo.setI_zlr_name(peopleMap.get(item.getI_zlr()));// 赠礼人信息(前台显示)
				item_vo.setI_slr_name(peopleMap.get(item.getI_slr()));// 受礼人信息(前台显示)
			}
			item_vo.setI_sztime_show(DateTools.getDisplayTime((long) item.getI_sztime()));// 受赠时间(前台显示)
			item_vo.setI_unit(item.getI_unit());// 单位(套，件)
			item_vo.setI_num(item.getI_num());// 礼品数量
			item_vo.setI_zhidi(item.getI_zhidi());// 礼品质地
			item_vo.setI_type(item.getI_type());// 礼品类型
			item_vo.setI_chandi(item.getI_chandi());// 礼品产地
			item_vo.setI_status(item.getI_status());// 礼品现状
			item_vo.setI_gongyi(item.getI_gongyi());// 礼品工艺
			item_vo.setI_background(item.getI_background());// 赠送背景
			item_vo.setI_desc(item.getI_desc());// 礼品说明
			item_vo.setI_memo(item.getI_memo());// 礼品备注

			// 礼品附件信息
			List<lightbox_fujian_vo> lightbox_fujian_vo_list = new ArrayList<lightbox_fujian_vo>();
			for (Gift_fujian fujian : gift_fujian_list)
			{
				lightbox_fujian_vo fujian_vo = new lightbox_fujian_vo();
				if (fujian.getFj_i_no().equals(item.getI_no()))
				{
					fujian_vo.setFj_name(fujian.getFj_name());// 附件名称
					fujian_vo.setFj_type(fujian.getFj_type());// 附件类型
					fujian_vo.setFj_path(fujian.getFj_path());// 附件地址
					fujian_vo.setFj_desc(fujian.getFj_desc());// 附件说明
					lightbox_fujian_vo_list.add(fujian_vo);
				}
			}

			item_vo.setLightbox_fujian_vo_list(lightbox_fujian_vo_list);

			rtnList.add(item_vo);
		}

		return rtnList;
	}

	/**
	 * 数据导入导出使用
	 * 把List<Gift_items>和List<Gift_cangku>和List<Gift_fujian>和List<Gift_peoples>
	 * 转换成List<dataVo>
	 * 
	 * @param list_gift_items
	 * @param list_gift_cangku
	 * @param list_gift_fujian
	 * @param list_gift_peoples
	 * @return List<dataVo>
	 */
	public List<dataVo> processDataVoList(List<Gift_items> list_gift_items, List<Gift_cangku> list_gift_cangku, List<Gift_fujian> list_gift_fujian, List<Gift_peoples> list_gift_peoples)
	{
		List<dataVo> list_dataVo = new ArrayList<dataVo>();

		if (list_gift_items != null && list_gift_items.size() > 0)
		{
			for (Gift_items item : list_gift_items)
			{
				dataVo vo = new dataVo();

				vo.setI_no(item.getI_no());// 礼品编号
				vo.setI_name(item.getI_name());// 礼品名称

				if (list_gift_peoples != null && list_gift_peoples.size() > 0)
				{
					for (Gift_peoples people : list_gift_peoples)
					{
						Gift_peoples zlr = new Gift_peoples();
						Gift_peoples slr = new Gift_peoples();

						if (item.getI_zlr() == people.getP_id())
						{
							zlr = people;
							vo.setZlr_info(zlr);// 赠礼人信息
						}
						if (item.getI_slr() == people.getP_id())
						{
							slr = people;
							vo.setSlr_info(slr);// 受礼人信息
						}
					}
				}

				vo.setI_sztime(item.getI_sztime());// 受赠时间
				vo.setI_unit(item.getI_unit());// 单位(套，件)
				vo.setI_num(item.getI_num());// 礼品数量
				vo.setI_zhidi(item.getI_zhidi());// 礼品质地
				vo.setI_type(item.getI_type());// 礼品类型
				vo.setI_chandi(item.getI_chandi());// 礼品产地
				vo.setI_status(item.getI_status());// 礼品现状
				vo.setI_gongyi(item.getI_gongyi());// 礼品工艺
				vo.setI_background(item.getI_background());// 赠送背景
				vo.setI_desc(item.getI_desc());// 礼品说明
				vo.setI_memo(item.getI_memo());// 礼品备注
				vo.setI_attribute1(item.getI_attribute1());// 礼品额外属性1
				vo.setI_attribute2(item.getI_attribute2());// 礼品额外属性2
				vo.setI_attribute3(item.getI_attribute3());// 礼品额外属性3
				vo.setI_attribute4(item.getI_attribute4());// 礼品额外属性4
				vo.setI_attribute5(item.getI_attribute5());// 礼品额外属性5
				vo.setI_createtime(item.getI_createtime());// 创建时间
				vo.setI_adduser(item.getI_adduser());// 创建人
				vo.setI_qrcode(item.getI_qrcode());// 二维码

				if (list_gift_cangku != null && list_gift_cangku.size() > 0)
				{
					for (Gift_cangku cangku : list_gift_cangku)
					{
						Gift_cangku _cangku = new Gift_cangku();

						if (item.getI_no() == cangku.getCk_i_no())
						{
							_cangku = cangku;
							vo.setCangku_info(_cangku);// 仓库信息
						}
					}
				}

				if (list_gift_fujian != null && list_gift_fujian.size() > 0)
				{
					List<Gift_fujian> list_fujian = new ArrayList<Gift_fujian>();
					for (Gift_fujian fujian : list_gift_fujian)
					{
						if (item.getI_no().equals(fujian.getFj_i_no()))
						{
							list_fujian.add(fujian);// 附件信息
						}
					}
					vo.setFujian_info(list_fujian);
				}

				list_dataVo.add(vo);
			}
		}
		else
		{
			return null;
		}

		return list_dataVo;
	}

}