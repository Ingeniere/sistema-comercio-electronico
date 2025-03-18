package com.ecommerce.jwt.service;

import com.ecommerce.jwt.configuracion.JwtFiltroResquest;
import com.ecommerce.jwt.dao.CarritoDao;
import com.ecommerce.jwt.dao.ProductoDao;
import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.Carrito;
import com.ecommerce.jwt.model.Producto;
import com.ecommerce.jwt.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private CarritoDao carritoDao;

    public Producto addNewProduct(Producto product) {
        return productoDao.save(product);
    }
	//int numeroPagina, String buscarPorId
    public List<Producto> getAllProducts(int numeroPagina, String buscarPorId) {

        Pageable paginador= PageRequest.of(numeroPagina,8);

            if(buscarPorId.equals("")){
                return (List<Producto>) productoDao.findAll(paginador);
            }else{
                return (List<Producto>) productoDao.findByNombreProductoContainingIgnoreCaseOrDescripcionProductoContainingIgnoreCase(
                        buscarPorId, buscarPorId, paginador
                );
            }
    }

    public void detallesProductoEliminar(Integer productoId) {
        productoDao.deleteById(productoId);
    }
	//Este método usamos para actualizar el registro del producto
    public Producto listarProductoDetallePorId(Integer productoId) {
        return productoDao.findById(productoId).get();
    }

    public List<Producto> obtenerDetallesDelProducto(boolean esPagoDeUnSoloProducto, Integer productoId) {
        if(esPagoDeUnSoloProducto && productoId != 0) {
            // Nosotros vamos a reservar un solo producto si se cumple las 2 condiciones
            List<Producto> list = new ArrayList<>();
            Producto product = productoDao.findById(productoId).get();
            list.add(product);
            return list;
        } else {
            // vamos a listar en el carrito las reservas de compra que hizo el usuario si no se cumplen las condiciones
            String usuarioActual = JwtFiltroResquest.USUARIO_ACTUAL;
           Usuario user = usuarioDao.findById(usuarioActual).get();
            List<Carrito> reservasEnCarrito = carritoDao.findByUsuario(user);

            return reservasEnCarrito.stream().map(x -> x.getProducto()).collect(Collectors.toList());

        }
    }
}
