package com.example.repx.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.repx.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SaleViewHolder extends RecyclerView.ViewHolder {
    public TextView productName;
    public TextView customer;


    public SaleViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        this.productName = itemView.findViewById(R.id.txt_dealer_code_card);
        this.customer = itemView.findViewById(R.id.txt_dealer_name_card);

    }
}
