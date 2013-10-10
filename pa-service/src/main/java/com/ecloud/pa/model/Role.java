package com.ecloud.pa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ecloud.pa.model.e.RoleDataScope;
import com.vaadin.data.fieldgroup.Caption;

/**
 * @author 张锴
 * 
 * 角色实体
 */
@Entity
@Table(name = "SYS_ROLE")
public class Role extends IdEntity implements Serializable {

	private static final long serialVersionUID = -7827961010342323851L;

	@Caption("名称")
	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	private String name;
	
	@Caption("数据范围")
	@NotNull
	private RoleDataScope dataScope;

	@Caption("备注")
	private String remark;
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public RoleDataScope getDataScope() {
		return dataScope;
	}

	public void setDataScope(RoleDataScope dataScope) {
		this.dataScope = dataScope;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
