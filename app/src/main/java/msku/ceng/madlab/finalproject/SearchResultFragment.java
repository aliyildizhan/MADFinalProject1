package msku.ceng.madlab.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView searchResultsListView;
    private ProductAdapter productAdapter;
    private List<Product> searchResultsList = new ArrayList<>();
    private String query;
    Cart cart;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchResultFragment newInstance(String query) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString("query", query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString("query");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        cart = CartSingleton.getInstance();
        searchResultsListView = view.findViewById(R.id.searchResultListView);
        productAdapter = new ProductAdapter(requireContext(), searchResultsList, cart);
        searchResultsListView.setAdapter(productAdapter);

        if (query != null && !query.isEmpty()) {
            performSearch(query);
        } else {
            Toast.makeText(requireContext(), "No search query provided", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void performSearch(String query) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String[] collections = {"Fruits", "Vegetables", "Beverages", "BakeryProducts", "DairyProducts", "Staples", "Snacks", "Breakfast", "CleaningProducts"};

        List<Task<QuerySnapshot>> tasks = new ArrayList<>();
        for (String collection : collections) {
            tasks.add(db.collection("Categories").document(collection).collection("Products")
                    .orderBy("name")
                    .startAt(query)
                    .endAt(query + "\uf8ff")
                    .get());
        }

        Tasks.whenAllSuccess(tasks).addOnSuccessListener(results -> {
            searchResultsList.clear();
            for (Object result : results) {
                QuerySnapshot querySnapshot = (QuerySnapshot) result;
                for (DocumentSnapshot doc : querySnapshot) {
                    Product product = doc.toObject(Product.class);
                    searchResultsList.add(product);
                }
            }
            productAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> {
            Toast.makeText(requireContext(), "Failed to fetch products: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}