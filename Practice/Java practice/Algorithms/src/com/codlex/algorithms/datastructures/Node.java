package com.codlex.algorithms.datastructures;

import java.util.Iterator;
import java.util.Scanner;

public class Node implements Iterable<Object>{
	
	public final Object data;
	public Node next;
	public Node(Object d) {
		this.data = d;
	}
	
	
	public void appendToTail(Object d) {
		Node newNode = new Node(d);
		Node n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = newNode;
	}
	
	public static Node deleteNode(Node head, Object d) {
		while (head.data == d) {
			head = head.next;
			
			if (head == null) {
				return null;
			}
		}
		
		Node n = head;
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
			}
			n = n.next;
		}
		
		return head;
	}
	
	public void deleteDuplicates() {
		Node n = this;
		while (n != null && n.next != null) {
			n.next = deleteNode(n.next, n.data);
			n = n.next;
		}
	}
	
	public static void main(String[] args) {
		Node list = new Node(2);
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			list.appendToTail(in.nextInt());
		}
		
		for (Object obj : list) {
			System.out.println((Integer) obj);
		}
		
		System.out.println("after duplicates");
		
		list.deleteDuplicates();
		
		for (Object obj : list) {
			System.out.println((Integer) obj);
		}
	}


	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			private Node current = Node.this;
			@Override
			public boolean hasNext() {
				return this.current != null;
			}

			@Override
			public Object next() {
				Object data = this.current.data;
				this.current = this.current.next;
				return data;
			}
			
		};
	}
}
