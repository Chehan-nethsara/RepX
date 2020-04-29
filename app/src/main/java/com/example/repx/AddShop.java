package com.example.repx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddShop extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseFirestore db;
    private final static String TAG = "AddShop";
    private EditText shopNameCustomer, ownerNameCustomer, phoneCustomer, emailCustomer, addressCustomer;
    private Button btnAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        shopNameCustomer = findViewById(R.id.txt_shopname_customer);
        ownerNameCustomer = findViewById(R.id.txt_ownername_customer);
        phoneCustomer = findViewById(R.id.txt_phone_customer);
        emailCustomer = findViewById(R.id.txt_email_customer);
        addressCustomer = findViewById(R.id.txt_address_customer);
        btnAddCustomer = findViewById(R.id.btn_addNewCustomer);

        toolbar.setTitle("Add New Customer");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCustomer();
            }
        });
    }

    private void addNewCustomer() {

        String customerShop, customerName, customerPhone, customerEmail, customerAddress;

        customerShop = shopNameCustomer.getText().toString();
        customerName = ownerNameCustomer.getText().toString();
        customerPhone = phoneCustomer.getText().toString();
        customerEmail = emailCustomer.getText().toString();
        customerAddress = addressCustomer.getText().toString();

        boolean shop = true;

        if (customerShop.isEmpty()) {
            shop = false;
            shopNameCustomer.setError("Shop's Name is Required!");
        }
        if (customerName.isEmpty()) {
            shop = false;
            ownerNameCustomer.setError("Owner Name is Required!");
        }
        if (customerPhone.isEmpty()) {
            shop = false;
            phoneCustomer.setError("Phone Number is Required!");
        } else {
            if (!customerPhone.matches("\\d{10}")) {
                shop = false;
                phoneCustomer.setError("10 digits are Required");
            }
        }
        if (customerEmail.isEmpty()) {
            shop = false;
            emailCustomer.setError("Email is Required!");
        }
        if (customerAddress.isEmpty()) {
            shop = false;
            addressCustomer.setError("Address is Required!");
        }

        if (shop) {
            Map<String, Object> customer = new HashMap<>();
            customer.put("ShopName", customerShop);
            customer.put("OwnerName", customerName);
            customer.put("PhoneNumber", customerPhone);
            customer.put("EmailAddress", customerEmail);
            customer.put("PostalAddress", customerAddress);

            // Add a new document with a generated ID
            db.collection("Customer")
                    .add(customer)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "Document Snapshot added with ID: " + documentReference.getId());
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
        Toast.makeText(this, "Customer Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, EditShops.class);
        startActivity(intent);
        finish();
    }

}

