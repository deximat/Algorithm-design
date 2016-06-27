package com.codlex.algorithms.datastructures;

import java.util.Stack;

public class SetOfStacks {
	private static final int TRESHOLD = 2;
	Stack<Stack<Integer>> stacks = new Stack<Stack<Integer>>();
	
	
	private Stack<Integer> fetchStack() {
		while (!this.stacks.isEmpty()) {
			if (this.stacks.size() == 1 || !this.stacks.peek().isEmpty()) {
				return this.stacks.peek();
			} else {
				this.stacks.pop(); 
			}
		}
		return null;
	}
	
	public void push(int value) {
		Stack<Integer> nextStack = fetchStack();
		if (nextStack == null || nextStack.size() >= TRESHOLD) {
			nextStack = new Stack<Integer>();
			this.stacks.add(nextStack);
		}
		nextStack.push(value);
	}
	
	public Integer peek() {
		Stack<Integer> nextStack = fetchStack();
		if (nextStack != null && !nextStack.isEmpty()) {
			return nextStack.peek();
		} else {
			return null;
		}
	}
	
	public Integer pop() {
		Stack<Integer> nextStack = fetchStack();
		if (nextStack != null && !nextStack.isEmpty()) {
			return nextStack.pop();
		} else {
			return null;
		}
	}
	
	public Integer popAt(int index) {
		if (index >= this.stacks.size()) {
			return null;
		}
		Stack<Integer> stack = this.stacks.get(index);
		if (!stack.isEmpty()) {
			return this.stacks.get(index).pop();
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		SetOfStacks stacks = new SetOfStacks();
		
		int number = 1;
		for (int i = 0; i < 60; i++) {
			if ((i / 10) % 2 == 0) {
				// System.out.println("Pushing " + i);
				stacks.push(number++);
			} else {
				// System.out.println("Poping " + i);
				System.out.println(stacks.pop());
			}
		}
		
	}

}
