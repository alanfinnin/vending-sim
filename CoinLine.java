import java.util.ArrayList;

public class CoinLine {
	private Coin coin;
	private int quantity;

	/**
	 *  Constructs a Money object.
	 *	Initialises the ArrayList
	 */
	public CoinLine(Coin coin, int quantity)
	{
		this.coin = coin;
		this.quantity = quantity;
	}

	public CoinLine(double value, String description, int quantity)
	{
		this.coin = new Coin(value, description);
		this.quantity = quantity;
	}

	/**
	 *	Adds a coin to the set of coins
	 */
	public void addQuantity(int quantity){
		this.quantity += quantity;
	}

	/**
	 *	Adds the value of all the coins in the set and returns the sum
	 *	@return value
	 */
	public double getTotalValue(){
		return (coin.getValue() * this.quantity);
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return this.quantity;
	}

	public Coin getCoin(){
		return this.coin;
	}

	/**
	 *	Clears the set of coins
	 */
	public void clearCoinLine(){
		this.quantity = 0;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof CoinLine) {
			CoinLine clObj = (CoinLine) o;
			return coin.equals(clObj.getCoin());
		}else
			return false;
	}

	@Override
	public String toString(){
		return coin.getName() + "," + coin.getValue() + "," + this.quantity;
	}
}
