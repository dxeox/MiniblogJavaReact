package com.example.blog;

import java.util.List;

public class Blog {
    private Integer id_comentario;
    private String nombre_usuario;
    private String imagen;
    private String mensaje;
    private Integer puntos;

    private Integer nivel;
    private List<Blog> answers;


    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Integer id_comentario) {
        this.id_comentario = id_comentario;
    }

    public List<Blog> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Blog> answers) {
        this.answers = answers;
    }
}
