package com.example.repx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    private static final String TAG = "LOGIN ACTIVITY";

    private Button signIn;
    private Button signUp;

    EditText editTextEmail;
    EditText editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.txt_email);
        editTextPassword = findViewById(R.id.txt_password);
        signIn = findViewById(R.id.btn_signin);
        signUp = findViewById(R.id.btn_signup);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(email.isEmpty()){
                    editTextEmail.setError("Email Required!");
                }else{
                    if(password.isEmpty()){
                        editTextPassword.setError("Password Required!");
                    }else{
                        if(password.length() < 6){
                            editTextPassword.setError("Minimum 6 characters required!");
                        }else{
                            emailAndPasswordSignIn(email,password);
                        }
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(email.isEmpty()){
                    editTextEmail.setError("Email Required!");
                }else{
                    if(password.isEmpty()){
                        editTextPassword.setError("Password Required!");
                    }else{
                        if(password.length() < 6){
                            editTextPassword.setError("Minimum 6 characters required!");
                        }else{
                            emailAndPasswordSignUp(email,password);
                        }
                    }
                }
            }
        });

    }


    private void emailAndPasswordSignUp(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this,"Login Failed!,Try again",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private void emailAndPasswordSignIn(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Login Failed!,Try again",Toast.LENGTH_LONG).show();
        }
    }

}
