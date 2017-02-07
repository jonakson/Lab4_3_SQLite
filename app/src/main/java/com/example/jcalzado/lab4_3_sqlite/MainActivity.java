package com.example.jcalzado.lab4_3_sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inCode, inDescription, inPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inCode = (EditText) findViewById(R.id.inCode);
        inDescription = (EditText) findViewById(R.id.inDescription);
        inPrice = (EditText) findViewById(R.id.inPrice);
    }

    public void create(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getWritableDatabase();

        String code = inCode.getText().toString();
        String description = inDescription.getText().toString();
        String price = inPrice.getText().toString();

        ContentValues record = new ContentValues();

        record.put("code",code);
        record.put("description",description);
        record.put("price",price);
        database.insert("articles",null,record);

        database.close();

        inCode.setText("");
        inDescription.setText("");
        inPrice.setText("");

        Toast.makeText(this, "Datos introducidos correctamente.", Toast.LENGTH_SHORT).show();
    }

    /*
     * LANZAMOS LAS ACTIVITIES CORRESPONDIENTES
     */

    public void launchReadByCode(View v) {
        Intent i = new Intent(this, ReadByCode.class);
        startActivity(i);
    }

    public void launchReadByDescription(View v) {
        Intent i = new Intent(this, ReadByDescription.class);
        startActivity(i);
    }

    public void launchDeleteByCode(View v) {
        Intent i = new Intent(this, DeleteByCode.class);
        startActivity(i);
    }

    public void launchUpdate(View v) {
        Intent i = new Intent(this, Update.class);
        startActivity(i);
    }

    public void launchReadAll(View v) {
        Intent i = new Intent(this, ReadAll.class);
        startActivity(i);
    }
}
