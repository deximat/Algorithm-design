package com.codlex.tests;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;

public class SortTest {
	
	
	
	private final String result;

	public SortTest(int tests, int elements) {
		
		List<Double> results = new ArrayList<>();
		for (int test = 0; test < tests; test++) {
			System.out.println("Testing " + test + " out of " + tests + " for " + elements + " elements.");
			results.add(doTheTest(elements));
			System.out.println("Finished " + test + " out of " + tests);
		}
		
		this.result = "After running " + tests + " tests, average time needed to sort " 
				+ elements + " array is " + String.format("%.2f", results.stream().mapToDouble(i -> i).average().orElse(0)) + "s";
	}

	private double doTheTest(int elements) {
		int[] randomArray = generateRandomArray(elements);
		Stopwatch stopwatch = new Stopwatch();
		Arrays.sort(randomArray);
		return stopwatch.elapsedTime();
	}

	private int[] generateRandomArray(int elements) {
		Random random = new Random();
		int[] randomArray = new int[elements];
		
		for (int i = 0; i < elements; i++) {
			randomArray[i] = random.nextInt();
		}
		
		return randomArray;
	}

	public static void main(String[] args) {
		int elements = 10000;
		List<SortTest> tests = new ArrayList<>();
		
		int orders = Integer.parseInt(args[0]);
		for (int i = 0; i < orders; i++) {
			tests.add(new SortTest(10, elements));
			elements *= 10;
		}

		for (SortTest test : tests) {
			test.printStats();
		}
	}

	private void printStats() {
		System.out.println(this.result);
	}
}
