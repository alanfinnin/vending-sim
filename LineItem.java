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
	Product getProduct() {
		return product;
	}

    /**
     * Getter for retrieving quantity
     *
     * @return
     */
	int getQuantity() {
		return quantity;
	}

    /**
     * Setter for setting quantity
     *
     * @param quantity Quantity of products
     */
	void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     * Decrement quantity by 1 if quantity is greater than 0
     */
	void decrementQuantity() {
		if (quantity >= 1)
			this.quantity--;
	}

    /**
     * Checks whether a LineItem is equals by checking whether the products are equal
     * Overrides the built in equals
     *
     * @param o object to check against this
     * @return boolean of whether its equal
     */
	@Override
	public boolean equals(Object o) {
		if (o instanceof LineItem) {
			LineItem lineItemObj = (LineItem) o;
			return this.getProduct().equals(lineItemObj.getProduct());
		} else
			return false;
	}

    /**
     * Overrides the default toString for lineItem
     *
     * @return description,price,quantity
     */
	@Override
	public String toString() {
		return product.toString() + "," + quantity;
	}
}
