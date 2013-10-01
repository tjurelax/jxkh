package com.ecloud.pa.ui;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("ecloud")
@Scope("prototype")
@PreserveOnRefresh
// 支持F5刷新
@Component
@Title("绩效考核")
public class MainUI extends UI {

	private static final long serialVersionUID = 4648610659662374773L;
	
	@Resource
	private InitTestData initTestData;
	
	IuiTemplate uiTemplate = new MainUiTemplate(); 

	@Override
	protected void init(VaadinRequest request) {
		//初始化测试数据
		initTestData.execute();
		//初始化导航
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, uiTemplate.getTargetContainer());
		super.setNavigator(navigator);
		//设置默认模板的内容
		super.setContent(uiTemplate.buildUI());
		super.setSizeFull();
	}

}