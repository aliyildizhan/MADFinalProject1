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
        if (!isInCart(product)) {
            product.setQuantity(1);
            cartItems.add(product);
        }
        else {
            for (int i=0; i<cartItems.size(); i++) {
                if (cartItems.get(i).getName().equals(product.getName())) {
                    cartItems.get(i).setQuantity(cartItems.get(i).getQuantity() + 1);
                }
            }
        }
    }

    public void deleteFromCart(Product product) {
        int quantity = product.getQuantity();
        if (quantity == 1){
            product.setQuantity(0);
            cartItems.remove(product);
        }

        else  {
            product.setQuantity(quantity-1);
        }
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        for (int i=0; i<cartItems.size(); i++) {
            Product product = cartItems.get(i);
            product.setQuantity(0);
            cartItems.set(i, product);
        }
        cartItems.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (int i=0; i<cartItems.size(); i++) {
            Product product = cartItems.get(i);
            totalPrice += (product.getPrice() * product.getQuantity());
        }

        return totalPrice;
    }

    private boolean isInCart(Product product) {
        boolean inCart = false;
        for (int i=0; i<cartItems.size(); i++) {
            if (cartItems.get(i).getName().equals(product.getName())) {
                inCart = true;
                return inCart;
            }
        }
        return inCart;
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

}
