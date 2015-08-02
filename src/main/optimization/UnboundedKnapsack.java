package main.optimization;

import main.optimization.model.Item;

import java.text.NumberFormat;

public class UnboundedKnapsack {

    protected Item[] items;

    protected final int n;
    protected int sackMaxWeight;
    protected Item bestSack = new Item("best", 0, 0);
    protected int[] maxIt;
    protected int[] iIt;
    protected int[] bestAm;

    public UnboundedKnapsack(Item[] items, int sackMaxWeight) {
        this.items = items;
        this.sackMaxWeight = sackMaxWeight;
        n = items.length;
        maxIt = new int[n];
        iIt = new int[n];
        bestAm = new int[n];
        for (int i = 0; i < n; i++) {
            maxIt[i] = (int) (sackMaxWeight / items[i].getWeight());
        }
        calcWithRecursion(0);

    }

    public void calcWithRecursion(int item) {
        for (int i = 0; i <= maxIt[item]; i++) {
            iIt[item] = i;
            if (item < n - 1) {
                calcWithRecursion(item + 1);
            } else {
                int currVal = 0;
                int currWei = 0;
                for (int j = 0; j < n; j++) {
                    currVal += iIt[j] * items[j].getValue();
                    currWei += iIt[j] * items[j].getWeight();
                }
                if (currVal > bestSack.getValue() && currWei <= sackMaxWeight) {
                    bestSack.setValue(currVal);
                    bestSack.setWeight(currWei);
                    for (int j = 0; j < n; j++)
                        bestAm[j] = iIt[j];
                }
            }
        }
    }

    public void printSolution() {
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println("Maximum value achievable is: " + bestSack.getValue());
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
        System.out.println("The weight to carry is: " + nf.format(bestSack.getWeight()));
    }

    public static void main(String[] args) {
        Item[] items = {new Item("1", 960, 24),
                new Item("2", 500, 19),
                new Item("3", 250, 16)};
        /*Item[] items = { new Item("1", 1000, 8),
				new Item("2", 550, 5),
				new Item("3", 460, 4) };*/
        UnboundedKnapsack knapsack = new UnboundedKnapsack(items, 90);
        knapsack.printSolution();
    }

}
