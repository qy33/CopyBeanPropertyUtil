package com.qy.beancopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractCopy {
	
	
	public abstract void copy(Object sourceValue, Object targetObj, Method writeMethod) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public abstract boolean checkType(Class<?> clazz);
	
	public boolean executeCopy(Object sourceValue, Object targetObj, Method writeMethod) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		boolean type = checkType(sourceValue.getClass());
		if (type) {
			copy(sourceValue, targetObj, writeMethod);
		}
		return type;
	}
}
