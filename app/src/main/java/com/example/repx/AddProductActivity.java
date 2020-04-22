package com.example.repx;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private FirebaseFirestore db;
    private final static String TAG = "add_product";
    private EditText productName, productPrice, productDescription, productCode, productQuntity, productDealer;
    private Button addNewProductButton,btnSelectProductImage;
    private ImageView imgSelectedProductImage;
    private Uri imagePath;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        btnSelectProductImage = findViewById(R.id.btn_selectProductImage);
        imgSelectedProductImage = findViewById(R.id.img_selectedProductImage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        productName  = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        productCode = findViewById(R.id.product_code);
        productQuntity = findViewById(R.id.product_quntity);
        productDealer = findViewById(R.id.product_dealer);
        toolbar.setTitle("Add New Product");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addNewProductButton = findViewById(R.id.btn_addNewProduct);

        btnSelectProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndroidVersion();
            }
        });

        addNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProduct();
            }
        });

    }

    private void addNewProduct(){

        String name, price, description, code;
        Double quntity = 0.0;

        name = productName.getText().toString();
        price = productPrice.getText().toString();
        description = productDescription.getText().toString();
        code = productCode.getText().toString();
        boolean flag = true;
        if (name.isEmpty()) {
            flag = false;
            productName.setError("Name is required!");
        }
        if (price.isEmpty()) {
            flag = false;
            productPrice.setError("Price is required");
        }
        if (description.isEmpty()) {
            flag = false;
            productDescription.setError("Description is required");
        }
        if (code.isEmpty()) {
            flag = false;
            productCode.setError("Code is required");
        }

        if (productQuntity.getText().toString().isEmpty()) {
            flag = false;
            productQuntity.setError("Quantity is required");
        }else{
            if(!productQuntity.getText().toString().matches("[+-]?([0-9]*[.])?[0-9]+")){
                flag = false;
                productQuntity.setError("Quantity must be a number");
            }else{
                quntity = Double.valueOf(productQuntity.getText().toString());
            }
        }


        if(flag){
            Map<String, Object> product = new HashMap<>();
            product.put("name", name);
            product.put("price", price);
            product.put("description", description);
            product.put("code", code);
            product.put("quntity", quntity);


            // Add a new document with a generated ID
            db.collection("product")
                    .add(product)
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

            if(imagePath != null){
                uploadImage();
            }

        }
    }

    private void showSuccessToast(){
        Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    // adding image functionalist


    public void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            } catch (Exception e) {

            }
        } else {
            pickImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            checkAndroidVersion();
        }
    }

    //PICK IMAGE METHOD
    public void pickImage() {
        CropImage.startPickImageActivity(this);
    }

    //CROP REQUEST JAVA
//    private void croprequest(Uri imageUri) {
//        CropImage.activity(imageUri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(this);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //RESULT FROM SELECTED IMAGE
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            imagePath = imageUri;
            System.out.println("Image Path : " + imageUri.getPath());
            Picasso.get().load(imageUri).into(imgSelectedProductImage);

            //croprequest(imageUri);
        }

//        //RESULT FROM CROPING ACTIVITY
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                imagePath = result.getUri();
//                System.out.println("IMAGE PATH ======== " + imagePath);
//                Picasso.get().load(imagePath).into(imgSelectedProductImage);
//            }
//        }
    }


    private void uploadImage(){
        StorageReference storageRef = storage.getReference();
        StorageReference riversRef = storageRef.child("products/"+productCode.getText().toString());
        UploadTask uploadTask = riversRef.putFile(imagePath);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }



}



