package com.codlex.algorithms.datastructures;

import java.util.Stack;

public class StackUtil {
	public static void sort(Stack<Integer> stack) {
		if (stack.size() < 2) {
			return;
		}
		
		Stack<Integer> temp = new Stack<Integer>();
		temp.add(stack.pop());
		
		while (true) {
			while (!stack.isEmpty() && stack.peek() >= temp.peek()) {

				temp.add(stack.pop());
			}
			
			if (stack.isEmpty()) {
				break;
			}
			
			int toInsert = stack.pop();
			
			while (!temp.isEmpty() && toInsert < temp.peek()) {

				stack.push(temp.pop());
			}
			
			temp.push(toInsert);
		}
		
		while (!temp.isEmpty()) {

			stack.push(temp.pop());
		}
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(5);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.push(4);
		
		sort(stack);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
	}
}
