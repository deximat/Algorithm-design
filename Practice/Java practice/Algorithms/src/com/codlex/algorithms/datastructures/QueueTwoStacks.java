package com.codlex.algorithms.datastructures;

import java.util.Stack;

public class QueueTwoStacks {
	
	private Stack<Integer> input = new Stack<Integer>();
	private Stack<Integer> output = new Stack<Integer>();
	
	private void fetchFromInput() {
		while (!input.isEmpty()) {
			output.push(input.pop());
		}
	}
	
	
	public void enqueue(int value) {
		this.input.push(value);
	}
	
	public Integer dequeue() {
		if (this.output.isEmpty()) {
			fetchFromInput();
		}
		if (!this.output.isEmpty()) {
			return this.output.pop();
		} else {
			return null;
		}
	} 
	
	
	public static void main(String[] args) {
		QueueTwoStacks queue = new QueueTwoStacks();
		
		for (int i = 0; i < 100; i++) {
			double random = Math.random();
			queue.enqueue(i);
			if (random < 0.9) {
				System.out.println("random " + i);
				System.out.println(queue.dequeue());
			}
		}
		
		while (!queue.isEmpty()) {
			System.out.println(queue.dequeue());
		}
	}


	private boolean isEmpty() {
		return this.input.isEmpty() && this.output.isEmpty();
	}
}
