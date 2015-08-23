package com.codlex.algorithms.graph.weighted.mst;

import com.codlex.algorithms.client.Client;
import com.codlex.algorithms.client.Command;
import com.codlex.algorithms.graph.weighted.EdgeWeightedGraph;
import com.codlex.algorithms.graph.weighted.EdgeWeightedGraphClient;
import com.codlex.util.IterableUtils;

public class MSTClient extends Client<MinimumSpanningTree> {

	private EdgeWeightedGraph graph;
	private final EdgeWeightedGraphClient graphClient = new EdgeWeightedGraphClient();

	private void doMst() {
		this.object = new PrimMST(this.graph);
	}

	@Override
	public String process(MinimumSpanningTree search, Command command) {
		switch (command.getKey()) {
		case "mst":
			doMst();
			return "MST finished";
		case "edges":
			return IterableUtils.toString(this.object.getEdges());
		case "weight":
			return Double.toString(this.object.getWeight());
		default:
			return this.graphClient.process(this.graph, command);
		}
	}

	@Override
	protected MinimumSpanningTree createObject() {
		this.graph = new EdgeWeightedGraph(5);
		return new PrimMST(this.graph);
	}

	public static void main(String[] args) {
		new MSTClient().execute();
	}

}
