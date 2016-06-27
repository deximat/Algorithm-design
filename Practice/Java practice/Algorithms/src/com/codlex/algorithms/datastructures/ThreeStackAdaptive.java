package com.codlex.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ThreeStackAdaptive {
	private static final int SIZE = 100;
	
	private List<Integer> freeSpace = new ArrayList<Integer>();
	private StackNode[] buffer = new StackNode[SIZE];

	private int[] stackPointer = {-1, -1, -1};
	
	public ThreeStackAdaptive() {
		for (int i = 0; i < buffer.length; i++) {
			this.freeSpace.add(i);
		}
	}
	
	
	private int drainSlot() {
		return freeSpace.remove(freeSpace.size() - 1);
	}
	
	public void push(int stackIndex, int value) {
		int freeSlot = drainSlot();
		this.buffer[freeSlot] = new StackNode(this.stackPointer[stackIndex], value);
		this.stackPointer[stackIndex] = freeSlot;
	}
	
	public int pop(int stackIndex) {
		int slotIndex = this.stackPointer[stackIndex];
		StackNode node = this.buffer[slotIndex];
		this.buffer[slotIndex] = null;
		this.stackPointer[stackIndex] = node.previous;
		returnSlot(slotIndex);
		return node.data;
		
	}
	
	public boolean isEmpty(int stackIndex) {
		return this.stackPointer[stackIndex] == -1;
	}
	
	public int peek(int stackIndex) {
		return this.buffer[this.stackPointer[stackIndex]].data;
	}
	
	private void returnSlot(int slotIndex) {
		this.freeSpace.add(slotIndex);
	}

	private class StackNode {
		final int previous;
		final int  data;
		
		public StackNode(int previous, int data) {
			this.previous = previous;
			this.data = data;
		}
	}
	
	
	public static void main(String[] args) {
		ThreeStackAdaptive stack = new ThreeStackAdaptive();
		
		stack.push(0,1);
		stack.push(0, 2);
		stack.push(1, 7);
		stack.push(0, 3);
		
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(0));

		stack.push(0,1);
		stack.push(0, 2);
		stack.push(1, 7);
		stack.push(0, 3);
		
		
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(0));
		System.out.println(stack.pop(1));
		System.out.println(stack.pop(1));
		System.out.println(stack.pop(0));

	}
}
