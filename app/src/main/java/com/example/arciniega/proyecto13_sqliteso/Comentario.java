package com.example.arciniega.proyecto13_sqliteso;

/**
 * Created by arciniega on 15/07/16.
 */
public class Comentario {
    private int id;
    private  String comentario;

    public Comentario(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString(){
        return comentario;
    }
}
