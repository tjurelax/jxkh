package com.ecloud.pa.model.e;

import java.io.Serializable;

/**
 * @author ����
 *
 * ��ɫ�ܷ��ʵ����ݷ�Χ
 */
public enum RoleDataScope implements Serializable{
	ȫ������(99), ����λ(80) ,����������(70),������(60),�Զ���(0);
	
	private int level;
	RoleDataScope(int i){
		this.level = i;
	}
	public int getLevel() {
		return level;
	}
	
}
