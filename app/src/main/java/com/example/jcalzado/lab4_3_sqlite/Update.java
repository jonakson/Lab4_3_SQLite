package com.example.jcalzado.lab4_3_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText inCode, inDescription, inPrice;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        inCode = (EditText) findViewById(R.id.inCode);
        inDescription = (EditText) findViewById(R.id.inDescription);
        inPrice = (EditText) findViewById(R.id.inPrice);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        inDescription.setEnabled(false);
        inPrice.setEnabled(false);
        buttonUpdate.setEnabled(false);
    }

    public void getArticleInfo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getReadableDatabase();

        String code = inCode.getText().toString();

        Cursor row = database.rawQuery("select description,price from articles where code=" + code , null);

        if (row.moveToFirst()) {
            inCode.setEnabled(false);
            inDescription.setText(row.getString(0));
            inDescription.setEnabled(true);
            inPrice.setText(row.getString(1));
            inPrice.setEnabled(true);
            buttonUpdate.setEnabled(true);
        } else {
            // Borramos los datos que puedan existir anteriormente y mostramos el Toast de información.
            inCode.setText("");
            Toast.makeText(this, "No existe el artículo con el código especificado.", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public void update (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getReadableDatabase();

        String code = inCode.getText().toString();
        String description = inDescription.getText().toString();
        String price = inPrice.getText().toString();

        ContentValues record = new ContentValues();

        record.put("code",code);
        record.put("description",description);
        record.put("price",price);
        int updatedRecords = database.update("articles" , record , "code="+code , null);

        if (updatedRecords == 1) {
            Toast.makeText(this, "Datos de artículo modificados correctamente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe ningún artículo con el código " + code, Toast.LENGTH_SHORT).show();
        }
        inCode.setText("");
        inDescription.setText("");
        inPrice.setText("");
    }
}
