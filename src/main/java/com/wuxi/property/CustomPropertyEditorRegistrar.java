package com.wuxi.property;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar{

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		//绑定属性和属性编辑器
		registry.registerCustomEditor(Area.class, new CustomAreaEditor());
	}

}
