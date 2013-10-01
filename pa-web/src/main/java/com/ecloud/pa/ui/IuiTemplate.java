package com.ecloud.pa.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

public interface IuiTemplate {

	public abstract ComponentContainer getTargetContainer();

	public abstract Component buildUI();

}