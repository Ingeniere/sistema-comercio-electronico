package com.ecommerce.jwt.model;


public class DetalleTransaccion {

    private String pedidoId;
    private String moneda;
    private Integer cantidad;
    private String clave;


    public DetalleTransaccion(String pedidoId, String moneda, Integer cantidad, String clave) {
        this.pedidoId = pedidoId;
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.clave = clave;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
