class CoinLine {
	private Coin coin;
	private int quantity;

	/**
	 * Constructs a coin object.
	 * Initialises the ArrayList
	 * 
	 * @param coin Coin
	 * @param quantity Quantity of coins   
	 */
	CoinLine(Coin coin, int quantity) {
		this.coin = coin;
		this.quantity = quantity;
	}

	/**
	 * Constructs a coin object.
	 * Initialises the ArrayList
	 * 
	 * @param value Value of coin
	 * @param description Description of coin
	 * @param quantity Quantity of coins
	 */
	CoinLine(double value, String description, int quantity) {
		this.coin = new Coin(value, description);
		this.quantity = quantity;
	}

	/**
	 * Increases the quantity by passed-in quantity
	 */
	void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	/**
	 * Adds the value of all the coins in the set and returns the sum
	 *
	 * @return value
	 */
	double getTotalValue() {
		return (coin.getValue() * this.quantity);
	}

	/**
	 * Getter to retrieve quantity from coinLine
	 * 
	 * @return Quantity
	 */
	int getQuantity() {
		return this.quantity;
	}

	/**
	 * Getter to retrieve coin from coinLine
	 * 
	 * @return Coin
	 */
	Coin getCoin() {
		return this.coin;
	}

	/**
	 * Clears the coinLine by setting the value to 0
	 */
	void clearCoinLine() {
		this.quantity = 0;
	}

	/**
	 * Checks whether a CoinLine is equals by checking whether the coin within are equal
	 * Overrides the built in equals
	 *
	 * @param o object to check against this
	 * @return boolean of whether its equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof CoinLine) {
			CoinLine clObj = (CoinLine) o;
			return coin.equals(clObj.getCoin());
		} else
			return false;
	}

	/**
	 * Overrides the default toString for product
	 * returns the name of the product
	 *
	 * @return name,value,quantity 
	 */
	@Override
	public String toString() {
		return coin.toString() + "," + this.quantity;
	}
}
