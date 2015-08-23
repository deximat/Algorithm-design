package com.codlex.algorithms.graph.weighted.mst;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.codlex.algorithms.graph.weighted.Edge;
import com.codlex.algorithms.graph.weighted.EdgeWeightedGraph;

/**
 * Assumes that graph is connected. 
 */
public class PrimMST implements MinimumSpanningTree {

	private final boolean inTree[];
	private double weight;
	private final List<Edge> edges = new LinkedList<>();

	public PrimMST(EdgeWeightedGraph graph) {
		this.inTree = new boolean[graph.getVerticesCount()];
		findMST(graph);
	}

	private void findMST(EdgeWeightedGraph graph) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();

		int verticesAdded = 1;
		this.inTree[0] = true;

		// add all adjecent to 0
		for (Edge edge : graph.adjecentTo(0)) {
			queue.add(edge);
		}

		while (!queue.isEmpty() && verticesAdded != graph.getVerticesCount()) {
			Edge nextEdge = queue.poll();
			int newVertex = nextEdge.getEither();
			// try other
			if (this.inTree[newVertex]) {
				newVertex = nextEdge.getOther(newVertex);
			}
			if (!this.inTree[newVertex]) {
				this.inTree[newVertex] = true;
				this.edges.add(nextEdge);
				this.weight += nextEdge.getWeight();
				verticesAdded++;
				// add all adjecent edges to new vertex
				for (Edge edge : graph.adjecentTo(newVertex)) {
					queue.add(edge);
				}
			}
		}

	}

	@Override
	public Iterable<Edge> getEdges() {
		return this.edges;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

}
