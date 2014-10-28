package main.graph;

import java.util.NavigableSet;

public class Dijkstra {

	public static void main(String[] args) {
		Node node1 = new Node(2011);
		Node node2 = new Node(2012);
		Node node3 = new Node(2013);
		Node node4 = new Node(2014);
		Node node5 = new Node(2015);
		Node node6 = new Node(2016);
		
		node1.addEdge(3400, node4).addEdge(2000, node3).addEdge(7800, node5);
		node2.addEdge(2200, node4).addEdge(7000, node6).addEdge(3600, node5);
		node3.addEdge(2400, node5).addEdge(3800, node6);
		node4.addEdge(2600, node6);
		
	}

	public static final class Node {
		private Integer number;
		private NavigableSet<Edge> edges;
		private boolean visited;

		public Node(Integer number) {
			super();
			this.number = number;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}
		
		public Node addEdge(Integer cost, Node destination) {
			edges.add(new Edge(cost, destination));
			return this;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
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
			public String toString() {
				return "Edge [cost=" + cost + ", destination=" + destination
						+ "]";
			}
		}
	}
}