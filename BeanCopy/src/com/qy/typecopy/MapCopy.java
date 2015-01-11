package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import com.qy.beancopy.AbstractCopy;
import com.qy.beancopy.BeanCopyUtils;
import com.qy.beancopy.utils.PrimitiveTypeCheckUtil;

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
			Object keyObject = mapEntry.getKey();
			Object valueObject = mapEntry.getValue();
			if (keyObject instanceof Class || valueObject instanceof Class) { 
				continue;
			}
			
			if (PrimitiveTypeCheckUtil.isPrimitive(keyObject.getClass()) && 
					PrimitiveTypeCheckUtil.isPrimitive(valueObject.getClass())) {
				targetMap.put(keyObject, valueObject);
			} else if (PrimitiveTypeCheckUtil.isPrimitive(keyObject.getClass())) {
				if (BeanCopyUtils.checkHasNoArgumentsConstructor(valueObject)) {
					Object obj = valueObject.getClass().newInstance();
					BeanCopyUtils.copyProperties(valueObject, obj);
					targetMap.put(keyObject, obj);
				}
			} else if (PrimitiveTypeCheckUtil.isPrimitive(valueObject.getClass())) {
				if (BeanCopyUtils.checkHasNoArgumentsConstructor(keyObject)) {
					Object obj = keyObject.getClass().newInstance();
					BeanCopyUtils.copyProperties(keyObject, obj);
					targetMap.put(obj, valueObject);
				}
			} else {
				if (BeanCopyUtils.checkHasNoArgumentsConstructor(keyObject) &&
						BeanCopyUtils.checkHasNoArgumentsConstructor(valueObject)) {
					Object keyObj = keyObject.getClass().newInstance();
					BeanCopyUtils.copyProperties(keyObject, keyObj);
					
					Object valueObj = valueObject.getClass().newInstance();
					BeanCopyUtils.copyProperties(valueObject, valueObj);
					targetMap.put(keyObj, valueObj);
				}
			}
		}
		writeMethod.invoke(targetObj, targetMap);
	}
}
