package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.qy.beancopy.AbstractCopy;

public class MapCopy extends AbstractCopy{
	
	public boolean checkType(Class<?> sourceClass) {
		return Map.class.isAssignableFrom(sourceClass);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void copy(Object sourceValue, Object targetObj, Method writeMethod) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map sourceMap = (Map)sourceValue;
		Map targetMap;
		targetMap = (Map)sourceValue.getClass().newInstance();
		for (Map.Entry mapEntry : (Set<Map.Entry>)sourceMap.entrySet()) {
			targetMap.put(mapEntry.getKey(), mapEntry.getValue());
		}
		writeMethod.invoke(targetObj, targetMap);
	}
}
