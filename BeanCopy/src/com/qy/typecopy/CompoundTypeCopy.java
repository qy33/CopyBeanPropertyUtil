package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.qy.beancopy.AbstractCopy;
import com.qy.beancopy.BeanCopyUtils;

public class CompoundTypeCopy extends AbstractCopy{
	
	public boolean checkType(Class<?> sourceClass) {
		return BeanCopyUtils.checkHasNoArgumentsConstructor(sourceClass);
	}
	
	public void copy(Object sourceValue, Object targetObj, Method writeMethod) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object obj = sourceValue.getClass().newInstance();
		writeMethod.invoke(targetObj, obj);
		BeanCopyUtils.copyProperties(sourceValue, obj);
	}
}
