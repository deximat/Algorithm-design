package com.codlex.algorithms.sort;

import java.util.Arrays;

public class Quick {

	  public static void sort(Comparable[] array) {
		
	    //Arrays.shuffle(array);
	    sort(array, 0, array.length - 1);
	  }

	  public static void sort(Comparable[] array, int from, int to) {
		if (from >= to) {
			return;
		}
	    int pivotPosition = partition(array, from, to, from);
	    sort(array, from, pivotPosition - 1);
	    sort(array, pivotPosition + 1, to);
	  }

	  private static void swap(Comparable[] array, int i, int j) {
	    Comparable temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	  }

	  private static int partition(Comparable[] array, int from, int to, int pivotIndex) {
	    Comparable pivot = array[pivotIndex];
	    int left = from + 1; 
	    int right = to;


	    while (true) {
	       while (left <= to && array[left].compareTo(pivot) <= 0) {
	         left++;
	       }
	       while (right >= from && array[right].compareTo(pivot) >= 0) {
	         right--;
	       }
	       if (left < right) {
	          swap(array, left, right);
	       } else {
	    	   swap(array, left - 1, from);
	    	  printArray(array, from, to);
	          return left - 1;
	       }
	    }
	  }
	  
	  
	  public static void main(String[] args) {
		Integer[] numbers = {1,2,3,4,-5,-10,7,13,15,22,2,3,4,5};
		
		sort(numbers);
		
		printArray(numbers, 0, numbers.length - 1);
		
	  }

	private static void printArray(Comparable[] numbers, int from, int to) {
		for (int i = from; i < to; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
	}
