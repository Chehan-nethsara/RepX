package com.example.repx;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

import com.example.repx.dto.Dealer;
import com.example.repx.dto.Product;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class product extends AppCompatActivity {

   // private Products products;
    //private FirebaseFirestore db;
    //private EditText productName, productPrice, productDescription, productCode, productQuntity, productDealer;
    //private Toolbar toolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);


        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dealer Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.products = new Gson().fromJson(getIntent().getStringExtra("PRODUCT"), Product.class);
        productName = findViewById(R.id.txt_dealerName_profile);
        productName.setEnabled(false);

        productPrice = findViewById(R.id.txt_dealer_code_profile);
        productPrice.setEnabled(false);

        productDescription = findViewById(R.id.txt_dealerArea_profile);
        productDescription.setEnabled(false);

        productCode = findViewById(R.id.txt_dealerCategory_profile);
        productCode.setEnabled(false);

        productQuntity = findViewById(R.id.txt_dealerPhoneNumber_profile);
        productQuntity.setEnabled(false);

        productDealer = findViewById(R.id.txt_dealerPhoneNumber_profile);
        productDealer.setEnabled(false);


        setDealerProfile();*/

    }

   /* private void setDealerProfile(){
        dealerName.setText(dealer.getName());
        dealerCode.setText(dealer.getId());
        dealerArea.setText(dealer.getArea());
        dealerCategory.setText(dealer.getCategory());
        dealerphoneNumber.setText(dealer.getTelePhoneNumber());
    }*/


}