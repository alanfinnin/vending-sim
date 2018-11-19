import javax.sound.sampled.Line;
import java.util.ArrayList;

class VendingMachine {
	private CoinSet coins;
	private CoinSet currentCoins;
    private ArrayList<LineItem> products;

    /**
     * Constructs the vending machine and initialises the list of products
	 * and the set of coins
     */
    public VendingMachine(){
        products = new ArrayList<LineItem>();
		coins = new CoinSet();
		currentCoins = new CoinSet();
    }

	/**
     * Returns a list of all the product choices available from the machine with price, containing each
     * product only once. 
     * @return String
     */
	public String showProducts(){
		Product[] productList = getProductTypes();
		String output = "";
		for (Product p : productList)
			output += p.getDescription() + ", " + p.getPrice() + "\n";
		
		return output;
	}
	
    /**
     * Returns a list of all the product choices available from the machine, containing each
     * product only once. (I'm proud of this one)
     * @return Product[]
     */
    public Product[] getProductTypes(){
        ArrayList<Product> distinctProducts = new ArrayList<Product>();
        for(LineItem currentItem : products){
            if(!distinctProducts.contains(currentItem.getProduct())) {
                distinctProducts.add(currentItem.getProduct());
            }
        }
        Product[] outputProducts = new Product[distinctProducts.size()];
        return distinctProducts.toArray(outputProducts);
    }

    /**
     * Increases credit by the value of the passed in coin
     * @param insertedCoin
     */
    public void addCoin(Coin insertedCoin){
		currentCoins.addCoin(insertedCoin);
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
     * from the products list if successful. Deducts all credit and adds it to machine
     * balance.
     * @param p Product to buy
     * @throws VendingException Insufficient Credit
     */
    public void buyProduct(Product p) throws VendingException{
		double currentCredit = currentCoins.getValue();
        if(currentCredit >= p.getPrice()) {
            if()
			
			
			products.remove(p);
			coins.addSetOfCoins(currentCoins.getSetOfCoins());
			currentCoins.clearCoinSet();
        }else
            throw new VendingException("Insufficient credit");
    }

    /**
     * Adds a product to the list of products.
     * @param newProduct Product to add
     * @param quantity Quantity of said product
     */
    public void addProduct(Product newProduct, int quantity){
		LineItem item = new LineItem(newProduct, quantity);
		products.add(item);
    }

}
