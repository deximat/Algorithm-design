package com.codlex;

import java.util.Random;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Stopwatch;

public class SubMapTest {
	
	public static void main(String[] args) {
		TreeMap<Integer, Integer> longsMap = new TreeMap<>();
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			longsMap.put(random.nextInt(), random.nextInt());
		}
		
		Stopwatch stop = new Stopwatch();
		for (Integer number : longsMap.subMap(1, 1000000).values()) {
			System.out.println(number);
		}		
		System.out.println("Time: " + stop.elapsedTime());
	}
}
