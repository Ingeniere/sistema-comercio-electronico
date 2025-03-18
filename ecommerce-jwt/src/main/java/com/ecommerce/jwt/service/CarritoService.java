package com.ecommerce.jwt.service;

import com.ecommerce.jwt.configuracion.JwtFiltroResquest;
import com.ecommerce.jwt.dao.CarritoDao;
import com.ecommerce.jwt.dao.ProductoDao;
import com.ecommerce.jwt.dao.UsuarioDao;
import com.ecommerce.jwt.model.Carrito;
import com.ecommerce.jwt.model.Producto;
import com.ecommerce.jwt.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoService {

    @Autowired
    private CarritoDao carritoDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private UsuarioDao usuarioDao;

    public void eliminarCarritoItem(Integer carritoId){
        carritoDao.deleteById(carritoId);
    }

    public Carrito adicionandoCarrito(Integer productoId ){
        Producto producto = productoDao.findById(productoId).get();

        String usuarioActual = JwtFiltroResquest.USUARIO_ACTUAL;

        Usuario usuario = null;
        if (usuarioActual != null){
            usuario=usuarioDao.findById(usuarioActual).get();
        }
        List<Carrito> carritoLista = carritoDao.findByUsuario(usuario);
        List<Carrito> filtrarLista = carritoLista.stream().filter(x->x.getProducto().getProductoId() == productoId).collect(Collectors.toList());

        if (filtrarLista.size()>0){
            return null;//con esto impedimos que se vuelva a reservar el mismo producto mas de 2 veces
        }

        if (producto != null && usuario != null){
            Carrito carrito = new Carrito(producto, usuario);
            return carritoDao.save(carrito);
        }
        return null;
        }

        public List<Carrito> listarDetalleCarrito(){
        String usuarioActual = JwtFiltroResquest.USUARIO_ACTUAL;
        Usuario usuario = usuarioDao.findById(usuarioActual).get();
        return carritoDao.findByUsuario(usuario);
        }

    }

