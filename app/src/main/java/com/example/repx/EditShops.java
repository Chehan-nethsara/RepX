package com.example.repx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.repx.dto.Customer;
import com.example.repx.recyclerView.adapter.CustomerRecycleViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditShops extends AppCompatActivity implements CustomerRecycleViewAdapter.CustomerListner {

    //CustomerRecycleViewAdapter.CustomerListner customerListner;
    private static final String TAG = "EditShops";
    private Toolbar toolbar;
    private FirebaseFirestore db;
    private RecyclerView recyclerViewCustomer;
    private List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shops);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Customers");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        recyclerViewCustomer = findViewById(R.id.recyclerView_Customer);
//        btn_Add_New_Customer_New = findViewById(R.id.btn_add_New_cudtomer_ui);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access a Cloud Firestone instance from your Activity
        db = FirebaseFirestore.getInstance();
        customerList = new ArrayList<>();
        getCustomersList();

    }


    private void getCustomersList() {
        db.collection("Customer")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());

                                System.out.println(document.getData());

                                Map<String,Object> customerHashMap = document.getData();

                                Customer customer = new Customer();
                                        customer.setShopNameCustomer(customerHashMap.get("ShopName").toString());
                                        customer.setOwnerNameCustomer(customerHashMap.get("OwnerName").toString());
                                        customer.setPhoneCustomer(customerHashMap.get("PhoneNumber").toString());
                                        customer.setEmailCustomer(customerHashMap.get("EmailAddress").toString());
                                        customer.setAddressCustomer(customerHashMap.get("PostalAddress").toString());
                                    customer.setCustomerDocumentID(document.getId());
                                    //customerListner.removeCustomer(i);
                               customerList.add(customer);
                            }
                            loadRecyclerViewCustomer();
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_customer:
                Intent intent = new Intent(this,AddShop.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   void loadRecyclerViewCustomer() {
       recyclerViewCustomer.setLayoutManager(new LinearLayoutManager(this));
        CustomerRecycleViewAdapter customerAdapter = new CustomerRecycleViewAdapter(customerList,this);
       recyclerViewCustomer.setAdapter(customerAdapter);
   }

    public void removeCustomer(int i) {
        this.customerList.remove(i);
        loadRecyclerViewCustomer();
    }

    /*public void showSuccessDeleteToast(){
        Toast.makeText(this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditShops.class);
        startActivity(intent);
        finish();
    }*/
}
