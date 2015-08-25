package com.codlex.algorithms.graph.weighteddirected;

import com.codlex.algorithms.graph.weighted.Edge;

public class DirectedEdge implements Comparable<DirectedEdge> {

	private final int from;
	private final int to;
	private final double weight;

	public DirectedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return this.from;
	}

	public int getTo() {
		return this.to;
	}

	public double getWeight() {
		return this.weight;
	}

	@Override
	public String toString() {
		return Edge.class.getSimpleName() + "[" + this.from + " -> " + this.to
				+ ": " + this.weight + "]";
	}

	@Override
	public int compareTo(DirectedEdge that) {
		return Double.compare(this.weight, that.weight);
	}
}
