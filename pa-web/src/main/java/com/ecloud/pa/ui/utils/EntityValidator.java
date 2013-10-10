package com.ecloud.pa.ui.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.validator.BeanValidator;

public class EntityValidator extends BeanValidator {
	private static final long serialVersionUID = 6751914717037053677L;
	
	private Class<?> beanClass;
	private String propertyName;
	private String caption ;

	public EntityValidator(Class<?> beanClass, String propertyName) {
		super(beanClass, propertyName);
		this.beanClass = beanClass;
		this.propertyName = propertyName;
		this.caption = getCaption(beanClass, propertyName);
	}

	@Override
	public void validate(Object value) throws InvalidValueException {
		  Set<?> violations = getJavaxBeanValidator().validateValue(beanClass,
	                propertyName, value);
	        if (violations.size() > 0) {
	            List<String> exceptions = new ArrayList<String>();
	            for (Object v : violations) {
	                final ConstraintViolation<?> violation = (ConstraintViolation<?>) v;
	                String msg = getJavaxBeanValidatorFactory()
	                        .getMessageInterpolator().interpolate(
	                                violation.getMessageTemplate(),
	                                new SimpleContext(value, violation
	                                        .getConstraintDescriptor()), super.getLocale());
	                exceptions.add(msg);
	            }
	            StringBuilder b = new StringBuilder();
	            for (int i = 0; i < exceptions.size(); i++) {
	                if (i != 0) {
	                    b.append("<br/>");
	                }
	                b.append(caption).append(" : ").append(exceptions.get(i));
	            }
	            throw new InvalidValueException(b.toString());
	        }
	}

	/**
	 * 根据Caption注解返回caption,不生成必填标识
	 * @param clazz
	 * @param fieldname
	 * @return 找不到则返回fieldname
	 */
	private String getCaption(Class<?> clazz, String fieldname){
		return getCaption(clazz, fieldname, false);
	}
	
	/**
	 * 根据Caption注解返回caption
	 * @param clazz
	 * @param fieldname
	 * @param showRequiredSign 是否生成必填标识
	 * @return
	 */
	private String getCaption(Class<?> clazz, String fieldname, boolean showRequiredSign){
		try {
			Field f = clazz.getDeclaredField(fieldname);
			String caption = getCaption(f,showRequiredSign);
			return caption == null ? fieldname : caption;
		} catch (Exception e1) {
			return fieldname;
		}
	}
	
	/**
	 * 获得Caption
	 * @param field
	 * @param showRequiredSign 是否生成必填标识(红*)
	 * @return
	 */
	private String getCaption(Field field, boolean showRequiredSign){
		Caption caption = field.getAnnotation(Caption.class);
		NotNull notNull = field.getAnnotation(NotNull.class);
		if(caption != null){
			String cap = caption.value();
			if(showRequiredSign && notNull != null){
				cap += "<font color=red>*</font>";
			}
			return cap;
		}else{
			return field.getName();
		}
	}
}
