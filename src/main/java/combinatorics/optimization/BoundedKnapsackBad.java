package combinatorics.optimization;

class BoundedKnapsackBad {
	
	public static void main(String[] args) throws Exception {
		//int weights[] = { 4, 7, 11, 12, 16, 20 };
		//int costs[] = { 7, 10, 15, 20, 27, 34 };
		//int maxWeight = 35;
		int weights[] = { 4, 3, 2};
		int costs[] = { 50, 40, 30 };
		int maxWeight = 9;
		int[][] knapsackCosts = knapsack(weights, costs, maxWeight);
		print(knapsackCosts);
		System.out.println("Max cost: " + getMaxCostOfKnapsack(knapsackCosts));
	}

	public static int[][] knapsack(int weights[], int costs[], int maxWeight) {
		int N = weights.length;
		int[][] knapsackCosts = new int[N + 1][maxWeight + 1];
		
		for (int col = 0; col <= maxWeight; col++) {
			knapsackCosts[0][col] = 0;
		}
		
		for (int item = 1; item <= N; item++) {
			for (int weight = 1; weight <= maxWeight; weight++) {
				if (weights[item - 1] <= weight) {
					knapsackCosts[item][weight] = Math.max(costs[item - 1]
							+ knapsackCosts[item - 1][weight - weights[item - 1]],
							knapsackCosts[item - 1][weight]);
				} else {
					knapsackCosts[item][weight] = knapsackCosts[item - 1][weight];
				}
			}
		}
		selectItemsToKnapsack(knapsackCosts, weights, maxWeight);
		return knapsackCosts;
	}
	
	private static void selectItemsToKnapsack(int[][] table, int weights[], int maxWeight) {
		int n = weights.length;
		int solutionWeight = 0;
		System.out.println("Chosen items: ");
		for (int i = n, j = maxWeight; i > 0 && j >= 0; i--) {
			int tempI = table[i][j];
			int tempI_1 = table[i - 1][j];
			if ((i == 0 && tempI > 0) || (i > 0 && tempI != tempI_1)) {
				int wH = weights[i - 1];
				System.out.println(i + "   ---> weight: " + wH);
				j -= wH;
				solutionWeight += wH;
			}
		}
		System.out.println("Weight of solution: " + solutionWeight);
	}
	
	private static int getMaxCostOfKnapsack(int[][] knapsackCosts) {
		return knapsackCosts[knapsackCosts.length - 1][knapsackCosts[0].length - 1];
	}
	
	private static void print(int[][] table) {
		System.out.print("     ");
		for (int i = 0; i < table[0].length; i++) {
			System.out.format("%5d", i);
		}
		System.out.println();
		int rowsCount = 0;
		for (int[] rows : table) {
			System.out.format("%5d", rowsCount++);
			for (int col : rows) {
				System.out.format("%5d", col);
			}
			System.out.println();
		}
	}
}
