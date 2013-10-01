package com.ecloud.pa.ui;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.ecloud.pa.model.Role;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * @author ’≈Ô«
 * 
 * JPAContainer ≤‚ ‘
 *
 */
@Component
@Scope("prototype")
@VaadinView("JPAContainer")
public class JPAContainerTestView extends VerticalLayout implements ComponentContainer, View {

	private static final long serialVersionUID = -541228663229144783L;
	
	@Resource
	private EntityManagerFactory entityManagerFactory;
	
	private Table mainTable;
    private Button newButton;
    private Button deleteButton;
    private Button editButton;
    
    private JPAContainer<Role> mainContainer;

	@PostConstruct
	public void PostConstruct(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		mainContainer = JPAContainerFactory.make(Role.class, entityManagerFactory.createEntityManager());
		mainTable = new Table(null, mainContainer);
		for (Field field:Role.class.getDeclaredFields()){
			Caption caption = field.getAnnotation(Caption.class);
			if (caption!=null) {
				map.put(field.getName(), caption.value());
			}
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
