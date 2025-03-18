package com.ecommerce.jwt.model;

import javax.persistence.*;

@Entity
@Table(name = "pedidos_productos")
public class PedidoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pedidoId;
    private String pedidoNombre;
    private String direccionCompletaPedido;
    private String numeroContactoPedido;
    private String numeroAlternoContactoPedido;
    private String estadoPedido;
    private Double montoPedido;

    @OneToOne
    private Producto producto;

    @OneToOne
    private Usuario usuario;
	private String transactionId;

    public PedidoProducto() {

    }

    public PedidoProducto(String pedidoNombre, String direccionCompletaPedido, String numeroContactoPedido, String numeroAlternoContactoPedido, String estadoPedido, Double montoPedido, Producto producto, Usuario usuario, String transactionId) {
        this.pedidoNombre = pedidoNombre;
        this.direccionCompletaPedido = direccionCompletaPedido;
        this.numeroContactoPedido = numeroContactoPedido;
        this.numeroAlternoContactoPedido = numeroAlternoContactoPedido;
        this.estadoPedido = estadoPedido;
        this.montoPedido = montoPedido;
        this.producto = producto;
        this.usuario = usuario;
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getPedidoNombre() {
        return pedidoNombre;
    }

    public void setPedidoNombre(String pedidoNombre) {
        this.pedidoNombre = pedidoNombre;
    }

    public String getDireccionCompletaPedido() {
        return direccionCompletaPedido;
    }

    public void setDireccionCompletaPedido(String direccionCompletaPedido) {
        this.direccionCompletaPedido = direccionCompletaPedido;
    }

    public String getNumeroContactoPedido() {
        return numeroContactoPedido;
    }

    public void setNumeroContactoPedido(String numeroContactoPedido) {
        this.numeroContactoPedido = numeroContactoPedido;
    }

    public String getNumeroAlternoContactoPedido() {
        return numeroAlternoContactoPedido;
    }

    public void setNumeroAlternoContactoPedido(String numeroAlternoContactoPedido) {
        this.numeroAlternoContactoPedido = numeroAlternoContactoPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Double getMontoPedido() {
        return montoPedido;
    }

    public void setMontoPedido(Double montoPedido) {
        this.montoPedido = montoPedido;
    }
}
