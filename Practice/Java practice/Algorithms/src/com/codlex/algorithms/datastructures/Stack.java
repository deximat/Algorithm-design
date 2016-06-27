package com.codlex.algorithms.datastructures;


public class Stack {

	private Node top;
	
	public void push(Object obj) {
		Node newTop = new Node(obj);
		newTop.next = this.top;
		this.top = newTop;
	}
	
	public Object pop() {
		if (this.top != null) {
			Object data = this.top.data;
			this.top = this.top.next;
			return data;
		}
		return null;
	}
}
