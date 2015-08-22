package com.codlex.algorithms.graph.implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.codlex.algorithms.graph.Graph;

public class GraphList implements Graph {
	
	private int edgesCount;
	private final int verticesCount;
	private final List<List<Integer>> adjecencyList;
	
	public GraphList (int verticesCount) {
		this.verticesCount = verticesCount;
		this.adjecencyList = new ArrayList<List<Integer>>();
		for (int i = 0; i < this.verticesCount; i++) {
			this.adjecencyList.add(new LinkedList<Integer>());
		}
	}
	
	@Override
	public int getVerticesCount() {
		return this.verticesCount;
	}
	
	@Override
	public int getEdgesCount() {
		return this.edgesCount;
	}
	
	@Override
	public void addEdge(int vertex1, int vertex2) {
		
		// add vertex1 -> vertex2
		this.adjecencyList.get(vertex1).add(vertex2);
		
		// add vertex2 -> vertex1
		this.adjecencyList.get(vertex2).add(vertex1);
	
		
		this.edgesCount++;
	}
	
	@Override
	public Iterable<Integer> adjecentTo(int vertex) {
		return this.adjecencyList.get(vertex);
	}
	
}