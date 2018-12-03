import java.util.ArrayList;

/**
 * A wrapper class for a set of coins.
 * @author Daniel Dalton 17219477
 */
class CoinSet {
	private ArrayList<CoinLine> set;

	/**
	 * Constructs a CoinSet object.
	 * Adds a default set of CoinLines with quantity 0
	 * Initialises the ArrayList
	 */
	CoinSet() {
		set = new ArrayList<>();
		set.add(new CoinLine(0.05, "5 Cent", 0));
		set.add(new CoinLine(0.10, "10 Cent", 0));
		set.add(new CoinLine(0.20, "20 Cent", 0));
		set.add(new CoinLine(0.50, "50 Cent", 0));
		set.add(new CoinLine(1.00, "1 Euro", 0));
		set.add(new CoinLine(2.00, "2 Euro", 0));
	}

	/**
	 * Adds a single coin to the set of coins
	 *
	 * @param coin the coin to be added
	 */
	void addCoin(Coin coin) {
		boolean exists = false;
		for (CoinLine cl : set) {
			if (coin.equals(cl.getCoin())) {
				cl.addQuantity(1);
				exists = true;
				break;
			}
		}
		if(!exists){
			addCoin(new CoinLine(coin, 1));
		}
	}

	/**
	 * Adds a CoinLine to the set. Increments the given quantity if it already
	 * exists in the set, adds a new CoinLine to the set if it doesn't
	 *
	 * @param coinLine the CoinLine to be added
	 */
	void addCoin(CoinLine coinLine) {
		boolean found = false;
		for (CoinLine cl : set) {
			if ((coinLine.getCoin()).equals(cl.getCoin())) {
				cl.addQuantity(coinLine.getQuantity());
				found = true;
				break;
			}
		}
		if (!found)
			set.add(coinLine);
	}

	/**
	 * Returns the Coinset as ArrayList
	 *
	 * @return Arraylist of CoinLines
	 */
	ArrayList<CoinLine> getSetOfCoins() {
		return set;
	}
  
	/**
	 * Adds one coinset to another by checking for coins in common and adding the
	 * quantity of the coins in the passed-in coinset to the quantity in the current coinset
	 *
	 * @param inputCoinLineList An ArrayList of CoinLine to add
	 */
	void addSetOfCoins(ArrayList<CoinLine> inputCoinLineList) {
		boolean found = false;
		for (CoinLine inputCoinLine : inputCoinLineList) {
			for (CoinLine setCoinLine : set) {
				if (inputCoinLine.equals(setCoinLine)) {
					setCoinLine.addQuantity(inputCoinLine.getQuantity());
					found = true;
					break; //only breaks from the innermost loop thank god
				}
			}
			if (!found)
				set.add(inputCoinLine);
		}
	}

	/**
	 * Adds the value of all the coins in the set and returns the sum
	 *
	 * @return value of all coins
	 */
	double getValue() {
		double value = 0;
		for (CoinLine cl : set) {
			value += cl.getTotalValue();
		}
		return value;
	}

	/**
	 * Clears the set of coins by setting the quantity of all the coins to 0
	 */
	void clearCoinSet() {
		for(CoinLine cl : set){
			cl.clearCoinLine();
		}
	}

	/**
	 * Gets the arraylist of coins that the machine can accept
	 *
	 * @return Arraylist of acceptable coins
	 */
	ArrayList<Coin> getAcceptedCoins() {
		ArrayList<Coin> acceptableCoins = new ArrayList<>();
		for (CoinLine cl : set) {
			acceptableCoins.add(cl.getCoin());
		}
		return acceptableCoins;
	}

	/**
	 * Gets how many coinlines are in the coinset
	 *
	 * @return Size of coinset
	 */
	public int size() {
		return set.size();
	}

	/**
	 * Overrides the default toString for coinset
	 *
	 * @return All the coinlines in the coinset
	 */
	@Override
	public String toString() {
		String output = "";
		for (CoinLine cl : set) {
			output += cl.toString() + ",\n";
		}
		return output;
	}
}
