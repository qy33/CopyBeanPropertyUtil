package com.qy.test;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class B extends C {
	private Integer a;
	private String b;
	private double c;
	private List<Integer> list;
	private C a_c;
	private Map<Integer, Integer> map;
	private BigInteger bigInteger;
	private Class<?> mineClass;
	private List<C> cList;
	private Map<C,C> cMap;
	private Map<Map<String,C>, C> nestedMap;
	
	public Class<?> getMineClass() {
		return mineClass;
	}

	public void setMineClass(Class<?> mineClass) {
		this.mineClass = mineClass;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}


	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public C getA_c() {
		return a_c;
	}

	public void setA_c(C a_c) {
		this.a_c = a_c;
	}

	public Map<Integer, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Integer> map) {
		this.map = map;
	}

	public BigInteger getBigInteger() {
		return bigInteger;
	}

	public void setBigInteger(BigInteger bigInteger) {
		this.bigInteger = bigInteger;
	}

	public List<C> getcList() {
		return cList;
	}

	public void setcList(List<C> cList) {
		this.cList = cList;
	}

	public Map<C, C> getcMap() {
		return cMap;
	}

	public void setcMap(Map<C, C> cMap) {
		this.cMap = cMap;
	}

	public Map<Map<String, C>, C> getNestedMap() {
		return nestedMap;
	}

	public void setNestedMap(Map<Map<String, C>, C> nestedMap) {
		this.nestedMap = nestedMap;
	}

	@Override
	public String toString() {
		return "B [a=" + a + ", b=" + b + ", c=" + c + ", list=" + list
				+ ", a_c=" + a_c + ", map=" + map + ", bigInteger="
				+ bigInteger + ", mineClass=" + mineClass + ", cList=" + cList
				+ ", cMap=" + cMap + ", nestedMap=" + nestedMap + "]";
	}

}
