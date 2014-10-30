package combinatorics.optimization.model;

public class Item {
	protected String name = "";
	protected int value = 0;
	protected int weight = 0;
	//protected double volume = 0;
	
	protected int bounding   = 1; // the maximal limit of item's pieces
    protected int inKnapsack = 0; // the pieces of item in solution
	
	public Item(String name, int value, int weight) {
        setName(name);
        setValue(value);
        setWeight(weight);
    }

	/*
	public Item(String name, int value, int weight, double volume) {
		setName(name);
		setValue(value);
		setWeight(weight);
		setVolume(volume);
	}*/

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = Math.max(value, 0);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = Math.max(weight, 0);
	}

	/*public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = Math.max(volume, 0);
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getInKnapsack() {
		return inKnapsack;
	}
	
	public void setInKnapsack(int _inKnapsack) {
        inKnapsack = Math.min(getBounding(), Math.max(_inKnapsack, 0));
    }
	
    public int getBounding() {
    	return bounding;
    }
 
    public void setBounding(int _bounding) {
        bounding = Math.max(_bounding, 0);
        if (bounding == 0)
            inKnapsack = 0;
    }
    
    public void checkMembers() {
        setWeight(weight);
        setValue(value);
        setBounding(bounding);
        setInKnapsack(inKnapsack);
    }
}
