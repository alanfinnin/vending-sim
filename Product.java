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

	/**
	 * Checks whether a product is equals by checking whether the descriptions are equal
	 * Overrides the built in equals
	 *
	 * @param o object to check against this
	 * @return boolean of whether its equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Product) {
			Product prodObj = (Product) o;
			return this.description.equals(prodObj.getDescription());
		} else
			return false;
	}

	/**
	 * Overrides the default toString for product
	 * returns the name of the product
	 *
	 * @return description,price
	 */
	public String toString() {
		return description + "," + price;
	}
}
