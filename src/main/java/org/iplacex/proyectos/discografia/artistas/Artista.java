package org.iplacex.proyectos.discografia.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artistas")
public class Artista {
    @Id
    private String id;
    private String nombre;
    private String pais;
    private int anioInicio;

    // Getters y Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getAnioInicio() {
        return anioInicio;
    }
    public void setAnioInicio(int anioInicio) {
        this.anioInicio = anioInicio;
    }
}
