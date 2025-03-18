package com.ecommerce.jwt.dao;

import com.ecommerce.jwt.model.PedidoProducto;
import com.ecommerce.jwt.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProductoDao extends CrudRepository<PedidoProducto, Integer> {

    public List<PedidoProducto> findByUsuario(Usuario usuario);

    public List<PedidoProducto> findByEstadoPedido(String estado);
}
