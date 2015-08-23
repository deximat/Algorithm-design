package com.codlex.algorithms.graph.weighted;

public class Edge implements Comparable<Edge> {

	private final int vertex1;
	private final int vertex2;
	private final double weight;

	public Edge(int vertex1, int vertex2, double weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public double getWeight() {
		return this.weight;
	}

	public int getEither() {
		return vertex1;
	}

	public int getOther(int vertex) {
		if (this.vertex1 == vertex) {
			return this.vertex2;
		} else {
			return this.vertex1;
		}
	}

	@Override
	public int compareTo(final Edge that) {
		return Double.compare(this.weight, that.weight);
	}

	@Override
	public String toString() {
		return Edge.class.getSimpleName() + "[" + this.vertex1 + ", "
				+ this.vertex2 + ": " + this.weight + "]";
	}
}
