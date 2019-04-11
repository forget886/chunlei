package com.wuxi.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.beans.SimpleBeanInfo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性编辑器(转换器)
 *
 * @author dasouche
 */
public class PropertyEditorTest {

    private static final Logger logger = LoggerFactory.getLogger(PropertyEditorTest.class);

    @Test
    public void edit() {

    }
}


/*
 * -----------------
 * JavaBean的属性编辑器
 * -----------------
 */

/**
 * 每个属性都有编辑器
 *
 * @author dasouche
 */
class LocationEditor extends PropertyEditorSupport {

    private String[] options = new String[]{"LEFT", "CENTER", "RIGHT"};

    /**
     * 可选属性值的字符串形式数组
     */
    @Override
    public String[] getTags() {
        return options;
    }

    /**
     * 代表属性初始值的字符串
     */
    @Override
    public String getJavaInitializationString() {
        return "" + getValue();
    }

    /**
     * 将内部属性值转换为字符串形式，供属性编辑器显示时用
     */
    @Override
    public String getAsText() {
        int value = (int) getValue();
        return options[value];
    }

    /**
     * 将外部字符串转换为内部属性值
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(text)) {
                setValue(i);
            }
        }
    }

}

class AreaBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            //为属性绑定属性编辑器
            PropertyDescriptor locationDescriptor = new PropertyDescriptor("location", Area.class);
            locationDescriptor.setPropertyEditorClass(LocationEditor.class);
            //TODO 其他属性都可以绑定


            return new PropertyDescriptor[]{locationDescriptor};
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        return null;
    }

}

class Area {
    private String location;
    private String code;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

/*
 * -----------------------------------------------------------
 * spring的属性编辑器不需要UI界面，仅需要将字面值转换为属性类型的对象就行，具体就是覆盖PropertyEditorSupport的setAsText方法
 * -----------------------------------------------------------
 */

class Factory2 {
    private String name;
    private Area area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}

/**
 * 自定义属性编辑器
 *
 * @author dasouche
 */
class CustomAreaEditor extends PropertyEditorSupport {

    /**
     * 将字面值转换为属性类型的对象
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.indexOf(",") == -1) {
            throw new IllegalArgumentException("设置的字符串格式不对");
        }
        String[] splits = text.split(",");
        Area area = new Area();
        area.setLocation(splits[0]);
        area.setCode(splits[1]);
        setValue(area);
    }

}

