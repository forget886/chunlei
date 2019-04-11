package com.wuxi.property;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //绑定属性和属性编辑器
        //如果JavaBean在同包中有<JavaBean>Editor，spring会自动自动绑定
        registry.registerCustomEditor(Area.class, new AreaEditor());
    }

}
