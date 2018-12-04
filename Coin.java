/**
 * A coin object with a name and value
 * @author Daniel Dalton 17219477
 */
public class Coin {
	private double value;
	private String name;

	/**
	 * Constructs a coin with a given value and name
	 *
	 * @param value
	 * @param name
	 */
	Coin(double value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * Returns the value of the coin
	 *
	 * @return value
	 */
	double getValue() {
		return value;
	}

	/**
	 * Returns the value of the coin
	 *
	 * @return value
	 */
    String getName() {
		return name;
	}

	/**
	 * Checks whether a coin is equals by checking whether the name and value are equal
	 * Overrides the built in equals
	 *
	 * @param o object to check against this
	 * @return boolean of whether its equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Coin) {
			Coin coinObj = (Coin) o;
			return (this.name.equals(coinObj.getName()) && this.value == coinObj.getValue());
		} else
			return false;
	}

	/**
	 * Overrides the default toString for coin
	 * returns the name of the coin
	 *
	 * @return name,value
	 */
	public String toString() {
		return name + "," + value;
	}
}
