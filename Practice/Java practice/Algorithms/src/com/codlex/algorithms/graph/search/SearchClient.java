package com.codlex.algorithms.graph.search;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.GraphClient;
import com.codlex.algorithms.graph.implementation.GraphList;
import com.codlex.algorithms.graph.search.implementation.BreathFirstSearch;
import com.codlex.algorithms.graph.search.implementation.DepthFirstSearch;

public class SearchClient extends Client<Search> {
	
	private Graph graph;
	private final GraphClient graphClient = new GraphClient();
	
	private void doDfs(int source) {
		this.object = new DepthFirstSearch(this.graph, source);
	}
	
	private void doBfs(int source) {
		this.object = new BreathFirstSearch(this.graph, source);
	}
	
	@Override
	public String process(Search search, Command command) {
		switch (command.getKey()) {
		case "dfs":
			doDfs(Integer.parseInt(command.getParam(0)));
			return "DFS finished";
		case "bfs":
			doBfs(Integer.parseInt(command.getParam(0)));
			return "BFS finished";
		case "count":
			return "Number of vertices connected: " + this.object.getConnectedCount();
		case "connected":
			return Boolean.toString(search.isConnectedTo(Integer.parseInt(command.getParam(0))));
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected Search createObject() {
		
		this.graph = new GraphList(100);
		return new DepthFirstSearch(this.graph, 0);
	}
	
	public static void main(String[] args) {
		new SearchClient().execute();
	}

}
