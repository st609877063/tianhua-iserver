package com.people.dptwb.manager.home;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.people.dptwb.common.PageList;
import com.people.dptwb.common.Pages;
import com.people.dptwb.entity.FeChuguo;
import com.people.dptwb.entity.FeUsers;
import com.people.dptwb.manager.TBaseManager;

public class HrManager extends TBaseManager {
	private Logger logger = Logger.getLogger(HrManager.class);

	public PageList getUserListPaged(String bianhao, String truename,
			Pages pages, String contextPath) {
		PageList pl = new PageList();
		if (pages.getTotals() == -1) {
			pages.setTotals(getUserListTotal(bianhao, truename));
		}
		pages.doPageBreak();
		List l = getUserList(bianhao, truename, pages.getSpage(),
				pages.getPerPageNum(), contextPath);
		pl.setObjectList(l);
		pl.setPageShowString(pages.getListPageBreak());
		pl.setPages(pages);
		return pl;
	}

	public long getUserListTotal(String bianhao, String truename) {
		Connection r2Conn = null;
		StringBuilder sb = new StringBuilder();
		try {
			r2Conn = getReadConnection();
			sb.append("select count(*) num from fe_users u where 1=1 ");
			if (StringUtils.isNotBlank(bianhao)) {
				sb.append(" and u.bianhao='").append(bianhao).append("' ");
			}
			if (StringUtils.isNotBlank(truename)) {
				sb.append(" and u.truename like '%").append(truename)
						.append("%' ");
			}

			Map<String, Object> result = queryMap(r2Conn, sb.toString());
			int count = 0;
			if (result != null) {
				count = ((Long) result.get("num")).intValue();
			}
			return count;
		} catch (Exception e) {
			logger.error("HrManager====>getUserListTotal()" + e.getMessage());
		} finally {
			closeConnection(r2Conn);
		}
		return 0;
	}

	public List getUserList(String bianhao, String truename, int start,
			int num, String contextPath) {
		Connection mainConn = null;
		List<FeUsers> rtnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from fe_users u  where 1=1 ");
		if (StringUtils.isNotBlank(bianhao)) {
			sb.append(" and u.bianhao='").append(bianhao).append("' ");
		}
		if (StringUtils.isNotBlank(truename)) {
			sb.append(" and u.truename like %").append(truename).append("%");
		}
		sb.append(" order by u.userid asc ");
		if (num != -1) {
			sb.append(" limit ").append(start).append(", ").append(num);
		}

		try {
			mainConn = getReadConnection();
			rtnList = (List<FeUsers>) queryEntityList(mainConn, sb.toString(),
					new FeUsers());
		} catch (Exception e) {
			logger.error("HrManager====>getUserList时出错：" + e.getMessage(), e);
		} finally {
			closeConnection(mainConn);
		}
		return rtnList;
	}

	public List<FeChuguo> getUserChuguoList(String bianhao, int start, int num) {
		Connection mainConn = null;
		List<FeChuguo> rtnList = null;
		String sql = "select * from fe_chuguo where bianhao='" + bianhao
				+ "' order by pkid asc ";
		if (num != -1) {
			sql = sql + " limit " + start + ", " + num;
		}

		try {
			mainConn = getReadConnection();
			rtnList = (List<FeChuguo>) queryEntityList(mainConn, sql,
					new FeChuguo());
		} catch (Exception e) {
			logger.error(
					"HrManager====>getUserChuguoList时出错：" + e.getMessage(), e);
		} finally {
			closeConnection(mainConn);
		}
		return rtnList;
	}

	public List<String> getJigouDataList() {
		Connection mainConn = null;
		List<String> rtnList = null;
		String sql = "select distinct(jigou) from fe_users ";

		try {
			mainConn = getReadConnection();
			List<Map<String, Object>> tempList = query(mainConn, sql);
			if (tempList != null && tempList.size() > 0) {
				rtnList = new ArrayList<String>();

				String value = null;
				for (Map<String, Object> map : tempList) {
					value = (String) map.get("jigou");
					if (StringUtils.isBlank(value)) {
						value = "未填数据";
					}
					rtnList.add(value);
				}
			}

		} catch (Exception e) {
			logger.error("HrManager====>getJigouDataList时出错：" + e.getMessage(),
					e);
		} finally {
			closeConnection(mainConn);
		}
		return rtnList;
	}

	public List<String> getJibieDataList() {
		Connection mainConn = null;
		List<String> rtnList = null;
		String sql = "select distinct(jibie) from fe_users ";

		try {
			mainConn = getReadConnection();
			List<Map<String, Object>> tempList = query(mainConn, sql);
			if (tempList != null && tempList.size() > 0) {
				rtnList = new ArrayList<String>();

				String value = null;
				for (Map<String, Object> map : tempList) {
					value = (String) map.get("jibie");
					if (StringUtils.isBlank(value)) {
						value = "未填数据";
					}
					rtnList.add(value);
				}
			}

		} catch (Exception e) {
			logger.error("HrManager====>getJibieDataList时出错：" + e.getMessage(),
					e);
		} finally {
			closeConnection(mainConn);
		}
		return rtnList;
	}

	public Map<String, String> getUserChartData(String weidu, String jigou,
			String jibie) {
		Map<String, String> dataMap = null;
		// weidu:jigou或者jibie

		Connection mainConn = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" select ").append(weidu).append(", count(").append(weidu)
				.append(") as num from fe_users where 1=1 ");

		if (StringUtils.isNotBlank(jigou)) {
			sb.append(" and jigou='").append(jigou).append("' ");
		}
		if (StringUtils.isNotBlank(jibie)) {
			sb.append(" and jibie='").append(jibie).append("' ");
		}
		sb.append(" group by ").append(weidu);

		try {
			mainConn = getReadConnection();
			List<Map<String, Object>> tempList = query(mainConn, sb.toString());
			if (tempList != null && tempList.size() > 0) {
				dataMap = new HashMap<String, String>();
				String temp = null;
				for (Map<String, Object> map : tempList) {
					if(weidu.equals("sex")) {
						int sex = (Integer) map.get(weidu);
						if(sex == 0) {
							temp = "女";
						} else if(sex == 1) {
							temp = "男";
						} else if(sex == 2) {
							temp = "未填数据";
						} else {
							temp = "";
						} 
					} else {
						temp = (String) map.get(weidu);
					}
					if (StringUtils.isBlank(temp)) {
						temp = "未填数据";
					}
					dataMap.put(temp, String.valueOf((Long) map.get("num")));
				}
			}

		} catch (Exception e) {
			logger.error("HrManager====>getUserChartData时出错：" + e.getMessage(),
					e);
		} finally {
			closeConnection(mainConn);
		}

		return dataMap;
	}

	public Map<String, String> testquery(String weidu, String jigou,
			String jibie) {
		Map<String, String> dataMap = null;

		Connection mainConn = null;
		String sql = "";

		try {
			mainConn = getReadConnection();
			ResultSetHandler rsh = new MapListHandler();
			QueryRunner qr = new QueryRunner();
			ArrayList<Map> result = (ArrayList<Map>) qr.query(mainConn, sql, rsh);
			if (result.size() > 0) {
			}
		} catch (Exception e) {
			logger.error("HrManager====>testquery时出错：" + e.getMessage(), e);
		} finally {
			closeConnection(mainConn);
		}

		return dataMap;
	}

}
