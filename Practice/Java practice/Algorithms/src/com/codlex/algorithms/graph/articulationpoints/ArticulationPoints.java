package com.codlex.algorithms.graph.articulationpoints;

import java.util.Arrays;

import com.codlex.algorithms.graph.Graph;

public class ArticulationPoints {
	
	private static final int UNDISCOVERED = -1;
	private int count;
	private final boolean[] articulation;
	private final int[] discoveryTime;
	private final int[] minimumDiscoveryTime;
	
	private int currentTime;
	
	public int getCount() {
		return this.count;
	}
	
	public boolean isArticulationPoint(int vertex) {
		return this.articulation[vertex];
	}
	
	public ArticulationPoints(Graph graph) {
		this.articulation = new boolean[graph.getVerticesCount()];
		
		this.discoveryTime = new int[graph.getVerticesCount()];
		Arrays.fill(this.discoveryTime, UNDISCOVERED);
		
		this.minimumDiscoveryTime = new int[graph.getVerticesCount()];
		Arrays.fill(this.minimumDiscoveryTime, UNDISCOVERED);
		
		for (int vertex = 0; vertex < graph.getVerticesCount(); vertex++) {
			if (!isDiscovered(vertex)) {
				dfs(graph, vertex, vertex);
			}
			
			if (this.articulation[vertex]) {
				this.count++;
			}
		}
	}

	private void dfs(Graph graph, int from, int to ) {
		this.discoveryTime[to] = this.currentTime++;
		this.minimumDiscoveryTime[to] = this.discoveryTime[to];
		
		int children = 0;
		for (int adjecentVertex : graph.adjecentTo(to)) {
			if (!isDiscovered(adjecentVertex)) {
				children++;		
				dfs(graph, to, adjecentVertex);
				
				this.minimumDiscoveryTime[to] = Math.min(this.minimumDiscoveryTime[to], this.minimumDiscoveryTime[adjecentVertex]);
				
				if (this.minimumDiscoveryTime[adjecentVertex] >= this.discoveryTime[to] && from != to) {
					createArticulationVertex(to);
				}
			
			// ignore backward edge
			} else if (from != adjecentVertex) {
				this.minimumDiscoveryTime[to] = Math.min(this.minimumDiscoveryTime[to], this.discoveryTime[adjecentVertex]);
			}
		}
		
		// special case for root
		if (from == to && children > 1) {
			createArticulationVertex(to);
		}
	}

	private void createArticulationVertex(int to) {
		this.articulation[to] = true;
	}

	private boolean isDiscovered(int i) {
		return this.discoveryTime[i] != UNDISCOVERED;
	}
}
