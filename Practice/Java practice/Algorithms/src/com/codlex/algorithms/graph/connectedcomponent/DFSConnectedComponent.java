package com.codlex.algorithms.graph.connectedcomponent;

import com.codlex.algorithms.graph.Graph;

public class DFSConnectedComponent implements ConnectedComponent {

	private static final int UNPROCESSED = -1;
	private final int count;
	private final int[] component;

	public DFSConnectedComponent(Graph graph) {
		this.component = new int[graph.getVerticesCount()];
		for (int i = 0; i < graph.getVerticesCount(); i++) {
			this.component[i] = UNPROCESSED;
		}

		int currentComponent = 0;

		for (int vertex = 0; vertex < graph.getVerticesCount(); vertex++) {
			if (!isProcessed(vertex)) {
				dfs(graph, vertex, currentComponent);
				currentComponent++;
			}
		}

		this.count = currentComponent;
	}

	private void dfs(Graph graph, int vertex, int component) {
		if (isProcessed(vertex)) {
			return;
		}
		this.component[vertex] = component;

		for (int vertexToProcess : graph.adjecentTo(vertex)) {
			dfs(graph, vertexToProcess, component);
		}
	}

	private boolean isProcessed(int vertex) {
		return this.component[vertex] != UNPROCESSED;
	}

	@Override
	public boolean isConnected(int vertex1, int vertex2) {
		return this.component[vertex1] == this.component[vertex2];
	}

	@Override
	public int count() {
		return this.count;
	}

	@Override
	public int id(int vertex) {
		return this.component[vertex];
	}

}
