package com.codlex.algorithms.graph.weighteddirected;

import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedDigraph {

	private final int verticesCount;
	private final List<DirectedEdge>[] adjecencyList;
	private int edgesCount;
	
	public EdgeWeightedDigraph(int verticesCount) {
		this.verticesCount = verticesCount;
		this.adjecencyList = new List[this.verticesCount];
		for (int i = 0; i < this.verticesCount; i++) {
			this.adjecencyList[i] = new LinkedList<DirectedEdge>();
		}
	}
	
	public int getVerticesCount() {
		return this.verticesCount;
	}
	
	public int getEdgesCount() {
		return this.edgesCount;
	}
	
	public void addEdge(DirectedEdge edge) {
		this.adjecencyList[edge.getFrom()].add(edge);
		this.edgesCount++;
	}
	
	public Iterable<DirectedEdge> adjecentTo(int vertex) {
		return this.adjecencyList[vertex];
	}
	
	public Iterable<DirectedEdge> getEdges() {
		List<DirectedEdge> edges = new LinkedList<>();
		
		for (int i = 0; i < this.verticesCount; i++) {
			edges.addAll(this.adjecencyList[i]);
		}
		
		return edges;
	}
}
