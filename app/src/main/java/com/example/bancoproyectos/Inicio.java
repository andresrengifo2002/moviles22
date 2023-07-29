package com.example.bancoproyectos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.example.bancoproyectos.api.LoginApiService;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inicio extends AppCompatActivity {
    private EditText etu, etc;

    Context context;
    LoginApiService loginApiService;
    Retrofit retrofit;
    Button boton , showPasswordButton;


    ImageView myImage;

    String token;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        etu = (EditText) findViewById(R.id.edtusuario);
        etc = (EditText) findViewById(R.id.edtcontraseña);
        boton = findViewById(R.id.buttoniniciar);
        showPasswordButton = findViewById(R.id.buttonver);
        ImageView myImage = findViewById(R.id.imageView4);
        EditText passwordEditText = findViewById(R.id.edtcontraseña);
        sharedPreferences = getSharedPreferences("MyPrefs" , MODE_PRIVATE);
        token = sharedPreferences.getString("accessToken", null);
        if (token != null){
            Intent intent = new Intent();
            startActivity(intent);
            finish();

        }

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (!isPasswordVisible) {
                    // Cambiar el tipo de entrada a "texto normal" para mostrar la contraseña
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isPasswordVisible = true;
                } else {
                    // Cambiar el tipo de entrada a "contraseña" para ocultar la contraseña
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPasswordVisible = false;
                }

                // Mover el cursor al final del texto
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/SENACauca");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });




        retrofit = new Retrofit.Builder()
                .baseUrl("https://lexa2334.pythonanywhere.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginApiService=retrofit.create(LoginApiService.class);
         boton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String usern =etu.getText().toString();
                 String pass = etc.getText().toString();
                 Call<Login> call = loginApiService.login(usern, pass );
                 call.enqueue(new Callback<Login>() {
                     @Override
                     public void onResponse(Call<Login> call, Response<Login> response) {
                         if(response.isSuccessful()) {
                             Login login = response.body();
                             SharedPreferences.Editor editor = sharedPreferences.edit();
                             String token=login.getAccess_token();
                             editor.putString("accessToken", token);
                             editor.apply();
                             /* Toast.makeText(Inicio.this,token,Toast.LENGTH_LONG).show();*/
                             Intent I = new Intent(Inicio.this, MainActivitylistado.class);
                             I.putExtra("token",token);
                             startActivity(I);


                         }

                     }

                     @Override
                     public void onFailure(Call<Login> call, Throwable t) {


                     }
                 });

             }
         });

        }


   /* public  void incio(View v){
        loginApiService=retrofit.create(LoginApiService.class);
        String usern =etu.getText().toString();
        String pass = etc.getText().toString();
        Call<Login> call = loginApiService.login(usern, pass );
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()) {
                    Login login = response.body();
                    String token=login.getAccess_token();
                    Toast.makeText(Inicio.this,token,Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {


            }
        });


    }*/


           /*  public void onClick(View view) {
                loginApiService=retrofit.create(LoginApiService.class);

                Intent I = new Intent(Inicio.this, MenuPrincipal.class);
                startActivity(I);







}*/
}

//}









