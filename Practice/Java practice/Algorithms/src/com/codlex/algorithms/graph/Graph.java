package com.codlex.algorithms.graph;

public interface Graph {	
	int getVerticesCount();
	int getEdgesCount();
	void addEdge(int vertex1, int vertex2);
	Iterable<Integer> adjecentTo(int vertex);
}
