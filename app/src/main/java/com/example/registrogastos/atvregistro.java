package com.example.registrogastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class atvregistro extends AppCompatActivity implements View.OnClickListener {

    private EditText etlog, etpas;
    private Button btnreg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atvregistro);

        etlog = findViewById(R.id.etnom);
        etpas = findViewById(R.id.etpas);
        btnreg = findViewById(R.id.btnreg);
        btnreg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        AdminDB admin = new AdminDB(this, "usuarios", null, 1);
        SQLiteDatabase base = admin.getWritableDatabase();


        String nombre = etlog.getText().toString();
        String pass = etpas.getText().toString();

        if (!nombre.isEmpty() && !pass.isEmpty()) {
            ContentValues crear = new ContentValues();
            crear.put("nombreU", nombre);
            crear.put("passU", pass);
            base.insert("usuario", null, crear);
            base.close();
            etlog.setText("");
            etpas.setText("");
            Toast.makeText(this, "Usuario creado", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }




    }
}