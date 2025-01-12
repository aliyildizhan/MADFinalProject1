package msku.ceng.madlab.finalproject;

public class Product {
    private String url;
    private String name;
    private double price;
    private int quantity = 0;

    public Product(){}

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUrl() {
        return url;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
