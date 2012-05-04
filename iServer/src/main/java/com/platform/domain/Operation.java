package com.platform.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
@NamedQueries({
	@NamedQuery(name="getOperations",query="select t from Operation t"),
	@NamedQuery(name="getOperationById",query="select t from Operation t where t.operationId = ?"),
	@NamedQuery(name="getOperationBySeq",query="select t from Operation t where t.seq = ?"),
	@NamedQuery(name="getOperationByName",query="select t from Operation t where t.name = ?")
	})
@Entity
@Table(name="operation",schema="ipdb",uniqueConstraints={@UniqueConstraint(columnNames={"operationId", "name","seq"})})
public class Operation implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="increment")
	private String operationId;
	
	@Column(unique = true,updatable = true, nullable = false, length = 50)  
    private String name;
    
	@Column(updatable = true, nullable = true, length = 50)  
    private String uri;
	
	@Column
    private boolean leaf;//1 yes 2 no
    
	@Column
    private Integer type;//操作的类型，分为 1.菜单,2.普通操作,3.虚拟节点
       
    @Column(unique=true)
    private Integer seq;//排序字段
    
    @Column
    private String createDate;//创建时间
    
    @Column(updatable = true, nullable = true,length = 255)  
    private Boolean rememberLog;//是否记录日志
    
    @Column(updatable = true, nullable = true,length = 255)  
    private String logString;//日志文本
    
    @Column
    private int parentId;//记录树关系

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Boolean getRememberLog() {
		return rememberLog;
	}
	public void setRememberLog(Boolean rememberLog) {
		this.rememberLog = rememberLog;
	}
	public String getLogString() {
		return logString;
	}
	public void setLogString(String logString) {
		this.logString = logString;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

}
