package com.ecloud.pa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ecloud.pa.model.e.RoleDataScope;
import com.vaadin.data.fieldgroup.Caption;

/**
 * @author ����
 * 
 * ��ɫʵ��
 */
@Entity
@Table(name = "SYS_ROLE")
public class Role extends IdEntity implements Serializable {

	private static final long serialVersionUID = -7827961010342323851L;

	@Caption("����")
	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	private String name;
	
	@Caption("���ݷ�Χ")
	@NotNull
	private RoleDataScope dataScope;

	@Caption("��ע")
	private String remark;
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ҫ���õ� name
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
