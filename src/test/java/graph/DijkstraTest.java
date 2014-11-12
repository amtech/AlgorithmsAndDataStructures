package graph;

import static org.junit.Assert.assertEquals;
import graph.model.Graph;
import graph.model.Graph.Node;

import org.junit.Before;
import org.junit.Test;

public class DijkstraTest {

	private Graph graph;
	
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		Node node1 = graph.addNode("2011");
		Node node2 = graph.addNode("2012");
		Node node3 = graph.addNode("2013");
		Node node4 = graph.addNode("2014");
		Node node5 = graph.addNode("2015");
		Node node6 = graph.addNode("2016");
		
		node1.addEdge(3400, node4).addEdge(2000, node3).addEdge(7800, node5);
		node2.addEdge(2200, node4).addEdge(7000, node6).addEdge(3600, node5);
		node3.addEdge(2400, node5).addEdge(3800, node6);
		node4.addEdge(2600, node6);
	}

	@Test
	public void shouldFindShortestPath() {
		Dijkstra dijkstra = new Dijkstra(graph, "2011", "2016");
		assertEquals(new Integer(5800), dijkstra.findShortesWay());
		System.out.println(dijkstra.printPath());
	}
	
	@Test
	public void shouldReturnZeo() {
		Dijkstra dijkstra = new Dijkstra(graph, "2011", "2011");
		assertEquals(new Integer(0), dijkstra.findShortesWay());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotFindNodeWithUnexistKey() {
		Dijkstra dijkstra = new Dijkstra(graph, "2011", "2017");
		dijkstra.findShortesWay();
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldFail() {
		Dijkstra dijkstra = new Dijkstra(graph, "2011", "2012");
		dijkstra.findShortesWay();
	}

}
