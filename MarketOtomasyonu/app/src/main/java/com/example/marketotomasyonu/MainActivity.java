package com.example.marketotomasyonu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
SQLiteDatabase database;
EditText searchtext;
ListView listView;
ArrayList<String> itemnamesarray;
ArrayAdapter adapter ;
ArrayList<Integer> itemnumbersarray;
ArrayList<String> listviewarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchtext= findViewById(R.id.searchnametext);
        listView = findViewById(R.id.listview);
        //arrays
        itemnumbersarray = new ArrayList<>();
        itemnamesarray = new ArrayList<>();
        listviewarray = new ArrayList<>();
        //database
        database = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
        getdata();
        for (int i=0; i<itemnamesarray.size(); i++){
            String itemname = itemnamesarray.get(i);
            String output = Character.toUpperCase(itemname.charAt(0)) + itemname.substring(1) + "    " + itemnumbersarray.get(i).toString()+" Adet";
            listviewarray.add(output);

        }
        adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,listviewarray);
        listView.setAdapter(adapter);
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MainActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(),ItemDetailsActivity.class);
            intent.putExtra("name",itemnamesarray.get(position));
            startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== R.id.additem){
            Intent intent = new Intent(getApplicationContext(),AddItemactivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
    public void  getdata(){
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM items",null);
            int  nameIx = cursor.getColumnIndex("name");
            int numberIx = cursor.getColumnIndex("itemnumber");
            while (cursor.moveToNext()) {
            itemnamesarray.add(cursor.getString(nameIx));
            itemnumbersarray.add(cursor.getInt(numberIx));
            }
            cursor.close();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage().toString());
        }

    }


}
