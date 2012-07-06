package com.gift.interceptor;

public class MySql5Dialect extends Dialect {
	@Override
	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

		pagingSelect.append(sql);

		pagingSelect.append(" limit " + offset + "," + limit);

		return pagingSelect.toString();
	}
}