package com.example.repx;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.repx.dto.Dealer;
import com.example.repx.dto.Product;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class product extends AppCompatActivity {

    private Product products;
    private FirebaseFirestore db;
    private EditText productName, productPrice, productDescription, productCode, productQuntity, productDealer;
    private Toolbar toolbar;
    private Button btnEditProduct,btnUpdateProductProfile;
    static ConstraintLayout container;


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


        System.out.println(new Gson().toJson(this.products));

        productName = findViewById(R.id.product_name_profile);
        productPrice = findViewById(R.id.product_price_profile);
        productDescription = findViewById(R.id.product_description_profile);
        productCode = findViewById(R.id.product_code_profile);
        productQuntity = findViewById(R.id.product_quntity_profile);
        btnEditProduct = findViewById(R.id.btn_edit_profileProduct);
        btnUpdateProductProfile= findViewById(R.id.btn_profile_product_update);
        container = findViewById(R.id.profileProduct);

        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledFields();
            }
        });

        btnUpdateProductProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateProduct();
            }
        });


        setProducts();
        setDisableFields();
    }


    private void updateProduct() {
        if(!products.getProductName().equals(productName.getText().toString()) || !products.getProductPrice().equals(productPrice.getText().toString())
                || !products.getProductDescription().equals(productDescription.getText().toString()) || !products.getProductCode().equals(productCode.getText().toString())
                || !(products.getPrductQuantity().equals(productQuntity.getText().toString()))){


            System.out.println("===================== Running");

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
    setDisableFields();

}


    private void successMessage(){
        Snackbar.make(container,"Updated Successfully!",Snackbar.LENGTH_LONG).show();
        //Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
    }

   private void setProducts(){
       productName.setText(products.getProductName());
       productPrice.setText(products.getProductPrice());
       productDescription.setText(products.getProductDescription());
       productCode.setText(products.getProductCode());
       productQuntity.setText(productQuntity.getText().toString());
    }

    private void setDisableFields(){
        productName.setEnabled(false);
        productPrice.setEnabled(false);
        productDescription.setEnabled(false);
        productCode.setEnabled(false);
        productQuntity.setEnabled(false);
        btnUpdateProductProfile.setEnabled(false);

    }

    //to enable fields
    private void setEnabledFields(){
        productName.setEnabled(true);
        productName.requestFocus();
        productPrice.setEnabled(true);
        productDescription.setEnabled(true);
        productQuntity.setEnabled(true);
        btnUpdateProductProfile.setEnabled(true);

    }

/*
    //to set data to dealer profile
  private void setProductspage(){
        productName.setText(products.getProductName());
        productPrice.setText(products.getProductPrice());
        productDescription.setText(products.getProductDescription());
        productCode.setText(products.getProductCode());
        productQuntity.setText(productQuntity.getText().toString());
    }
    */

}