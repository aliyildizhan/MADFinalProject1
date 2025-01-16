package msku.ceng.madlab.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class CartConfirmation extends AppCompatActivity {

    private ListView cartListView;
    private CartAdapter adapter;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_confirmation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Spinner spinner = findViewById(R.id.my_spinner);

        ArrayAdapter<CharSequence> sadapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_items, android.R.layout.simple_spinner_item);

        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(sadapter);

        cartListView = findViewById(R.id.cartListView);
        TextView priceText = findViewById(R.id.priceTextView);

        cart = CartSingleton.getInstance();

        List<Product> cartItems = cart.getCartItems();
        adapter = new CartAdapter(this, cartItems, cart);
        cartListView.setAdapter(adapter);

        double totalPrice = cart.getTotalPrice();

        priceText.setText("Total price= " + totalPrice + "TL");

        Button confirm = findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.clearCart();
                Toast.makeText(CartConfirmation.this, "Order Confirmed!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CartConfirmation.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

    }
}