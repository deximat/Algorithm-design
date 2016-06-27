package com.codlex.algorithms.numbers;

public class Operations {

	public static int negate(int toNegate) {
		int negativeMaxInt = Integer.MIN_VALUE + 1;
		int result = Integer.MAX_VALUE + toNegate;
		
		return result + Integer.MIN_VALUE;
		
	}
	
	public static void main(String[] args) {
		System.out.println(negate(5));
	}
}
