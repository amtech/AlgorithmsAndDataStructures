package main.graph;

import main.graph.model.Graph;
import main.graph.model.Graph.Node;

import java.io.*;
import java.util.List;

public class Dijkstra {

    public static void main(String[] args) {
        Graph graph = readGraph();
        Dijkstra dijkstra = new Dijkstra(graph, "1", "80");
        dijkstra.findShortesWay();
        System.out.println(dijkstra.printPath());
    }

    public static Graph readGraph() {
        Graph graph = new Graph();
        for (int i = 0; i < 200; i++) {
            graph.addNode("" + (i + 1));
        }
        try (BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/graph/dijkstraData.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\t");
                Node currentMode = graph.getByKey(parts[0].trim());
                for (int i = 1; i < parts.length; i++) {
                    String[] vertexToCost = parts[i].split(",");
                    Node toMode = graph.getByKey(vertexToCost[0].trim());
                    int cost = new Integer(vertexToCost[1].trim());
                    currentMode.addEdge(cost, toMode);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded");
        return graph;
    }

    private final Graph graph;
    private final Node startNode;
    private final Node finishNode;

    public Dijkstra(final Graph graph, final String startNodeKey, final String finishNodeKey) {
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

    private void makeStep(Node node) {
        if (node.equals(finishNode)) {
            return;
        }
        updateCostOfUnvisitedNeighbours(node);
        node.visit();
        for (Node neighbour : node.getNeighbours()) {
            makeStep(neighbour);
        }
    }

    private void updateCostOfUnvisitedNeighbours(Node node) {
        List<Node> neighbours = node.getNeighbours();
        for (Node neighbour : neighbours) {
            if (neighbour.isVisited()) {
                continue;
            }
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
        StringBuilder path = new StringBuilder(finishNode.getKey());
        Node predecessor = finishNode.getPredecessor();
        while (predecessor != null) {
            path.insert(0, predecessor.getKey() + " -> ");
            predecessor = predecessor.getPredecessor();
        }
        path.append(" : " + finishNode.getCurrentCost());
        return path.toString();
    }
}