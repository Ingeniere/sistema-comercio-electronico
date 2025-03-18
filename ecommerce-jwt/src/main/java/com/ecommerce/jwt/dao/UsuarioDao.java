package com.ecommerce.jwt.dao;

import com.ecommerce.jwt.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, String> {
}
