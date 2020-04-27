package com.example.repx.recyclerView.view_holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repx.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView userName;
    public TextView userType;
    public ImageView imgUserProfileImage;
    public Button btnDeleteUser;

    public UserViewHolder(@NonNull final View itemView, final Context context) {
        super(itemView);
        this.userName = itemView.findViewById(R.id.txt_userDetailsUsername);
        this.userType = itemView.findViewById(R.id.txt_userDetailsUserType);
        this.imgUserProfileImage = itemView.findViewById(R.id.img_userDetailsProfile);
        this.btnDeleteUser = itemView.findViewById(R.id.btn_deleteUser);
    }

}
