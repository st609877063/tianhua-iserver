package com.gift.vo;

import java.util.List;

import com.gift.bean.Gift_cangku;
import com.gift.bean.Gift_fujian;
import com.gift.bean.Gift_items;
import com.gift.bean.Gift_peoples;

public class dataVo extends Gift_items
{
	private static final long	serialVersionUID	= 1L;

	private Gift_peoples		zlr_info;

	private Gift_peoples		slr_info;

	private Gift_cangku			cangku_info;

	private List<Gift_fujian>	fujian_info;

	public Gift_peoples getZlr_info()
	{
		return zlr_info;
	}

	public void setZlr_info(Gift_peoples zlr_info)
	{
		this.zlr_info = zlr_info;
	}

	public Gift_peoples getSlr_info()
	{
		return slr_info;
	}

	public void setSlr_info(Gift_peoples slr_info)
	{
		this.slr_info = slr_info;
	}

	public Gift_cangku getCangku_info()
	{
		return cangku_info;
	}

	public void setCangku_info(Gift_cangku cangku_info)
	{
		this.cangku_info = cangku_info;
	}

	public List<Gift_fujian> getFujian_info()
	{
		return fujian_info;
	}

	public void setFujian_info(List<Gift_fujian> fujian_info)
	{
		this.fujian_info = fujian_info;
	}

}