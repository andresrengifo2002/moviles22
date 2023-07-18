package com.example.bancoproyectos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class regristro extends AppCompatActivity {
    private EditText et_email,et_contraseña1,et_contraseña2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regristro);
        et_email= (EditText) findViewById(R.id.txt_email);
        et_contraseña1= (EditText) findViewById(R.id.tVpaprendizSelect2);
        et_contraseña2 = (EditText) findViewById(R.id.txt_contraseña2);

    }
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String email = et_email.getText().toString();
        String contraseña1 = et_contraseña1.getText().toString();
        String contraseña2 = et_contraseña2.getText().toString();

        if(!email.isEmpty() && !contraseña1.isEmpty() && !contraseña2.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("email",email);
            registro.put("contraseña1",contraseña1);


            BaseDeDatos.insert("articulos",null,registro);//se hace la insercion
            BaseDeDatos.close();
            et_email.setText("");
            et_contraseña1.setText("");

            Toast.makeText(this, "Registro Exitoso:", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Debes ingresar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}