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
// ֧��F5ˢ��
@Component
@Title("��Ч����")
public class MainUI extends UI {

	private static final long serialVersionUID = 4648610659662374773L;
	
	@Resource
	private InitTestData initTestData;
	
	IuiTemplate uiTemplate = new MainUiTemplate(); 

	@Override
	protected void init(VaadinRequest request) {
		//��ʼ����������
		initTestData.execute();
		//��ʼ������
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, uiTemplate.getTargetContainer());
		super.setNavigator(navigator);
		//����Ĭ��ģ�������
		super.setContent(uiTemplate.buildUI());
		super.setSizeFull();
	}

}