package com.codlex.algorithms.graph.search.implementation;

import java.util.LinkedList;
import java.util.Queue;

import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.search.Search;

public class BreathFirstSearch implements Search {

	private final boolean[] connected;
	private int connectedVerticesCount;

	public BreathFirstSearch(Graph graph, int source) {
		this.connected = new boolean[graph.getVerticesCount()];
		doFullSearch(graph, source);
	}

	private void doFullSearch(Graph graph, int vertex) {
		Queue<Integer> unprocessed = new LinkedList<Integer>();
		unprocessed.add(vertex);
		this.connected[vertex] = true;
		this.connectedVerticesCount++;

		while (!unprocessed.isEmpty()) {
			int unprocessedVertex = unprocessed.poll();
			for (int vertexToVisit : graph.adjecentTo(unprocessedVertex)) {
				if (!this.connected[vertexToVisit]) {
					this.connected[vertexToVisit] = true;
					this.connectedVerticesCount++;
					unprocessed.add(vertexToVisit);
				}
			}
		}
	}

	@Override
	public boolean isConnectedTo(int vertex) {
		return this.connected[vertex];
	}

	@Override
	public int getConnectedCount() {
		return this.connectedVerticesCount;
	}

}
