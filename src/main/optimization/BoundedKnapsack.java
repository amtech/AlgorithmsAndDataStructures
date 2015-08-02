package main.optimization;

import main.optimization.model.Item;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoundedKnapsack {
    protected List<Item> itemList = new ArrayList<Item>();
    protected int maxWeight = 0;
    protected int solutionWeight = 0;
    protected int profit = 0;
    protected boolean calculated = false;

    public static void main(String[] args) {
        BoundedKnapsack zok = new BoundedKnapsack(35);

        zok.add("1", 4, 7);
        zok.add("2", 7, 10);
        zok.add("3", 11, 15);
        zok.add("4", 12, 20);
        zok.add("5", 16, 27);
        zok.add("6", 20, 34);

        //List<Item> itemList = zok.calcSolution();
        zok.calcSolution();
        zok.printSolution();
    }

    public BoundedKnapsack(int maxWeight) {
        setMaxWeight(maxWeight);
    }

    public BoundedKnapsack(List<Item> itemList) {
        setItemList(itemList);
    }

    public BoundedKnapsack(List<Item> itemList, int maxWeight) {
        setItemList(itemList);
        setMaxWeight(maxWeight);
    }

    public void calcSolution() {
        int n = itemList.size();

        setInitialStateForCalculation();
        if (n > 0 && maxWeight > 0) {
            List<List<Integer>> table = new ArrayList<List<Integer>>();
            List<Integer> currentItemRow = new ArrayList<Integer>();

            table.add(currentItemRow);
            for (int j = 0; j <= maxWeight; j++) {
                currentItemRow.add(0);
            }
            for (int i = 1; i <= n; i++) {
                List<Integer> previousItemRow = currentItemRow;
                table.add(currentItemRow = new ArrayList<Integer>());
                currentItemRow.add(0);
                for (int j = 1; j <= maxWeight; j++) {
                    int wH = itemList.get(i - 1).getWeight();
                    currentItemRow.add((wH > j) ? previousItemRow.get(j) : Math.max(previousItemRow.get(j),
                            itemList.get(i - 1).getValue() + previousItemRow.get(j - wH)));
                }
            }
            profit = currentItemRow.get(maxWeight);
            selectItemsToKnapsack(table);
            calculated = true;
        }
    }

    private void selectItemsToKnapsack(List<List<Integer>> table) {
        int n = itemList.size();
        for (int i = n, j = maxWeight; i > 0 && j >= 0; i--) {
            int tempI = table.get(i).get(j);
            int tempI_1 = table.get(i - 1).get(j);
            if ((i == 0 && tempI > 0) || (i > 0 && tempI != tempI_1)) {
                Item iH = itemList.get(i - 1);
                int wH = iH.getWeight();
                iH.setInKnapsack(1);
                j -= wH;
                solutionWeight += wH;
            }
        }
    }

    public void add(String name, int weight, int value) {
        if (name.equals("")) {
            name = "" + (itemList.size() + 1);
        }
        itemList.add(new Item(name, value, weight));
        setInitialStateForCalculation();
    }

    public void add(int weight, int value) {
        add("", weight, value);
    }

    public void remove(String name) {
        for (Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            if (name.equals(it.next().getName())) {
                it.remove();
            }
        }
        setInitialStateForCalculation();
    }

    public void removeAllItems() {
        itemList.clear();
        setInitialStateForCalculation();
    }

    public List<Item> getItems() {
        return itemList;
    }

    public List<Item> getItemsInKnapsack() {
        final List<Item> itemsInKnapsack = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getInKnapsack() == 1) {
                itemsInKnapsack.add(item);
            }
        }
        return itemsInKnapsack;
    }

    public int getProfit() {
        if (!calculated) {
            calcSolution();
        }
        return profit;
    }

    public int getSolutionWeight() {
        return solutionWeight;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int _maxWeight) {
        maxWeight = Math.max(_maxWeight, 0);
    }

    public void setItemList(List<Item> itemList) {
        if (itemList != null) {
            this.itemList = itemList;
            for (Item item : itemList) {
                item.checkMembers();
            }
        }
    }

    private void setInKnapsackByAll(int inKnapsack) {
        for (Item item : itemList)
            if (inKnapsack > 0) {
                item.setInKnapsack(1);
            } else {
                item.setInKnapsack(0);
            }
    }

    protected void setInitialStateForCalculation() {
        setInKnapsackByAll(0);
        calculated = false;
        profit = 0;
        solutionWeight = 0;
    }

    public void printSolution() {
        if (isCalculated()) {
            NumberFormat nf = NumberFormat.getInstance();
            System.out.println("Maximal weight           = "
                    + nf.format(getMaxWeight()) + " kg");
            System.out.println("Total weight of solution = "
                    + nf.format(getSolutionWeight()) + " kg");
            System.out.println("Total value              = " + getProfit());
            System.out.println();
            System.out.format("%1$-5s %2$3s %3$5s \n", "item", "kg  ", "cost");
            for (Item item : itemList) {
                if (item.getInKnapsack() == 1) {
                    System.out.format("%1$-5s %2$-3s %3$5s\n", item.getName(),
                            item.getWeight(), item.getValue());
                }
            }
        } else {
            System.out.println("The problem is not solved. "
                    + "Maybe you gave wrong data.");
        }
    }
}
