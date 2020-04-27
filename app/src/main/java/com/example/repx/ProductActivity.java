package com.example.repx;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import com.example.repx.dto.Product;
import com.example.repx.recyclerView.adapter.ProductRecycleViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity implements ProductRecycleViewAdapter.ProductListner {


    RecyclerView recyclerView;

    private List<Product> productList;
    private FirebaseFirestore db;
    private static final String TAG = "view_product";
    private Button editProductButton;
    //private Button deleteProductButton;
    Toolbar toolbar;


    SearchView mySearchView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerViewMyProducts);
        productList = new ArrayList<>();
        loadProductData();

       editProductButton = (Button) findViewById(R.id.btn_editProduct);

        //editProductButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  openProduct();
            //}
        //});
        //editProductButton.setOnClickListener(R.layout.product);

    }
    public void openProduct() {
       Intent intent = new Intent(this, product.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_product:
                Intent intent = new Intent(this,AddProductActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    void loadProductData(){
        db.collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getData());
                                Map<String,Object> productMap = document.getData();

                                Product product = new Product();
                                product.setProductName(productMap.get("name").toString());
                                product.setPrductQuantity(productMap.get("quntity").toString());
                                product.setProductCode(productMap.get("code").toString());
                                product.setProductDescription(productMap.get("description").toString());
                                product.setProductPrice(productMap.get("price").toString());
                                product.setProductDocumnetID(document.getId());
                                productList.add(product);

                            }
                            loadRecyclerView();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductRecycleViewAdapter myAdapter = new ProductRecycleViewAdapter(productList, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void removeProduct(int i) {
        this.productList.remove(i);
        loadRecyclerView();
    }
}
