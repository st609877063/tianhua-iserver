package com.people.dptwb.action.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;
import com.people.dptwb.action.TBaseAction;
import com.people.dptwb.common.PageList;
import com.people.dptwb.common.Pages;
import com.people.dptwb.entity.FeChuguo;
import com.people.dptwb.entity.FeUsers;
import com.people.dptwb.manager.home.HrManager;

public class HrHomeAction extends TBaseAction implements Preparable {
	private static final long serialVersionUID = 1683360952741740802L;

	private static final int perPageNum = 15;
	private int page;
	private PageList pl;
	private int t;

	private String bianhao; // 编号
	private String truename; // 姓名

	private FeUsers userVo;
	private List<FeChuguo> chuguoList;

	// 数据分析
	private List<String> jigouList = null;
	private List<String> jibieList = null;

	// 筛选条件
	private String jigou;
	private String jibie;
	// 维度
	private String weidu;

	private String chartRtn = "";
	private String chartRtn1 = "";
	private String chartRtn2 = "";

	@Override
	public void prepare() throws Exception {
		// 在validate之前执行
		setIndex(1);
	}

	public void validate() {
		if (getCookieUid() == -1) {
			setCookieInfo();
		}

		if (page <= 0) {
			page = 1;
		}
	}

	@Override
	public String execute() throws Exception {
		setIndex(1);
		return SUCCESS;
	}

	public String toHrHome() throws Exception {
		setIndex(1);

		// 分页
		HttpServletRequest request = ServletActionContext.getRequest();
		Pages pages = new Pages(request);
		pages.setPage(page);
		pages.setPerPageNum(perPageNum);
		pages.setStyle(13);
		if (t > 0) {
			pages.setTotals(t);
		}
		pages.setFileName(request.getContextPath() + "/hrHome.action?bianhao="
				+ bianhao + "&truename=" + truename);

		HrManager manager = new HrManager();
		PageList pl = manager.getUserListPaged(bianhao, truename, pages,
				request.getContextPath());

		setPl(pl);

		return SUCCESS;
	}

	public String toHrDetail() throws Exception {
		setIndex(2);

		if (StringUtils.isNotBlank(bianhao)) {
			HrManager manager = new HrManager();
			List<FeUsers> temp = manager.getUserList(bianhao, null, 0, 1, "");
			if (temp != null && temp.size() == 1) {
				userVo = temp.get(0);
			}

			chuguoList = manager.getUserChuguoList(bianhao, 0, -1);
		}

		return SUCCESS;
	}

	// 人员分析
	public String toHrChartUser() throws Exception {
		setIndex(3);
		setPageType("1");

		HrManager manager = new HrManager();
		jigouList = manager.getJigouDataList();
		jibieList = manager.getJibieDataList();

		if (StringUtils.isNotBlank(weidu)) {
			Map<String, String> dataMap = manager.getUserChartData(weidu,
					jigou, jibie);
			if (dataMap != null && dataMap.size() > 0) {
				StringBuilder sb1 = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				for(String key : dataMap.keySet()) {
					sb1.append("'").append(key).append("',");
					sb2.append(dataMap.get(key)).append(",");
				}
				
				if(sb1 != null && sb1.length() > 0) {
					chartRtn1 = sb1.substring(0, sb1.length()-1).toString();
				}
				if(sb2 != null && sb2.length() > 0) {
					chartRtn2 = sb2.substring(0, sb2.length()-1).toString();
				}
			}
		}

		return SUCCESS;
	}

	// 出国分析
	public String toHrChartChuguo() throws Exception {
		setIndex(3);
		setPageType("2");

		return SUCCESS;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageList getPl() {
		return pl;
	}

	public void setPl(PageList pl) {
		this.pl = pl;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
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

	public FeUsers getUserVo() {
		return userVo;
	}

	public void setUserVo(FeUsers userVo) {
		this.userVo = userVo;
	}

	public List<FeChuguo> getChuguoList() {
		return chuguoList;
	}

	public void setChuguoList(List<FeChuguo> chuguoList) {
		this.chuguoList = chuguoList;
	}

	public List<String> getJigouList() {
		return jigouList;
	}

	public void setJigouList(List<String> jigouList) {
		this.jigouList = jigouList;
	}

	public List<String> getJibieList() {
		return jibieList;
	}

	public void setJibieList(List<String> jibieList) {
		this.jibieList = jibieList;
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

	public String getWeidu() {
		return weidu;
	}

	public void setWeidu(String weidu) {
		this.weidu = weidu;
	}

	public String getChartRtn() {
		return chartRtn;
	}

	public void setChartRtn(String chartRtn) {
		this.chartRtn = chartRtn;
	}

	public String getChartRtn1() {
		return chartRtn1;
	}

	public void setChartRtn1(String chartRtn1) {
		this.chartRtn1 = chartRtn1;
	}

	public String getChartRtn2() {
		return chartRtn2;
	}

	public void setChartRtn2(String chartRtn2) {
		this.chartRtn2 = chartRtn2;
	}

}
