package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import com.qy.beancopy.AbstractCopy;


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
			targetCollection.add(iterator.next());
		}
		writeMethod.invoke(targetObj, targetCollection);
	}
}
