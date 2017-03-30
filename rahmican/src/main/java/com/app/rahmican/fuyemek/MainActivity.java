package com.app.rahmican.fuyemek;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends Activity {


    public ArrayList<String> YemekListesi = new ArrayList<String>();

    public ArrayAdapter<String> adapter;


    ListView lv1;
    Document doc;

    String url = "http://uevi.firat.edu.tr/tr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 =(ListView) findViewById(R.id.Lv);
        if(InternetKontrol()){ //internet kontrol methodu çağırılıyor
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,YemekListesi);
            new Download().execute();
        }else{
            Toast.makeText(getApplicationContext(), "İnternet Yok!", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean InternetKontrol() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable() && manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.developer:
                Toast.makeText(getApplicationContext(), "Rahmican Büyükyekdeli ", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public class Download  extends  AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {



            try
            {


                YemekListesi.clear();
                doc = Jsoup.connect(url).get();
                Elements options = doc.select("strong");
                for(Element option:options)
                {
                    YemekListesi.add(option.text()+"\n");
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);



        }

        @Override
        protected void onPostExecute(String s) {
                super.onPostExecute(s);

            lv1.setAdapter(adapter);

        }
    }
}

