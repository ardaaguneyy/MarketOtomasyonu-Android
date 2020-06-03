package com.example.marketotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemactivity extends AppCompatActivity {
EditText nametext;
EditText pricetext;

EditText itemnumbertext;
SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_itemactivity);
        nametext = findViewById(R.id.addnametext);
        pricetext = findViewById(R.id.addpricetext);
        itemnumbertext= findViewById(R.id.itemnumberaddtext);
        database = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
    }
    public void additemonclick(View view){
        String name = nametext.getText().toString();
        double price = Double.parseDouble(pricetext.getText().toString());
        int itemnumber = Integer.parseInt(itemnumbertext.getText().toString());
        if (!name.matches("")&& !pricetext.getText().toString().matches("")&& !pricetext.getText().toString().matches("")&&!itemnumbertext.getText().toString().matches("")){
        try {
            Items items = new Items(name,price,database,itemnumber);
            items.adddatabase();
            Toast.makeText(AddItemactivity.this, "Ürün Eklendi", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(AddItemactivity.this, "Ürün Eklenemedi", Toast.LENGTH_SHORT).show();
        }

    }
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
