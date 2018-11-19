public class LineItem {
    private Product product;
    private int quantity;

    LineItem(Product prod, int quantity){
        this.product = prod;
        this.quantity = quantity;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
	
	public boolean decrementQuantity(){
		if(quantity >= 1)
			this.quantity--;
		else
			return false;
			
		return true;
	}

    @Override
    public String toString(){
        return product.toString() + ", " + quantity;
    }
}
