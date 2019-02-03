package com.example.checkout;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarException;

public class GetBarcodeDetail extends AsyncTask<Void,Void,Void> {
    String data="";
    String barcode = "";
    String name = "";
    String currency = "";
    double price_raw = 0;
    static double price_cad = 0;
    static double rate = 0;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("http://www.searchupc.com/handlers/upcsearch.ashx?request_type=1&access_token=0B1DDBB6-99BD-49CA-AD35-A398BF6F5B42&upc=" + barcode);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line ="";

            line = bufferedReader.readLine();   // col headers
            line = bufferedReader.readLine();   // data - only one row's worth

            line = line.replace("\"", "");
            data = line;
            String[] daddy = data.split(",");       // hussain told be the plural for data was daddy

            name = daddy[0];

            if (!(name.equals(" ") || name.equals("")))
            {
                try
                {
                    price_raw = Float.parseFloat(daddy[3]);
                }
                catch (NumberFormatException e)
                {
                    price_raw = 0;
                }
                currency = daddy[4];

//                if (!(price_raw == 0 || currency.equals("N/A")))
//                {
//                    Exchange rate = new Exchange();
//                    rate.from = currency;
//                    rate.execute();
//                    price_cad = price_raw * rate.rate;
//                }
            }

        } catch (MalformedURLException e){
            data = "error with URL";
            e.printStackTrace();
        } catch (IOException e) {
            data = "Error with URL";
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.resultTextView.setText(this.name + " " + this.price_raw);
        //MainActivity.prod_name = this.name;
        //MainActivity.prod_price = this.price_raw;
        ScanCodeActivity.prod_name = this.name;
        ScanCodeActivity.price = this.price_raw;

    }
}
