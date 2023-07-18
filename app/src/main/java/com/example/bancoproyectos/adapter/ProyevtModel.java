package com.example.bancoproyectos.adapter;

import java.io.Serializable;
import java.util.List;

public class ProyevtModel implements Serializable {

    private int id;
    private String nombre_proyecto;
    private  String descripcion;
    private  String foto;
    private int aprendiz;
    private String codigo_fuente;



    private List <String> categorias;



    public ProyevtModel(String nombre_proyecto, String descripcion, String foto , String aprendiz , String codigo_fuente ) {

        this.nombre_proyecto = nombre_proyecto ;
        this.descripcion = descripcion;
        this.foto = foto;
        this.aprendiz = Integer.parseInt(aprendiz);
        this.codigo_fuente = codigo_fuente ;
     /*   this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.cantforcar = cantforcar;
        this.imagen = imagen;*/
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(int aprendiz) {
        this.aprendiz = aprendiz;
    }

    public String getCodigo_fuente() {
        return codigo_fuente;
    }

    public void setCodigo_fuente(String codigo_fuente) {
        this.codigo_fuente = codigo_fuente;
    }




    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }







    @Override
    public String toString() {
        return "ProductoModel{" +
                "nombre_proyecto='" + nombre_proyecto + '\'' +
                ", descripcion_producto='" + descripcion + '\'' +

                ", foto=" + foto +

                ", aprendiz='" + aprendiz + '\'' +
                ", codigo_fuente='" + codigo_fuente + '\''+
                '}';
    }
}
