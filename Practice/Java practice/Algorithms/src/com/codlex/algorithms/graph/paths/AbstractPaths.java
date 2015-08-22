package com.codlex.algorithms.graph.paths;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.codlex.algorithms.graph.Graph;

public abstract class AbstractPaths implements Paths {

	public AbstractPaths(Graph graph, int source) {
		this.fromVertex = new int[graph.getVerticesCount()];

		for (int i = 0; i < graph.getVerticesCount(); i++) {
			this.fromVertex[i] = UNPROCESSED;
		}

		algorithm(graph, source);
	}

	@Override
	public boolean hasPathTo(int vertex) {
		return isProcessed(vertex);
	}

	@Override
	public Iterable<Integer> pathTo(int vertex) {
		if (!hasPathTo(vertex)) {
			throw new NoSuchElementException();
		}

		List<Integer> list = new LinkedList<>();

		int currentVertex = vertex;
		while (this.fromVertex[currentVertex] != BEGINNING) {
			list.add(currentVertex);
			currentVertex = this.fromVertex[currentVertex];
		}
		list.add(currentVertex);

		// wanted to reverse by Stack but iterator is wrong
		Collections.reverse(list);

		return list;
	}

	protected abstract void algorithm(Graph graph, int source);

	protected int[] fromVertex;

	protected boolean isProcessed(int vertex) {
		return this.fromVertex[vertex] != UNPROCESSED;
	}
}
