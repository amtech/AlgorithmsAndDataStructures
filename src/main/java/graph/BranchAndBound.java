package graph;

import graph.model.Graph;
import graph.model.Graph.Node;

import java.util.List;

public class BranchAndBound {

	private final Graph graph;
	private final Node startNode;
	private final Node finishNode;
	
	public BranchAndBound(final Graph graph, final String startNodeKey, final String finishNodeKey) {
		super();
		if (graph == null) {
			throw new NullPointerException("Graph cannot be null");
		}
		this.graph = graph;
		this.startNode = this.graph.getByKey(startNodeKey);
		if (startNode == null) {
			throw new IllegalArgumentException("Cant find element with key " + startNodeKey);
		}
		this.finishNode = this.graph.getByKey(finishNodeKey);
		if (finishNode == null) {
			throw new IllegalArgumentException("Cant find element with key " + finishNodeKey);
		}
		startNode.tryUpdateCost(0, null);
	}
	
	public Integer findShortesWay() {
		makeStep(startNode);
		return proceedResult();
	}
	
	private void makeStep (Node node) {
		if (node.equals(finishNode)) {
			return;
		}
		updateCostOfUnvisitedNeighbours(node);
		for (Node neighbour : node.getNeighbours()) {
			if (neighbour.getCurrentCost() < node.getCurrentCost() + node.getCostForNeighbour(neighbour)) {
				continue;
			}
			makeStep(neighbour);
		}
	}
	
	private void updateCostOfUnvisitedNeighbours(Node node) {
		List<Node> neighbours = node.getNeighbours();
		for(Node neighbour : neighbours) {
			Integer edgeCost = node.getCostForNeighbour(neighbour);
			neighbour.tryUpdateCost(node.getCurrentCost() + edgeCost, node);
		}
	}
	
	private Integer proceedResult() {
		if (finishNode.getCurrentCost() == null) {
			throw new RuntimeException("Path hasn't been found");
		}
		return finishNode.getCurrentCost();
	}
	
	public String printPath() {
		StringBuilder path = new StringBuilder(finishNode.getKey().toString());
		Node predecessor = finishNode.getPredecessor();
		while(predecessor != null) {
			path.insert(0, predecessor.getKey() + " -> ");
			predecessor = predecessor.getPredecessor();
		}
		path.append(" : " + finishNode.getCurrentCost());
		return path.toString();
	}
}
