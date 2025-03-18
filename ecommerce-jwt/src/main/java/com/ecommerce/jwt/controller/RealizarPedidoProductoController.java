package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.model.DetalleTransaccion;
import com.ecommerce.jwt.model.OrdenarEntradaPedido;
import com.ecommerce.jwt.model.PedidoProducto;
import com.ecommerce.jwt.service.RealizarPedidoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RealizarPedidoProductoController {

    @Autowired
    private RealizarPedidoProductoService realizarPedidoProductoService;

    @PreAuthorize("hasRole('Usuario')")
    @PostMapping({"/hacerPedido/{esPagoDeUnSoloProducto}"})// esto "hacerPedido"lo usamos para comprar y esto "esPagoDeUnSoloProducto" lo usamos para vaciar las reservas del carrito
    public void hacerPedido(@PathVariable(name = "esPagoDeUnSoloProducto") boolean esPagoDeUnSoloProducto,
                            @RequestBody OrdenarEntradaPedido ordenarEntradaPedido){
        realizarPedidoProductoService.lugarPedido(ordenarEntradaPedido, esPagoDeUnSoloProducto);
    }

    @PreAuthorize("hasRole('Usuario')")
    @GetMapping({"/obtenerDetallesPedido"})
    public List<PedidoProducto> obtenerDetallesPedido(){
        return realizarPedidoProductoService.obteniendoDetallesPedido();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/obtenerTodosLosDetallesPedido/{estado}"})
    public List<PedidoProducto> obtenerTodosLosDetallesPedido(@PathVariable(name = "estado") String estado){
        return realizarPedidoProductoService.obteniendoTodosLosDetallesPedido(estado);
    }
	
	@PreAuthorize("hasRole('Admin')")
    @GetMapping({"/marcarComoEntregado/{pedidoId}"})
	public void marcarComoEntregadoPedido(@PathVariable(name="pedidoId") Integer pedidoId){
		realizarPedidoProductoService.marcarComoEntregado(pedidoId);
	}

    @PreAuthorize("hasRole('Usuario')")
    @GetMapping({"/crearTransaccion/{monto}"})
    public DetalleTransaccion crearTransaccionPedido(@PathVariable(name = "monto") Double monto){
       return realizarPedidoProductoService.crearTransacciones(monto);
    }
}
