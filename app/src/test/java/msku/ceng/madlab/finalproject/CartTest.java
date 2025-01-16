package msku.ceng.madlab.finalproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

public class CartTest {
    private Cart cart;

    @Before
    public void setUp() {
        // Initialize the Cart object before each test
        cart = new Cart();
    }

    @Test
    public void testGetTotalPrice() {
        Product product1 = new Product(10.0, "Tomato");
        Product product2 = new Product(20.0, "Potato");

        cart.addToCart(product1);
        cart.addToCart(product2);

        double totalPrice = cart.getTotalPrice();
        assertEquals(30.0, totalPrice, 0.0);

        System.out.println("Total price: " + totalPrice);
    }

}
