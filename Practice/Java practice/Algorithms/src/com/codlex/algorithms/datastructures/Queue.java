package com.codlex.algorithms.datastructures;

import java.util.Scanner;

public class Queue {
	
	private Node first;
	private Node last;
	
	public void enqueue(Object data) {
		Node newNode = new Node(data);
		if (this.first == null) {
			this.first = newNode;
			this.last = newNode;
		} else {
			this.last.next = newNode;
			this.last = newNode;
		}
	}
	
	public Object dequeue() {
		if (this.first != null) {
			Node n = this.first;
			this.first = this.first.next;
			return n.data;
		}
		return null;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Stack queue = new Stack();
		int n = in.nextInt();
		
		for (int i = 0; i < n; i++) {
			queue.push(in.nextInt());
		}
		
		Integer data = (Integer) queue.pop();
		while (data != null) {
			System.out.println(data);
			data = (Integer) queue.pop();
		}
	}
}
