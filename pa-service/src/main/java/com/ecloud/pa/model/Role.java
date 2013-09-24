package com.ecloud.pa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}
