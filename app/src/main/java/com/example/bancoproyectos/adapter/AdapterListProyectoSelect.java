package com.example.bancoproyectos.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.bancoproyectos.R;

import java.io.Serializable;
import java.util.List;

public class AdapterListProyectoSelect extends RecyclerView.Adapter<AdapterListProyectoSelect.ViewHolder> {

    private List<ProyevtModel> proyecto;
    private Context context;
    private TextView tvCantProductos;
    private ImageView verCar;
    public static int count = 1;


    public AdapterListProyectoSelect(List<ProyevtModel> proyecto, Context context, TextView tvCantProductos, ImageView verCar) {
        this.proyecto = proyecto;
        this.context = context;
        this.tvCantProductos = tvCantProductos;
        this.verCar = verCar;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalle_proyecto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ProyevtModel model = proyecto.get(position);
        holder.tVnombreProySelect.setText(model.getNombre_proyecto());
        int aprendizproselect = model.getAprendiz();
        holder.tVpaprendizSelect.setText("$" + aprendizproselect);
        String url = model.getFoto();
        //Picasso.with(context).load(url).into(holder.imgprodSelect);
        Glide.with(context).load(url).into(holder.imgproySelect);

       // int cantidaprod = model.getCantidad();
       // holder.tVcantidad.setText("" + cantidaprod);
        // model.setCantforcar(count);
        //Seleccionando cantidad para comprar



        //Enviando datos al carrito



    }

    @Override
    public int getItemCount() {
        return proyecto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tVnombreProySelect,tVpaprendizSelect;
        ImageView imgproySelect;
        CheckBox cBaddcarro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //referecia
            imgproySelect = itemView.findViewById(R.id.imageView3);
            tVnombreProySelect = itemView.findViewById(R.id.nombre_proyecto);

            tVpaprendizSelect = itemView.findViewById(R.id.tVpaprendizSelect2);




        }
    }
}
