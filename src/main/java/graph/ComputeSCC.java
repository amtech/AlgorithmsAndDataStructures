package graph;

import graph.model.UndirectedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Ordering;

public class ComputeSCC {

	public static int VERTEX_AMOUNT = 875714;
    public static int LINES_AMOUNT = 5105043;

    private UndirectedGraph graph;

    private int counter = 0;
    private int currentLeaderVertex = -1;

    private boolean visited[];
    private int leaders[];
    private int finishingTime[];
    private int finishingTimeReversed[];
    
    public static void main(String[] args) {
    	ComputeSCC problem = new ComputeSCC();
    	problem.run();
	}

    public void run() {
        graph = readGraph(VERTEX_AMOUNT, LINES_AMOUNT);

        dfs1Loop();
        dfs2Loop();

        List<Integer> result = sortedSizes(5);
        System.out.print(result + ",");
    }

    public UndirectedGraph readGraph(int n, int l) {
        UndirectedGraph graph = new UndirectedGraph(n);
        try (Scanner scanner = new Scanner(new File("src/main/resources/graph/SCC.txt"))) {
	        for (int i = 0; i < l; i++) {
	            int v = scanner.nextInt();
	            int u = scanner.nextInt();
	
	            graph.addEdge(v - 1, u - 1);
	
	            if (i % 1000 == 0) {
	                System.out.println(i + "th has been just read");
	            }
	        }
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        System.out.println("Loaded");
        return graph;
    }

    public void dfs1Loop() {
        visited = new boolean[graph.getN()];
        finishingTime = new int[graph.getN()];
        Arrays.fill(finishingTime, -1);
        finishingTimeReversed = new int[graph.getN()];
        Arrays.fill(finishingTimeReversed, -1);

        for (int i = graph.getN() - 1; i >= 0; i--) {
            if (!visited[i]) {
                currentLeaderVertex = i;
                dfs1(i);
            }
        }
    }

    private void dfs1(int u) {
        visited[u] = true;

        for (int v : graph.reverse(u)) {
            if (!visited[v]) {
                dfs1(v);
            }
        }

        finishingTime[u] = counter;
        finishingTimeReversed[counter] = u;
        counter++;
    }

    public void dfs2Loop() {
        visited = new boolean[graph.getN()];
        leaders = new int[graph.getN()];
        Arrays.fill(leaders, -1);

        for (int i = graph.getN() - 1; i >= 0; i--) {
            int ft = finishingTimeReversed[i];
            if (!visited[ft]) {
                currentLeaderVertex = ft;
                dfs2(ft);
            }
        }
    }

    private void dfs2(int u) {
        visited[u] = true;
        leaders[u] = currentLeaderVertex;

        for (int v : graph.adjacent(u)) {
            if (!visited[v]) {
                dfs2(v);
            }
        }
    }

    public void setGraph(UndirectedGraph graph) {
        this.graph = graph;
    }

    public int[] getFinishingTime() {
        return finishingTime;
    }

    public int[] getLeaders() {
        return leaders;
    }

    public List<Integer> sortedSizes(int desiredSize) {
        List<Integer> result = sort(countFrequencies());

        if (desiredSize <= result.size()) {
            return result.subList(0, desiredSize);
        } else {
            return addUpZeros(desiredSize, result);
        }
    }

    private static List<Integer> sort(List<Integer> result) {
        Ordering<Integer> ordering = Ordering.natural().reverse();
        return ordering.sortedCopy(result);
    }

    private List<Integer> countFrequencies() {
        Multiset<Integer> distinctLeaders = distinctLeaders();
        List<Integer> result = Lists.newArrayList();

        for (Entry<Integer> entry : distinctLeaders.entrySet()) {
            result.add(entry.getCount());
        }
        return result;
    }

    public Multiset<Integer> distinctLeaders() {
        Multiset<Integer> result = HashMultiset.create();
        for (int i : leaders) {
            result.add(i);
        }
        return result;
    }

    private static List<Integer> addUpZeros(int desiredSize, List<Integer> result) {
        List<Integer> newResult = Lists.newArrayList(result);
        int n = desiredSize - result.size();
        while (n > 0) {
            newResult.add(0);
            n--;
        }
        return newResult;
    }
}
