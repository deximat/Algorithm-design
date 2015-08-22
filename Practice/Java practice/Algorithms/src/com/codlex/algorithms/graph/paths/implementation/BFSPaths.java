package com.codlex.algorithms.graph.paths.implementation;

import java.util.LinkedList;
import java.util.Queue;

import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.paths.AbstractPaths;

public class BFSPaths extends AbstractPaths {

	public BFSPaths(Graph graph, int source) {
		super(graph, source);
	}

	private void doFullSearch(Graph graph, int source) {
		Queue<Integer> toProcess = new LinkedList<>();
		this.fromVertex[source] = BEGINNING;
		toProcess.add(source);

		while (!toProcess.isEmpty()) {
			int vertexFrom = toProcess.poll();
			for (int vertexToProcess : graph.adjecentTo(vertexFrom)) {
				if (!isProcessed(vertexToProcess)) {
					this.fromVertex[vertexToProcess] = vertexFrom;
					toProcess.add(vertexToProcess);
				}
			}
		}
	}

	@Override
	protected void algorithm(Graph graph, int source) {
		doFullSearch(graph, source);
	}
}
