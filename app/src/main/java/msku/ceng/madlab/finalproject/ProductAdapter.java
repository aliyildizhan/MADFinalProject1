package msku.ceng.madlab.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;

import java.util.List;


public class ProductAdapter extends ArrayAdapter<Product> {

    private Cart cart;

    public ProductAdapter(Context context, List<Product> users, Cart cart) {
        super(context, 0, users);
        this.cart = cart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_row, parent, false);
        }

        Product product = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);
        Button addToCartButton = convertView.findViewById(R.id.addToCartButton);

        nameTextView.setText(product.getName());
        priceTextView.setText(String.valueOf(product.getPrice()));

        addToCartButton.setOnClickListener(v -> {
            cart.addToCart(product);
            Log.d("TAG", "getView: "+product);
        });

        return convertView;
    }
}
