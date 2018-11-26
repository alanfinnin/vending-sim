/**
*	
*/
public class Product {
    private double price;
    private String description;

    public Product(String description, double price){
        this.description = description;
        this.price = price;
    }

    public String getDescription(){
        return description;
    }

    public double getPrice(){
        return price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String toString(){
        return description + "," + price;
    }
}
