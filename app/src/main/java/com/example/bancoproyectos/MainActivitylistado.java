package com.example.bancoproyectos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bancoproyectos.api.ProyectosApiService;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivitylistado extends AppCompatActivity {

    RecyclerView recyclerView;
    ListaListadoProyectosAdapter listaListadoProyectosAdapter;
    Retrofit retrofit;
    ImageView imageView;
    private static final String TAG = "proyecto";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    DetalleProyectoFragment detalleProyectoFragment;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        recyclerView = findViewById(R.id.card_recycler_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());






        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        listaListadoProyectosAdapter = new ListaListadoProyectosAdapter(this);
        recyclerView.setAdapter(listaListadoProyectosAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://lexa2334.pythonanywhere.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        obtenerDatos();
        //listarproyectoselect();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.perfil:
                        Intent i = new Intent(MainActivitylistado.this, Perfil.class);
                        startActivity(i);
                        return true;

                    case R.id.mis_proyectos:
                        Intent in = new Intent(MainActivitylistado.this, MisProyectos.class);
                        startActivity(in);
                        return true;


                    default:
                        return false;
                }
            }
        });
    }

    private void remplaceFragment(Fragment fragment){
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmenPadre, fragment);
        fragmentTransaction.commit();
    }

    public void enviarProyecto(Listadoproyectos listadoproyectos) {

        detalleProyectoFragment = new DetalleProyectoFragment();

        Bundle bundleEnvio = new Bundle();

        bundleEnvio.putSerializable("objeto", (Serializable) listadoproyectos);
        detalleProyectoFragment.setArguments(bundleEnvio);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detalleProyectoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }







    private void obtenerDatos() {
        ProyectosApiService service = retrofit.create(com.example.bancoproyectos.api.ProyectosApiService.class);

        Call<List<Listadoproyectos>> productsRespuestaCall = service.obtenerListaProyectos();
        productsRespuestaCall.enqueue(new Callback<List<Listadoproyectos>>() {
            @Override
            public void onResponse(Call<List<Listadoproyectos>> call, Response<List<Listadoproyectos>> response) {
                if (response.isSuccessful()) {
                    List<Listadoproyectos> proyectos = response.body();
                    for (int i = 0; i < proyectos.size(); i++) {
                        Listadoproyectos p= proyectos.get(i);
                        Log.e(TAG, "products: " + p.getNombre_proyecto());
                    }

                    listaListadoProyectosAdapter.add((ArrayList<Listadoproyectos>) proyectos);
                }
            }

            @Override
            public void onFailure(Call<List<Listadoproyectos>> call, Throwable t) {

            }



        });
    }

}