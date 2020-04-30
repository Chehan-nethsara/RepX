package com.example.repx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewDealerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseFirestore db;
    private final static String TAG = "AddNewDealerActivity";
    private EditText dealerCode,dealerName,dealerArea,dealerCategory,dealerPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_dealer);
        db = FirebaseFirestore.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dealerCode = findViewById(R.id.txt_dealer_code);
        dealerName = findViewById(R.id.txt_dealer_name);
        dealerArea = findViewById(R.id.txt_dealerArea);
        dealerCategory = findViewById(R.id.txt_dealerCategory);
        dealerPhoneNumber = findViewById(R.id.txt_dealerPhoneNumber);

        toolbar.setTitle("Add New Dealer");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void addNewDealer(){

        String name,code,area,category,phoneNumber;
        name = dealerName.getText().toString();
        code  =dealerCode.getText().toString();
        area = dealerArea.getText().toString();
        category = dealerCategory.getText().toString();
        phoneNumber = dealerPhoneNumber.getText().toString();
        boolean flag = true;
        if(name.isEmpty()){
            flag = false;
            dealerName.setError("Name is required!");
        }if(code.isEmpty()){
            flag = false;
            dealerCode.setError("Code is required");
        }if(area.isEmpty()){
            flag = false;
            dealerArea.setError("Area is required");
        }if(category.isEmpty()){
            flag = false;
            dealerCategory.setError("Category is required");
        }

        if(phoneNumber.isEmpty()){
            flag = false;
            dealerPhoneNumber.setError("Phone Number is required");
        }else{
            if(!phoneNumber.matches("\\d{10}")){
                flag = false;
                dealerPhoneNumber.setError("10 digits required");
            }
        }

        if(flag){
            Map<String, Object> dealer = new HashMap<>();
            dealer.put("area",area);
            dealer.put("name",name);
            dealer.put("category",category);
            dealer.put("code",code);
            dealer.put("phoneNumber",phoneNumber);

            // Add a new document with a generated ID
            db.collection("dealer")
                    .add(dealer)
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
        Toast.makeText(this, "Dealer Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,DealerActivity.class);
        startActivity(intent);
        finish();
    }
}
