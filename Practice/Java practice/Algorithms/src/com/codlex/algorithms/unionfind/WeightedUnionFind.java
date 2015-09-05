package com.codlex.algorithms.unionfind;

import java.util.Scanner;

public class WeightedUnionFind {
	
	private class Node {
		int id;
		int size;
		Node parent;
		
		private Node(int id) {
			this.id = id;
			this.size = 1;
			this.parent = this;
		}
		
		private boolean isRoot() {
			return this.id == this.parent.id;
		}
		
		private Node calculateRoot() {
			Node currentNode = this;
			while (!currentNode.isRoot()) {
				currentNode = currentNode.parent;
			}
			return currentNode;
		}
	}
	
	private Node nodes[];
	public WeightedUnionFind(int n) {
		this.nodes = new Node[n];
		
		for (int i = 0; i < n; i++) {
			this.nodes[i] = new Node(i);
		}
	}
	
	public boolean isConnected(int firstNode, int secondNode) {
		return isConnected(this.nodes[firstNode], this.nodes[secondNode]);
	}
	
	private boolean isConnected(Node firstNode, Node secondNode) {
		return firstNode.calculateRoot() == secondNode.calculateRoot();
	}
	
	public void union(int firstNodeId, int secondNodeId) {
		union(this.nodes[firstNodeId], this.nodes[secondNodeId]);
	}
	
	private void union(Node firstNode, Node secondNode) {
		Node firstRoot = firstNode.calculateRoot();
		Node secondRoot = secondNode.calculateRoot();
		
		if (firstRoot == secondRoot) {
			return;
		}
		
		if (firstRoot.size > secondRoot.size) {
			firstRoot.parent = secondRoot;
			secondRoot.size += firstRoot.size;
		} else {
			secondRoot.parent = firstRoot;
			firstRoot.size += secondRoot.size;
		}
	}
	
	
	public static void main(String[] args) {
		WeightedUnionFind union = new WeightedUnionFind(10);
		Scanner in = new Scanner(System.in);
		while (true) {
			String command = in.next();
			int firstNode = in.nextInt();
			int secondNode = in.nextInt();
			
			switch (command) {
			case "union":
				union.union(firstNode, secondNode);
				System.out.println("Union finished!");
				break;
			case "connected":
				System.out.println("Connected:" + union.isConnected(firstNode, secondNode));
				break;
			}
		}
	}
}