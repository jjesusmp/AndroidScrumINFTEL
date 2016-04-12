package com.example.asus.androidscruminftel.model;

/**
 * Created by aitorpagan on 12/4/16.
 */
public class State {

    private String nombre;
    private String posicion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "State{" +
                "nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                '}';
    }
}
