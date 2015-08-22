package com.codlex.algorithms.graph.paths;

public interface Paths {
	int BEGINNING = -1;
	int UNPROCESSED = -2;
	
	boolean hasPathTo(int vertex);
	Iterable<Integer> pathTo(int vertex);
}
