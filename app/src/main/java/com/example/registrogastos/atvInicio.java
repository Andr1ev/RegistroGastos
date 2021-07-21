package com.example.registrogastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class atvInicio extends AppCompatActivity {

    private TextView tvbien,tvres;
    private Spinner spinner;
    private String[] meses = {"Seleccione Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private EditText etLuz, etAgua, etGas, etInter, etArr, etServ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_inicio);

        tvbien = findViewById(R.id.tvbien);
        String nom = getIntent().getStringExtra("nombre");
        tvbien.setText("Bienvenido " + nom);
        tvres=findViewById(R.id.tvres);

        etLuz = findViewById(R.id.etLuz);
        etAgua = findViewById(R.id.etAgua);
        etGas = findViewById(R.id.etGas);
        etInter = findViewById(R.id.etInternet);
        etArr = findViewById(R.id.etArriendo);
        etServ = findViewById(R.id.etServicio);
        spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        spinner.setAdapter(adaptador);


    }

    public void cargar(View v) {


        AdminDB admin = new AdminDB(this, "cuentas", null, 1);
        SQLiteDatabase base = admin.getWritableDatabase();

        String op = spinner.getSelectedItem().toString();

        if (!op.equals("Seleccione Mes")) {
        //
            // if (!op.isEmpty()) {
            Cursor fila = base.rawQuery("select luzC, aguaC, gasC, internetC, arriendoC, servicioC from cuenta where mesC='"+op+"'", null);
            if (fila.moveToFirst()) {
               /* etLuz.setText(Integer.parseInt(fila.getString(0)));
                etAgua.setText(Integer.parseInt(fila.getString(1)));
                etGas.setText(Integer.parseInt(fila.getString(2)));
                etInter.setText(Integer.parseInt(fila.getString(3)));
                etArr.setText(Integer.parseInt(fila.getString(4)));
                etServ.setText(Integer.parseInt(fila.getString(5)));*/

                etLuz.setText(fila.getString(0));
                etAgua.setText(fila.getString(1));
                etGas.setText(fila.getString(2));
                etInter.setText(fila.getString(3));
                etArr.setText(fila.getString(4));
                etServ.setText(fila.getString(5));
                base.close();
            }
            else{
                Toast.makeText(this, "No se encuentran datos", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void guardar(View v){

        AdminDB admin = new AdminDB(this, "cuentas", null, 1);
        SQLiteDatabase base = admin.getWritableDatabase();

        String op = spinner.getSelectedItem().toString();

        String luz=etLuz.getText().toString();
        String agua=etAgua.getText().toString();
        String gas=etGas.getText().toString();
        String inter=etInter.getText().toString();
        String arr=etArr.getText().toString();
        String serv=etServ.getText().toString();

        if (!op.equals("Seleccione Mes")) {


        ContentValues crear = new ContentValues();
        crear.put("mesC", op);
        crear.put("luzC", luz);
        crear.put("aguaC", agua);
        crear.put("gasC", gas);
        crear.put("internetC", inter);
        crear.put("arriendoC", arr);
        crear.put("servicioC", serv);

        base.insert("cuenta", null,crear);
        base.close();
        etLuz.setText("");
        etAgua.setText("");
        etGas.setText("");
        etInter.setText("");
        etArr.setText("");
        etServ.setText("");
        Toast.makeText(this, "Datos Guardados", Toast.LENGTH_LONG).show();
    }
        else{
            Toast.makeText(this, "Error guardando datos", Toast.LENGTH_LONG).show();
        }
    }

    //public void calcular(EditText[] Ets){
    public void calcular(View v){
     /*   final EditText[] Ets;
        Ets = new EditText[]{etLuz,etAgua,etGas,etInter,etArr,etServ};

        int suma =0;
        for (int i=0;i<Ets.length;i++){
            if (!Ets[i].getText().toString().isEmpty()){
            suma = Integer.parseInt(Ets[i].getText().toString());

            }}
        tvres.setText(Integer.toString(suma)); */

    int op1= Integer.parseInt(etLuz.getText().toString());
    int op2= Integer.parseInt(etAgua.getText().toString());
    int op3= Integer.parseInt(etGas.getText().toString());
    int op4= Integer.parseInt(etInter.getText().toString());
    int op5= Integer.parseInt(etArr.getText().toString());
    int op6= Integer.parseInt(etServ.getText().toString());/**/
    int suma=0;
             suma=op1+op2+op3+op4+op5+op6;//;
        tvres.setText("El gasto mensual es de: "+Integer.toString(suma));



    }

}