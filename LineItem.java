class LineItem {
	private Product product;
	private int quantity;

	LineItem(Product prod, int quantity) {
		this.product = prod;
		this.quantity = quantity;
	}

	LineItem(String description, double price, int quantity) {
		this.product = new Product(description, price);
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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
