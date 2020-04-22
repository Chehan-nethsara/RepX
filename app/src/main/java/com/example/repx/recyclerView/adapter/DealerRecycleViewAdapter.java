package com.example.repx.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.DealerProfile;
import com.example.repx.R;
import com.example.repx.dto.Dealer;
import com.example.repx.recyclerView.view_holder.DealerViewHolder;
import com.google.gson.Gson;

import java.util.List;

public class DealerRecycleViewAdapter extends RecyclerView.Adapter<DealerViewHolder> {

    List<Dealer> dealerList;
    CardView cardView;
    Context context;

    public DealerRecycleViewAdapter(List<Dealer> dealerList, Context context) {
        this.context = context;
        this.dealerList = dealerList;

    }
    @NonNull
    @Override
    public DealerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dealer_cardview, viewGroup, false);
        cardView = (CardView) view;
        return new DealerViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final DealerViewHolder dealerViewHolder, final int i) {
        String name = dealerList.get(i).getName();
        String code = dealerList.get(i).getCode();
        dealerViewHolder.dealerName.setText("Name : " +  name);
        dealerViewHolder.dealerCode.setText("Code : " + code);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DealerProfile.class);
            intent.putExtra("DEALER",new Gson().toJson(dealerList.get(i)));
            context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (dealerList == null) return 0;
        else
            return dealerList.size();
    }
}
