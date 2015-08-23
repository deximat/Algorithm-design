package com.codlex.algorithms.graph.weighted.mst;

import com.codlex.algorithms.graph.weighted.Edge;

public interface MinimumSpanningTree {
	Iterable<Edge> getEdges();
	double getWeight();
}
