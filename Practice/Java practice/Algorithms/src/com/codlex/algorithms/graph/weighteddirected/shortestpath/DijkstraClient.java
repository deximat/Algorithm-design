package com.codlex.algorithms.graph.weighteddirected.shortestpath;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.weighteddirected.EdgeWeightedDigraph;
import com.codlex.algorithms.graph.weighteddirected.EdgeWeightedDigraphClient;

public class DijkstraClient extends Client<DijkstraShortestPath> {

	private EdgeWeightedDigraph graph;
	private final EdgeWeightedDigraphClient graphClient = new EdgeWeightedDigraphClient();

	private void doMst(int source) {
		this.object = new DijkstraShortestPath(this.graph, source);
	}

	@Override
	public String process(DijkstraShortestPath shortestPath, Command command) {
		switch (command.getKey()) {
		case "dijkstra":
			doMst(Integer.parseInt(command.getParam(0)));
			return "DIJKSTRA finished";
		case "dist":
			return Double.toString(shortestPath.getDistanceTo(Integer
					.parseInt(command.getParam(0))));
		case "connected":
			return Boolean.toString(this.object.hasPathTo(Integer
					.parseInt(command.getParam(0))));
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected DijkstraShortestPath createObject() {
		this.graph = new EdgeWeightedDigraph(15);
		return new DijkstraShortestPath(this.graph, 0);
	}

	public static void main(String[] args) {
		new DijkstraClient().execute();
	}

}
