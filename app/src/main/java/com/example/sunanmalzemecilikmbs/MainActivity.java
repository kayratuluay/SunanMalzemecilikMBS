package com.example.sunanmalzemecilikmbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        final TextView musteri_arama = (TextView) findViewById(R.id.musteriIdSearch);
        final Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String hedef = musteri_arama.getText().toString();
                openSonucActivity(hedef);
            }
        });

    }

    public void openSonucActivity(String musteri_id) {
        Intent intent = new Intent(MainActivity.this, SonucActivity.class);
        intent.putExtra("musteri_kimlik",musteri_id);
        startActivity(intent);
    }
}