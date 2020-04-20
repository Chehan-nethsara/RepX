package com.example.repx;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.repx.dto.Dealer;
import com.google.gson.Gson;


public class DealerProfile extends AppCompatActivity {

    private Dealer dealer;

    private EditText dealerName;
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
        setDealerProfile();
    }

    private void setDealerProfile(){
        dealerName.setText(dealer.getName());
    }
}