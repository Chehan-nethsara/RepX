package com.example.repx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    private CardView cardViewDealer;
    private CardView cardViewProduct;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cardViewDealer = (CardView) findViewById(R.id.cardView_dealer);
        cardViewProduct=(CardView) findViewById(R.id.cardView_product);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);

        cardViewDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DealerActivity.class);
                startActivity(intent);
            }
        });
//
        cardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
