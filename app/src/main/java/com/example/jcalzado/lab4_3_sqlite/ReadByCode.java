package com.example.jcalzado.lab4_3_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReadByCode extends AppCompatActivity {

    private EditText inCode;
    private TextView outCode, outDescription, outPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_by_code);

        inCode = (EditText) findViewById(R.id.inCode);
        outCode = (TextView) findViewById(R.id.outCode);
        outDescription = (TextView) findViewById(R.id.outDescription);
        outPrice = (TextView) findViewById(R.id.outPrice);
    }

    public void readByCode(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getReadableDatabase();

        String code = inCode.getText().toString();

        Cursor row = database.rawQuery("select description,price from articles where code=" + code , null);

        if (row.moveToFirst()) {
            outCode.setText("Código: " + code);
            outDescription.setText("Descripción: " + row.getString(0));
            outPrice.setText("Precio: " + row.getString(1) + " €");
        } else {
            // Borramos los datos que puedan existir anteriormente y mostramos el Toast de información.
            outCode.setText("");
            outDescription.setText("");
            outPrice.setText("");
            Toast.makeText(this, "No existe el artículo con el código especificado.", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }
}
