package com.example.repx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.repx.dto.Sale;
import com.example.repx.recyclerView.adapter.SalesRecycleViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesMenu extends AppCompatActivity {
    private static final String TAG = "SalesMenu";


    private Toolbar toolbar;
    private FirebaseFirestore db;

    private RecyclerView recyclerView;

    private List<Sale> saleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_menu);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sales");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewSales);

        db = FirebaseFirestore.getInstance();

        saleList = new ArrayList<>();

        getSalesData();


    }
    public  void getSalesData(){

        db.collection("Sales")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getData());
                                Map<String,Object> saleHashMap = document.getData();
                                Sale sale = new Sale(saleHashMap.get("product").toString(),saleHashMap.get("qty").toString(),saleHashMap.get("customer").toString(),saleHashMap.get("discount").toString(),saleHashMap.get("total").toString());
                                sale.setId(document.getId());
                                saleList.add(sale);

                            }
                            loadRecyclerView();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sale_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_sale:
                Intent intent = new Intent(this,AddSales.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SalesRecycleViewAdapter myAdapter = new SalesRecycleViewAdapter(saleList, this);
        recyclerView.setAdapter(myAdapter);
    }
}
