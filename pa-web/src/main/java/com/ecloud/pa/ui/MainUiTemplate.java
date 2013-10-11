package com.ecloud.pa.ui;

import java.util.Map;

import ru.xpoft.vaadin.SpringApplicationContext;
import ru.xpoft.vaadin.VaadinView;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainUiTemplate implements IuiTemplate {

	private VerticalLayout page = new VerticalLayout();
	
	private HorizontalLayout header = new HorizontalLayout();
	
	private VerticalLayout main = new VerticalLayout();
	
	/* (non-Javadoc)
	 * @see com.ecloud.pa.ui.IUITemplate#getTargetContainer()
	 */
	@Override
	public ComponentContainer getTargetContainer(){
		return main;
	}
	
	/* (non-Javadoc)
	 * @see com.ecloud.pa.ui.IUITemplate#buildUI()
	 */
	@Override
	public Component buildUI() {
		page.addComponent(header);
		page.setExpandRatio(header, 1);
		page.addComponent(main);
		page.setExpandRatio(main, 17);
		Map<String,View> viewMap =SpringApplicationContext.getApplicationContext().getBeansOfType(View.class);
		page.setSizeFull();
		header.setSizeFull();
		page.setSizeFull();
		header.setSizeFull();
		main.setSizeFull();
		for (View view : viewMap.values()) {
			Label label = new Label("<a href='#!"+view.getClass().getAnnotation(VaadinView.class).value()+"'>"+view.getClass().getName()+"</a>",ContentMode.HTML);
			header.addComponent(label);
			
		}
		return page;
	}
	
}
