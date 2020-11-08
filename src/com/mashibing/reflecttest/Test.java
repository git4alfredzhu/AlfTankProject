package com.mashibing.reflecttest;

import java.util.Properties;

public class Test {
	public static void main(String[] args) throws Exception {
		String str = "AliPay";
		
		AliPay ap = new AliPay();
		WeChat wc = new WeChat();
		
		if("WeChat".equals(str)) {
			pay(wc);
		}
		
		if("AliPay".equals(str)) {
			pay(ap);
		}
		
		
		Class c1 = AliPay.class;
		
		AliPay a = new AliPay();
		Class c4 = a.getClass();
		
		Class c2 = Class.forName("com.mashibing.reflecttest.AliPay");
		
		ClassLoader classLoader = Test.class.getClassLoader();
		Class c3 = classLoader.loadClass("com.mashibing.reflecttest.AliPay");
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c1==c2);
		System.out.println(c3==c4);
	}
	
	public static void pay(MTwm mt) {
		mt.payOnline();
	}
}
