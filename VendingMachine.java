import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Daniel Dalton
 */
class VendingMachine {
	private CoinSet coins; //the total machine balance of coins
	private CoinSet currentCoins; //the user coins currently held in machine credit
	private ArrayList<LineItem> stock;
	private Operator currentOperator;
	private ArrayList<Operator> operators;
	private FileWriting fileOutput;
	private FileReading fileInput;

	/**
	 * Constructs the vending machine and initialises the list of stock
	 * and the set of coins
	 */
	VendingMachine() throws IOException {
		FileIO.fileCheck();
		fileInput = new FileReading();
		fileOutput = new FileWriting();
		stock = fileInput.readFromStockFile();
		coins = fileInput.readFromMoneyFile();
		operators = fileInput.readFromOperatorsFile();
		currentCoins = new CoinSet();
		initialiseOperators();
	}

	/**
	 * Returns a list of all the product choices available from the machine with price, containing each
	 * product only once.
	 *
	 * @return String
	 */
	String showProducts() {
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
	 *
	 * @return Product[]
	 */
	Product[] getProductTypes() {
		ArrayList<LineItem> inStockProducts = getProductsInStock();
		int outputArraySize = inStockProducts.size();
		Product[] outputProducts = new Product[outputArraySize];
		for (int i = 0; i < outputArraySize; i++) {
			outputProducts[i] = inStockProducts.get(i).getProduct();
		}
		return outputProducts;
	}

	/**
	 * Gets the list of lineItems that are in stock
	 *
	 * @return list of lineItem
	 */
	ArrayList<LineItem> getProductsInStock() {
		ArrayList<LineItem> inStockProducts = new ArrayList<>();
		for (LineItem li : stock) {
			if (li.getQuantity() > 0)
				inStockProducts.add(li);
		}
		return inStockProducts;
	}

	/**
	 * Gets the list of coin objects that the machine can accept
	 *
	 * @return acceptable coins
	 */
	Coin[] getAcceptableCoins() {
		Coin[] tempCoins = new Coin[coins.size()];
		return coins.getAcceptedCoins().toArray(tempCoins);
	}

	/**
	 * Increases credit by the value of the passed in coin
	 *
	 * @param insertedCoin the Coin object being inserted
	 */
	void addCoin(Coin insertedCoin) { //todo fix this with CLI, coins dont add after one purchase
		currentCoins.addCoin(insertedCoin);
	}

	/**
	 * @return
	 */
	String refundCoins() {
		//todo make an implementation for change/refunding coins (Or don't, see if I care)
		String refundedAmount = "No coins returned";
		if (currentCoins.getValue() > 0) {
			refundedAmount = currentCoins.toString();
			currentCoins.clearCoinSet();
		}
		return refundedAmount;
	}

	/**
	 * Empties the takings(coins) of the machine
	 *
	 * @return Amount Removed
	 */
	double removeMoney() {
		System.out.println("remove money" + currentOperator.canRemove());
		if (currentOperator.canRemove()) {
			double amountRemoved = coins.getValue();
			System.out.println(coins.getValue());
			coins.clearCoinSet();
			saveAllToFiles();
			return amountRemoved;
		}
		return 0;
	}

	/**
	 * Returns the current credit amount
	 *
	 * @return current credit
	 */
	double getCredit() {
		return currentCoins.getValue();
	}

	/**
	 * Checks if a user has enough credit to buy a given product, then removes it
	 * from the stock list if successful. Deducts all credit and adds it to machine
	 * balance.
	 *
	 * @param p Product to buy
	 * @return successful purchase
	 * @throws VendingException Insufficient Credit
	 */
	boolean buyProduct(Product p) throws VendingException {
		double currentCredit = currentCoins.getValue();
		boolean success = false;
		for (LineItem item : stock) {
			int currentItemQuantity = item.getQuantity();
			Product currentItemProduct = item.getProduct();
			if (p.equals(currentItemProduct) && currentItemQuantity >= 1 && currentCredit >= p.getPrice()) {
				item.decrementQuantity();
				coins.addSetOfCoins(currentCoins.getSetOfCoins());
				currentCoins.clearCoinSet();
				success = true;
			} else if (p.equals(currentItemProduct) && currentItemQuantity < 1) {
				throw new VendingException("Not enough stock");
			} else if (p.equals(currentItemProduct) && currentCredit < p.getPrice()) {
				throw new VendingException("Insufficient credit");
			}
		}
		saveAllToFiles();
		return success;
	}

	/**
	 * Adds a product to the list of stock.
	 *
	 * @param newProduct Product to add
	 * @param quantity   Quantity of said product
	 */
	void addProduct(Product newProduct, int quantity) {
		boolean found = false;
		for (LineItem li : stock) {
			if (newProduct.equals(li.getProduct())) {
				found = true;
				int quantityToAdd = li.getQuantity() + quantity;
				li.setQuantity(quantityToAdd);
				li.getProduct().setPrice(newProduct.getPrice());
				break;
			}
		}
		//Note to self: this is outside the foreach loop to prevent concurrent exception thingys
		if (!found) {
			LineItem item = new LineItem(newProduct, quantity);
			stock.add(item);
		}
		saveAllToFiles();
	}

	void addProduct(String description, double price, int quantity) {
		addProduct(new Product(description, price), quantity);
	}

	ArrayList<Operator> getOperators() {
		return operators;
	}

	private void initialiseOperators() {
		currentOperator = operators.get(0);
	}

	void addOperator(Operator op) {
		operators.add(op);
		saveAllToFiles();
	}

	void addOperator(String type, String code, String permissions) {
		Operator newOp = new Operator(type, code, permissions);
		operators.add(newOp);
		saveAllToFiles();
	}

	void saveAllToFiles() {
		try { //move the try catch to alans stuff
			fileOutput.fromArrayListToFiles(stock, coins, operators);
		} catch (IOException iox) {
			System.out.println("Failed to save files, Reason:\n" + iox.toString());
		}
	}
}
