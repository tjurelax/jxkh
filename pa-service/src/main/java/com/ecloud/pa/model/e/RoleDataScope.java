package com.ecloud.pa.model.e;

import java.io.Serializable;

/**
 * @author 张锴
 *
 * 角色能访问的数据范围
 */
public enum RoleDataScope implements Serializable{
	全部数据(99), 本单位(80) ,本顶级部门(70),本部门(60),自定义(0);
	
	private int level;
	RoleDataScope(int i){
		this.level = i;
	}
	public int getLevel() {
		return level;
	}
	
}
