package com.example.repx;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.repx.dto.Customer;
import com.example.repx.dto.Product;
import com.google.gson.Gson;

public class productProfile extends AppCompatActivity  {

   private Toolbar toolbar;
    private Product products;
    private TextView profileProductName, profileProductPrice, profileProductDescription, profileProductCode, profileProductQuntity;
    private ImageView imgSelectedProductImageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_product);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.products = new Gson().fromJson(getIntent().getStringExtra("PRODUCT"), Product.class);

        imgSelectedProductImageProfile = findViewById(R.id.txt_product_profile_image);
        imgSelectedProductImageProfile.setEnabled(false);

        profileProductName = findViewById(R.id.txt_product_profile_name);
        profileProductName.setEnabled(false);

        profileProductPrice = findViewById(R.id.txt_product_profile_price);
        profileProductPrice.setEnabled(false);

        profileProductDescription = findViewById(R.id.txt_product_profile_description);
        profileProductDescription.setEnabled(false);

        profileProductCode = findViewById(R.id.txt_product_profile_code);
        profileProductCode.setEnabled(false);

        profileProductQuntity = findViewById(R.id.txt_product_profile_quntity);
        profileProductQuntity.setEnabled(false);

        setProductProfile();
    }

    private void setProductProfile(){
        profileProductName.setText(products.getProductName());
        profileProductPrice.setText(products.getProductPrice());
        profileProductDescription.setText(products.getProductDescription());
        profileProductCode.setText(products.getProductCode());
        profileProductQuntity.setText(products.getPrductQuantity());
    }

}
