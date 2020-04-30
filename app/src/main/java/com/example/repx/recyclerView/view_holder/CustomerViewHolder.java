package com.example.repx.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.repx.R;


public class CustomerViewHolder extends RecyclerView.ViewHolder {

    public TextView customerName;
    public TextView customerAddress;
    public Button btnEditCustomer;
    public Button btnDeleteCustomer;
   // public Button btn_Add_New_Customer_New;


    public CustomerViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        this.customerName = itemView.findViewById(R.id.txt_customer_card_1);
        this.customerAddress = itemView.findViewById(R.id.txt_customer_card_2);
        this.btnDeleteCustomer = itemView.findViewById(R.id.btn_deleteCustomer);
        this.btnEditCustomer = itemView.findViewById(R.id.btn_editCustomer);
        //this.btn_Add_New_Customer_New = itemView.findViewById(R.id.btn_add_New_cudtomer_ui);

    }

}
