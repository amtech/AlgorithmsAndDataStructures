package combinatorics.optimization;

import java.text.NumberFormat;

import combinatorics.optimization.model.Item;

public class UnboundedKnapsack {
	
	protected Item[] items = { new Item("1", 960, 24),
			new Item("2", 500, 19),
			new Item("3", 250, 16) };
	

	protected final int n = items.length; // the number of items
	protected Item sack = new Item("sack", 0, 90);
	protected Item best = new Item("best", 0, 0);
	protected int[] maxIt = new int[n]; // maximum number of items
	protected int[] iIt = new int[n]; // current indexes of items
	protected int[] bestAm = new int[n]; // best amounts

	public UnboundedKnapsack() {
		// initializing:
		for (int i = 0; i < n; i++) {
			maxIt[i] = (int) (sack.getWeight() / items[i].getWeight());
		} // for (i)

		// calc the solution:
		calcWithRecursion(0);

		// Print out the solution:
		NumberFormat nf = NumberFormat.getInstance();
		System.out.println("Maximum value achievable is: " + best.getValue());
		System.out.print("This is achieved by carrying: \n");
		System.out.format("%1$-5s %2$3s %3$5s %4$-5s\n", "item", "count", "kg  ", "cost");
		for (int i = 0; i < n; i++) {
			System.out.format(
                    "%1$-5s %2$-3s %3$5s %4$5s\n",
                    items[i].getName(),
                    bestAm[i],
                    items[i].getWeight(),
                    items[i].getValue());
		}
		System.out.println();
		System.out.println("The weight to carry is: "
				+ nf.format(best.getWeight()));

	}

	// calculation the solution with recursion method
	// item : the number of item in the "items" array
	public void calcWithRecursion(int item) {
		for (int i = 0; i <= maxIt[item]; i++) {
			iIt[item] = i;
			if (item < n - 1) {
				calcWithRecursion(item + 1);
			} else {
				int currVal = 0; // current value
				int currWei = 0; // current weight
				for (int j = 0; j < n; j++) {
					currVal += iIt[j] * items[j].getValue();
					currWei += iIt[j] * items[j].getWeight();
				}

				if (currVal > best.getValue() && currWei <= sack.getWeight()) {
					best.setValue(currVal);
					best.setWeight(currWei);
					for (int j = 0; j < n; j++)
						bestAm[j] = iIt[j];
				} // if (...)
			} // else
		} // for (i)
	} // calcWithRecursion()

	// the main() function:
	public static void main(String[] args) {
		new UnboundedKnapsack();
	} // main()

}
