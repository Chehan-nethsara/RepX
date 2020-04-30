package com.example.repx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private TextView txtName,txtRegNo,txtEmail;
    private Button btnRegister;

    String email,name,regNo;

    private FirebaseFirestore db;

    private final static String TAG = "Signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user_register);

        this.txtName = findViewById(R.id.txt_name);
        this.txtRegNo = findViewById(R.id.txt_regNo);
        this.txtEmail = findViewById(R.id.txt_email);
        this.btnRegister = findViewById(R.id.btn_register);

        email = getIntent().getStringExtra("EMAIL");
        name = getIntent().getStringExtra("NAME");

        db = FirebaseFirestore.getInstance();

        this.txtEmail.setText(email);
        this.txtEmail.setEnabled(false);
        this.txtName.setText(name);


        db.collection("Users").orderBy("RegNo", Query.Direction.DESCENDING).limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult().getDocuments().get(0);
                    regNo = document.get("RegNo").toString();
                    System.out.println("REG NO : " + regNo);
                    int regNumber = Integer.parseInt(regNo.substring(0,6));
                    ++regNumber;

                    String temp = String.valueOf(regNumber);
                    int noOfLeadingZeros = 6 - temp.length();
                    String format = "%0" + String.valueOf(noOfLeadingZeros) + "d";

                    System.out.println("Format : " + format);

                    txtRegNo.setText(String.format(format,regNumber)+"U");

                    txtRegNo.setEnabled(false);

                }
            }
        });




        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    private void register(){
        if(validation()){

            name = txtName.getText().toString();

            Map<String, Object> user = new HashMap<>();
            user.put("Full Name",name);
            user.put("Email",email);
            user.put("RegNo",regNo);


            // Add a new document with a generated ID
            db.collection("Users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            showSuccessToast();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showSFailedToast();
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

        }


        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void showSuccessToast() {
        Toast.makeText(this,"Registration Success!",Toast.LENGTH_LONG).show();
    }

    private void showSFailedToast() {
        Toast.makeText(this,"Registration Failed!",Toast.LENGTH_LONG).show();
    }


    private boolean validation(){
        boolean flag = true;
        if(txtName.getText().toString().isEmpty()){
            txtName.setError("Name is required!");
            flag = false;
        }
        return flag;
    }
}
