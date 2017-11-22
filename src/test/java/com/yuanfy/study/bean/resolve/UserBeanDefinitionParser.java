package com.yuanfy.study.bean.resolve;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.yuanfy.monitorsite.system.entity.UserEntity;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{
	
	protected Class getBeanClass(Element element) {
		return UserEntity.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		
		if (StringUtils.hasText(id)) {
			builder.addPropertyValue(id, id);
		}
		if (StringUtils.hasText(name)) {
			builder.addPropertyValue(name, name);
		}
	}
}
