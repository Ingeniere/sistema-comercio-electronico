package com.ecommerce.jwt.model;

import javax.persistence.*;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carritoId;

    @OneToOne
    private Producto producto;
    @OneToOne
    private Usuario usuario;

    public Carrito() {
    }

    public Carrito(Producto producto, Usuario usuario) {
        this.producto = producto;
        this.usuario = usuario;
    }

    public Integer getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Integer carritoId) {
        this.carritoId = carritoId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
