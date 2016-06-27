package com.codlex.reflection;

import java.lang.reflect.Method;

public class Instantiate {

	
	public static void main(String[] args) {
		try {
			Class classs = Class.forName("java.lang.Integer");
			for (Method method : classs.getDeclaredMethods()) {
				System.out.println(method);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
