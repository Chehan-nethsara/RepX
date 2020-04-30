package com.example.repx.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {


    public TextView productName, productPrice;
    public ImageView productImage, imgSelectedProductImageProfile;
    public Button editProductButton;
    public Button deleteProductButton;

    public ProductViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        this.productName = itemView.findViewById(R.id.txt_productName);
        this.productPrice = itemView.findViewById(R.id.txt_productPrice);
        this.productImage = itemView.findViewById(R.id.img_productView);
        this.imgSelectedProductImageProfile = itemView.findViewById(R.id.txt_product_profile_image);
        this.editProductButton = itemView.findViewById(R.id.btn_editProduct);
        this.deleteProductButton = itemView.findViewById(R.id.btn_deleteProduct);
    }

}
