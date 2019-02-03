package com.example.checkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    public static String prod_name = "";
    static double price     = 0;

    public static String[] temp = {};

    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);


    }

    @Override
    public void handleResult(Result result) {
        // scan result in result.getText
        GetBarcodeDetail barcode = new GetBarcodeDetail();
        barcode.barcode = result.getText();
        barcode.execute();

        MainActivity.resultTextView.setText(prod_name + " " + price);
        temp = new String[] {prod_name};


        Intent intent = new Intent(ScanCodeActivity.this, StockList.class);
        intent.putExtra("list",temp);
        startActivity(intent);


        onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
