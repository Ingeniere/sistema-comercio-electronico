package com.ecommerce.jwt.model;

import javax.persistence.*;

@Entity
@Table(name = "imagenes_modal")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String tipo;
    @Column(length = 50000000)
    private byte[] picByte;

    public ImageModel() {
    }

    public ImageModel(String nombre, String tipo, byte[] picByte) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.picByte = picByte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
