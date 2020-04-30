package com.example.repx.recyclerView.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.repx.DealerProfile;
import com.example.repx.R;
import com.example.repx.dto.Dealer;
import com.example.repx.dto.Product;
import com.example.repx.product;
import com.example.repx.productProfile;
import com.example.repx.recyclerView.view_holder.DealerViewHolder;
import com.example.repx.recyclerView.view_holder.ProductViewHolder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    List<Product> productList;
    CardView cardView;
    Context context;
    FirebaseFirestore db;
    ProductListner productListner;
    private Product products;
    static ConstraintLayout container;

    public ProductRecycleViewAdapter(List<Product> products, Context context) {
        this.context = context;
        this.productList = products;
        this.db = FirebaseFirestore.getInstance();
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
        String productPrice = productList.get(i).getProductPrice();
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
        productViewHolder.productPrice.setText(productPrice);
        productViewHolder.editProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, product.class);

                System.out.println("============================ " + productList.get(i).getProductPrice());

                intent.putExtra("PRODUCT",new Gson().toJson(productList.get(i)));
                context.startActivity(intent);
            }
        });

        productViewHolder.deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMessage(i);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, productProfile.class);
                intent.putExtra("PRODUCT",new Gson().toJson(productList.get(i)));
                context.startActivity(intent);
            }
        });

    }



    private void alertMessage(final int i) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Alert");
        builder.setMessage("Do You Want To Delete This Product?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        db.collection("product").document(productList.get(i).getProductDocumnetID()).delete();
                        productListner.removeProduct(i);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        try {
            productListner = (ProductListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ProductListner ");
        }

    }

    @Override
    public int getItemCount() {
        if (productList == null) return 0;
        else
            return productList.size();
    }

    public interface ProductListner {
        void removeProduct(int i);
    }







}

