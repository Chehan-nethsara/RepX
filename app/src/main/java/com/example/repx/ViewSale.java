package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.repx.dto.Sale;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class ViewSale extends AppCompatActivity {

    private Sale sale;
    private FirebaseFirestore db;
    private static final String TAG = "ViewSale";
    private EditText product,qty,saleCustomer,discount,total;
    private Toolbar toolbar;
    private Button editSale, deleteSale, saveSale;
    static LinearLayout containerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sale);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sale Details");
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = FirebaseFirestore.getInstance();

        this.sale = new Gson().fromJson(getIntent().getStringExtra("SALE"),Sale.class);
        product = findViewById(R.id.viewSale_product);
        qty = findViewById(R.id.viewSale_qty);
        saleCustomer = findViewById(R.id.viewSale_customer);
        discount = findViewById(R.id.viewSale_date);
        total = findViewById(R.id.viewSale_total);
        editSale = findViewById(R.id.edit_sale);
        deleteSale = findViewById(R.id.delete_sale);
        saveSale = findViewById(R.id.save_sale);
        containerView = findViewById(R.id.viewSaleProfile);


        product.setEnabled(false);
        qty.setEnabled(false);
        saleCustomer.setEnabled(false);
        discount.setEnabled(false);
        total.setEnabled(false);
        setSaleDetails();

        editSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledFieldsView();
            }
        });

        saveSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveViewSale();
            }
        });

        deleteSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteViewSale();
            }
        });

        setDisableFieldsView();
        setSaleDetails();

    }
    private void deleteViewSale() {
        db.collection("Sales").document(sale.getId()).delete();
        Toast.makeText(this,"Deleted Successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SalesMenu.class);
        startActivity(intent);
        finish();
    }

    private void saveViewSale() {
        if(!sale.getProduct().equals(product.getText().toString()) || !sale.getQty().equals(qty.getText().toString())
                || !sale.getCustomer().equals(saleCustomer.getText().toString())
                || !sale.getDiscout().equals(discount.getText().toString()) || !sale.getTotal().equals(total.getText().toString())){
            sale.setCustomer(saleCustomer.getText().toString());
            sale.setDiscout(discount.getText().toString());
            sale.setProduct(product.getText().toString());
            sale.setQty(qty.getText().toString());
            sale.setTotal(total.getText().toString());


            Map<String, Object> salesMap = new HashMap<>();
            salesMap.put("customer",sale.getCustomer());
            salesMap.put("discount",sale.getDiscout());
            salesMap.put("product",sale.getProduct());
            salesMap.put("qty",sale.getQty());
            salesMap.put("total",sale.getTotal());

            db.collection("Sales").document(sale.getId()).update(salesMap);




        }
        successMessage();
        setDisableFieldsView();
    }

    //success message
    private void successMessage(){
        Snackbar.make(containerView,"Saved Successfully!",Snackbar.LENGTH_LONG).show();
        Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();
    }
    //to disable fields
    private void setDisableFieldsView(){
        product.setEnabled(false);
        qty.setEnabled(false);
        saleCustomer.setEnabled(false);
        discount.setEnabled(false);
        total.setEnabled(false);
        saveSale.setEnabled(false);
    }

    //to enable fields
    private void setEnabledFieldsView(){
        product.setEnabled(true);
        qty.setEnabled(true);
        saleCustomer.setEnabled(true);
        discount.setEnabled(true);
        total.setEnabled(true);
        saveSale.setEnabled(true);
    }


    private void setSaleDetails(){
        product.setText(sale.getProduct());
        qty.setText(sale.getQty());
        saleCustomer.setText(sale.getCustomer());
        discount.setText(sale.getDiscout());
        total.setText(sale.getTotal());
    }
}
