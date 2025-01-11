package msku.ceng.madlab.finalproject;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cartItems.add(product);
        Log.w("TAG", "addToCart: " + product);
    }

    public void deleteFromCart(Product product) {
        cartItems.remove(product);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i=0; i<cartItems.size(); i++) {
            Product product = cartItems.get(i);
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }
}
