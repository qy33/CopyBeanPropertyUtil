package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.qy.beancopy.AbstractCopy;
import com.qy.beancopy.utils.PrimitiveTypeCheckUtil;

public class PrimitiveCopy extends AbstractCopy {
		
	public boolean checkType(Class<?> sourceClass) {
		return PrimitiveTypeCheckUtil.isPrimitive(sourceClass) 
				|| PrimitiveTypeCheckUtil.isPrimitive(sourceClass);
	}
	
	public void copy(Object sourceValue, Object targetObj, Method writeMethod) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		writeMethod.invoke(targetObj, sourceValue);
	}
	
}
