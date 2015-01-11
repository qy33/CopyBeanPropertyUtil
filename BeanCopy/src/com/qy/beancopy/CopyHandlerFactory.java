package com.qy.beancopy;

import java.util.HashSet;
import java.util.Set;

public class CopyHandlerFactory {
	
	private static CopyHandlerFactory handlerFactory;
	
	private static Set<AbstractCopy> copySet = new HashSet<AbstractCopy>();

	public static Set<AbstractCopy> getCopySet() {
		return copySet;
	}
	
	private CopyHandlerFactory(){
		
	}
	
	public static CopyHandlerFactory getInstance() {
		if (null == handlerFactory) {
			synchronized(CopyHandlerFactory.class) {
				if (null == handlerFactory) {
					handlerFactory = new CopyHandlerFactory();
				}
			}
		}
		return handlerFactory;
	}
	
	public void registerHandler(AbstractCopy copy) { 
		copySet.add(copy);
	}
}
