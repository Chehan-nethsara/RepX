package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.repx.dto.Customer;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class EditCustomerUI extends AppCompatActivity {

    private Context context;
    private Customer customer;
    private FirebaseFirestore db;
    private EditText txt_upCus_ShopName, txt_upCus_OwnerName, txt_upCus_Phone, txt_upCus_Email, txt_upCus_Address;
    private Toolbar toolbar;
    private Button btn_upCus_UpdateCustomer;
    static RelativeLayout relativeCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer_u_i);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Update");

        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();

        this.customer = new Gson().fromJson(getIntent().getStringExtra("CUSTOMER"), Customer.class);

        txt_upCus_ShopName = findViewById(R.id.txt_shopname_customer_update);
        txt_upCus_ShopName.setEnabled(true);

        txt_upCus_OwnerName = findViewById(R.id.txt_ownername_customer_update);
        txt_upCus_OwnerName.setEnabled(true);

        txt_upCus_Phone = findViewById(R.id.txt_phone_customer_update);
        txt_upCus_Phone.setEnabled(true);

        txt_upCus_Email = findViewById(R.id.txt_email_customer_update);
        txt_upCus_Email.setEnabled(true);

        txt_upCus_Address = findViewById(R.id.txt_address_customer_update);
        txt_upCus_Address.setEnabled(true);

        btn_upCus_UpdateCustomer = findViewById(R.id.btn_updateCustomer);
        relativeCustomer = findViewById(R.id.updateCustomerProfile);

        btn_upCus_UpdateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerProfileUpdate();
            }
        });

        setCustomerUpdateProfile();
    }

    private void CustomerProfileUpdate(){
        if(!customer.getShopNameCustomer().equals(txt_upCus_ShopName.getText().toString()) ||
            !customer.getOwnerNameCustomer().equals(txt_upCus_OwnerName.getText().toString()) ||
            !customer.getPhoneCustomer().equals(txt_upCus_Phone.getText().toString()) ||
            !customer.getEmailCustomer().equals(txt_upCus_Email.getText().toString()) ||
            !customer.getAddressCustomer().equals(txt_upCus_Address.getText().toString()))
        {
            customer.setShopNameCustomer(txt_upCus_ShopName.getText().toString());
            customer.setOwnerNameCustomer(txt_upCus_OwnerName.getText().toString());
            customer.setPhoneCustomer(txt_upCus_Phone.getText().toString());
            customer.setAddressCustomer(txt_upCus_Address.getText().toString());
            customer.setEmailCustomer(txt_upCus_Email.getText().toString());

            Map<String, Object> customerMap = new HashMap<>();

            customerMap.put("ShopName", customer.getShopNameCustomer());
            customerMap.put("OwnerName", customer.getOwnerNameCustomer());
            customerMap.put("PhoneNumber", customer.getPhoneCustomer());
            customerMap.put("EmailAddress", customer.getEmailCustomer());
            customerMap.put("PostalAddress", customer.getAddressCustomer());

            db.collection("Customer").document(customer.getCustomerDocumentID()).update(customerMap);
        }
        successUpdateCustomerMsg();
    }
    //to set data to customer update
   private void setCustomerUpdateProfile(){
       txt_upCus_ShopName.setText(customer.getShopNameCustomer());
       txt_upCus_OwnerName.setText(customer.getOwnerNameCustomer());
       txt_upCus_Phone.setText(customer.getPhoneCustomer());
       txt_upCus_Email.setText(customer.getEmailCustomer());
       txt_upCus_Address.setText(customer.getAddressCustomer());

    }

    //success message
    private void successUpdateCustomerMsg() {
        Snackbar.make(relativeCustomer,"Customer Updated Successfully!",Snackbar.LENGTH_LONG).show();
    }
}
