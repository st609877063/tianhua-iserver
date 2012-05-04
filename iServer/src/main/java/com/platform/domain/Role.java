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
	@NamedQuery(name="getRoles",query="select t from Role t"),
	@NamedQuery(name="getRoleById",query="select t from Role t where t.roleId=?"),
	@NamedQuery(name="getRoleByName",query="select t from Role t where t.name=?")
	})
@Entity
@Table(name="role",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"roleId", "name"})})
public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="increment")
	private String roleId;
    
    @Column(unique = true,updatable = true, nullable = false, length = 20)  
    private String name;
    
    @Column(updatable = true, nullable = true, length = 100)  
    private String roleDesc;
    
    @ManyToMany(targetEntity=com.platform.domain.Operation.class,fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinTable(schema="ipdb",name="role_operation",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="operationId")})
    private Set<Operation> operations;
    
	@Column
    private String createDate;
    
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Operation> getOperations() {
		return operations;
	}
	
	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	
	
}
