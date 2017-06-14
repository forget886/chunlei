package com.wuxi.property;

import java.beans.PropertyEditorSupport;

class CustomAreaEditor extends PropertyEditorSupport{

	/**
	 * 将字面值转换为属性类型的对象
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text == null || text.indexOf(",") == -1){
			throw new IllegalArgumentException("设置的字符串格式不对");
		}
		String[] splits = text.split(",");
		Area area = new Area();
		area.setLocation(splits[0]);
		area.setCode(splits[1]);
		setValue(area);
	}
	
}
