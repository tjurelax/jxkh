package com.ecloud.pa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vaadin.data.fieldgroup.Caption;


/**
 * @author 张锴
 * 
 * 机构实体
 */
@Entity
@Table(name = "SYS_GROUP")
public class Group extends IdEntity implements Serializable {

	private static final long serialVersionUID = -1196897697108418117L;

	@Caption("名称")
	private String name;

	@Caption("父机构")
	private Group parentGroup;

	private Set<User> users;


	/**
	 * @return name
	 */
	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return parentGroup
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Group getParentGroup() {
		return parentGroup;
	}

	/**
	 * @param parentGroup 要设置的 parentGroup
	 */
	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}

	/**
	 * @return users
	 */
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="groups")
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users 要设置的 users
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
