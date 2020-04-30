package com.example.repx.recyclerView.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.AddShop;
import com.example.repx.Customer_Details;
import com.example.repx.EditCustomerUI;
import com.example.repx.R;
import com.example.repx.dto.Customer;
import com.example.repx.recyclerView.view_holder.CustomerViewHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import java.util.List;

public class CustomerRecycleViewAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

    private final List<Customer> customerList;
    private CardView cardView;
    private Context context;
    FirebaseFirestore db;
    CustomerListner customerListner;

    public CustomerRecycleViewAdapter(List<Customer> customerList, Context context) {
        this.context = context;
        this.customerList = customerList;
        this.db = FirebaseFirestore.getInstance();
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

        customerViewHolder.btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMessageCustomer(i);
            }
        });

        customerViewHolder.btnEditCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditCustomerUI.class);
                intent.putExtra("CUSTOMER",new Gson().toJson(customerList.get(i)));
                context.startActivity(intent);

            }
        });

//        customerViewHolder.btn_Add_New_Customer_New.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AddShop.class);
//                context.startActivity(intent);
//            }
//        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Customer_Details.class);
                intent.putExtra("CUSTOMER",new Gson().toJson(customerList.get(i)));
                context.startActivity(intent);
            }
        });
    }


    private void alertMessageCustomer(final int i) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Warning Message");
        builder.setMessage("Are you sure you want to permanently delete?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        db.collection("Customer").document(customerList.get(i).getCustomerDocumentID()).delete();
                        customerListner.removeCustomer(i);

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

       try {
            customerListner = (CustomerListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CustomerListner ");
        }
    }

    @Override
    public int getItemCount()
    {
        return customerList == null ? 0 : customerList.size();
    }

    public interface CustomerListner { void removeCustomer(int i);}
}
