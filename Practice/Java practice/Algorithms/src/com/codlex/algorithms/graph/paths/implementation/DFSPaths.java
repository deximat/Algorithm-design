package com.codlex.algorithms.graph.paths.implementation;

import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.paths.AbstractPaths;

public class DFSPaths extends AbstractPaths {

	public DFSPaths(Graph graph, int source) {
		super(graph, source);
	}

	@Override
	protected void algorithm(Graph graph, int source) {
		doFullSearch(graph, source, BEGINNING);
	}

	private void doFullSearch(Graph graph, int vertex, int from) {
		if (isProcessed(vertex)) {
			return;
		}

		this.fromVertex[vertex] = from;

		for (int adjecentVertex : graph.adjecentTo(vertex)) {
			doFullSearch(graph, adjecentVertex, vertex);
		}
	}

}
