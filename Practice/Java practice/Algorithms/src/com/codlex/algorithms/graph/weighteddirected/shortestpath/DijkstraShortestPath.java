package com.codlex.algorithms.graph.weighteddirected.shortestpath;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.codlex.algorithms.graph.weighteddirected.DirectedEdge;
import com.codlex.algorithms.graph.weighteddirected.EdgeWeightedDigraph;

public class DijkstraShortestPath {

	private double[] distanceTo;

	public DijkstraShortestPath(EdgeWeightedDigraph graph, int source) {
		this.distanceTo = new double[graph.getVerticesCount()];
		Arrays.fill(this.distanceTo, Integer.MAX_VALUE);

		dijkstra(graph, source);

	}

	private void dijkstra(EdgeWeightedDigraph graph, int source) {
		this.distanceTo[source] = 0;

		PriorityQueue<DirectedEdge> edges = new PriorityQueue<>();

		for (DirectedEdge edge : graph.adjecentTo(source)) {
			edges.add(edge);
		}

		while (!edges.isEmpty()) {
			DirectedEdge edge = edges.poll();
			if (this.distanceTo[edge.getTo()] == Integer.MAX_VALUE) {
				relax(edge);
				
				for (DirectedEdge adjEdge : graph.adjecentTo(edge.getTo())) {
					edges.add(adjEdge);
				}
			}
		}
	}

	private void relax(DirectedEdge edge) {
		this.distanceTo[edge.getTo()] = this.distanceTo[edge.getFrom()]
				+ edge.getWeight();
	}
	
	public double getDistanceTo(int vertex) {
		return this.distanceTo[vertex];
	}
	
	public boolean hasPathTo(int vertex) {
		return this.distanceTo[vertex] != Integer.MAX_VALUE;
	}
	
	public Iterable<DirectedEdge> pathTo(int vertex) {
		// TODO: implement
		return null;
	}
}
