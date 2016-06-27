package com.codlex.algorithms.search;

import java.util.Arrays;

public class Binary {

	
	public static int search(int[] array, int start, int end, int value) {
		int middle = (start + end) / 2;
		
		if (start > end) {
			return -1;
		}
		
		if (value < array[middle]) {
			return search(array, start, middle -1, value);
		} else if (value > array[middle]) {
			return search(array, middle + 1, end, value);
		} else { // value == array[middle]
//			while (middle - 1 >= 0 && value == array[middle - 1]) {
//				middle--;
//			}
			int lowerIndex = search(array, start, middle - 1, value);
			
			if (lowerIndex != -1) {
				return lowerIndex;
			} else {
				return middle;
			}
		}
		
	}
	
	public static int search(int[] array, int value) {
		return search(array, 0, array.length - 1, value);
	}
	
	public static void main(String[] args) {
		int[] array = generateArray(10, 10);
		int[] questions = generateQuestions(array);
		
		int errorCount = 0;
		for (int r = 0; r < array.length; r++) {
			Arrays.sort(array);
			rotate(array, r);
			showArray(array);
			int[] answers = generateAnswers(questions, array);
			
			for (int i = 0; i < questions.length; i++) {
				int javaAnswer = answers[i];
				int myAnswer = BinaryRotated.search(array, questions[i]);
				
				if (!(javaAnswer < 0 && myAnswer < 0) && (javaAnswer != myAnswer)) {
					printError(array, questions[i], javaAnswer, myAnswer);
					errorCount++;
				}
			}
		
		}
		System.out.println("errors: " + errorCount);
	}

	private static void showArray(int[] array) {
		System.out.print("Generated array: ");
		for (int element : array) {
			System.out.print(element + ", ");
		}
		System.out.println();
	}

	private static void rotate(int[] array, int r) {
		reverse(array, 0, array.length - 1);
		reverse(array, 0, r);
		reverse(array, r + 1, array.length - 1);
	}

	private static void reverse(int[] array, int start, int end) {
		while (start < end) {
			swap(array, start, end);
			start++;
			end--;
		}
	}
	
	private static void swap(int[] array, int start, int end) {
		int tmp = array[start];
		array[start] = array[end];
		array[end] = tmp;
	}

	private static int[] generateAnswers(int[] questions, int[] array) {
		int[] answers = new int[questions.length];
		for (int i = 0; i< questions.length; i++) {
			answers[i] = linearFind(array, questions[i]);
		}
		return answers;
	}

	private static int linearFind(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		
		return -1;
	}

	private static int[] generateQuestions(int[] array) {
		int[] questions = new int[array.length + 5];
		int current = 0;
		for (int inArray : array) {
			questions[current++] = inArray;
		}
		questions[current++] = 10;
		questions[current++] = -10;
		questions[current++] = 100000;
		questions[current++] = 0;
		questions[current++] = 1000;
		return questions;
	}

	private static void printError(int[] questions, int question, int javaAnswer, int myAnswer) {
		System.out.println("Error on: " + question + " java said: " + javaAnswer + " you said: " + myAnswer);
		for (int question2 : questions) {
			System.out.print(question2 + " ");
		}
		System.out.println();
	}

	private static int[] generateArray(int n, int range) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = (int)(Math.random() * range);
		}
		return array;
	}
}
