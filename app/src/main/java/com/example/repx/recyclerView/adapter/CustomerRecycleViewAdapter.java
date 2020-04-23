package com.example.repx.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.Customer_Details;
import com.example.repx.EditShops;
import com.example.repx.R;
import com.example.repx.dto.Customer;
import com.example.repx.recyclerView.view_holder.CustomerViewHolder;
import com.google.gson.Gson;
import java.util.List;

public class CustomerRecycleViewAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

    List<Customer> customerList;
    CardView cardView;
    Context context;

    public CustomerRecycleViewAdapter(List<Customer> customerList, Context context) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customer_cardview, viewGroup, false);
        cardView = (CardView) view;
        return new CustomerViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerViewHolder customerViewHolder, final int i) {
        String customerName = customerList.get(i).getShopNameCustomer();
        String customerAddress = customerList.get(i).getAddressCustomer();
        customerViewHolder.customerName.setText(customerName);
        customerViewHolder.customerAddress.setText(customerAddress);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Customer_Details.class);
                intent.putExtra("CUSTOMER",new Gson().toJson(customerList.get(i)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (customerList == null) return 0;
        else
            return customerList.size();
    }
}
