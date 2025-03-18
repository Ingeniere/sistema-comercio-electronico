package com.ecommerce.jwt.model;

import java.util.List;

public class OrdenarEntradaPedido {

    private String nombreCompleto;
    private String direccion;
    private String numeroContacto;
    private String numeroAlternoContacto;
	private String transactionId;
    private List<CantidadPedidoProducto> cantidadPedidoProductos;

	public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
	
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getNumeroAlternoContacto() {
        return numeroAlternoContacto;
    }

    public void setNumeroAlternoContacto(String numeroAlternoContacto) {
        this.numeroAlternoContacto = numeroAlternoContacto;
    }

    public List<CantidadPedidoProducto> getCantidadPedidoProductos() {
        return cantidadPedidoProductos;
    }

    public void setCantidadPedidoProductos(List<CantidadPedidoProducto> cantidadPedidoProductos) {
        this.cantidadPedidoProductos = cantidadPedidoProductos;
    }
}
