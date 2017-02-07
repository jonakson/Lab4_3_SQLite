package com.example.jcalzado.lab4_3_sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReadByDescription extends AppCompatActivity {

    private EditText inDescription;
    private TextView outCode, outDescription, outPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_by_description);

        inDescription = (EditText) findViewById(R.id.inDescription);
        outCode = (TextView) findViewById(R.id.outCode);
        outDescription = (TextView) findViewById(R.id.outDescription);
        outPrice = (TextView) findViewById(R.id.outPrice);
    }

    public void readByDescription(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getReadableDatabase();

        String description = inDescription.getText().toString();

        Cursor row = database.rawQuery("select code,price from articles where description='" + description + "'" , null);

        if (row.moveToFirst()) {
            outCode.setText("Código: " + row.getString(0));
            outDescription.setText("Descripción: " + description);
            outPrice.setText("Precio: " + row.getString(1) + " €");
        } else {
            // Borramos los datos que puedan existir anteriormente y mostramos el Toast de información.
            outCode.setText("");
            outDescription.setText("");
            outPrice.setText("");
            Toast.makeText(this, "No existe el artículo con la descripción especificada.", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }
}
