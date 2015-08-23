package com.codlex.algorithms.graph.weighted;

import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedGraph {

	private final int verticesCount;
	private int edgesCount;
	private List<Edge>[] adjecencyList;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int verticesCount) {
		this.verticesCount = verticesCount;
		this.adjecencyList = (List<Edge>[]) new List[this.verticesCount];
		for (int i = 0; i < this.verticesCount; i++) {
			this.adjecencyList[i] = new LinkedList<>();
		}
	}

	public int getVerticesCount() {
		return this.verticesCount;
	}

	public int getEdgesCount() {
		return this.edgesCount;
	}

	public void addEdge(Edge edge) {
		int vertex1 = edge.getEither();
		int vertex2 = edge.getOther(vertex1);
		this.adjecencyList[vertex1].add(edge);
		this.adjecencyList[vertex2].add(edge);
		this.edgesCount++;
	}

	public Iterable<Edge> adjecentTo(int vertex) {
		return this.adjecencyList[vertex];
	}

	public Iterable<Edge> getEdges() {
		List<Edge> allEdges = new LinkedList<Edge>();
		for (int i = 0; i < this.verticesCount; i++) {
			for (Edge edge : this.adjecencyList[i]) {
				// sack duplicated edges
				if (i < edge.getOther(i)) {
					allEdges.add(edge);
				}
			}
		}
		return allEdges;
	}
}
