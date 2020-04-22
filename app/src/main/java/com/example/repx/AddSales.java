package com.example.repx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddSales extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseFirestore db;
    private final static String TAG = "AddNewSale";
    private EditText saleproduct,saleqty,salecus,saletotal,saledis;
    private Button addNewSaleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);

        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        saleproduct = findViewById(R.id.editText111);
        saleqty = findViewById(R.id.editText112);
        salecus = findViewById(R.id.editText113);
        saledis = findViewById(R.id.editText114);
        saletotal = findViewById(R.id.editText115);
        addNewSaleButton = findViewById(R.id.button13);

        toolbar.setTitle("Add New Sale");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addNewSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSale();
            }
        });

    }
    private void addNewSale(){

        String product,qty,customer,discount,total,id;
        product = saleproduct.getText().toString();
        qty  =saleqty.getText().toString();
        customer = salecus.getText().toString();
        discount = saledis.getText().toString();
        total = saletotal.getText().toString();
        id = UUID.randomUUID().toString();

        boolean flag = true;
        if(product.isEmpty()){
            flag = false;
            saleproduct.setError("Product is required!");
        }if(qty.isEmpty()){
            flag = false;
            saleqty.setError("Quantity is required");
        }if(customer.isEmpty()){
            flag = false;
            salecus.setError("Customer is required");
        }if(discount.isEmpty()){
            flag = false;
            saledis.setError("Discount is required");
        }
        if(total.isEmpty()){
            flag = false;
            saletotal.setError(" Total is required");
        }


        if(flag){
            Map<String, Object> sale = new HashMap<>();
            sale.put("product",product);
            sale.put("qty",qty);
            sale.put("customer",customer);
            sale.put("discount",discount);
            sale.put("total",total);

            // Add a new document with a generated ID
            db.collection("Sales")
                    .add(sale)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            showSuccessToast();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

        }
    }
    private void showSuccessToast(){
        Toast.makeText(this, "Sale Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,SalesMenu.class);
        startActivity(intent);
        finish();
    }

}
