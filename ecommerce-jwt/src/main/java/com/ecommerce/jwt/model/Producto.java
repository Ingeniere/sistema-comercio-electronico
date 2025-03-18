package com.ecommerce.jwt.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productoId;
    private String nombreProducto;
    @Column(length = 2000)
    private String descripcionProducto;
    private Double descuentoPrecioProducto;
    private Double precioActualProducto;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "producto_imagenes",
            joinColumns = {
                    @JoinColumn(name = "producto_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "imagen_id")
            }
    )
    private Set<ImageModel> productoImagenes;

    public Set<ImageModel> getProductoImagenes() {
        return productoImagenes;
    }

    public void setProductoImagenes(Set<ImageModel> productoImagenes) {
        this.productoImagenes = productoImagenes;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Double getDescuentoPrecioProducto() {
        return descuentoPrecioProducto;
    }

    public void setDescuentoPrecioProducto(Double descuentoPrecioProducto) {
        this.descuentoPrecioProducto = descuentoPrecioProducto;
    }

    public Double getPrecioActualProducto() {
        return precioActualProducto;
    }

    public void setPrecioActualProducto(Double precioActualProducto) {
        this.precioActualProducto = precioActualProducto;
    }
}
