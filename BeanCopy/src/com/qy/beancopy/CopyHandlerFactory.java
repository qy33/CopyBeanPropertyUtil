package com.qy.beancopy;

import java.util.HashSet;
import java.util.Set;

import com.qy.typecopy.CollectionCopy;
import com.qy.typecopy.CompoundTypeCopy;
import com.qy.typecopy.MapCopy;
import com.qy.typecopy.PrimitiveCopy;

public class CopyHandlerFactory {
	
/*	private static CopyHandlerFactory handlerFactory;
*/	
	private static Set<AbstractCopy> copySet = new HashSet<AbstractCopy>();
	
	static {
		copySet.add(new PrimitiveCopy());
		copySet.add(new CollectionCopy());
		copySet.add(new MapCopy());
		copySet.add(new CompoundTypeCopy());
	}

	public static Set<AbstractCopy> getCopySet() {
		return copySet;
	}
	 
/*	
	private CopyHandlerFactory(){
		super();
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
	}*/
}
