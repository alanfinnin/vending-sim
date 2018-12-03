/**
 *
 */
public class Product {
	private double price;
	private String description;

	Product(String description, double price) {
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
