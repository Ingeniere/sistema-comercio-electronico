package com.ecommerce.jwt.dao;

import com.ecommerce.jwt.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoDao extends CrudRepository<Producto, Integer> {
    public List<Producto> findAll(Pageable paginador);

    //Aqui "ContainingIgnoreCase" es la clave para que  funcione yo le coloque en español y no funciona
    public List<Producto> findByNombreProductoContainingIgnoreCaseOrDescripcionProductoContainingIgnoreCase(
            String clave1, String clave2, Pageable paginador
    );
}
