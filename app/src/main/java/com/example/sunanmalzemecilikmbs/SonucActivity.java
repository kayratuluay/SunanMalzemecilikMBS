package com.example.sunanmalzemecilikmbs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SonucActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "tablo.db";
    public final static String DATABASE_PATH = "/data/data/com.example.sunanmalzemecilikmbs/databases/";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        AlertDialog.Builder kontrol = new AlertDialog.Builder(SonucActivity.this);
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);

        Bundle extras = getIntent().getExtras();
        String kimlik = null;

        if (extras != null) {
            kimlik = extras.getString("musteri_kimlik");
            kimlik = kimlik.toUpperCase();
        }

        final TextView sonuc_header = (TextView) findViewById(R.id.musteriId);
        final ListView liste = (ListView) findViewById(R.id.listview);

        ArrayList bilgiler = new ArrayList();
        ArrayAdapter adapter = new ArrayAdapter(SonucActivity.this,android.R.layout.simple_spinner_dropdown_item,bilgiler);

        String[] musteri_id = {kimlik};
        Cursor c = db.rawQuery("SELECT * FROM Table31 WHERE _id = ?", musteri_id);


        if (c != null && c.moveToFirst()) {

            sonuc_header.setText(kimlik);

            bilgiler.add("AD SOYAD : "+ c.getString(c.getColumnIndex("Muhatap_adı")));
            bilgiler.add("TELEFON : " + c.getString(c.getColumnIndex("Telefon")));
            bilgiler.add("BÖLGE : " + c.getString(c.getColumnIndex("Bölge")));
            bilgiler.add("ÖDEME KOŞULU : " + c.getString(c.getColumnIndex("ÖDEME_KOŞULU")));
            bilgiler.add("AÇIK HESAP DURUMU : " + c.getString(c.getColumnIndex("AÇIK_HESAP_DURUMU")));

            liste.setAdapter(adapter);

        }
        else{

            kontrol.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    openMainActivity();
                }
            });
            kontrol.setTitle("MÜŞTERİ BULUNAMADI...");
            kontrol.setMessage("Ana ekrana dönmek için 'OK' e basın");
            kontrol.show();

        }

        c.close();
        db.close();

    }

    public void openMainActivity(){
        Intent intent = new Intent(SonucActivity.this, MainActivity.class);
        startActivity(intent);
    }


}