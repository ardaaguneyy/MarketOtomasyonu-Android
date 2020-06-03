package com.example.marketotomasyonu;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Items {
    String name;
    Double price;
    int itemnumber;
    SQLiteDatabase database;
    public Items(String name, Double price,SQLiteDatabase database,int itemnumber) {
        this.name = name;
        this.price = price;
        this.database = database;
        this.itemnumber = itemnumber;
    }


    public  void  adddatabase(){
        try {
            name = name.toLowerCase();
            double finalprice = price;
            database.execSQL("CREATE TABLE IF NOT EXISTS items (id INTEGER PRIMARY KEY, name VARCHAR, price REAL, itemnumber INTEGER)");
            String sqlitestring = "INSERT INTO items (name,price,itemnumber) VALUES (?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlitestring);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindDouble(2,finalprice);
            sqLiteStatement.bindLong(3,itemnumber);
            sqLiteStatement.execute();
        }catch (Exception e){

        }

    }
    public void deletedatabase(){
        try {
            name = name.toLowerCase();
            String sqlitestringdelete = "DELETE FROM items WHERE name = ? ";
            SQLiteStatement statement = database.compileStatement(sqlitestringdelete);
            statement.bindString(1,name);
            statement.execute();
        }catch (Exception e){

        }

    }
    public void uptadedatabase(String newname){
        try {
            name = name.toLowerCase();
            newname = newname.toLowerCase();
            double finalprice = price;
            String sqlitestringuptade = " UPDATE items SET name = ? , price = ? , itemnumber = ? WHERE name = ?";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlitestringuptade);
            sqLiteStatement.bindString(1,newname);
            sqLiteStatement.bindDouble(2,finalprice);
            sqLiteStatement.bindLong(3,itemnumber);
            sqLiteStatement.bindString(4,name);
            sqLiteStatement.execute();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage().toString());
        }


    }

}
