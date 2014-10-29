package test.graph;

import static org.junit.Assert.assertEquals;
import main.graph.BranchAndBound;
import main.graph.Graph;
import main.graph.Graph.Node;

import org.junit.Before;
import org.junit.Test;

public class BranchAndBoundTest {

	private Graph graph;
	
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		Node nodeA = graph.addNode("A");
		Node nodeB = graph.addNode("B");
		Node nodeC = graph.addNode("C");
		Node nodeD = graph.addNode("D");
		Node nodeE = graph.addNode("E");
		Node nodeF = graph.addNode("F");
		Node nodeG = graph.addNode("G");
		
		nodeA.addEdge(2, nodeB).addEdge(4, nodeC).addEdge(5, nodeD);
		nodeB.addEdge(1, nodeC).addEdge(5, nodeE).addEdge(12, nodeF);
		nodeC.addEdge(9, nodeF).addEdge(18, nodeG);
		nodeD.addEdge(8, nodeG);
		nodeE.addEdge(4, nodeF);
		nodeF.addEdge(1, nodeG);
	}
	
	@Test
	public void shouldFindShortestPath() {
		BranchAndBound branchAndBound = new BranchAndBound(graph, "A", "G");
		assertEquals(new Integer(12), branchAndBound.findShortesWay());
		System.out.println(branchAndBound.printPath());
	}
	
	@Test
	public void shouldReturnZeo() {
		BranchAndBound branchAndBound = new BranchAndBound(graph, "A", "A");
		assertEquals(new Integer(0), branchAndBound.findShortesWay());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldNotFindNodeWithUnexistKey() {
		BranchAndBound branchAndBound = new BranchAndBound(graph, "A", "Z");
		branchAndBound.findShortesWay();
	}

}
