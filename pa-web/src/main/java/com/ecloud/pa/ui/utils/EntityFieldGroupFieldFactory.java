package com.ecloud.pa.ui.utils;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Entity;

import ru.xpoft.vaadin.SpringApplicationContext;

import com.vaadin.addon.jpacontainer.EntityProvider;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.fieldfactory.SingleSelectConverter;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;

public class EntityFieldGroupFieldFactory extends DefaultFieldGroupFieldFactory  implements TableFieldFactory{
	private static final long serialVersionUID = -5159857470726117668L;
	@SuppressWarnings("rawtypes")
	@Override
	public <T extends Field> T createField(Class<?> dataType, Class<T> fieldType) {
		Entity e = dataType.getAnnotation(Entity.class);
		if(e != null & AbstractSelect.class.isAssignableFrom(fieldType)){//ʵ����������������
			return  createEntityField(dataType, fieldType);
		}
		if (Date.class.isAssignableFrom(dataType)) {
			return (T) createDateField();
		}
		return super.createField(dataType, fieldType);
	}

	/**
	 * Ϊʵ����������������ѡ���
	 * TODO ����ʵ���ӦEntityProvider�������Ͻ�
	 * @param dataType
	 * @param fieldType
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T extends Field, E > T  createEntityField(Class<E> dataType, Class<T> fieldType) {
		ComboBox cb = new ComboBox();
		JPAContainer<E> container = getJPAContainer(dataType);
		cb.setContainerDataSource(container);
		cb.setItemCaptionMode(ItemCaptionMode.ITEM);
		cb.setConverter(new SingleSelectConverter<Object>(cb));
		return (T) cb;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T extends Field> T createDateField() {
		DateField field = new DateField();
		field.setDateFormat("yyyy-MM-dd");
		field.setImmediate(true);
		return (T) field;
	}

	@Override
	public Field<?> createField(Container container, Object itemId,
			Object propertyId, Component uiContext) {
		Property containerProperty = container.getContainerProperty(itemId,
				propertyId);
		Class<?> type = containerProperty.getType();
		Entity e = type.getAnnotation(Entity.class);
		if (e != null || Enum.class.isAssignableFrom(type)){//ʵ�塢enum�����Զ�����������
			return createField(type,ComboBox.class);
		}
		return createFieldByPropertyType(type);
	}
	/**
	 * Creates fields based on the property type.
	 * <p>
	 * The default field type is {@link TextField}. Other field types generated
	 * by this method:
	 * <p>
	 * <b>Boolean</b>: {@link CheckBox}.<br/>
	 * <b>Date</b>: {@link DateField}(resolution: day).<br/>
	 * <b>Item</b>: {@link Form}. <br/>
	 * <b>default field type</b>: {@link TextField}.
	 * <p>
	 * 
	 * @param type
	 *            the type of the property
	 * @return the most suitable generic {@link Field} for given type
	 */
	public static Field<?> createFieldByPropertyType(Class<?> type) {
		// Null typed properties can not be edited
		if (type == null) {
			return null;
		}

		// Item field
		if (Item.class.isAssignableFrom(type)) {
			return new Form();
		}

		// Date field
		if (Date.class.isAssignableFrom(type)) {
			final DateField df = new DateField();
			df.setResolution(Resolution.DAY);
			return df;
		}

		// Boolean field
		if (Boolean.class.isAssignableFrom(type)) {
			return new CheckBox();
		}

		return new TextField();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static  <T> EntityProvider<T> getEntityProvider(Class<T> entityClass){
		EntityProvider<T> ep = null;
		Map<String, TransactionalEntityProvider> beans = SpringApplicationContext.getApplicationContext().getBeansOfType(TransactionalEntityProvider.class);
		for(Entry<String, TransactionalEntityProvider> en : beans.entrySet()) {
			TransactionalEntityProvider tep = en.getValue();
			if(entityClass.equals(tep.getEntityClass())){
				ep = tep; 
				break;
			}
		}
		if(ep == null){
			Map<String, BatchUpdateEntityProvider> beans2 = SpringApplicationContext.getApplicationContext().getBeansOfType(BatchUpdateEntityProvider.class);
			for(Entry<String, BatchUpdateEntityProvider> en : beans2.entrySet()) {
				BatchUpdateEntityProvider tep = en.getValue();
				if(entityClass.equals(tep.getEntityClass())){
					ep = tep; 
					break;
				}
			}
		}
		if(ep == null){
			throw new RuntimeException("δ�ҵ��� "+entityClass.getName()+" ��Ӧ��EntityProvider bean");
		}
		return ep;
	}
	
	/**
	 * Ϊָ��ʵ��������JPAContaine
	 * @param entityClass
	 * @return
	 */
	public <T> JPAContainer<T> getJPAContainer(final Class<T> entityClass){
		EntityProvider<T> ep =  getEntityProvider( entityClass);
		JPAContainer<T> container= new JPAContainer<T>(entityClass);
		container.setEntityProvider(ep);
		return container;
	}

}
