package com.codlex.algorithms.graph.connectedcomponent;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.GraphClient;
import com.codlex.algorithms.graph.implementation.GraphList;

public class ConnectedComponentClient extends Client<ConnectedComponent> {

	private Graph graph;
	private final GraphClient graphClient = new GraphClient();

	private void doDfsCC() {
		this.object = new DFSConnectedComponent(this.graph);
	}

	@Override
	public String process(ConnectedComponent connectedComponent, Command command) {
		switch (command.getKey()) {
		case "dfs":
			doDfsCC();
			return "DFS finished";
		case "count":
			return "Number of connected components: " + this.object.count();
		case "connected":
			int vertex1 = Integer.parseInt(command.getParam(0));
			int vertex2 = Integer.parseInt(command.getParam(1));
			return Boolean.toString(connectedComponent.isConnected(vertex1,
					vertex2));
		case "id":
			return Integer.toString(connectedComponent.id(Integer
					.parseInt(command.getParam(0))));
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected ConnectedComponent createObject() {
		this.graph = new GraphList(100);
		return new DFSConnectedComponent(this.graph);
	}

	public static void main(String[] args) {
		new ConnectedComponentClient().execute();
	}

}
