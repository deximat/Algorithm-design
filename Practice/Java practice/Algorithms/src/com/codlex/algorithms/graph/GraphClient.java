package com.codlex.algorithms.graph;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.implementation.GraphMatrix;
import com.codlex.util.IterableUtils;

public class GraphClient extends Client<Graph> {

	public static void main(String[] args) {
		new GraphClient().execute();
	}

	@Override
	public String process(Graph graph, Command command) {
		switch (command.getKey()) {
		case "add":
			int vertex1 = Integer.parseInt(command.getParam(0));
			int vertex2 = Integer.parseInt(command.getParam(1));
			graph.addEdge(vertex1, vertex2);
			return "Added {" + vertex1 + ", " + vertex2 + "}";
		case "edges":
			return Integer.toString(graph.getEdgesCount());
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
	protected Graph createObject() {
		return new GraphMatrix(15);
	}

}
