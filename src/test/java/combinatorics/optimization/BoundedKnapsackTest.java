package combinatorics.optimization;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import combinatorics.optimization.model.Item;

public class BoundedKnapsackTest {

	private BoundedKnapsack zok;
	
	@Before
	public void setUp() throws Exception {
		zok = new BoundedKnapsack(35);
		zok.add("1", 4, 7);
		zok.add("2", 7, 10);
		zok.add("3", 11, 15);
		zok.add("4", 12, 20);
		zok.add("5", 16, 27);
		zok.add("6", 20, 34);
	}
	
	@Test
	public void shouldReturnProfit() {
		zok.calcSolution();
		assertEquals(57, zok.getProfit());
	}
	
	@Test
	public void shouldReturnSolutionWeight() {
		zok.calcSolution();
		assertEquals(35, zok.getSolutionWeight());
	}
	
	@Test
	public void shouldReturnItemsInKnapsack() {
		zok.calcSolution();
		List<Item> itemsInKnapsack = zok.getItemsInKnapsack();
		assertEquals(3, itemsInKnapsack.size());
		assertEquals("2", itemsInKnapsack.get(0).getName());
		assertEquals("4", itemsInKnapsack.get(1).getName());
		assertEquals("5", itemsInKnapsack.get(2).getName());
	}

}
