package com.example.jcalzado.lab4_3_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteByCode extends AppCompatActivity {

    private EditText inCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_by_code);

        inCode = (EditText) findViewById(R.id.inCode);
    }

    public void deleteByCode (View v) {
        String code = inCode.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this , "administration" , null , 1);

        SQLiteDatabase database = admin.getWritableDatabase();

        int deletedRecords = database.delete("articles" , "code="+code , null);

        if (deletedRecords == 1) {
            Toast.makeText(this, "Artículo número " + code + " borrado correctamente", Toast.LENGTH_SHORT).show();
            inCode.setText("");
        } else {
            Toast.makeText(this, "No existe ningún artículo con el código " + code, Toast.LENGTH_SHORT).show();
        }

        database.close();
    }
}
