package com.codlex.algorithms.search;

public class Binary2 {

	   private static int search (Comparable[] array, int from, int to, Comparable value) {
	     System.out.println("from : " + from + " to: " + to);
		   if (from > to) {
	       return -1;
	      }

	      int middle = (from + to) / 2;

	      int result = value.compareTo(array[middle]);
	       if (result < 0) {
	          return search(array, from, middle - 1, value);
	       } else if (result > 0) {
	    	  return search(array, middle + 1, to, value);
	      } else {
	          return middle;
	      }
	      
	   }

	  public static int search(Comparable[] array, int value) {
	    return search(array, 0, array.length - 1, value);
	  }
	  
	  public static void main(String[] args) {
		  Integer[] booojevi = {0,2,3,5,6,7,8,9};
		  
		  
		  System.out.println(search(booojevi, 9));
		  
	  }
	}

