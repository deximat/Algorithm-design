package com.codlex.algorithms.graph.search.implementation;

import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.search.Search;


public class DepthFirstSearch implements Search {

	private final boolean[] connected;
	private int connectedVerticesCount;
	
	public DepthFirstSearch(Graph graph, int source) {
		this.connected = new boolean[graph.getVerticesCount()];
		
		doFullSearch(graph, source);
	}
	
	private void doFullSearch(Graph graph, int vertex) {
		// already visited
		if (this.connected[vertex]) {
			return;
		}
		
		this.connected[vertex] = true;
		this.connectedVerticesCount++;
		
		// recursively visit all adjacent vertices to this vertex
		for (int adjecentVertex : graph.adjecentTo(vertex)) {
			doFullSearch(graph, adjecentVertex);
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
