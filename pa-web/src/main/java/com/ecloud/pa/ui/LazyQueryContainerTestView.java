package com.ecloud.pa.ui;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.addons.lazyquerycontainer.BeanQueryFactory;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import ru.xpoft.vaadin.VaadinView;

import com.ecloud.pa.model.Role;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Title;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * @author ’≈Ô«
 * 
 *  ◊“≥
 *
 */
@Component
@Scope("prototype")
@VaadinView("test2")
public class LazyQueryContainerTestView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	@Resource
	private EntityManagerFactory entityManagerFactory;
	
	private LazyQueryContainer mainContainer;
	
	private Table mainTable;

	@PostConstruct
	public void PostConstruct(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		BeanQueryFactory<RoleBeanQuery> queryFactory = new
				BeanQueryFactory<RoleBeanQuery>(RoleBeanQuery.class);
		mainContainer = new LazyQueryContainer(queryFactory,"id",50,true);
		mainTable = new Table(null, mainContainer);
		for (Field field:Role.class.getDeclaredFields()){
			Caption caption = field.getAnnotation(Caption.class);
			if (caption!=null) {
				map.put(field.getName(), caption.value());
			}
			mainContainer.addContainerProperty(field.getName(), field.getDeclaringClass(), "", true, true);
		}
		mainTable.setVisibleColumns(map.keySet().toArray());
		for(Object col : map.keySet()){
			mainTable.setColumnHeader(col, map.get(col));
		}
		mainTable.setSizeFull();
		super.setSizeFull();
		super.addComponent(mainTable);
	}
	@Override
	public void enter(ViewChangeEvent event) {

	}

}
