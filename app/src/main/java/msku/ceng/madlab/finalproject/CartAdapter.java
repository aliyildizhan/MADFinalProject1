package msku.ceng.madlab.finalproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class CartAdapter extends ArrayAdapter<Product> {

    private Cart cart;

    public CartAdapter(Context context, List<Product> products, Cart cart) {
        super(context, 0, products);
        this.cart = cart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_row, parent, false);
        }

        Product product = getItem(position);

        ImageView urlImageView = convertView.findViewById(R.id.urlImageView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);
        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
        Button deleteToCartButton = convertView.findViewById(R.id.deleteToCartButton);

        nameTextView.setText(product.getName());
        priceTextView.setText(String.valueOf(product.getPrice()));
        quantityTextView.setText(String.valueOf(product.getQuantity()));

        String imageUrl = product.getUrl();
        new Thread(() -> {
            Bitmap bitmap = downloadImage(imageUrl);
            if (bitmap != null) {
                // Update the ImageView on the main thread
                new Handler(Looper.getMainLooper()).post(() -> urlImageView.setImageBitmap(bitmap));
            }
        }).start();

        deleteToCartButton.setOnClickListener(v -> {
            cart.deleteFromCart(product);
            Log.d("TAG", "getView: " + product);
            Context context = getContext();
            if (context instanceof Activity) {
                ((Activity) context).recreate();
            }
            notifyDataSetChanged();
        });

        return convertView;
    }

    private Bitmap downloadImage(String urlString) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
