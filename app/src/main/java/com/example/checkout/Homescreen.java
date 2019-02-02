package com.example.checkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Homescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }
    public void barcode(View view){
        Intent startNewIntent = new Intent(this, MainActivity.class);
        startActivity(startNewIntent);
    }
    public void stock(View view) {
        Intent startNewIntent = new Intent(this, Stock.class);
        startActivity(startNewIntent);
    }

    public void missing(View view){
        Intent startNewIntent = new Intent(this, Missing.class);
        startActivity(startNewIntent);
    }
}

