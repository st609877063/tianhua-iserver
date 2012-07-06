package com.gift.bean;import org.springframework.context.annotation.Scope;import org.springframework.stereotype.Repository;/** * @Repository @Service @Controller，它们分别对应存储层Bean，业务层Bean，和展示层Bean。 * @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。 * @scope="prototype"来保证每一个请求有一个单独的Action来处理， 避免struts中Action的线程安全问题。 */@Repository@Scope("prototype")public class Gift_operation implements java.io.Serializable {	private static final long serialVersionUID = 1L;		private Integer oper_id;	private String oper_name;	private String oper_parent;	private String oper_uri;	private Integer create_time;	private Integer add_user_id;	public Integer getOper_id() {		return oper_id;	}	public void setOper_id(Integer oper_id) {		this.oper_id = oper_id;	}	public String getOper_name() {		return oper_name;	}	public void setOper_name(String oper_name) {		this.oper_name = oper_name;	}	public String getOper_parent() {		return oper_parent;	}	public void setOper_parent(String oper_parent) {		this.oper_parent = oper_parent;	}	public String getOper_uri() {		return oper_uri;	}	public void setOper_uri(String oper_uri) {		this.oper_uri = oper_uri;	}	public Integer getCreate_time() {		return create_time;	}	public void setCreate_time(Integer create_time) {		this.create_time = create_time;	}	public Integer getAdd_user_id() {		return add_user_id;	}	public void setAdd_user_id(Integer add_user_id) {		this.add_user_id = add_user_id;	}}