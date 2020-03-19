package com.example.repx;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;

public class view_product extends AppCompatActivity {

    SearchView mySearchView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);


        mySearchView=(SearchView)findViewById(R.id.searchView);
    }
}
