package com.qy.demo;

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

public class D {
	
	public static void main(String[] args) {
		A a = new A();
		a.setA(1);
		a.setB("b");
		a.setC(3.0);
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		a.setList(list);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		a.setMap(map);
		BigInteger binBigInteger = new BigInteger("112222222222121121211212");
		a.setBigInteger(binBigInteger);
		C c = new C();
		c.setNum(123);
		a.setA_c(c);
		a.setMineClass(A.class);
		
		List<C> aList = new ArrayList<C>();
		aList.add(c);
		a.setcList(aList);
		
		Map<C,C> cMap = new HashMap<C,C>();
		cMap.put(c, c);
		a.setcMap(cMap);
		
		B b = new B();
		
		
		CopyHandlerFactory.getInstance().registerHandler(new PrimitiveCopy());
		CopyHandlerFactory.getInstance().registerHandler(new CollectionCopy());
		CopyHandlerFactory.getInstance().registerHandler(new MapCopy());
		CopyHandlerFactory.getInstance().registerHandler(new CompoundTypeCopy());
		BeanCopyUtils.copyProperties(a, b, "a","n","c","list","a_c","map","mineClass","cList","","");
		System.out.println(a);
		System.out.println(b);
		a.getcMap().clear();
		System.out.println(a);
		System.out.println(b);
		
		
	}
}
