package com.qy.beancopy;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * deep copy小工具
 * 两个JavaBean的属性名称和类型需要一样
 * 如果要复制复合类型，则
 * @author qianyuan
 * @Version V1.0
 */
public class BeanCopyUtils {
	
	/**
	 * 									执行复制
	 * @param sourceObj					源对象
	 * @param targetObj					目标对象
	 * @param notCopyFidlds				不需要复制的属性名称
	 * @return void
	 * @author qianyuan
	 * @version V1.0
	 */
	public static void copyProperties(Object sourceObj, Object targetObj, String...notCopyFidlds) {
		
		Map<String, PropertyDescriptor> nameAndSourceProp = getPropertyDesc(sourceObj);
		
		Map<String, PropertyDescriptor> nameAndTargetProp = getPropertyDesc(targetObj);
		
		List<PropertyCopyInfo> propertyCopyInfoList = getBeanCopyInfo(nameAndSourceProp, nameAndTargetProp);
		
		filterNotCopy(propertyCopyInfoList, notCopyFidlds);
		
		copyProperties(sourceObj, targetObj, propertyCopyInfoList);
	}
	
	
	/**
	 * 									过滤不需要复制的属性
	 * @param propertyCopyInfoList		
	 * @param notCopyFidlds
	 * @return void
	 * @author qianyuan
	 * @date 2015年1月7日 下午8:42:05
	 * @version V1.0
	 */
	private static void filterNotCopy(List<PropertyCopyInfo> propertyCopyInfoList, String...notCopyFidlds) {
		if (null == notCopyFidlds || 0 == notCopyFidlds.length) {
			return;
		}
		List<String> fieldsList = Arrays.asList(notCopyFidlds);
		for (int i = propertyCopyInfoList.size() - 1; i >= 0; i--) {
			if (fieldsList.contains(propertyCopyInfoList.get(i).getPropertyName())) {
				propertyCopyInfoList.remove(i);
			}
		}
	}
	
	private static void copyProperties(Object source, Object target, List<PropertyCopyInfo> propertyCopyInfoList) {
		for (PropertyCopyInfo copyInfo : propertyCopyInfoList) {
			Object sourceValue;
			Method readMeghod = copyInfo.getReadMethod();
			try {
				sourceValue = readMeghod.invoke(source);
				//null值不复制
				if (null == sourceValue) {
					continue;
				}
				//class对象不复制
				if (sourceValue instanceof Class) {
					continue;
				}
				
				Method writeMethod = copyInfo.getWriteMeghod();
				
				for (AbstractCopy abstractCopy : CopyHandlerFactory.getCopySet()) {
					boolean bool = abstractCopy.executeCopy(sourceValue, target, writeMethod);
					if (bool) {
						break;
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 									将源对象和模板对象的属性名、读写方法合并到一个对象
	 * @param nameAndSourceProp
	 * @param nameAndTargetProp
	 * @return
	 * @return List<PropertyCopyInfo>
	 * @author qianyuan
	 * @date 2015年1月7日 下午8:42:59
	 * @version V1.0
	 */
	private static List<PropertyCopyInfo> getBeanCopyInfo(Map<String, PropertyDescriptor> nameAndSourceProp, 
			Map<String, PropertyDescriptor> nameAndTargetProp) {
		List<PropertyCopyInfo> proList = new ArrayList<PropertyCopyInfo>();
		for (Map.Entry<String, PropertyDescriptor> entry : nameAndTargetProp.entrySet()) {
			Method writeMethod = null;
			Method readMethod = null;
			//filter not exist in source obj
			if (null == nameAndSourceProp.get(entry.getKey())) {
				continue;
			} 
			//filter has no writeMethod
			if (null == entry.getValue() || null == entry.getValue().getWriteMethod()) {
				continue;
			} 
			
			if (null == nameAndSourceProp.get(entry.getKey()).getReadMethod()) {
				continue;
			}
			
			writeMethod = entry.getValue().getWriteMethod();
			readMethod = nameAndSourceProp.get(entry.getKey()).getReadMethod();
			
			PropertyCopyInfo copyInfo = new PropertyCopyInfo();
			copyInfo.setPropertyName(entry.getKey());
			copyInfo.setReadMethod(readMethod);
			copyInfo.setWriteMeghod(writeMethod);
			proList.add(copyInfo);
		}
		return proList;
	}
	
	/**
	 * 												解析对象属性
	 * @param obj
	 * @return
	 * @return Map<String,PropertyDescriptor>
	 * @author qianyuan
	 * @date 2015年1月7日 下午8:44:40
	 * @version V1.0
	 */
	public static Map<String, PropertyDescriptor> getPropertyDesc(Object obj) {
		if (null == obj) {
			throw new RuntimeException("obj can't be null");
		}
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
		for (PropertyDescriptor property : beanInfo.getPropertyDescriptors()) {
			map.put(property.getDisplayName(), property);
		}
		return map;
	}
	
	/**
	 * 								判断是否有无参构造函数
	 * @param obj
	 * @return
	 * @return boolean
	 * @author qianyuan
	 * @date 2015年1月7日 下午8:44:57
	 * @version V1.0
	 */
	public static boolean checkHasNoArgumentsConstructor(Object obj) {
		Constructor<?>[] constructors = null;
		if (obj instanceof Class) {
			constructors = ((Class)obj).getConstructors();
		} else {
			constructors = obj.getClass().getConstructors();
		}
		
		boolean hasNoArguments = false;
		for (Constructor<?> constructor : constructors) {
			if (0 == constructor.getParameterTypes().length) {
				hasNoArguments = true;
				break;
			}
		}
		return hasNoArguments;
	}

}
