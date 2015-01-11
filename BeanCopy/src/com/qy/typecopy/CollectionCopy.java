package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import com.qy.beancopy.AbstractCopy;
import com.qy.beancopy.BeanCopyUtils;
import com.qy.beancopy.utils.PrimitiveTypeCheckUtil;


public class CollectionCopy extends AbstractCopy{
	public boolean checkType(Class<?> sourceClass) {
		return Collection.class.isAssignableFrom(sourceClass);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void copy(Object sourceValue, Object targetObj, Method writeMethod) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Collection sourceCollection = (Collection)sourceValue;
		Collection targetCollection;
		targetCollection = (Collection)sourceValue.getClass().newInstance();
		Iterator iterator = sourceCollection.iterator();
		while(iterator.hasNext()) {
			Object valueObj = iterator.next();
			if (valueObj instanceof Class) {
				continue;
			}
			
			if (PrimitiveTypeCheckUtil.isPrimitive(valueObj.getClass())) {
				targetCollection.add(valueObj);
				continue;
			} 
			
			if (BeanCopyUtils.checkHasNoArgumentsConstructor(valueObj)) {
				Object obj = valueObj.getClass().newInstance();
				BeanCopyUtils.copyProperties(valueObj, obj);
				targetCollection.add(obj);
			}
		}
		writeMethod.invoke(targetObj, targetCollection);
	}
}
