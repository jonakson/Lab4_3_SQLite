package com.example.jcalzado.lab4_3_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReadAll extends AppCompatActivity {

    private ListView recordList;
    private ArrayList<String> list;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all);

        recordList = (ListView) findViewById(R.id.recordList);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);
        SQLiteDatabase database = admin.getReadableDatabase();
        Cursor row = database.rawQuery("select code,description,price from articles" , null);
        list = new ArrayList<>();
        if (row.moveToFirst()) {
            do {
                list.add(row.getString(0) + ": " + row.getString(1) + " (" + row.getString(2) + "€)");
            } while (row.moveToNext());
        }


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        recordList.setAdapter(adapter);

        database.close();
    }
}
