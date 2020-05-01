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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrogotPasswordActivity extends AppCompatActivity {

    private TextView txtEmail;
    private Button btnReset;
    private String TAG = "FrogotPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frogot_password);

        this.txtEmail = findViewById(R.id.txt_frogotPasswordEmail);
        this.btnReset = findViewById(R.id.btn_reset);

        this.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });
    }

    private void forgotPassword() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = txtEmail.getText().toString();
        if(emailAddress.isEmpty()){
            txtEmail.setError("Email is required");

        }else{
            if(isEmail(emailAddress)){
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(FrogotPasswordActivity.this,"Check your email to reset the password",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(FrogotPasswordActivity.this,Login.class);
                                    startActivity(intent);
                                    finish();
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });
            }else {
                txtEmail.setError("Enter valid email");
            }
        }
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
