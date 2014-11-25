package graph.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Graph {
	
	private HashMap<String, Node> nodes = new HashMap<String, Node>();
	
	public Node addNode(String key) {
		nodes.put(key, new Node(key));
		return nodes.get(key);
	}

	public Node getByKey(String key) {
		return nodes.get(key);
	}

	@Override
	public String toString() {
		return "Graph [nodes=" + nodes + "]";
	}

	public static final class Node {
		private final String key;
		private NavigableSet<Edge> edges = new TreeSet<>();;
		private boolean visited;
		private Integer currentCost;
		private Node predecessor;

		public Node(String key) {
			super();
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public Node addEdge(Integer cost, Node destination) {
			edges.add(new Edge(cost, destination));
			return this;
		}

		public boolean isVisited() {
			return visited;
		}

		public void visit() {
			this.visited = true;
		}

		public Integer getCurrentCost() {
			return currentCost;
		}
		
		public Node getPredecessor() {
			return predecessor;
		}
		
		public void tryUpdateCost(Integer cost, Node predecessor) {
			if (cost == null) {
				return;
			}
			if (currentCost == null || currentCost > cost) {
				currentCost = cost;
				this.predecessor = predecessor;
			}
		}
		
		public Integer getCostForNeighbour(Node neighbour) {
			if (neighbour == null) {
				throw new NullPointerException();
			}
			Iterator<Edge> edgeIterator;
			for (edgeIterator = edges.iterator(); edgeIterator.hasNext();) {
				Edge edge = edgeIterator.next();
				if (neighbour.equals(edge.getDestination())) {
					return edge.getCost();
				}
			}
			return null;
		}

		public List<Node> getNeighbours() {
			List<Node> neighbours = new LinkedList<>();
			for (Iterator<Edge> edgeIterator = edges.iterator(); 
					edgeIterator.hasNext(); 
					neighbours.add(edgeIterator.next().getDestination()));
			return neighbours;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + (visited ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (visited != other.visited)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", visited=" + visited
					+ ", currentCost=" + currentCost + "]";
		}
		
		private static final class Edge implements Comparable<Edge> {
			private final Integer cost;
			private final Node destination;

			public Edge(Integer cost, Node destination) {
				super();
				this.cost = cost;
				this.destination = destination;
			}

			public Integer getCost() {
				return cost;
			}

			public Node getDestination() {
				return destination;
			}

			@Override
			public int compareTo(Edge another) {
				if (this.cost == null || another.cost == null) {
					throw new NullPointerException("Cost is null");
				}
				return this.cost - another.cost;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result
						+ ((cost == null) ? 0 : cost.hashCode());
				result = prime * result
						+ ((destination == null) ? 0 : destination.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Edge other = (Edge) obj;
				if (cost == null) {
					if (other.cost != null)
						return false;
				} else if (!cost.equals(other.cost))
					return false;
				if (destination == null) {
					if (other.destination != null)
						return false;
				} else if (!destination.equals(other.destination))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "Edge [cost=" + cost + ", destination=" + destination
						+ "]";
			}
		}
	}
}
