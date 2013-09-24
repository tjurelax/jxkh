package com.ecloud.pa.model;

import javax.persistence.ManyToOne;

import com.ecloud.pa.model.e.ResourceType;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.navigator.View;

/**
 * 系统资源
 * 
 * @author 张锴
 *
 */
public class Resource {

	@Caption("名称")
	private String name;
	 
	@Caption("访问路径")
	private String path;

	@Caption("视图类")
	private Class<? extends View> viewClass;

	@Caption("类型")
	private ResourceType resType;

	@Caption("资源KEY")
	private String resKey;

	@ManyToOne
	private Resource parent;
}
