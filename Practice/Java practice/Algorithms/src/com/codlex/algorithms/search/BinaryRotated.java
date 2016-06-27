package com.codlex.algorithms.search;

public class BinaryRotated {
	
	
	private static int search(int[] array, int start, int end, int value) {
		if (start > end) {
			return -1;
		}
		
		if (isSorted(array, start, end)) {
			//System.out.println("Binary(" + start + ", " + end + ")");
			return Binary.search(array, start, end, value);
		}
		
		int middle = (start + end) / 2;
		
		
		int leftIndex = search(array, start, middle, value);
		//System.out.println("TryLeft(" + start + ", " + middle + ")");

		if (leftIndex != -1) {
			return leftIndex;
		}
		
		//System.out.println("TryRight(" + middle + 1 + ", " + end + ")");

		return search(array, middle + 1, end, value);
	}
	
	private static boolean isSorted(int[] array, int start, int end) {
		return array[start] < array[end] || start == end;
	}

	public static int search(int[] array, int value) {
		return search(array, 0, array.length - 1, value);
	}
	
	
}
