package com.ecloud.pa.ui.role;

import java.lang.reflect.Field;

import javax.persistence.Entity;

import com.ecloud.pa.model.Role;
import com.ecloud.pa.ui.utils.JPAContainerItemFieldGroup;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Window;

/**
 * @author 张锴
 * 
 * 角色编辑窗口
 * 
 */
public class RoleEditor extends Window {

	private static final long serialVersionUID = 6632395948632753187L;

	JPAContainer<Role> jpaContainer = null;
	JPAContainerItem<Role> jpaitem = null;

	@SuppressWarnings("unchecked")
	public RoleEditor(final Item item, final JPAContainer<Role> jpaContainer) {
		this.setCaption("角色编辑/新增");
		this.jpaitem = (JPAContainerItem<Role>) item;
		this.jpaContainer = jpaContainer;
		final FormLayout formLayout = new FormLayout();
		formLayout.setMargin(true);
		final JPAContainerItemFieldGroup<Role> fieldGroup = new JPAContainerItemFieldGroup<Role>(Role.class);
		fieldGroup.setItemDataSource(item);
		for (Field f : Role.class.getDeclaredFields()) {
			Caption caption = f.getAnnotation(Caption.class);
			if (caption != null) {
				Class<?> type = f.getType();
				Entity e = type.getAnnotation(Entity.class);
				if (e != null || Enum.class.isAssignableFrom(type)) {// 实体、enum类型自动生成下拉框
					formLayout.addComponent(fieldGroup.buildAndBind(caption.value(), f.getName(), ComboBox.class));
				} else {
					formLayout.addComponent(fieldGroup.buildAndBind(caption.value(), f.getName()));
				}
			}
		}
		// Buffer the form content
		fieldGroup.setBuffered(true);

		// 处理保存事件
		Button saveButton = new Button("保存");
		saveButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					// 编辑的直接提交即可
					fieldGroup.commit();
					// 新增的需要单独处理
					if (jpaitem.getEntity().getId() == null) {
						Role p = fieldGroup.getItemDataSource().getEntity();
						jpaContainer.addEntity(p);
					}
					Notification.show("保存成功");
					// error.setVisible(false);
					RoleEditor.this.close();// 关闭，防止再点击，重复增加
				} catch (FieldGroup.CommitException e) {
					String msg = "提交异常，" + e.getMessage();
					Throwable ta = e.getCause();
					if (ta instanceof EmptyValueException) {
						// TODO 主表精确定位异常,因为设置了setRequired, 如果不设置则样式不对,需要自定义红*样式
						msg = "请填写完整，红色星号为必填";
					} else if (ta instanceof Validator.InvalidValueException) {
						msg = ta.getMessage();
					} 
					Notification.show(msg, Type.WARNING_MESSAGE);
				}
			}
		});
		Button cancelButton = new Button("重置");
		cancelButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				fieldGroup.discard();
			}
		});

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setMargin(true);
		buttons.addComponent(saveButton);
		buttons.addComponent(cancelButton);
		formLayout.addComponent(buttons);
		formLayout.setComponentAlignment(buttons, Alignment.MIDDLE_LEFT);
		setContent(formLayout);
	}
}
