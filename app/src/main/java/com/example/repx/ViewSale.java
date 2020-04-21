package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.repx.dto.Dealer;
import com.example.repx.dto.Sale;
import com.google.gson.Gson;

public class ViewSale extends AppCompatActivity {
    private Sale sale;

    private TextView product,qty,customer,date,total;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sale);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Details");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.sale = new Gson().fromJson(getIntent().getStringExtra("SALE"),Sale.class);
        product = findViewById(R.id.viewSale_product);
        qty = findViewById(R.id.viewSale_customer);
        customer = findViewById(R.id.viewSale_qty);
        date = findViewById(R.id.viewSale_date);
        total = findViewById(R.id.viewSale_total);

        product.setEnabled(false);
        qty.setEnabled(false);
        customer.setEnabled(false);
        date.setEnabled(false);
        total.setEnabled(false);

        setSaleDetails();
    }
    private void setSaleDetails(){
        product.setText(sale.getProduct());
        qty.setText(sale.getQty());
        customer.setText(sale.getCustomer());
        //date.setText(sale.());
        total.setText(sale.getTotal());
    }
}
