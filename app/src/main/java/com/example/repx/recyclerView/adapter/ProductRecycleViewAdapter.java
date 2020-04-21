package com.example.repx.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.repx.DealerProfile;
import com.example.repx.R;
import com.example.repx.dto.Dealer;
import com.example.repx.dto.Product;
import com.example.repx.recyclerView.view_holder.DealerViewHolder;
import com.example.repx.recyclerView.view_holder.ProductViewHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> productList;
    CardView cardView;
    Context context;

    public ProductRecycleViewAdapter(List<Product> products, Context context) {
        this.context = context;
        this.productList = products;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_product_card, viewGroup, false);
        cardView = (CardView) view;
        return new ProductViewHolder(view, context);
    }



    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder productViewHolder, final int i) {

        String productName = productList.get(i).getProductName();
        String productCode = productList.get(i).getProductCode();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("/products/"+productCode.trim());

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                // Pass it to Picasso to download, show in ImageView and caching
                Picasso.get().load(uri).into(productViewHolder.productImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        productViewHolder.productName.setText(productName);

    }

    @Override
    public int getItemCount() {
        if (productList == null) return 0;
        else
            return productList.size();
    }
}

