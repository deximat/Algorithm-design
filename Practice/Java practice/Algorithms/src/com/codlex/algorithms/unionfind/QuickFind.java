package com.codlex.algorithms.unionfind;

import java.util.Scanner;

public class QuickFind {
	
	private final int[] parents;
	
	public QuickFind(int n) {
		this.parents = new int[n];
		
		for (int i = 0; i < n; i++) {
			this.parents[i] = i;
		}
	}
	
	public boolean isConnected(int firstNode, int secondNode) {
		return calculateRoot(firstNode) == calculateRoot(secondNode);
	}
	
	private int calculateRoot(int node) {
		int currentNode = node;
		while (currentNode != this.parents[currentNode]) {
			currentNode = this.parents[currentNode];
		}
		return currentNode;
	}
	
	public void union(int firstNode, int secondNode) {
		int firstRoot = calculateRoot(firstNode);
		int secondRoot = calculateRoot(secondNode);
		this.parents[firstRoot] = secondRoot;
	}
	
	public static void main(String[] args) {
		UnionFind union = new UnionFind(10);
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