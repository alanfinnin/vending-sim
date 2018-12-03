class LineItem {
	private Product product;
	private int quantity;
	/**
		* A LineItem
		*/
	LineItem(Product prod, int quantity) {
		this.product = prod;
		this.quantity = quantity;
	}
	/**
	 * Constructs a LineItem with a given description, price and quantity
	 
	 * @param description
	 * @param price
	 * @param quantity
	 */
	LineItem(String description, double price, int quantity) {
		this.product = new Product(description, price);
		this.quantity = quantity;
	}
	/**
	 * Returns the product
	 *
	 * @return product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * Returns the quantity
	 *
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Sets the quantity
	 *
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * Decrements the quantity
	 *
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

