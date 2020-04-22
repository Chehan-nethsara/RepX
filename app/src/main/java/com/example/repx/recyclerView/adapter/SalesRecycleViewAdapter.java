package com.example.repx.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.repx.AddSales;
import com.example.repx.R;
import com.example.repx.ViewSale;
import com.example.repx.dto.Sale;
import com.example.repx.recyclerView.view_holder.SaleViewHolder;
import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SalesRecycleViewAdapter extends RecyclerView.Adapter<SaleViewHolder> {
    List<Sale> saleList;
    CardView cardView;
    Context context;

    public SalesRecycleViewAdapter(List<Sale> saleList, Context context) {
        this.context = context;
        this.saleList = saleList;

    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dealer_cardview, viewGroup, false);
        cardView = (CardView) view;
        return new SaleViewHolder(view, context);
    }



    @Override
    public void onBindViewHolder(@NonNull final SaleViewHolder saleViewHolder, final int i) {
        String product = saleList.get(i).getProduct();
        String customer = saleList.get(i).getCustomer();
        saleViewHolder.productName.setText("Product : " +  product);
        saleViewHolder.customer.setText("Customer : " + customer);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewSale.class);
                intent.putExtra("SALE",new Gson().toJson(saleList.get(i)));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        if (saleList == null) return 0;
        else
            return saleList.size();
    }
}
