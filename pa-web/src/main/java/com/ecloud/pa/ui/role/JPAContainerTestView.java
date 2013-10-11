package com.ecloud.pa.ui.role;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import ru.xpoft.vaadin.VaadinView;

import com.ecloud.pa.model.Role;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.util.filter.Like;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author 张锴
 * 
 * JPAContainer 测试
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
    
    private TextField searchField;

    private String textFilter;
    
	@PostConstruct
	public void PostConstruct(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		mainContainer = JPAContainerFactory.make(Role.class, entityManagerFactory.createEntityManager());
		mainTable = new Table(null, mainContainer);
		mainTable.setSelectable(true);
        mainTable.setImmediate(true);
        mainTable.setRowHeaderMode(RowHeaderMode.INDEX);
        mainTable.addItemClickListener(new ItemClickListener() {
			
        	private static final long serialVersionUID = 1L;
        	
			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick() && event.getItem()!=null) {
					RoleEditor pe = new RoleEditor(event.getItem(),mainContainer);
					pe.center();
					UI.getCurrent().addWindow(pe);
				}
			}
		});
        mainTable.addValueChangeListener(new Property.ValueChangeListener() {

        	private static final long serialVersionUID = 1L;

			@Override
            public void valueChange(ValueChangeEvent event) {
            	Object value = event.getProperty().getValue();
                setModificationsEnabled( value != null);
            }

        });
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
        mainTable.addItemClickListener(new ItemClickListener() {
        	
        	private static final long serialVersionUID = 1L;
        	
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    mainTable.select(event.getItemId());
                }
            }
        });
		
		super.setSizeFull();
		HorizontalLayout toolbar = new HorizontalLayout();
		 newButton = new Button("增加");
	        newButton.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 637976130363157012L;

				@Override
	            public void buttonClick(ClickEvent event) {
	                final EntityItem<Role> newRoleItem = mainContainer.createEntityItem(new Role());
	                RoleEditor roleEditor = new RoleEditor(newRoleItem,mainContainer);
	                roleEditor.center();
	                UI.getCurrent().addWindow(roleEditor);
	            }
	        });

	        deleteButton = new Button("删除");
	        deleteButton.addClickListener(new Button.ClickListener() {
	            
	        	private static final long serialVersionUID = 1L;
	        	
	        	@Override
	            public void buttonClick(ClickEvent event) {
	            	ConfirmDialog.show(UI.getCurrent(),"警告","确定要删除吗？删除后将不能恢复！","是","否",
	           			 new ConfirmDialog.Listener() {
	            		
	            		private static final long serialVersionUID = 1L;
	            		
						@Override
						public void onClose(ConfirmDialog dialog) {
							if(dialog.isConfirmed()){
								mainContainer.removeItem(mainTable.getValue());
							}
							setModificationsEnabled(false);
						}
					});
	            }
	        });
	        deleteButton.setEnabled(false);

	        editButton = new Button("编辑");
	        editButton.addClickListener(new Button.ClickListener() {

	        	private static final long serialVersionUID = 1L;
	        	
	            @Override
	            public void buttonClick(ClickEvent event) {
	            	RoleEditor pe = new RoleEditor(mainTable.getItem(mainTable.getValue()),mainContainer);
	            	pe.center();
	                UI.getCurrent().addWindow(pe);
	            }
	        });
	        editButton.setEnabled(false);
	        

	        searchField = new TextField();
	        searchField.setInputPrompt("Search by name");
	        searchField.addTextChangeListener(new TextChangeListener() {

				private static final long serialVersionUID = 1L;

				@Override
	            public void textChange(TextChangeEvent event) {
	                textFilter = event.getText();
	                updateFilters();
	            }
	        });

	        toolbar.addComponent(newButton);
	        toolbar.addComponent(deleteButton);
	        toolbar.addComponent(editButton);
	        toolbar.addComponent(searchField);
	        toolbar.setWidth("100%");
	        toolbar.setExpandRatio(searchField, 1);
	        toolbar.setComponentAlignment(searchField, Alignment.TOP_RIGHT);
	        toolbar.setMargin(true);
		super.addComponent(toolbar);
		super.setExpandRatio(toolbar, 1);
		super.addComponent(mainTable);
		super.setExpandRatio(mainTable, 10);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {

	}
	
	 private void setModificationsEnabled(boolean b) {
         deleteButton.setEnabled(b);
         editButton.setEnabled(b);
     }
	 private void updateFilters() {
	        mainContainer.setApplyFiltersImmediately(false);
	        mainContainer.removeAllContainerFilters();
	        if (textFilter != null && !textFilter.equals("")) {
	            Like like =new Like("name","%"+ textFilter + "%", false);
	            mainContainer.addContainerFilter(like);
	        }
	        mainContainer.applyFilters();
	    }

}
