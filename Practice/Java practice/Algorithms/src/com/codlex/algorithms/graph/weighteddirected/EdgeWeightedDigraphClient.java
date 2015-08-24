package com.codlex.algorithms.graph.weighteddirected;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.util.IterableUtils;

public class EdgeWeightedDigraphClient extends Client<EdgeWeightedDigraph> {

	public static void main(String[] args) {
		new EdgeWeightedDigraphClient().execute();
	}

	@Override
	public String process(EdgeWeightedDigraph graph, Command command) {
		switch (command.getKey()) {
		case "add":
			int vertex1 = Integer.parseInt(command.getParam(0));
			int vertex2 = Integer.parseInt(command.getParam(1));
			double weight = Double.parseDouble(command.getParam(2));
			graph.addEdge(new DirectedEdge(vertex1, vertex2, weight));
			return "Added {" + vertex1 + ", " + vertex2 + "} of weight " + weight;
		case "edgesCount":
			return Integer.toString(graph.getEdgesCount());
		case "edges":
			return IterableUtils.toString(graph.getEdges());
		case "vertices":
			return Integer.toString(graph.getVerticesCount());
		case "adj":
			int vertex = Integer.parseInt(command.getParam(0));
			return IterableUtils.toString(graph.adjecentTo(vertex));
		default:
			return "Invalid command";
		}
	}

	@Override
	protected EdgeWeightedDigraph createObject() {
		return new EdgeWeightedDigraph(15);
	}

}
