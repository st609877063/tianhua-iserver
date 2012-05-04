package com.platform.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@NamedQueries({
	@NamedQuery(name="getUsers",query="select t from User t"),
	@NamedQuery(name="getUserById",query="select t from User t where t.userId=?"),
	@NamedQuery(name="getUserByName",query="select t from User t where t.name=?")
	})
@Entity
@Table(name="users",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"userId", "name"})})
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    //@GeneratedValue(generator="increment")
    //@GenericGenerator(name="increment",strategy="increment")
	private String userId;
	
	@Column(unique = true,updatable = false, nullable = false, length = 20)  
	private String name;
	
	@Column(updatable = true, nullable = false, length = 64)  
	private String password;
	
	@Column(updatable = false, nullable = true, length = 18)  
	private String lastIp;

	@Column(updatable = false)
	private int valid = 0;
	
	@Column(updatable = false)
	private String  code;
	
    @ManyToMany(targetEntity=com.platform.domain.Role.class,fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(schema="ipdb",name="user_role",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name="roleId")})
	private Set<Role> roles;//用户角色
    
    @Column(updatable = false)
	private String createDate;


	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}
	


	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
