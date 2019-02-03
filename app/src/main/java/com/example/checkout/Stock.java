package com.example.checkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
    }
        public void barcode(View view){
            Intent startNewIntent = new Intent(this, MainActivity.class);
            startActivity(startNewIntent);
        }
        public void printList(View view){
        Intent startNewIntent = new Intent(this, StockList.class);
        startActivity(startNewIntent);
    }
}

