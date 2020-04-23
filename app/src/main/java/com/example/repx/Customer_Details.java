package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.repx.dto.Customer;
import com.google.gson.Gson;

public class Customer_Details extends AppCompatActivity {

    private Toolbar toolbar;
    private Customer customer;
    private TextView shopName, ownerName, email, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Profile");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.customer = new Gson().fromJson(getIntent().getStringExtra("CUSTOMER"),Customer.class);

        shopName = findViewById(R.id.txt_customer_profile_shopname);
        shopName.setEnabled(false);

        ownerName = findViewById(R.id.txt_customer_profile_ownername);
        ownerName.setEnabled(false);

        email = findViewById(R.id.txt_customer_profile_email);
        email.setEnabled(false);

        phone = findViewById(R.id.txt_customer_profile_phone);
        phone.setEnabled(false);

        address = findViewById(R.id.txt_customer_profile_address);
        address.setEnabled(false);

        setCustomerProfile();
    }

    private void setCustomerProfile(){
        shopName.setText(customer.getShopNameCustomer());
        ownerName.setText(customer.getOwnerNameCustomer());
        email.setText(customer.getEmailCustomer());
        phone.setText(customer.getPhoneCustomer());
        address.setText(customer.getAddressCustomer());
    }

}
