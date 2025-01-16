package msku.ceng.madlab.finalproject;

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
import android.widget.Toast;

import com.google.firebase.firestore.auth.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class ProductAdapter extends ArrayAdapter<Product> {

    private Cart cart;

    public ProductAdapter(Context context, List<Product> products, Cart cart) {
        super(context, 0, products);
        this.cart = cart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_row, parent, false);
        }

        Product product = getItem(position);

        ImageView urlImageView = convertView.findViewById(R.id.urlImageView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);
        Button addToCartButton = convertView.findViewById(R.id.addToCartButton);


        String imageUrl = product.getUrl();
        new Thread(() -> {
            Bitmap bitmap = downloadImage(imageUrl);
            if (bitmap != null) {
                new Handler(Looper.getMainLooper()).post(() -> urlImageView.setImageBitmap(bitmap));
            }
        }).start();

        nameTextView.setText(product.getName());
        priceTextView.setText(String.valueOf(product.getPrice()));

        addToCartButton.setOnClickListener(v -> {
            cart.addToCart(product);
            Log.d("TAG", "getView: "+product);
            Context context = getContext();
            Toast.makeText(context,  product.getName() + " added to the cart!", Toast.LENGTH_SHORT).show();
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

    public void setProducts(List<Product> products) {
        clear();
        addAll(products);
        notifyDataSetChanged();
    }
}
