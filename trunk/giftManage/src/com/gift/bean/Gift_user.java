package com.gift.bean;import org.apache.commons.lang.StringUtils;import org.springframework.context.annotation.Scope;import org.springframework.stereotype.Repository;import com.gift.tools.DateTools;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Repository@Scope("prototype")public class Gift_user implements java.io.Serializable {	private static final long serialVersionUID = 1L;	private Integer user_id;	private String user_name;	private String nick_name;	private String password;	private String user_img;	private String user_title;	private String user_type;   //用户类型：系统使用者1，接受礼品者0，两者都是2	private String user_admin;  //系统使用者类型：超级管理员1，一般管理员2，普通用户3	private String is_close;	//是否关闭：关闭1，开启0	private Long create_time;	private Integer add_user_id;		//用户对应分组	private String groupIdStr; //setter有变动		//显示	private String user_type_show;	private String user_admin_show;	private String is_close_show;	private String create_time_show;	private String add_user_name;		public String getUser_type_show() {		if("0".equals(user_type)){			user_type_show = "接受礼品者";		}else if("1".equals(user_type)){			user_type_show = "系统使用者";		}else if("2".equals(user_type)){			user_type_show = "两者都是";		}		return user_type_show;	}	public String getUser_admin_show() {		if("1".equals(user_admin)){			user_admin_show = "超级管理员";		}else if("2".equals(user_admin)){			user_admin_show = "一般管理员";		}else if("3".equals(user_admin)){			user_admin_show = "普通用户";		}		return user_admin_show;	}	public String getIs_close_show() {		if("1".equals(is_close)){			is_close_show = "关闭";		}else if("0".equals(is_close)){			is_close_show = "开启";		}		return is_close_show;	}	public String getCreate_time_show() {		create_time_show = DateTools.getDisplayTime(create_time);		return create_time_show;	}	public Integer getUser_id() {		return user_id;	}	public void setUser_id(Integer user_id) {		this.user_id = user_id;	}	public String getUser_name() {		return user_name;	}	public void setUser_name(String user_name) {		this.user_name = user_name;	}	public String getNick_name() {		return nick_name;	}	public void setNick_name(String nick_name) {		this.nick_name = nick_name;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getUser_img() {		return user_img;	}	public void setUser_img(String user_img) {		this.user_img = user_img;	}	public String getUser_title() {		return user_title;	}	public void setUser_title(String user_title) {		this.user_title = user_title;	}	public String getUser_type() {		return user_type;	}	public void setUser_type(String user_type) {		this.user_type = user_type;	}	public String getUser_admin() {		return user_admin;	}	public void setUser_admin(String user_admin) {		this.user_admin = user_admin;	}	public String getIs_close() {		return is_close;	}	public void setIs_close(String is_close) {		this.is_close = is_close;	}	public Long getCreate_time() {		return create_time;	}	public void setCreate_time(Long create_time) {		this.create_time = create_time;	}	public Integer getAdd_user_id() {		return add_user_id;	}	public void setAdd_user_id(Integer add_user_id) {		this.add_user_id = add_user_id;	}	public void setAdd_user_name(String add_user_name) {		this.add_user_name = add_user_name;	}	public String getAdd_user_name() {		return add_user_name;	}	public void setGroupIdStr(String groupIdStr) {		if(StringUtils.isNotBlank(groupIdStr)){			this.groupIdStr = groupIdStr.replace(", ", "#");		}	}	public String getGroupIdStr() {		return groupIdStr;	}}