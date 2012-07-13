package com.phillit.prototype.pez.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 모든 Model 객체는 이 객체를 상속받아 만들어야 한다.
 * 
 * @author sylee
 * 
 */
public class PezSuperModel {

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
