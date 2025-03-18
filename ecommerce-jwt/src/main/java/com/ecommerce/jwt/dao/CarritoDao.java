package com.ecommerce.jwt.dao;

import com.ecommerce.jwt.model.Carrito;
import com.ecommerce.jwt.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoDao extends CrudRepository<Carrito, Integer> {
    public List<Carrito> findByUsuario(Usuario usuario);
}
