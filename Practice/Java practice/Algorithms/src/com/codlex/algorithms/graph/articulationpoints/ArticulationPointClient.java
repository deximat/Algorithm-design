package com.codlex.algorithms.graph.articulationpoints;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.Graph;
import com.codlex.algorithms.graph.GraphClient;
import com.codlex.algorithms.graph.implementation.GraphList;

public class ArticulationPointClient extends Client<ArticulationPoints> {

	private Graph graph;
	private final GraphClient graphClient = new GraphClient();

	private void doDfsAp() {
		this.object = new ArticulationPoints(this.graph);
	}

	@Override
	public String process(ArticulationPoints articulationPoint, Command command) {
		switch (command.getKey()) {
		case "dfs":
			doDfsAp();
			return "DFS finished";
		case "count":
			return "Number of articulation points: " + articulationPoint.getCount();
		case "isPoint":
			int vertex = Integer.parseInt(command.getParam(0));
			return Boolean.toString(articulationPoint.isArticulationPoint(vertex));
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected ArticulationPoints createObject() {
		this.graph = new GraphList(100);
		return new ArticulationPoints(this.graph);
	}

	public static void main(String[] args) {
		new ArticulationPointClient().execute();
	}

}
