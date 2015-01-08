package com.qy.beancopy;

import java.lang.reflect.Method;

public class PropertyCopyInfo {
	private String propertyName;
	private Method readMethod;
	private Method writeMeghod;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}

	public Method getWriteMeghod() {
		return writeMeghod;
	}

	public void setWriteMeghod(Method writeMeghod) {
		this.writeMeghod = writeMeghod;
	}
	
	@Override
	public String toString() {
		return "PropertyCopyInfo [propertyName=" + propertyName
				+ ", readMethod=" + readMethod + ", writeMeghod=" + writeMeghod
				+ "]";
	}

}
