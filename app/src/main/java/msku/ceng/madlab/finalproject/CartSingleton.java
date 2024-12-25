package msku.ceng.madlab.finalproject;

public class CartSingleton {
    private static Cart instance;

    private CartSingleton() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }
}
