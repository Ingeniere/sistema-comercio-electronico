package com.ecommerce.jwt.controller;

import com.ecommerce.jwt.model.ImageModel;
import com.ecommerce.jwt.model.Producto;
import com.ecommerce.jwt.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/adicionarProducto"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Producto adicionarNuevoProducto(@RequestPart("producto") Producto producto,
                                  @RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = subirImagenes(file);
            producto.setProductoImagenes(images);
            return productoService.addNewProduct(producto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Set<ImageModel> subirImagenes(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }

	//ESTO ESO PARA LISTAR Y PAGINAR LOS PRODUCTOS
    @GetMapping({"/listarLosProductos"})
    public List<Producto> listarTodosLosProductos(@RequestParam(defaultValue = "0") int numeroPagina,
                                                  @RequestParam(defaultValue = "") String buscarPorId) {
        List<Producto> resultado = productoService.getAllProducts(numeroPagina, buscarPorId);
        System.out.println("El tamaño del Resultado es"+ resultado.size());
        return resultado;
    }

	//ESTE METODO ES PARA PODER ACTUALIZAR EL PRODUCTO
    @GetMapping("/listarProductoDetallePorId/{productoId}")
    public Producto listarProductoDetallePorId(@PathVariable("productoId")Integer productoId){
        return productoService.listarProductoDetallePorId(productoId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/eliminarDetalleProducto/{productoId}"})
    public void eliminarDetalleProducto(@PathVariable("productoId") Integer productoId) {
        productoService.detallesProductoEliminar(productoId);
    }

    @PreAuthorize("hasRole('Usuario')")
    @GetMapping({"/obtenerDetallesDelProducto/{esPagoDeUnSoloProducto}/{productoId}"})
    public List<Producto> obtenerDetallesProducto(@PathVariable(name = "esPagoDeUnSoloProducto" ) boolean esPagoDeUnSoloProducto,
                                           @PathVariable(name = "productoId")  Integer productoId) {
        return productoService.obtenerDetallesDelProducto(esPagoDeUnSoloProducto, productoId);
    }
}
