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
 * @author ����
 * 
 * ��ɫ�༭����
 * 
 */
public class RoleEditor extends Window {

	private static final long serialVersionUID = 6632395948632753187L;

	JPAContainer<Role> jpaContainer = null;
	JPAContainerItem<Role> jpaitem = null;

	@SuppressWarnings("unchecked")
	public RoleEditor(final Item item, final JPAContainer<Role> jpaContainer) {
		this.setCaption("��ɫ�༭/����");
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
				if (e != null || Enum.class.isAssignableFrom(type)) {// ʵ�塢enum�����Զ�����������
					formLayout.addComponent(fieldGroup.buildAndBind(caption.value(), f.getName(), ComboBox.class));
				} else {
					formLayout.addComponent(fieldGroup.buildAndBind(caption.value(), f.getName()));
				}
			}
		}
		// Buffer the form content
		fieldGroup.setBuffered(true);

		// �������¼�
		Button saveButton = new Button("����");
		saveButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				try {
					// �༭��ֱ���ύ����
					fieldGroup.commit();
					// ��������Ҫ��������
					if (jpaitem.getEntity().getId() == null) {
						Role p = fieldGroup.getItemDataSource().getEntity();
						jpaContainer.addEntity(p);
					}
					Notification.show("����ɹ�");
					// error.setVisible(false);
					RoleEditor.this.close();// �رգ���ֹ�ٵ�����ظ�����
				} catch (FieldGroup.CommitException e) {
					String msg = "�ύ�쳣��" + e.getMessage();
					Throwable ta = e.getCause();
					if (ta instanceof EmptyValueException) {
						// TODO ����ȷ��λ�쳣,��Ϊ������setRequired, �������������ʽ����,��Ҫ�Զ����*��ʽ
						msg = "����д��������ɫ�Ǻ�Ϊ����";
					} else if (ta instanceof Validator.InvalidValueException) {
						msg = ta.getMessage();
					} 
					Notification.show(msg, Type.WARNING_MESSAGE);
				}
			}
		});
		Button cancelButton = new Button("����");
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
