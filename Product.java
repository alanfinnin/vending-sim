/**
 * Product object class that stores price and description
 * @author Daniel Dalton 17219477
 */
public class Product {
	private double price;
	private String description;

	/**
	 * Constructor for product object
	 * 
	 * @param description Description of product (Name)
	 * @param price Selling price
	 */
	Product(String description, double price) {
		this.description = description;
		this.price = price;
	}

	/**
	 * Getter to retrieve description of product
	 * 
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter to retrieve price of product
	 * 
	 * @return Selling price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter for price of product
	 * 
	 * @param price selling price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Product) {
			Product prodObj = (Product) o;
			return this.description.equals(prodObj.getDescription());
		} else
			return false;
	}

	public String toString() {
		return description + "," + price;
	}
}
