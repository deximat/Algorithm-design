package com.codlex.algorithms.datastructures;

import java.util.Iterator;

public class LinkedList<Type> implements Iterable<Type> {
	private Node<Type> current;
	private Node<Type> last;

	private class Node<Type> {
		private Type value;
		private Node<Type> next;

		public Node(Type value) {
			this.value = value;
		}
	}

	public LinkedList() {
	}

	public boolean isEmpty() {
		return this.current == null;
	}

	public void pushBack(Type value) {
		Node newNode = new Node(value);
		if (isEmpty()) {
			this.current = newNode;
			this.last = newNode;
		} else {
			this.last.next = newNode;
			this.last = newNode;
		}
	}

	private class LinkedListIterator implements Iterator<Type> {
		private Node<Type> current;

		public LinkedListIterator(Node<Type> first) {
			this.current = first;
		}

		public boolean hasNext() {
			return this.current != null;
		}

		public Type next() {
			Type value = this.current.value;
			this.current = this.current.next;

			return value;
		}
	}

	public Iterator iterator() {
		return new LinkedListIterator(this.current);
	}

	public static void printLinkedList(LinkedList list) {
		
		for (Object object : list) {
			System.out.print(object + " ");
		}
		System.out.println();
	}
//	public static void main(String[] args) {
//		LinkedList<Integer> linkedList = new LinkedList<>();
//		
//		
//		for (int i = 0; i < 100; i++) {
//			linkedList.pushBack(i);
//			printLinkedList(linkedList);
//		}
//	}
	
	public static void main(String[] args) {
		double a = 12.3;
		long b = ~0;
		
		Stack stack = new Stack();
		java.util.Queue<?> queue = new java.util.LinkedList<>();
		
		System.out.println(Long.toBinaryString(b));
		
	}
}
