package com.codlex.algorithms.graph.connectedcomponent;

public interface ConnectedComponent {
	boolean isConnected(int vertex1, int vertex2);
	int count();
	int id(int vertex);
}
