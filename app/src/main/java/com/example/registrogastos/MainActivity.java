package com.example.registrogastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etlogin,etpass;
   // private Button btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etlogin=findViewById(R.id.etlogin);
        etpass=findViewById(R.id.etpass);
       // btnlog=findViewById(R.id.btnlogin);
        //btnlog.setOnClickListener(this);




    }
        public void login(View v){
        AdminDB admin=new AdminDB(this,"usuarios", null, 1);
        SQLiteDatabase base=admin.getWritableDatabase();

        String nombreU=etlogin.getText().toString();
        String passU=etpass.getText().toString();

        if(nombreU.isEmpty()) {
            Toast.makeText(this, "Debe ingresar nombre", Toast.LENGTH_LONG).show();
        }
        if(passU.isEmpty()) {
            Toast.makeText(this, "Debe ingresar contraseña", Toast.LENGTH_LONG).show();
        }

        if(!nombreU.isEmpty()&&!passU.isEmpty()){
            Cursor fila= base.rawQuery("select nombreU, passU from usuario where nombreU='"+nombreU+"' and passU='"+passU+"'", null);
            if(fila.moveToFirst()){
               String nom=fila.getString(0);
                String pass=fila.getString(1);
                base.close();

            if (nombreU.equals(nom) && passU.equals(pass))
            {
                Intent i = new Intent(this, atvInicio.class);
                i.putExtra("nombre",etlogin.getText().toString());
                startActivity(i);
            }}
            else{
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
            }
        }}

        public void registrar(View v){
            Intent i = new Intent(this, atvregistro.class);
            startActivity(i);
        }


}

