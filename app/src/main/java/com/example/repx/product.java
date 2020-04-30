package com.example.repx;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.repx.dto.Dealer;
import com.example.repx.dto.Product;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class product extends AppCompatActivity {

    private Context context;
    private Product products;
    private FirebaseFirestore db;
    private EditText productName, productPrice, productDescription, productCode, productQuntity, productDealer;
    private Toolbar toolbar;
    private Button btnEditProduct,btnUpdateProductProfile;

    static RelativeLayout relativeProduct;
    ImageView mImageView;
    Button mChooseBtn;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = FirebaseFirestore.getInstance();

        this.products = new Gson().fromJson(getIntent().getStringExtra("PRODUCT"), Product.class);


       // System.out.println(new Gson().toJson(this.products));
      //  mImageView = findViewById(R.id.img_productView);

        productName = findViewById(R.id.product_name_profile);
        productName.setEnabled(true);

        productPrice = findViewById(R.id.product_price_profile);
        productPrice.setEnabled(true);

        productDescription = findViewById(R.id.product_description_profile);
        productDescription.setEnabled(true);

        productCode = findViewById(R.id.product_code_profile);
        productCode.setEnabled(true);

        productQuntity = findViewById(R.id.product_quntity_profile);
        productQuntity.setEnabled(true);
       // btnEditProduct = findViewById(R.id.btn_edit_profileProduct);
        btnUpdateProductProfile= findViewById(R.id.btn_profile_product_update);
        //container = findViewById(R.id.profileProduct);
        relativeProduct = findViewById(R.id.profileProduct);


        btnUpdateProductProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductProfileUpdate();
            }
        });

        setProductUpdateProfile();


    }





    private void ProductProfileUpdate(){
        if(!products.getProductName().equals(productName.getText().toString()) ||
                !products.getProductPrice().equals(productPrice.getText().toString()) ||
                !products.getProductDescription().equals(productDescription.getText().toString()) ||
                !products.getProductCode().equals(productCode.getText().toString()) ||
                !products.getPrductQuantity().equals(productQuntity.getText().toString()))
        {
            products.setProductName(productName.getText().toString());
            products.setProductPrice(productPrice.getText().toString());
            products.setProductDescription(productDescription.getText().toString());
            products.setProductCode(productCode.getText().toString());
            products.setPrductQuantity(productQuntity.getText().toString());

            Map<String, Object> productMap = new HashMap<>();

            productMap.put("name", products.getProductName());
            productMap.put("price", products.getProductPrice());
            productMap.put("description", products.getProductDescription());
            productMap.put("code", products.getProductCode());
            productMap.put("quntity", products.getPrductQuantity());

            db.collection("product").document(products.getProductDocumnetID()).update(productMap);
        }

        successMessage();
    }

    //to set data to customer update
    private void setProductUpdateProfile(){
        productName.setText(products.getProductName());
        productPrice.setText(products.getProductPrice());
        productDescription.setText(products.getProductDescription());
        productCode.setText(products.getProductCode());
        productQuntity.setText(products.getPrductQuantity());

    }



//}




    //success message
    private void successMessage(){
        Snackbar.make(relativeProduct,"Updated Successfully!",Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(this,ProductActivity.class);
        //startActivity(intent);
        //finish();
        //Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
    }








}