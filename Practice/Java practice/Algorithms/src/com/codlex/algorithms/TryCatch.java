package com.codlex.algorithms;

public class TryCatch {

	
	public static void main(String[] args) {
		try {
			throw new Exception("bla");
		}  catch (Exception e) {
			System.out.println("First");
		} finally {
			System.out.println("second");
		} 
	}
}
