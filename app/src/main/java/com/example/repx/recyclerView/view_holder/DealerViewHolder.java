package com.example.repx.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.repx.R;

public class DealerViewHolder extends RecyclerView.ViewHolder {

    public TextView dealerName;
    public TextView dealerCode;


    public DealerViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
       this.dealerName = itemView.findViewById(R.id.txt_dealer_name_card);
       this.dealerCode = itemView.findViewById(R.id.txt_dealer_code_card);

    }

}
