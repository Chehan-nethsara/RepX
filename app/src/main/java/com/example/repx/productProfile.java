package com.example.repx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.repx.dto.Customer;
import com.example.repx.dto.Product;
import com.example.repx.recyclerView.view_holder.ProductViewHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productProfile extends AppCompatActivity  {

   private Toolbar toolbar;
    List<Product> productList;
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

    public void onBindViewHolder(@NonNull final ProductViewHolder productViewHolder, final int i) {

        //String productName = productList.get(i).getProductName();
        //String productPrice = productList.get(i).getProductPrice();
        String profileProductCode = productList.get(i).getProductCode();



        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/products/"+profileProductCode.trim());

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                Picasso.get().load(uri).into(productViewHolder.imgSelectedProductImageProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


    }


}
