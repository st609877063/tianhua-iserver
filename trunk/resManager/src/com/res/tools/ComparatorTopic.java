package com.res.tools;

import java.util.Comparator;

import com.res.bean.ResOrders;

//具体的比较类，实现Comparator接口
public class ComparatorTopic implements Comparator {

	public int compare(Object arg0, Object arg1) {
		ResOrders obj0 = (ResOrders) arg0;
		ResOrders obj1 = (ResOrders) arg1;

		// 比较orderNo， orderType
		int flag = obj0.getOrderNo().compareTo(obj1.getOrderNo());
		if (flag == 0) {
			return obj1.getOrderType().compareTo(obj0.getOrderType());
		} else {
			return flag;
		}
	}
}
