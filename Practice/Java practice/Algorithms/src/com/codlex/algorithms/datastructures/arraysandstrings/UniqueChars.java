package com.codlex.algorithms.datastructures.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

public class UniqueChars {

	
	private boolean solution1(String input) {
		Map<Character, Integer> counts = new HashMap<>();
		
		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);
			Integer count = counts.get(character);
			if (count == null) {
				count = 0;
			}
			if (count == 0) {
				count++;
				counts.put(character, count);
			} else {
				return false;
			}
		}
		
		return true;
		
	} 
	
	private boolean solution2(String input) {
		int bitmap = 0;
		
		for (int i = 0; i < input.length(); i++) {
			int numCharValue = input.charAt(i) - 'a';
			if ((bitmap & (1 << numCharValue)) != 0) {
				return false;
			} else {
				bitmap = bitmap | (1 << numCharValue);
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String input = "a";
		System.out.println(new UniqueChars().solution1(input));
		System.out.println(new UniqueChars().solution2(input));
		
		System.out.println((int)'0');
		
		for (char c  = 767; c < 810; c++) {
			System.out.print(c);
		}

	}
}
