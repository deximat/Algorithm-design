package com.codlex.algorithms.datastructures;

public class ThreeStack {
	private static final int stackSize = 10;
	private int[] buffer = new int[stackSize * 3];
	private int[] stackPointer = {-1, stackSize - 1, stackSize*2 - 1};
	
	public void push(int stackIndex, int value) {
		this.stackPointer[stackIndex]++;
		this.buffer[this.stackPointer[stackIndex]] = value;
	}
	
	public int pop(int stackIndex) {
		int data = this.buffer[this.stackPointer[stackIndex]];
		this.stackPointer[stackIndex]--;
		return data;
	}
	
	public int peek(int stackIndex) {
		return this.buffer[this.stackPointer[stackIndex]];
	}
	
	public boolean isEmpty(int stackIndex) {
		return stackIndex * stackSize - 1 == stackPointer[stackIndex];
	}

	
	public static void main(String[] args) {
		ThreeStack stack = new ThreeStack();
		
		stack.push(0,1);
		stack.push(0, 2);
		stack.push(1, 7);
		stack.push(0, 3);
		
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(1));
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(0));
		
	}
}
