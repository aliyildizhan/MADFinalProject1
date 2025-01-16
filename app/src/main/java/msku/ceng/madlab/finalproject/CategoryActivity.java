package msku.ceng.madlab.finalproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class CategoryActivity extends AppCompatActivity {
    ImageView btnVegetables;
    ImageView btnFruits;
    ImageView btnDrinks;
    ImageView btnBakery;
    ImageView btnDairy;
    ImageView btnStaple;
    ImageView btnSnacks;
    ImageView btnBreakfast;
    ImageView btnCleaning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVegetables = findViewById(R.id.vegetables);
        btnVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Vegetables");
                startActivity(intent);
            }
        });

        btnFruits = findViewById(R.id.fruits);
        btnFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Fruits");
                startActivity(intent);
            }
        });

        btnDrinks = findViewById(R.id.drinks);
        btnDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Beverages");
                startActivity(intent);
            }
        });

        btnBakery = findViewById(R.id.bakery);
        btnBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "BakeryProducts");
                startActivity(intent);
            }
        });

        btnDairy = findViewById(R.id.dairy);
        btnDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "DairyProducts");
                startActivity(intent);
            }
        });

        btnStaple = findViewById(R.id.staple);
        btnStaple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Staples");
                startActivity(intent);
            }
        });

        btnSnacks = findViewById(R.id.snacks);
        btnSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Snacks");
                startActivity(intent);
            }
        });

        btnBreakfast = findViewById(R.id.breakfast);
        btnBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "Breakfast");
                startActivity(intent);
            }
        });

        btnCleaning = findViewById(R.id.cleaning);
        btnCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ProductsActivity.class);
                intent.putExtra("category", "CleaningProducts");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search groceries...");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search on submit
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search on text change
                performSearch(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            // Handle search action
            return true;
        } else if (id == R.id.action_cart) {
            Intent intent = new Intent(CategoryActivity.this, CartActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_settings) {
            // Handle settings action
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void performSearch(String query) {
        SearchResultFragment fragment = SearchResultFragment.newInstance(query);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null) // Optional: adds the transaction to the back stack
                .commit();
    }

}