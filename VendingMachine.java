import java.util.ArrayList;

class VendingMachine {
	private CoinSet coins; //the total machine balance of coins
	private CoinSet currentCoins; //the user coins currently held in machine credit
    private ArrayList<LineItem> stock;
    private ArrayList<Operator> operators;

    /**
     * Constructs the vending machine and initialises the list of stock
	 * and the set of coins
     */
    VendingMachine(){
        stock = new ArrayList<>();
		coins = new CoinSet();
		currentCoins = new CoinSet();
    }

    Coin[] getAcceptableCoins(){
        Coin[] tempCoins = new Coin[coins.size()];
        return coins.getAcceptedCoins().toArray(tempCoins);
    }
    
    public Operator getOperator(int i){
        return operators.get(i);
    }
    
    public void addOperator(Operator o){
        operators.add(o);
    }

	/**
     * Returns a list of all the product choices available from the machine with price, containing each
     * product only once. 
     * @return String
     */
	public String showProducts(){ //todo dont display out of stock product
		Product[] productList = getProductTypes();
		StringBuilder output = new StringBuilder();
		for (Product p : productList) {
            output.append(p.getDescription());
            output.append(", \u20ac");
            output.append(p.getPrice());
            output.append("\n");
        }
		return output.toString();
	}
	
    /**
     * Returns a list of all the product choices available from the machine, containing each
     * product only once.
     * @return Product[]
     */
    public Product[] getProductTypes(){
        ArrayList<LineItem> inStockProducts = getProductsInStock();
        int outputArraySize = inStockProducts.size();
        Product[] outputProducts = new Product[outputArraySize];
        for(int i = 0; i < outputArraySize; i++){
                outputProducts[i] = inStockProducts.get(i).getProduct();
        }
        return outputProducts;
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
     * @return successful purchase
     */
    public boolean buyProduct(Product p) throws VendingException{ //todo fix option to choose buying an out of  stock product
		double currentCredit = currentCoins.getValue();
		boolean success = false;
        for(LineItem item : stock){
            int currentItemQuantity = item.getQuantity();
            Product currentItemProduct = item.getProduct();
            if(p.equals(currentItemProduct) && currentItemQuantity >= 1 && currentCredit >= p.getPrice()) {
                item.decrementQuantity();
                coins.addSetOfCoins(currentCoins.getSetOfCoins());
                currentCoins.clearCoinSet();
                success = true;
            }else if(p.equals(currentItemProduct) && currentItemQuantity < 1){
                throw new VendingException("Not enough stock");
            }else if(p.equals(currentItemProduct) && currentCredit < p.getPrice()){
                throw new VendingException("Insufficient credit");
            }
        }
        return success;
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
