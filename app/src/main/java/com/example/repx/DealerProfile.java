package com.example.repx;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.repx.dto.Dealer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;


public class DealerProfile extends AppCompatActivity {

    private Dealer dealer;
    private FirebaseFirestore db;
    private EditText dealerName,dealerCode,dealerArea,dealerCategory,dealerphoneNumber;
    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealer_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dealer Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.dealer = new Gson().fromJson(getIntent().getStringExtra("DEALER"),Dealer.class);
        dealerName = findViewById(R.id.txt_dealerName_profile);
        dealerName.setEnabled(false);

        dealerCode = findViewById(R.id.txt_dealer_code_profile);
        dealerCode.setEnabled(false);

        dealerArea = findViewById(R.id.txt_dealerArea_profile);
        dealerArea.setEnabled(false);

        dealerCategory = findViewById(R.id.txt_dealerCategory_profile);
        dealerCategory.setEnabled(false);

        dealerphoneNumber = findViewById(R.id.txt_dealerPhoneNumber_profile);
        dealerphoneNumber.setEnabled(false);

        setDealerProfile();

    }

    private void setDealerProfile(){
        dealerName.setText(dealer.getName());
        dealerCode.setText(dealer.getId());
        dealerArea.setText(dealer.getArea());
        dealerCategory.setText(dealer.getCategory());
        dealerphoneNumber.setText(dealer.getTelePhoneNumber());
    }


}