/**
 * A wrapper for product that contains quantity
 * @author Daniel Dalton 17219477
 */
class LineItem {
	private Product product;
	private int quantity;

    /**
     * Constructs a lineItem with a given product and quantity
     *
     * @param prod Product
     * @param quantity Quantity of said product
     */
	LineItem(Product prod, int quantity) {
		this.product = prod;
		this.quantity = quantity;
	}

    /**
     * Getter for retrieving product from this lineItem
     *
     * @return Product
     */
	public Product getProduct() {
		return product;
	}

    /**
     * Getter for retrieving quantity
     * 
     * @return
     */
	public int getQuantity() {
		return quantity;
	}

    /**
     * Setter for setting quantity
     * 
     * @param quantity Quantity of products
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     * Decrement quantity by 1 if quantity is greater than 0
     */
	public void decrementQuantity() {
		if (quantity >= 1)
			this.quantity--;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof LineItem) {
			LineItem lineItemObj = (LineItem) o;
			return this.getProduct().equals(lineItemObj.getProduct());
		} else
			return false;
	}

	@Override
	public String toString() {
		return product.toString() + "," + quantity;
	}
}
