package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.model.Carrito;
import com.ecommerce.jwt.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PreAuthorize("hasRole('Usuario')")
    @GetMapping({"/adicionarCarrito/{productoId}"})
    public Carrito adicionarCarrito(@PathVariable(name = "productoId") Integer productoId){
    return carritoService.adicionandoCarrito(productoId);
    }

    @PreAuthorize("hasRole('Usuario')")
    @DeleteMapping({"/eliminarCarrito/{carritoId}"})
    public void eliminarCarritoPorItem(@PathVariable(name = "carritoId") Integer carritoId){
        carritoService.eliminarCarritoItem(carritoId);
    }

    @PreAuthorize("hasRole('Usuario')")
    @GetMapping({"/listarCarrito"})
    public List<Carrito> listarCarritoDetalles(){
        return carritoService.listarDetalleCarrito();
    }
}
