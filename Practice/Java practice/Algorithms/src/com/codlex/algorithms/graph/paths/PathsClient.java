package com.codlex.algorithms.graph.paths;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.GraphClient;
import com.codlex.algorithms.graph.implementation.GraphList;
import com.codlex.algorithms.graph.paths.implementation.BFSPaths;
import com.codlex.algorithms.graph.paths.implementation.DFSPaths;
import com.codlex.util.IterableUtils;

public class PathsClient extends Client<Paths> {
	
	private Graph graph;
	private final GraphClient graphClient = new GraphClient();
	
	private void doDfsPaths(int source) {
		this.object = new DFSPaths(this.graph, source);
	}
	
	private void doBfsPaths(int source) {
		this.object = new BFSPaths(this.graph, source);
	}
	
	@Override
	public String process(Paths paths, Command command) {
		switch (command.getKey()) {
		case "dfs":
			doDfsPaths(Integer.parseInt(command.getParam(0)));
			return "DFS finished";
		case "bfs":
			doBfsPaths(Integer.parseInt(command.getParam(0)));
			return "BFS finished";
		case "path":
			int pathTo = Integer.parseInt(command.getParam(0));
			return "Path: " + IterableUtils.toString(paths.pathTo(pathTo));
		case "hasPath":
			return Boolean.toString(paths.hasPathTo(Integer.parseInt(command.getParam(0))));
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected Paths createObject() {	
		this.graph = new GraphList(100);
		return new DFSPaths(this.graph, 0);
	}
	
	public static void main(String[] args) {
		new PathsClient().execute();
	}

}
