package com.codlex.algorithms.sort;

public class Merge {

	  public static void sort(Comparable[] array) {
	    Comparable[] auxilary = new Comparable[array.length];
	    sort(array, 0, array.length - 1, auxilary);
	  }

	  private static void sort(Comparable[] array, int from, int to, Comparable[] auxilary) {
	    if (from >= to) {
	      return;
	    }
	    int middle = (from + to) / 2;
	    
	    sort(array, from, middle, auxilary);
	    sort(array, middle + 1, to, auxilary);
	    merge(array, from, middle, to, auxilary);
	  }
	  
	  private static void copy(Comparable[] array, int from, int to, Comparable[] auxilary) {
	    for (int i = from; i <= to; i++) {
	    	auxilary[i] = array[i];
	    } 
	  }  

	  private static void merge(Comparable[] array, int from, int middle, int to, Comparable[] auxilary) {
	    copy(array, from, to, auxilary);
	    int firstIndex = from;
	    int secondIndex = middle + 1;
	    int currentIndex = from;
	    while (firstIndex <= middle || secondIndex <= to) {
	      if (firstIndex > middle) {
	         array[currentIndex] = auxilary[secondIndex];
	         secondIndex++;
	      } else if (secondIndex > to) {
	         array[currentIndex] = auxilary[firstIndex];
	         firstIndex++;
	      } else {
	         if (auxilary[firstIndex].compareTo(auxilary[secondIndex]) < 0) {
	           array[currentIndex] = auxilary[firstIndex];
	           firstIndex++;
	         } else {
	           array[currentIndex] = auxilary[secondIndex];
	           secondIndex++;
	         }
	      }

	      currentIndex++;
	    }
	  }
	  
	  public static void sortBU(Comparable[] array) {
		  Comparable[] auxilary = new Comparable[array.length];
		  
		  for (int size = 1; size < array.length; size *= 2) {
			  for (int i = 0; i < array.length; i += size*2 ) {
				  int from = i;
				  int middle = Math.min(array.length - 1, i + size - 1);
				  int to = Math.min(array.length - 1,i + size * 2 - 1);
				  merge(array, from, middle, to, auxilary);
			  }
		  }
	  }
	  
	  public static void main(String[] args) {
		Integer[] numbers = {1,2,3,4,-5,-10,7,13,15,22,2,3,4,5};
		
		sortBU(numbers);
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		
	  }
	}

