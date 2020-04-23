package com.example.repx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.repx.dto.Dealer;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class DealerProfile extends AppCompatActivity {


    private Dealer dealer;
    private FirebaseFirestore db;
    private EditText dealerName,dealerCode,dealerArea,dealerCategory,dealerphoneNumber;
    private Toolbar toolbar;
    private Button btnEditProfile,btnUpdateProfile,btnProfileDelete;
    private static final String TAG = "DealerProfile";
    static ConstraintLayout container;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dealer_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dealer Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();

        // listener = (DeleteClickOnListener) this;

        this.dealer = new Gson().fromJson(getIntent().getStringExtra("DEALER"),Dealer.class);
        dealerName = findViewById(R.id.txt_dealerName_profile);
        dealerCode = findViewById(R.id.txt_dealer_code_profile);
        dealerArea = findViewById(R.id.txt_dealerArea_profile);
        dealerCategory = findViewById(R.id.txt_dealerCategory_profile);
        dealerphoneNumber = findViewById(R.id.txt_dealerPhoneNumber_profile);
        btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnUpdateProfile = findViewById(R.id.btn_update_profile);
        btnProfileDelete = findViewById(R.id.btn_profile_delete);
        container = findViewById(R.id.profile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledFields();
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        btnProfileDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();

            }
        });

        setDisableFields();
        setDealerProfile();

    }


    private void deleteProfile() {
        db.collection("dealer").document(dealer.getID()).delete();
        Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,DealerActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateProfile() {
        if(!dealer.getName().equals(dealerName.getText().toString()) || !dealer.getCategory().equals(dealerCategory.getText().toString())
                || !dealer.getArea().equals(dealerArea.getText().toString()) || !dealer.getTelePhoneNumber().equals(dealerphoneNumber.getText().toString())){
            dealer.setArea(dealerArea.getText().toString());
            dealer.setName(dealerName.getText().toString());
            dealer.setCategory(dealerCategory.getText().toString());
            dealer.setTelePhoneNumber(dealerphoneNumber.getText().toString());

            Map<String, Object> dealerMap = new HashMap<>();
            dealerMap.put("area",dealer.getArea());
            dealerMap.put("name",dealer.getName());
            dealerMap.put("category",dealer.getCategory());
            dealerMap.put("code",dealer.getCode());
            dealerMap.put("phoneNumber",dealer.getTelePhoneNumber());

            db.collection("dealer").document(dealer.getID()).update(dealerMap);


        }
        successMessage();
        setDisableFields();
    }

    //success message
    private void successMessage(){
        Snackbar.make(container,"Updated Successfully!",Snackbar.LENGTH_LONG).show();
        //Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
    }


    //to disable fields
    private void setDisableFields(){
        dealerName.setEnabled(false);
        dealerCode.setEnabled(false);
        dealerArea.setEnabled(false);
        dealerCategory.setEnabled(false);
        dealerphoneNumber.setEnabled(false);
        btnUpdateProfile.setEnabled(false);
    }

    //to enable fields
    private void setEnabledFields(){
        dealerName.setEnabled(true);
        dealerName.requestFocus();
        dealerArea.setEnabled(true);
        dealerCategory.setEnabled(true);
        dealerphoneNumber.setEnabled(true);
        btnUpdateProfile.setEnabled(true);
    }


    //to set data to dealer profile
    private void setDealerProfile(){
        dealerName.setText(dealer.getName());
        dealerCode.setText(dealer.getCode());
        dealerArea.setText(dealer.getArea());
        dealerCategory.setText(dealer.getCategory());
        dealerphoneNumber.setText(dealer.getTelePhoneNumber());
    }



}


