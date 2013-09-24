package com.ecloud.pa.model;

import javax.persistence.ManyToOne;

import com.ecloud.pa.model.e.ResourceType;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.navigator.View;

/**
 * ϵͳ��Դ
 * 
 * @author ����
 *
 */
public class Resource {

	@Caption("����")
	private String name;
	 
	@Caption("����·��")
	private String path;

	@Caption("��ͼ��")
	private Class<? extends View> viewClass;

	@Caption("����")
	private ResourceType resType;

	@Caption("��ԴKEY")
	private String resKey;

	@ManyToOne
	private Resource parent;
}
