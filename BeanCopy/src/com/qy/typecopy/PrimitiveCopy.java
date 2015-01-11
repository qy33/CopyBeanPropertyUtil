package com.qy.typecopy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.qy.beancopy.AbstractCopy;

public class PrimitiveCopy extends AbstractCopy {
	
	private final static Set<Class<?>> primitiveClasses   = new HashSet<Class<?>>();
	
	private final static Set<Class<?>> wrapperClass = new HashSet<Class<?>>();
	
	static {
	    primitiveClasses.add(boolean.class);
	    primitiveClasses.add(byte.class);
	    primitiveClasses.add(short.class);
	    primitiveClasses.add(int.class);
	    primitiveClasses.add(long.class);
	    primitiveClasses.add(float.class);
	    primitiveClasses.add(double.class);
	    primitiveClasses.add(char.class);
	    
	    wrapperClass.add(Boolean.class);
	    wrapperClass.add(Byte.class);
	    wrapperClass.add(Short.class);
	    wrapperClass.add(Integer.class);
	    wrapperClass.add(Long.class);
	    wrapperClass.add(Float.class);
	    wrapperClass.add(Double.class);
	    wrapperClass.add(Character.class);
	    wrapperClass.add(String.class);
	}
	
	public boolean checkType(Class<?> sourceClass) {
		return primitiveClasses.contains(sourceClass) 
				|| wrapperClass.contains(sourceClass);
	}
	
	public void copy(Object sourceValue, Object targetObj, Method writeMethod) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		writeMethod.invoke(targetObj, sourceValue);
	}
	
}
