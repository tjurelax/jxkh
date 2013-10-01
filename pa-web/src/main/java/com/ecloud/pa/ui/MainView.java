package com.ecloud.pa.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author 张锴
 * 
 * 首页
 *
 */
@Component
@Scope("prototype")
@Title("首页")
@VaadinView
public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public MainView() {
		final Label title = new Label("主页");
		addComponent(title);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
