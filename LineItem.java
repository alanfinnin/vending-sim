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

    @Override
    public String toString(){
        return product.toString() + ", " + quantity;
    }
}
