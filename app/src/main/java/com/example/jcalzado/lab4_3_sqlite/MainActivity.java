package com.example.jcalzado.lab4_3_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
