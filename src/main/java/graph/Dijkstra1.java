package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra1 {

	private static class Graph {

		private Map<Integer, List<Edge>> adjacent = new HashMap<>();
		private List<Edge> edges = new LinkedList<>();

		public void addEdge(Integer from, Integer to, int length) {
			List<Edge> e = adjacent.get(from);
			if (e == null) {
				e = new LinkedList<Edge>();
			}
			Edge edge = new Edge(from, to, length);
			edges.add(edge);
			e.add(edge);
			adjacent.put(from, e);

			if (adjacent.get(to) == null)
				adjacent.put(to, new LinkedList<Edge>());
		}

		public Edge getEdge(Integer from, Integer to) {
			Edge ret = null;
			List<Edge> edges = adjacent.get(from);
			if (edges != null) {
				for (Edge e : edges) {
					Integer v = e.head;
					if (v.equals(to))
						ret = e;
				}
			}
			return ret;
		}

		public List<Edge> getEdgesFrom(Integer from) {
			List<Edge> edges = adjacent.get(from);
			if (edges == null)
				edges = new LinkedList<>();
			return edges;
		}

		public Set<Integer> vertices() {
			return adjacent.keySet();
		}

		public List<Edge> edges() {
			return edges;
		}

		public int numVertices() {
			return adjacent.keySet().size();
		}

		public int numEdges() {
			return edges.size();
		}

	}

	static void print(Graph g) {
		for (Map.Entry<Integer, List<Edge>> entry : g.adjacent.entrySet()) {
			System.out.print(String.format("%s: ", entry.getKey()));
			for (Edge e : entry.getValue())
				System.out.print(String.format("%s,%s ", e.head, e.length));
			System.out.println();
		}
	}

	static class Edge {

		private int tail;
		private int head;
		private int length;

		public Edge(int tail, int head, int length) {
			this.tail = tail;
			this.head = head;
			this.length = length;
		}
	}

	public static Map<Integer, Integer> dijkstra(Graph g, int source) {
		Map<Integer, Integer> dist = new HashMap<>();
		for (Integer v : g.vertices())
			dist.put(v, 1000000);
		dist.put(source, 0);

		Set<Integer> x = new HashSet<>();
		x.add(source);
		Set<Integer> vertices = new HashSet<Integer>(g.vertices());
		while (!x.equals(vertices)) {
			Edge edge = null;
			int min = 1000000;
			for (Integer v : x)
				for (Edge e : g.getEdgesFrom(v))
					if (x.contains(e.tail) && !x.contains(e.head))
						if (dist.get(e.tail) + e.length < min) {
							min = dist.get(e.tail) + e.length;
							edge = e;
						}
			x.add(edge.head);
			dist.put(edge.head, dist.get(edge.tail) + edge.length);
		}
		return dist;
	}

	public static void main(String[] args) throws IOException {

		long t0 = System.nanoTime();
		Graph g = readGraph("src/main/resources/graph/dijkstraData.txt");
		long t1 = System.nanoTime();
		System.out.println(String.format("Graph created in %sms.",
				(t1 - t0) / 1000000D));

		long t2 = System.nanoTime();
		Map<Integer, Integer> res = dijkstra(g, 1);
		long t3 = System.nanoTime();
		System.out.println(String.format("Distances computed in %sms.",
				(t3 - t2) / 1000000D));

		int[] arr = new int[] { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };
		for (int i : arr)
			System.out.println(String.format("Distance  1 -> %d: %d", i,
					res.get(i)));

		System.out.println();

		for (int i = 0; i < arr.length; ++i) {
			System.out.print(res.get(arr[i]));
			if (i == arr.length - 1)
				System.out.println();
			else
				System.out.print(',');
		}
	}

	static void printResult(Map<Integer, Integer> res) {
		System.out.println("Distance from vertice 1");
		for (Map.Entry<Integer, Integer> e : res.entrySet()) {
			System.out.println(String.format("to edge %s = %s", e.getKey(),
					e.getValue()));
		}
	}

	static Graph test4() {
		return new Graph() {
			{
				addEdge(1, 2, 1);
				addEdge(1, 7, 2);
				addEdge(2, 1, 1);
				addEdge(2, 3, 10);
				addEdge(3, 2, 10);
				addEdge(3, 4, 1);
				addEdge(4, 3, 1);
				addEdge(4, 5, 1);
				addEdge(5, 4, 1);
				addEdge(5, 6, 1);
				addEdge(6, 5, 1);
				addEdge(6, 7, 1);
				addEdge(7, 1, 2);
				addEdge(7, 6, 1);
			}
		};
	}

	static Graph test2() {
		return new Graph() {
			{
				addEdge(1, 2, 1);
				addEdge(1, 3, 4);
				addEdge(2, 3, 2);
				addEdge(2, 4, 6);
				addEdge(3, 4, 3);
			}
		};
	}

	static Graph test1() {
		// Expected results from vertice 1
		// to 6 dist = 11
		// to 5 dist = 20
		// to 4 dist = 20
		// to 3 dist = 9
		// to 2 dist = 7
		return new Graph() {
			{
				// 1 2,7 3,9 6,14
				addEdge(1, 2, 7);
				addEdge(1, 3, 9);
				addEdge(1, 6, 14);
				// 2 1,7 3,10 4,15
				addEdge(2, 1, 7);
				addEdge(2, 3, 10);
				addEdge(2, 4, 15);
				// 3 1,9 2,10 4,11 6,2
				addEdge(3, 1, 9);
				addEdge(3, 2, 10);
				addEdge(3, 4, 11);
				addEdge(3, 6, 2);
				// 4 2,15 3,11 5,6
				addEdge(4, 2, 15);
				addEdge(4, 3, 11);
				addEdge(4, 5, 6);
				// 5 4,6 6,9
				addEdge(5, 4, 6);
				addEdge(5, 6, 9);
				// 6 1,14 3,2 5,9
				addEdge(6, 1, 14);
				addEdge(6, 3, 2);
				addEdge(6, 5, 9);

			}
		};
	}

	public static Graph readTest() throws IOException {
		return readGraph("tiny-graph");
	}

	public static Graph readGraph(String path) throws IOException {
		Graph ret = new Graph();
		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = bf.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				Integer n0 = Integer.parseInt(tokens[0]);
				for (int i = 1; i < tokens.length; ++i) {
					String[] pair = tokens[i].split(",");
					Integer n1 = Integer.parseInt(pair[0]);
					Integer len = Integer.parseInt(pair[1]);
					ret.addEdge(n0, n1, len);
				}
			}
		}
		return ret;
	}
}
