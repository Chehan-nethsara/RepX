package com.example.repx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.dto.Dealer;
import com.example.repx.recyclerView.adapter.DealerRecycleViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DealerActivity extends AppCompatActivity {

    private static final String TAG = "DealerActivity";

    private Toolbar toolbar;
    private  FirebaseFirestore db;

    private  RecyclerView recyclerView;

    private  List<Dealer> dealerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dealer");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();

        dealerList = new ArrayList<>();

        getdealersData();

    }


    public  void getdealersData(){

        db.collection("dealer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getData());
                                Map<String,Object> dealerHashMap = document.getData();
                                Dealer dealer = new Dealer(dealerHashMap.get("code").toString(),dealerHashMap.get("name").toString(),dealerHashMap.get("area").toString(),dealerHashMap.get("category").toString(),dealerHashMap.get("phoneNumber").toString());
                                dealerList.add(dealer);

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
        getMenuInflater().inflate(R.menu.dealer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_dealer:
                Intent intent = new Intent(this,AddNewDealerActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    void loadRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DealerRecycleViewAdapter myAdapter = new DealerRecycleViewAdapter(dealerList, this);
        recyclerView.setAdapter(myAdapter);
    }

}
