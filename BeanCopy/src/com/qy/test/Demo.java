package com.qy.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.qy.beancopy.BeanCopyUtils;
import com.qy.beancopy.CopyHandlerFactory;
import com.qy.typecopy.CollectionCopy;
import com.qy.typecopy.CompoundTypeCopy;
import com.qy.typecopy.MapCopy;
import com.qy.typecopy.PrimitiveCopy;

public class Demo {
	
	public static A construcA() {
		//copy primitive type
		A a = new A();
		a.setA(1);
		a.setB("b");
		a.setC(3.0);
		
		//copy list
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		a.setList(list);
		
		//copy map
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		a.setMap(map);
		
		//no arguments constructor which can't be copied
		BigInteger binBigInteger = new BigInteger("112222222222121121211212");
		a.setBigInteger(binBigInteger);
		
		//compoundType
		C c = new C();
		c.setNum(123);
		a.setA_c(c);
		
		//class obj won't be copied
		a.setMineClass(A.class);
		
		//copy list
		List<C> aList = new ArrayList<C>();
		aList.add(c);
		a.setcList(aList);
		
		//copy map
		Map<C,C> cMap = new HashMap<C,C>();
		cMap.put(c, c);
		a.setcMap(cMap);
		
		//copy nested map
		Map<Map<String,C>, C> nestedMap = new HashMap<Map<String,C>, C>();
		Map<String,C> strCMap = new HashMap<String, C>(); 
		strCMap.put("nested", c);
		nestedMap.put(strCMap, c);
		a.setNestedMap(nestedMap);
		return a;
	}

	public static void main(String[] args) {
		
		A a = construcA();
		
		B b = new B();
		
		//可以实现自己的copy类型，注册进去
		CopyHandlerFactory.getInstance().registerHandler(new PrimitiveCopy());
		CopyHandlerFactory.getInstance().registerHandler(new CollectionCopy());
		CopyHandlerFactory.getInstance().registerHandler(new MapCopy());
		CopyHandlerFactory.getInstance().registerHandler(new CompoundTypeCopy());
		BeanCopyUtils.copyProperties(a, b);
		
		System.out.println(a);
		System.out.println(b);
	}

}
