package msku.ceng.madlab.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private CartAdapter adapter;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartListView = findViewById(R.id.cartListView);
        Button clearCartButton = findViewById(R.id.clearCartButton);
        TextView priceText = findViewById(R.id.priceTextView);
        Button confirmCartButton = findViewById(R.id.confirmCartButton);

        cart = CartSingleton.getInstance();

        List<Product> cartItems = cart.getCartItems();
        adapter = new CartAdapter(this, cartItems, cart);
        cartListView.setAdapter(adapter);

        double totalPrice = cart.getTotalPrice();

        priceText.setText("Total price= " + totalPrice + "TL");

        clearCartButton.setOnClickListener(v -> {
            cart.clearCart();
            adapter.notifyDataSetChanged();
            Toast.makeText(CartActivity.this, "Cart cleared!", Toast.LENGTH_SHORT).show();
            recreate();
        });

        confirmCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cart.isCartEmpty()) {
                    Intent intent = new Intent(CartActivity.this, CartConfirmation.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CartActivity.this, "Cart is empty!" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}