
import java.util.ArrayList;

class VendingMachine {
	private CoinSet coins; //the total machine balance of coins
	private CoinSet currentCoins; //the user coins currently held in machine credit
    private ArrayList<LineItem> stock;

    /**
     * Constructs the vending machine and initialises the list of stock
	 * and the set of coins
     */
    public VendingMachine(){
        stock = new ArrayList<>();
		coins = new CoinSet();
		currentCoins = new CoinSet();
    }

	/**
     * Returns a list of all the product choices available from the machine with price, containing each
     * product only once. 
     * @return String
     */
	public String showProducts(){ //
		Product[] productList = getProductTypes();
		String output = "";
		for (Product p : productList)
			output += p.getDescription() + ", \u20ac" + p.getPrice() + "\n";
		
		return output;
	}
	
    /**
     * Returns a list of all the product choices available from the machine, containing each
     * product only once. (I'm proud of this one)
     * @return Product[]
     */
    public Product[] getProductTypes(){
        ArrayList<Product> distinctProducts = new ArrayList<>();
        for(LineItem currentItem : stock){
            if(!distinctProducts.contains(currentItem.getProduct())) {
                distinctProducts.add(currentItem.getProduct());
            }
        }
        Product[] outputProducts = new Product[distinctProducts.size()];
        return distinctProducts.toArray(outputProducts);
    }

    public ArrayList<LineItem> getProductsInStock(){
        ArrayList<LineItem> inStockProducts = new ArrayList<>();
        for(LineItem li : stock){
            if(li.getQuantity() > 0)
                inStockProducts.add(li);
        }
        return inStockProducts;
    }

    /**
     * Increases credit by the value of the passed in coin
     * @param insertedCoin the Coin object being inserted
     */
    public void addCoin(Coin insertedCoin){
		currentCoins.addCoin(insertedCoin);
    }

    public String refundCoins(){
    	//todo make an implementation for change/refunding coins (Or don't, see if I care)
		String refundedAmount = "No coins returned";
		if(currentCoins.getValue() > 0){
			refundedAmount = currentCoins.toString();
			currentCoins.clearCoinSet();
		}
		return refundedAmount;
	}

    /**
     * Empties the takings(coins) of the machine
     * @return Amount Removed
     */
    public double removeMoney(){
        double amountRemoved = coins.getValue();
        coins.clearCoinSet();
        return amountRemoved;
    }

    /**
     * Checks if a user has enough credit to buy a given product, then removes it
     * from the stock list if successful. Deducts all credit and adds it to machine
     * balance.
     * @param p Product to buy
     * @throws VendingException Insufficient Credit
     */
    public void buyProduct(Product p) throws VendingException{ //todo fix option to choose buying an out of  stock product
		double currentCredit = currentCoins.getValue();
        if(currentCredit >= p.getPrice()) {
			for(LineItem item : stock){
				if(p.equals(item.getProduct()) && item.getQuantity() >= 1) {
					item.decrementQuantity();
					coins.addSetOfCoins(currentCoins.getSetOfCoins());
					currentCoins.clearCoinSet();
				}else {
					throw new VendingException("Not enough stock"); //todo make it display this when attempting to buy out of stock products, now it doesn't for unknown reason?
				}
			}
        }else
            throw new VendingException("Insufficient credit");
    }

    /**
     * Adds a product to the list of stock.
     * @param newProduct Product to add
     * @param quantity Quantity of said product
     */
    public void addProduct(Product newProduct, int quantity){
		LineItem item = new LineItem(newProduct, quantity);
		stock.add(item);
    }

}
