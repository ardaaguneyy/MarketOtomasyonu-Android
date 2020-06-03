package com.example.marketotomasyonu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity {
EditText nametext,pricetext,itemnumbertext;
SQLiteDatabase database;
String itemname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        //findviewbyid
        nametext = findViewById(R.id.nametextdetail);
        pricetext = findViewById(R.id.pricetextdetail);
        itemnumbertext = findViewById(R.id.itemnumberdetailtext);
        getdata();

    }
    public void edititemonclick(View view){
        String name = nametext.getText().toString();
        double price = Double.parseDouble(pricetext.getText().toString());
        int itemnumber = Integer.parseInt(itemnumbertext.getText().toString());
        if (!name.matches("")&& !pricetext.getText().toString().matches("")&& !pricetext.getText().toString().matches("")&&!itemnumbertext.getText().toString().matches("")){
            try {
                Items items = new Items(itemname,price,database,itemnumber);
                items.uptadedatabase(name);
                Toast.makeText(ItemDetailsActivity.this, "Ürün Kaydı Yapıldı", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(ItemDetailsActivity.this, "Ürün Kaydedilmedi", Toast.LENGTH_SHORT).show();
            }

        }
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();

    }
    public void removeitemonclick(View view){
        Items items = new Items(itemname,1.1,database,4);
        items.deletedatabase();
        Toast.makeText(ItemDetailsActivity.this, "Ürün Silindi", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();


    }
    public void getdata(){
        database = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
        Intent intent = getIntent();
        itemname = intent.getStringExtra("name");
        String output = Character.toUpperCase(itemname.charAt(0)) + itemname.substring(1);
        nametext.setText(output);
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM items WHERE name = ?",  new String[]{ String.valueOf(itemname) }  );
            int priceIx = cursor.getColumnIndex("price");
            int itemnumberIx = cursor.getColumnIndex("itemnumber");
            while (cursor.moveToNext()){
                pricetext.setText(String.valueOf(cursor.getDouble(priceIx)));
                itemnumbertext.setText(String.valueOf(cursor.getInt(itemnumberIx)));
            }

            cursor.close();



        }catch (Exception e ){
            e.printStackTrace();

        }


    }
}
