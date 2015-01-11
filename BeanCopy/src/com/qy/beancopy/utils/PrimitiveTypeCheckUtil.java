package com.qy.beancopy.utils;

import java.util.HashSet;
import java.util.Set;

public class PrimitiveTypeCheckUtil {
	
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
	
	public static boolean isPrimitive(Class<?> clazz) {
		return primitiveClasses.contains(clazz) 
				|| wrapperClass.contains(clazz);
	}

}
